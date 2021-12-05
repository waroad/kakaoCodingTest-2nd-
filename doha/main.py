import requests
import json
import random
auth_key=''


def send_api(path, method, body):
    global auth_key
    API_HOST = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
    url = API_HOST + path
    body = body
    try:
        if method=="POST":
            headers = {'X-Auth-Token': '23456ebe1b795774c79226e7d23fa1b7','Content-Type': 'application/json', 'charset': 'UTF-8', 'Accept': '*/*'}
            response = requests.post(url, headers=headers, data=json.dumps(body, ensure_ascii=False, indent="\t"))
            print("response status %r\n" % response.status_code)
            auth_key = response.json()['auth_key']
        else:
            headers = {'Authorization': auth_key, 'Content-Type': 'application/json'}
            response = requests.put(url,  headers=headers, data=json.dumps(body, ensure_ascii=False, indent="\t"))
            print("response status %r\n" % response.status_code)
    except Exception as ex:
        print(ex)


def get_api(path):
    API_HOST = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
    url = API_HOST + path
    headers = {'Authorization': auth_key,'Content-Type': 'application/json'}
    try:
        response = requests.get(url, headers=headers)
        print("response status %r" % response.status_code)
        print("response text %r\n" % response.text)
        return response.json()
    except Exception as ex:
        print(ex)


# 호출 예시
bike_average = 4
field_size=25
send_api("/start", "POST", {"problem":1})
trucks_can_go=[[[1,1],[5,2]],[[0,3],[2,1],[6,2]],[[1,3],[3,1],[7,2]],[[2,3],[4,1],[8,2]],[[3,3],[9,2]],
               [[0,4],[6,1],[10,2]],[[1,4],[5,3],[7,1],[11,2]],[[2,3],[6,3],[8,1],[12,2]],[[3,4],[7,3],[9,1],[13,2]],[[4,4],[8,3],[14,2]],
               [[5,4],[11,1],[15,2]],[[6,4],[10,3],[12,1],[16,2]],[[7,3],[11,3],[13,1],[17,2]],[[8,4],[12,3],[14,1],[18,2]],[[9,4],[13,3],[19,2]],
               [[10,4],[16,1],[20,2]],[[11,4],[15,3],[17,1],[21,2]],[[12,3],[16,3],[18,1],[22,2]],[[13,4],[17,3],[19,1],[23,2]],[[14,4],[18,3],[24,2]],
               [[15,4],[21,1]],[[16,4],[20,3],[22,1]],[[17,4],[21,3],[23,1]],[[18,4],[22,3],[24,1]],[[19,4],[23,3]]]
trucks_act=[[] for _ in range(5)]


def go():
    global trucks_act
    trucks_act=[[] for _ in range(5)]
    bikes=get_api("/locations")['locations']
    trucks=get_api("/trucks")['trucks']
    for i in range(10):
        for j in range(5):
            cur_truck=trucks[j]
            bike_count=bikes[cur_truck["location_id"]]['located_bikes_count']
            if bike_count>bike_average:
                if cur_truck["loaded_bikes_count"]<20:
                    trucks_act[j].append(5)
                    trucks[j]["loaded_bikes_count"]+=1
                else:
                    least=1000
                    direction=0
                    updated_loc=0
                    for k in trucks_can_go[cur_truck["location_id"]]:
                        if bikes[k[0]]['located_bikes_count']<least:
                            direction=k[1]
                            updated_loc=k[0]
                    trucks_act[j].append(direction)
                    trucks[j]["location_id"]=updated_loc

            elif bike_count<bike_average:
                if cur_truck["loaded_bikes_count"]>0:
                    trucks_act[j].append(6)
                else:
                    least = -1
                    direction=0
                    updated_loc=0
                    for k in trucks_can_go[cur_truck["location_id"]]:
                        if bikes[k[0]]['located_bikes_count'] > least:
                            direction = k[1]
                            updated_loc=k[0]
                    trucks_act[j].append(direction)
                    trucks[j]["location_id"]=updated_loc

            else:
                if cur_truck["loaded_bikes_count"] > 0:
                    least=1000
                    direction=0
                    updated_loc=0
                    for k in trucks_can_go[cur_truck["location_id"]]:
                        if bikes[k[0]]['located_bikes_count']<least:
                            direction=k[1]
                            updated_loc=k[0]
                    trucks_act[j].append(direction)
                    trucks[j]["location_id"]=updated_loc
                else:
                    least = -1
                    direction=0
                    updated_loc=0
                    for k in trucks_can_go[cur_truck["location_id"]]:
                        if bikes[k[0]]['located_bikes_count'] > least:
                            direction = k[1]
                            updated_loc=k[0]
                    trucks_act[j].append(direction)
                    trucks[j]["location_id"]=updated_loc

    print("wow")
    print(trucks_act)


go()
# for i in range(720):
#     go()
#     send_api("/simulate", "PUT",{"commands": [
#         { "truck_id": 0, "command": trucks_act[0]},
#         { "truck_id": 1, "command": trucks_act[1]},
#         { "truck_id": 2, "command": trucks_act[2]},
#         { "truck_id": 3, "command": trucks_act[3]},
#         { "truck_id": 4, "command": trucks_act[4]},
#     ]})
get_api("/score")
