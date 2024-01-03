package shushi.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.sushi.entity.SushiEntity;
import shushi.sushi.repository.SushiRepository;
import shushi.sushi.service.SushiService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sushi")
public class SushiController {
    @Autowired
    private SushiService sushiService;



    // Get all sushi items
    @GetMapping("/all")
    public ResponseEntity<List<SushiEntity>> getAllSushi() {
        List<SushiEntity> sushiList = sushiService.findAll();
        return ResponseEntity.ok(sushiList);
    }

    // Get a specific sushi item by ID
    @GetMapping("/one/{sushiId}")
    public ResponseEntity<SushiEntity> getSushiById(@PathVariable String sushiId) {
        Optional<SushiEntity> sushi = sushiService.getSushiById(sushiId);

        return sushi.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new sushi item
    @PostMapping("/admin/create")
    public ResponseEntity<SushiEntity> createSushi(@RequestBody SushiEntity sushiEntity) {
        SushiEntity createdSushi = sushiService.createSushi(sushiEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSushi);
    }

    // Update a sushi item by ID
    @PutMapping("/admin/update/{sushiId}")
    public ResponseEntity<SushiEntity> updateSushi(@PathVariable String sushiId, @RequestBody SushiEntity updatedSushi) {
        Optional<SushiEntity> updatedSushiEntity = sushiService.updateSushi(sushiId, updatedSushi);

        return updatedSushiEntity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a sushi item by ID

    @DeleteMapping("/admin/delete/{sushiId}")
    public ResponseEntity<Void> deleteSushi(@PathVariable String sushiId) {
        boolean deleted = sushiService.deleteSushi(sushiId);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    // Filter sushi items by price range, category, and stars
    @GetMapping("/filter")
    public ResponseEntity<List<SushiEntity>> filterSushi(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer stars) {

        List<SushiEntity> filteredSushi = sushiService.filterSushi(minPrice, maxPrice, category, stars);
        return ResponseEntity.ok(filteredSushi);
    }
}
