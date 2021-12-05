import lombok.*;
import org.json.JSONObject;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Location implements Comparable<Location> {
    int id;
    int located_bikes_count;

//    static class locationComparator implements Comparable<Location>{
//        public int compare(Location loc1, Location loc2){
//            return loc1.located_bikes_count - loc2.located_bikes_count;
//        }
//    }


    @Override
    public int compareTo(Location o) {
        return this.located_bikes_count - o.located_bikes_count;
    }

    public static Location of(JSONObject jsonObject){
        return Location.builder()
                .id(jsonObject.getInt("id"))
                .located_bikes_count(jsonObject.getInt("located_bikes_count"))
                .build();
    }
}