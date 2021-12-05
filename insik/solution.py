from api import KakaoBikeAPI

__ordinal_prefixes = ["st", "nd", "rd"] + ["th"] * 7

def ordinal(num: int) -> str:
    digit = num % 10
    return str(num) + __ordinal_prefixes[digit]

class Solution:
    def __init__(self, api: KakaoBikeAPI):
        self.api = api
    
    def do_nothing(self) -> int:
        self.api.start()
        for i in range(720):
            print(f'simulate at {ordinal(i)}... ', end='\r')
            self.api.simulate([])
        print()
        return self.api.score()
    
