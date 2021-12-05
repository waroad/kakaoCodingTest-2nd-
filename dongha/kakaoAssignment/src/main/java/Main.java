import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int centerR = 2;
    static int centerC = 2;
    private static class Pair{
        int r;
        int c;

        Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    private static Map<Integer, Pair> map = new HashMap<>();
    private static JsonParser jsonParser = new JsonParser();

    private static ArrayList<Truck> trucks(){
        return jsonParser.getTrucks(Connection.getInstance().trucks());
    }

    private static ArrayList<Location> locations(){
        return jsonParser.getLocations(Connection.getInstance().locations());
    }

    // get least places
    private static ArrayList<Integer> getLeastExistingPlace(ArrayList<Location> locations, int size){
        Collections.sort(locations);
        List<Integer> mostPlaceIdxList = locations.stream().map(Location::getId).collect(Collectors.toList()).subList(0, size);
        return new ArrayList<>(mostPlaceIdxList);
    }

    // get most places
    private static ArrayList<Integer> getMostExistingPlace(ArrayList<Location> locations, int size){
        Collections.sort(locations, Collections.reverseOrder());
        List<Integer> mostPlaceIdxList = locations.stream().map(Location::getId).collect(Collectors.toList()).subList(0, size);
        return new ArrayList<>(mostPlaceIdxList);
    }

    private static ArrayList<Integer> getRoute(int r, int c, int tr, int tc, ArrayList<Location> locations){
        ArrayList<Integer> orders = new ArrayList<>();
        ArrayList<Location> locations1 = new ArrayList<>(locations);
        // move from r to tr
        if(tr > r){
            for(int curR = r ; curR <= tr ; curR ++){
                if(orders.size() < 10){
                    if(locations1.get(curR + 5 * c).located_bikes_count > 4) orders.add(5);
                    orders.add(1);
                }
            }
        }
        else{
            for(int curR = r ; curR >= tr ; curR --){
                if(orders.size() < 10) {
                    if(locations1.get(curR + 5 * c).located_bikes_count > 4) orders.add(5);
                    orders.add(3);
                }
            }
        }
        // move from c to tc
        if(tc > c){
            for(int curC = c ; curC <= tc ; curC ++){
                if(orders.size() < 10) {
                    if(locations1.get(r + 5 * curC).located_bikes_count > 4) orders.add(5);
                    orders.add(2);
                }
            }
        }
        else{
            for(int curC = c ; curC >= tc ; curC --){
                if(orders.size() < 10) {
                    if(locations1.get(r + 5 * curC).located_bikes_count > 4) orders.add(5);
                    orders.add(4);
                }
            }
        }
        return orders;
    }

    private static double score(){
        System.out.println("##### result #####");
        JSONObject jsonObject = Connection.getInstance().score();
        return jsonParser.getScore(jsonObject);
    }

    private static String simulate(ArrayList<Command> commands){
        JSONObject jsonObject = Connection.getInstance().simulate(jsonParser.getCommandJSONArray(commands));
        Simulation simulation = jsonParser.putSimulation(jsonObject);
        return simulation.toString();
    }

    private static String start(int problemId){
        return TokenManager.getInstance().createToken(problemId);
    }

    private static void makeMap(final int N){
        int idx = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                map.put(idx++, new Pair(j, i));
            }
        }
    }

    public static void main(String[] args) {
        int problemId = 1;
        String response = start(problemId);
        if(response.equals("200")){
            makeMap(5);
            int time = 0;

            while(time++ < 720){
                ArrayList<Location> locations = locations();
                ArrayList<Truck> trucks = trucks();
                List<Integer> trucksIdx = trucks.stream().map(Truck::getLoaded_bikes_count).collect(Collectors.toList());
                ArrayList<Integer> load = new ArrayList<>();
                load.add(5);
                ArrayList<Integer> unload = new ArrayList<>();
                load.add(6);
                ArrayList<Command> commands = new ArrayList<>();
                ArrayList<Integer> order;
                ArrayList<Integer> order2;
//                ArrayList<Integer> mostPlaces = getMostExistingPlace(locations, 5);
                ArrayList<Integer> leastPlaces = getLeastExistingPlace(locations, 5);


                if(time % 50 == 1){
                    for(int i = 0 ; i < 5 ; i ++){
                        List<Integer> joined = new ArrayList<>(List.of(6, 6, 6, 6, 6, 6, 6, 6, 6, 6));
                        commands.add(new Command(i, joined));
                    }
                }
                else for(int i = 0 ; i < 5 ; i ++){
                    List<Integer> joined = new ArrayList<>();
                    joined.addAll(load);
                    joined.addAll(getRoute(map.get(trucksIdx.get(i)).r, map.get(trucksIdx.get(i)).c, map.get(leastPlaces.get(i)).r, map.get(leastPlaces.get(i)).c, locations));
                    joined.addAll(unload);
                    commands.add(new Command(i, joined));
                }
                System.out.println(simulate(commands));
            }
            System.out.println(score());
        }
    }
}