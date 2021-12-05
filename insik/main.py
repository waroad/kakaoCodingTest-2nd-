from config import BASE_URL, X_AUTH_TOKEN
from api import KakaoBikeAPI

if __name__ == "__main__":
    kakao_api = KakaoBikeAPI(BASE_URL, X_AUTH_TOKEN)
    print(kakao_api)
    print(kakao_api.locations())
    print(kakao_api.trucks())
    for i in range(720):
        print(f'{i + 1}. {kakao_api.simulate([])}')
    print(f'score: {kakao_api.score()}')