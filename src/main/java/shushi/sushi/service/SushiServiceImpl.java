package shushi.sushi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.sushi.dto.SushiDto;
import shushi.sushi.entity.SushiEntity;
import shushi.sushi.repository.SushiRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SushiServiceImpl implements SushiService{
    @Autowired
    private SushiRepository sushiRepository;

    @Override
    public Map<String, Object> findAll() {
        List<SushiEntity> sushiList = sushiRepository.findAll();

        Map<String, Object> response = new HashMap<>();

        if (!sushiList.isEmpty()) {
            response.put("sushiList", sushiList);
            response.put("message", "success");
        } else {
            response.put("sushiList", null);
            response.put("message", "No sushi entities found.");
        }

        return response;
    }

    public Map<String, Object> getSushiById(String sushiId) {
        Optional<SushiEntity> sushi = sushiRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (sushi.isPresent()) {
            response.put("sushi", sushi.get());
            response.put("message", "success");
        } else {
            response.put("sushi", null);
            response.put("message", "Sushi entity not found.");
        }

        return response;
    }
    public Map<String, Object> updateSushi(String sushiId, SushiEntity updatedSushi) {
        Optional<SushiEntity> existingSushiOptional = sushiRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (existingSushiOptional.isPresent()) {
            SushiEntity existingSushi = existingSushiOptional.get();
            existingSushi.setName(updatedSushi.getName());
            existingSushi.setPrice(updatedSushi.getPrice());
            existingSushi.setDescription(updatedSushi.getDescription());
            existingSushi.setStars(updatedSushi.getStars());
            existingSushi.setCategory(updatedSushi.getCategory());
            existingSushi.setImageUrl(updatedSushi.getImageUrl());

            SushiEntity savedSushi = sushiRepository.save(existingSushi);

            response.put("updatedSushi", savedSushi);
            response.put("message", "success");
        } else {
            response.put("updatedSushi", null);
            response.put("message", "Sushi entity not found for update.");
        }

        return response;
    }
    @Override
    public Map<String, Object> createSushi(SushiDto sushiDto) {
        SushiEntity sushiEntity = SushiEntity.builder()
                .name(sushiDto.getName())
                .price(sushiDto.getPrice())
                .description(sushiDto.getDescription())
                .stars(0)
                .category(sushiDto.getCategory())
                .imageUrl(sushiDto.getImageUrl())
                .build();

        SushiEntity createdSushi = sushiRepository.save(sushiEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("createdSushi", createdSushi);
        response.put("message", "success");

        return response;
    }
    @Override
    public Map<String, Object> deleteSushi(String sushiId) {
        Optional<SushiEntity> existingSushiOptional = sushiRepository.findById(sushiId);

        Map<String, Object> response = new HashMap<>();

        if (existingSushiOptional.isPresent()) {
            sushiRepository.deleteById(sushiId);
            response.put("message", "success");
        } else {
            response.put("message", "Sushi entity not found for deletion.");
        }

        return response;
    }


    @Override
    public Map<String, Object> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars) {
        System.out.println(minPrice + "__" + maxPrice + "__" + category + "__" + stars);

        List<SushiEntity> filteredSushi = sushiRepository.findByPriceBetweenAndCategoryAndStars(
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
