import lombok.*;
import org.json.JSONObject;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Simulation { // 시뮬레이션 결과
    String status;
    int time;
    String failed_requests_count;
    String distance;

    public static Simulation of(JSONObject jsonObject){
        return Simulation.builder()
                .status(jsonObject.getString("status"))
                .time(jsonObject.getInt("time"))
                .failed_requests_count(jsonObject.getString("failed_requests_count"))
                .distance(jsonObject.getString("distance"))
                .build();
    }
}