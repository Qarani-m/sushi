package shushi.item.service;

import shushi.item.dto.ItemDto;
import shushi.item.entity.ItemEntity;

import java.util.Map;

public interface ItemService {
    Map<String, Object> findAll();

    Map<String, Object> getSushiById(String sushiId);

    Map<String, Object> updateSushi(String sushiId, ItemEntity updatedSushi);

    Map<String, Object>  createSushi(ItemDto itemDto);

    Map<String, Object>  deleteSushi(String sushiId);

    Map<String, Object> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars);
}
