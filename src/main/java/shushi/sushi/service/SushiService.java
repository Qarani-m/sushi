package shushi.sushi.service;

import shushi.sushi.dto.SushiDto;
import shushi.sushi.entity.SushiEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SushiService {
    Map<String, Object> findAll();

    Map<String, Object> getSushiById(String sushiId);

    Map<String, Object> updateSushi(String sushiId, SushiEntity updatedSushi);

    Map<String, Object>  createSushi(SushiDto sushiDto);

    Map<String, Object>  deleteSushi(String sushiId);

    Map<String, Object> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars);
}
