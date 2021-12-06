import math
from numpy import asarray, split, rot90

def is_square(i: int) -> bool:
    return i == math.isqrt(i) ** 2

# fowrad declaration
class Location: pass
class Locations: pass

class Locations:
    def __init__(self, location_infos: list[tuple[int, int]]):
        self.setvalues(location_infos)
        self.__locations = list(map(lambda i: Location(i, self), 
                                range(len(location_infos))))
        
    def xy(self, id: int) -> tuple[int, int]:
        return (id % 5, id // 5)

    def get(self, x: int, y: int) -> int:
        assert(x in range(self.edge_length))
        assert(y in range(self.edge_length))
        
        return self.__values[x * self.edge_length + y]
    
    def getlocation(self, id: int) -> Location:
        return self.__locations[id]
    
    def __getitem__(self, subscript: tuple[int, int]) -> int:
        x, y = subscript
        return self.get(x, y)
    
    def setvalues(self, location_infos: list[tuple[int, int]]) -> None:
        assert(is_square(len(location_infos)))
        location_infos.sort(key= lambda tup: tup[0])
        
        self.edge_length = math.isqrt(len(location_infos))
        self.__values = list(map(lambda tup: tup[1], location_infos))   

class Location:
    def __init__(self, id: int, locations: Locations):
        assert(id in range(locations.edge_length ** 2))
        self.__locations = locations
        self.__id = id
        
    @property
    def bike_count(self) -> int:
        (x, y) = self.__locations.xy(self.__id)
        return self.__locations.get(x, y)
    
    @property
    def x(self) -> int:
        (x, _) = self.__locations.xy(self.__id)
        return x
    
    @property
    def y(self) -> int:
        (_, y) = self.__locations.xy(self.__id)
        return y
    
    @property
    def xy(self) -> tuple[int, int]:
        return self.__locations.xy(self.__id)
    
    def __str__(self) -> str:
        return f'x: {self.x}, y: {self.y}, bike_count: {self.bike_count}'


if __name__ == '__main__':
    locations = Locations(list((i, i) for i in range(25)))
    print(locations.getlocation(0))
    print(locations.getlocation(9))
    #print(locations.values)
    