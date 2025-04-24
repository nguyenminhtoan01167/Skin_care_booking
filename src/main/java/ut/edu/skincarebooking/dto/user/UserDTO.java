package ut.edu.skincarebooking.dto.user;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String userType;
}
