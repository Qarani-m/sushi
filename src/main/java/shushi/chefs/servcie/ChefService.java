package shushi.chefs.servcie;

import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;

import java.util.List;

public interface ChefService  {
    ChefEntity getChefByName(String name);

    ChefEntity getTopChef();

    List<ChefEntity> getAllChefs();

    ChefEntity createChef(ChefDto chefDto);
}
