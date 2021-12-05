# kakaoCodingTest-2nd-

카카오 2차 코테 준비

## 남도하

점수:

## 김동하

점수:

## 권오민

점수:

## 원인식

점수: 0  
최고점수: 0

### 요구사항

파이썬: 3.3 버전 이상  
환경: 윈도우 파워쉘 혹은 리눅스 우분투 혹은 WSL

### 설치방법: 윈도우 파워쉘

```shell
cd insik
python -m venv .venv
.venv/Scripts/activate.ps1
python -m pip install --upgrade pip
pip install -r requirements.txt
deactivate
```

### 설치방법: 리눅스 우분투

```shell
cd insik
python3 -m venv .venv
source .venv/Scripts/activate
python3 -m pip install --upgrade pip
pip install -r requirements.txt
deactivate
```

### 실행방법: 윈도우 파워쉘

1. insik 디렉토리에 .env 파일을 만든다.
2. .env 파일에 BASE_URL="[테스트 API URL]"과 X_AUTH_TOKEN="[X_AUTH_TOKEN 토큰]"을 쓴다.
3. 다음 명령어를 입력한다.

```shell
cd insik
.venv/Scripts/activate.ps1
python main.py; deactivate;
```

### 실행방법: 리눅스 우분투

1. insik 디렉토리에 .env 파일을 만든다.
2. .env 파일에 BASE_URL="[테스트 API URL]"과 X_AUTH_TOKEN="[X_AUTH_TOKEN 토큰]"을 쓴다.
3. 다음 명령어를 입력한다.

```shell
cd insik
source .venv/Scripts/activate
python3 main.py; deactivate;
```
