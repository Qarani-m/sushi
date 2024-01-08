package shushi.auth.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class LoginUserDto {
    private String userName;
    private String Password;
}
