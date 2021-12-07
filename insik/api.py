import requests
import json

class KakaoBikeAPI:
    response_code_match: dict[tuple[bool, str]] = {
        200: (True, "OK: success."),
        400: (False, "Bad Request: Parameter has been wrong."),
        401: (False, "Unauthorized: Authorization header has been wrong."),
        500: (False, "Internal Server Error: Server gone wrong"),
    }
    
    def __init__(self, BASE_URL: str, X_AUTH_TOKEN: str, problem: int=1):
        self.base_url = BASE_URL
        self.x_auth_token = X_AUTH_TOKEN
        self.problem = problem
        
    def validate_or_raise(self, response: requests.Response) -> None:
        match_result = KakaoBikeAPI.response_code_match.get(response.status_code)
        
        if (match_result is None):
            raise Exception("status code is unexpected.")
        
        if (match_result[0] is False):
            raise Exception(match_result[1])

    def start(self):
        res = requests.post(url=f'{self.base_url}/start',
                            headers={'X-Auth-Token': self.x_auth_token},
                            json={'problem': self.problem})
        self.validate_or_raise(res)
        
        start_params = res.json()
        self.auth_key = start_params['auth_key']
        self.time = start_params['time']
        self.auth_header = {'Authorization': self.auth_key, 'Content-Type': 'application/json'}
        self.status = "ready"
        self.failed_requests = 0
        self.distance = 0

    def locations(self) -> list[tuple[int, int]]:
        '''
        Get bike-stations information
        
        Returns:
            locations: tuple of (station id, bikes count)
        '''
        res = requests.get(url=f'{self.base_url}/locations', headers=self.auth_header)
        self.validate_or_raise(res)
        
        return list(map(lambda loc: (loc['id'], loc['located_bikes_count']), 
                   res.json()['locations']))

    def trucks(self) -> list[tuple[int, int, int]]:
        '''
        Get trucks informations
        
        Returns:
            trucks: tuple of (turck id, station id, loaded bikes count)
        '''
        res = requests.get(url=f'{self.base_url}/trucks', headers=self.auth_header)
        self.validate_or_raise(res)

        return list(map(lambda truck: (truck['id'], truck['location_id'], truck['loaded_bikes_count']), 
                   res.json()['trucks']))
        
    def simulate(self, commands: list[(int, list[int])]) -> int:
        '''
        Simulate Trucks in KakaoBike service
        
        Params:
            commands: list of (truck id, commands)
        
        Returns:
            failed requests count
        '''
        command_dict_list = list(map(lambda tup: {'truck_id': tup[0], 'command': tup[1]}, 
                                commands))
        
        res = requests.put(url=f'{self.base_url}/simulate', 
                           headers=self.auth_header, 
                           json={'commands': command_dict_list})
        self.validate_or_raise(res)
        
        res_json = res.json()
        self.status = res_json['status']
        self.time = res_json['time']
        self.failed_requests += int(float(res_json['failed_requests_count']))
        self.distance = res_json['distance']    
        
        return int(float(res_json['failed_requests_count']))
        
    def score(self) -> float:
        '''
        Get score
        
        Returs:
            score
        '''
        res = requests.get(url=f'{self.base_url}/score', headers=self.auth_header)
        self.validate_or_raise(res)
        
        return float(res.json()['score'])
    
    def __str__(self):
        return json.dumps(self.__dict__, sort_keys=True, indent=4)        
        
