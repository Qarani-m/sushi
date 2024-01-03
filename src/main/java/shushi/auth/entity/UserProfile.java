package shushi.auth.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cart")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  String id;
    private String username;
    private String email;
    private String phone;
    private String address;
}
