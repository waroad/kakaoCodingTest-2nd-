import requests
import json
auth_key = ''
url = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
start_headers = {'X-Auth-Token': '68915f1a0bc7e3401caf93bdfd15ef0f','Content-Type': 'application/json', 'charset': 'UTF-8', 'Accept': '*/*'}
headers = {'Authorization': auth_key, 'Content-Type': 'application/json'}
arr = [[0 for col in range(5)] for row in range(5)]
more = []
less = []


def start_api():
    # "auth_key": "1fd74321-d314-4885-b5ae-3e72126e75cc",
    # "problem": 1,
    # "time": 0
    global auth_key
    body = {"problem": 1}
    print("start")
    response = requests.post(url + "/start", headers=start_headers, data=json.dumps(body, ensure_ascii=False, indent="\t"))

    print("response status %r" % response.status_code)
    print("response text %r" % response.text)
    auth_key = response.json()["auth_key"]
    headers["Authorization"] = auth_key

    print(auth_key)
    print()
    return response


def getlocation():
    # "locations": [
    #    {"id": 0, "located_bikes_count": 3},
    #    {"id": 1, "located_bikes_count": 3},
    #    ...
    # ]
    global arr, more, less
    more = []
    less = []

    print("GetLocation")
    print(auth_key)

    response = requests.get(url + "/locations", headers=headers)
    for location in response.json()["locations"]:
        arr[4 - location["id"] % 5][int(location["id"]/5)] = location["located_bikes_count"]
        if location["located_bikes_count"] > 4:
            more.append(location["id"])
        elif location["located_bikes_count"] < 4:
            less.append(location["id"])
    print("response status %r" % response.status_code)
    print("response text %r" % response.text)
    print()
    return response


# 호출 예시
start_api()
getlocation()
