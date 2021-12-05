import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    public ArrayList<Truck> getTrucks(JSONObject responseJson){
        ArrayList<Truck> trucks = new ArrayList<>();
        try{
            JSONArray trucksArray = responseJson.getJSONArray("trucks");
            for(int i = 0 ; i < trucksArray.length() ; i ++){
                JSONObject truckObject = trucksArray.getJSONObject(i);
                trucks.add(Truck.of(truckObject));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return trucks;
    }

    public ArrayList<Location> getLocations(JSONObject responseJson){
        ArrayList<Location> locations = new ArrayList<>();
        try{
            JSONArray locationsArray = responseJson.getJSONArray("locations");
            for(int i = 0 ; i < locationsArray.length() ; i ++){
                JSONObject locationObject = locationsArray.getJSONObject(i);
                locations.add(Location.of(locationObject));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return locations;
    }

    public double getScore(JSONObject responseJson){
        try{
            return responseJson.getDouble("score");
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public JSONArray getCommandJSONArray(ArrayList<Command> commandsList){
        JSONArray commandArray = new JSONArray();
        for(Command commands : commandsList) commandArray.put(commands.getJSONObject());
        return commandArray;
    }

    public Simulation putSimulation(JSONObject responseJson){
        Simulation simulation = null;
        try{
            simulation = Simulation.of(responseJson);
        } catch (Exception e){
            e.printStackTrace();
        }
        return simulation;
    }
}
