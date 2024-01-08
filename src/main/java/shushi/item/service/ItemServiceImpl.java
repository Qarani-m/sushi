package shushi.item.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.item.dto.ItemDto;
import shushi.item.entity.ItemEntity;
import shushi.item.repository.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Map<String, Object> findAll() {
        List<ItemEntity> sushiList = itemRepository.findAll();

        Map<String, Object> response = new HashMap<>();

        if (!sushiList.isEmpty()) {
            response.put("sushiList", sushiList);
            response.put("message", "success");
        } else {
            response.put("sushiList", null);
            response.put("message", "No item entities found.");
        }

        return response;
    }

    public Map<String, Object> getSushiById(String sushiId) {
        Optional<ItemEntity> sushi = itemRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (sushi.isPresent()) {
            response.put("item", sushi.get());
            response.put("message", "success");
        } else {
            response.put("item", null);
            response.put("message", "Sushi entity not found.");
        }

        return response;
    }
    public Map<String, Object> updateSushi(String sushiId, ItemEntity updatedSushi) {
        Optional<ItemEntity> existingSushiOptional = itemRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (existingSushiOptional.isPresent()) {
            ItemEntity existingSushi = existingSushiOptional.get();
            existingSushi.setName(updatedSushi.getName());
            existingSushi.setPrice(updatedSushi.getPrice());
            existingSushi.setDescription(updatedSushi.getDescription());
            existingSushi.setStars(updatedSushi.getStars());
            existingSushi.setCategory(updatedSushi.getCategory());
            existingSushi.setImageUrl(updatedSushi.getImageUrl());

            ItemEntity savedSushi = itemRepository.save(existingSushi);

            response.put("updatedSushi", savedSushi);
            response.put("message", "success");
        } else {
            response.put("updatedSushi", null);
            response.put("message", "Sushi entity not found for update.");
        }

        return response;
    }
    @Override
    public Map<String, Object> createSushi(ItemDto itemDto) {
        ItemEntity itemEntity = ItemEntity.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .description(itemDto.getDescription())
                .stars(0)
                .category(itemDto.getCategory())
                .imageUrl(itemDto.getImageUrl())
                .build();

        ItemEntity createdSushi = itemRepository.save(itemEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("createdSushi", createdSushi);
        response.put("message", "success");

        return response;
    }
    @Override
    public Map<String, Object> deleteSushi(String sushiId) {
        Optional<ItemEntity> existingSushiOptional = itemRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (existingSushiOptional.isPresent()) {
            itemRepository.deleteById(sushiId);
            response.put("message", "success");
        } else {
            response.put("message", "Sushi entity not found for deletion.");
        }

        return response;
    }


    @Override
    public Map<String, Object> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars) {
        System.out.println(minPrice + "__" + maxPrice + "__" + category + "__" + stars);

        List<ItemEntity> filteredSushi = itemRepository.findByPriceBetweenAndCategoryAndStars(
                minPrice != null ? minPrice : 0,
                maxPrice != null ? maxPrice : Double.MAX_VALUE,
                category,
                stars != null ? stars : 0
        );

        Map<String, Object> response = new HashMap<>();
        response.put("filteredSushi", filteredSushi);
        response.put("message", "success");

        return response;
    }

}
