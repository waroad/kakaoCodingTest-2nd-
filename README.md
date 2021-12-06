# kakaoCodingTest-2nd-

카카오 2차 코테 준비

## 관련 링크  

[카카오 2021 신입공채 2차 코딩 테스트][kakaolink]  
[프로그래머스 테스트 - '2021 카카오 신입공채 2차 코딩 테스트'][programmerslink]  

[kakaolink]: https://tech.kakao.com/2021/02/16/2021-kakao-recruitment-round-2/  
[programmerslink]: https://programmers.co.kr/skill_check_assignments/67  
## 남도하

점수:

## 김동하

점수:

## 권오민

점수:

## 원인식

점수: 230점  
최고점수: 230점

### 요구사항

파이썬: Python 3.9 이상  
환경: 윈도우 파워쉘 혹은 리눅스 우분투 혹은 WSL

### 설치방법: 윈도우 파워쉘

1. 파이썬 3.9를 설치한다.
2. 다음 명령어를 파워쉘에 입력한다.

```shell
cd insik
python -m venv .venv
.venv/Scripts/activate.ps1
pip install --upgrade pip
pip install -r requirements.txt
deactivate
cd ..
```

### 설치방법: 리눅스 우분투 or WSL

```shell
sudo apt update
sudo apt upgrade -y
sudo apt install -y python3.9
sudo apt install -y python3.9-venv

cd insik
python3.9 -m venv .venv
source .venv/bin/activate
pip install --upgrade pip
pip install -r requirements.txt
deactivate
cd ..
```

### 실행방법: 윈도우 파워쉘

1. insik 디렉토리에 .env 파일을 만든다.
2. .env 파일에 BASE_URL="[테스트 API URL]"과 X_AUTH_TOKEN="[X_AUTH_TOKEN 토큰]"을 쓴다.
3. 다음 명령어를 입력한다.

```shell
cd insik
.venv/Scripts/activate.ps1
python main.py
deactivate
cd ..
```

### 실행방법: 리눅스 우분투 or WSL

1. insik 디렉토리에 .env 파일을 만든다.
2. .env 파일에 BASE_URL="[테스트 API URL]"과 X_AUTH_TOKEN="[X_AUTH_TOKEN 토큰]"을 쓴다.
3. 다음 명령어를 입력한다.

```shell
cd insik
source .venv/bin/activate
python3.9 main.py
deactivate
cd ..
```
