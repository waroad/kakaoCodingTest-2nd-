from util import is_square
from location import Location, Locations

class Truck:
    def __init__(self, location: Location, bike_count: int):
        self.location = location
        self.bike_count = bike_count
        
    @property
    def x(self):
        return self.location.x
    
    @property
    def y(self):
        return self.location.y
    
    @property
    def xy(self):
        return self.location.xy
        
class Trucks:
    def __init__(self, trucks_info:list[tuple[int, int ,int]], locations: Locations):
        trucks_info = sorted(trucks_info, key=lambda tup: tup[0])
        self.__locations = locations
        self.__values = [Truck(locations[location_id], bike_count) 
                         for _, location_id, bike_count in trucks_info]
        

        
