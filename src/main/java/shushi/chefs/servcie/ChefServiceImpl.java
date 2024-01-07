package shushi.chefs.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;
import shushi.chefs.repository.ChefRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefRepository chefRepository;

    @Override
    public Map<String, Object> getChefByName(String name) {
        Map<String, Object> response = new HashMap<>();
        Optional<ChefEntity> chefOptional = chefRepository.findById(String.valueOf(name));

        if (chefOptional.isPresent()) {
            response.put("chef", chefOptional.get());
            response.put("message", "success");
        } else {
            response.put("chef", null);
            response.put("message", "Chef not found.");
        }

        return response;
    }


    public Map<String, Object> getTopChef() {
        Map<String, Object> response = new HashMap<>();
        ChefEntity topChef = chefRepository.findByTopChefIsTrue();

        if (topChef != null) {
            response.put("chef", topChef);
            response.put("message", "success");
        } else {
            response.put("chef", null);
            response.put("message", "Top Chef not found.");
        }

        return response;
    }

    @Override
    public Map<String, Object> getAllChefs() {
        Map<String, Object> response = new HashMap<>();
        List<ChefEntity> chefs = chefRepository.findAll();

        if (!chefs.isEmpty()) {
            response.put("chefs", chefs);
            response.put("message", "success");
        } else {
            response.put("chefs", null);
            response.put("message", "No Chefs found.");
        }

        return response;
    }

    @Override
    public Map<String, Object> createChef(ChefDto chefDto) {
        Map<String, Object> response = new HashMap<>();
        ChefEntity chefEntity = ChefEntity.builder()
                .name(chefDto.getName())
                .specialty(chefDto.getSpecialty())
                .experienceYears(chefDto.getExperienceYears())
                .email(chefDto.getEmail())
                .topChef(chefDto.isTopChef())
                .phone(chefDto.getPhone())
                .build();

        ChefEntity createdChef = chefRepository.save(chefEntity);

        if (createdChef != null) {
            response.put("createdChef", createdChef);
            response.put("message", "success");
        } else {
            response.put("createdChef", null);
            response.put("message", "Chef creation failed.");
        }

        return response;
    }

}
