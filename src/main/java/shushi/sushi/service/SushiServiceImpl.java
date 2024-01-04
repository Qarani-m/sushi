package shushi.sushi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.sushi.entity.SushiEntity;
import shushi.sushi.repository.SushiRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SushiServiceImpl implements SushiService{
    @Autowired
    private SushiRepository sushiRepository;

    @Override
    public List<SushiEntity> findAll() {
        return  sushiRepository.findAll();
    }

    public Optional<SushiEntity> getSushiById(String sushiId) {
        return sushiRepository.findById(sushiId);
    }
    public Optional<SushiEntity> updateSushi(String sushiId, SushiEntity updatedSushi) {
        return sushiRepository.findById(sushiId)
                .map(existingSushi -> {
                    existingSushi.setName(updatedSushi.getName());
                    existingSushi.setPrice(updatedSushi.getPrice());
                    existingSushi.setDescription(updatedSushi.getDescription());
                    existingSushi.setStars(updatedSushi.getStars());
                    existingSushi.setCategory(updatedSushi.getCategory());
                    existingSushi.setImageUrl(updatedSushi.getImageUrl());

                    return sushiRepository.save(existingSushi);
                });
    }
    public SushiEntity createSushi(SushiEntity sushiEntity) {
        return sushiRepository.save(sushiEntity);
    }
    public boolean deleteSushi(String sushiId) {
        if (sushiRepository.existsById(sushiId)) {
            sushiRepository.deleteById(sushiId);
            return true;
        }
        return false;
    }


    public List<SushiEntity> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars) {
        System.out.println(minPrice +"__"+maxPrice+"__"+ category+"__"+stars);
        return sushiRepository.findByPriceBetweenAndCategoryAndStars(
                minPrice != null ? minPrice : 0,
                maxPrice != null ? maxPrice : Double.MAX_VALUE,
                category,
                stars != null ? stars : 0
        );
    }
}
