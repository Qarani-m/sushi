package shushi.chefs.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;
import shushi.chefs.repository.ChefRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefRepository chefRepository;

    @Override
    public ChefEntity getChefByName(String name) {
        Optional<ChefEntity> chefOptional = chefRepository.findById(String.valueOf(name));
        return chefOptional.orElse(null);
    }

    public ChefEntity getTopChef() {
        return chefRepository.findByTopChefIsTrue();
    }
    @Override
    public List<ChefEntity> getAllChefs() {
        return chefRepository.findAll();
    }


    @Override
    public ChefEntity createChef(ChefDto chefDto) {
        ChefEntity chefEntity = ChefEntity.builder()
                .name(chefDto.getName())
                .specialty(chefDto.getSpecialty())
                .experienceYears(chefDto.getExperienceYears())
                .email(chefDto.getEmail())
                .topChef(chefDto.isTopChef())
                .phone(chefDto.getPhone())
                .build();

        return chefRepository.save(chefEntity);
    }
}
