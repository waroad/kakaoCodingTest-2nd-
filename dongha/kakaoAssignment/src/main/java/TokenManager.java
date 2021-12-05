import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class TokenManager {
    private String token;
    static TokenManager instance = null;

    public static TokenManager getInstance(){
        if(instance == null) instance = new TokenManager();
        return instance;
    }

    public synchronized String createToken(int problemId){
        String token = Connection.getInstance().start(problemId);
        setToken(token);
        return "200";
    }

    public String getToken(){
        return token;
    }
}