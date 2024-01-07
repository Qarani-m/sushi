package shushi.chefs.servcie;

import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;

import java.util.List;
import java.util.Map;

public interface ChefService  {
    Map<String, Object> getChefByName(String name);

    Map<String, Object> getTopChef();

    Map<String, Object> getAllChefs();

    Map<String, Object> createChef(ChefDto chefDto);
}
