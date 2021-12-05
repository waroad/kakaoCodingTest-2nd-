import lombok.*;
import org.json.JSONObject;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Truck {
    int id;
    int location_id;
    int loaded_bikes_count;

    public static Truck of(JSONObject jsonObject){
        return Truck.builder()
                .location_id(jsonObject.getInt("id"))
                .loaded_bikes_count(jsonObject.getInt("location_id"))
                .loaded_bikes_count(jsonObject.getInt("loaded_bikes_count"))
                .build();
    }
}