package shushi.auth.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class RegisterDto {
    private String userName;
    private String email;
    private String phoneNumner;
    private String address;
}
