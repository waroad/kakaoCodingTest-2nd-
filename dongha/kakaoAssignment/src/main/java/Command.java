import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Command {
    int truck_id;
    List<Integer> command = new ArrayList<>();

    public JSONObject getJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("truck_id", truck_id);
            if(command != null) jsonObject.put("command", command);
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

}
