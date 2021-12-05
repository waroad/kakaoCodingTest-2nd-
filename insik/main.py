from config import BASE_URL, X_AUTH_TOKEN
from api import KakaoBikeAPI
from solution import Solution

if __name__ == "__main__":
    api = KakaoBikeAPI(BASE_URL, X_AUTH_TOKEN)
    solution = Solution(api)
    score = solution.do_nothing()
    print(f'score : {score}')