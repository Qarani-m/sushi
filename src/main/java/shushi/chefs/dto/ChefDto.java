package shushi.chefs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChefDto {
    private String name;
    private String specialty;
    private int experienceYears;
    private String email;
    private String phone;
    private boolean topChef;
}
