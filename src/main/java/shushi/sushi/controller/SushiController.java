package shushi.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.sushi.dto.SushiDto;
import shushi.sushi.entity.SushiEntity;
import shushi.sushi.repository.SushiRepository;
import shushi.sushi.service.SushiService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sushi")
public class SushiController {
    @Autowired
    private SushiService sushiService;



    // Get all sushi items
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllSushi() {
        Map<String, Object> sushiResult = sushiService.findAll();

        if (sushiResult.get("sushiList") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sushiResult);
        } else {
            return ResponseEntity.ok(sushiResult);
        }
    }

    // Get a specific sushi item by ID
    @GetMapping("/one/{sushiId}")
    public ResponseEntity<Map<String, Object>> getSushiById(@PathVariable String sushiId) {
        Map<String, Object> sushiResult = sushiService.getSushiById(sushiId);
        if (sushiResult.get("sushi") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sushiResult);
        } else {
            return ResponseEntity.ok(sushiResult);
        }
    }

    // Update a sushi item by ID
    @PutMapping("/admin/update/{sushiId}")
    public ResponseEntity<Map<String, Object>> updateSushi(@PathVariable String sushiId, @RequestBody SushiEntity updatedSushi) {
        Map<String, Object> updateResult = sushiService.updateSushi(sushiId, updatedSushi);
        if (updateResult.get("updatedSushi") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateResult);
        } else {
            return ResponseEntity.ok(updateResult);
        }
    }

    // Create a new sushi item

    @PostMapping("/admin/create")
    public ResponseEntity<Map<String, Object>> createSushi(@RequestBody SushiDto sushiDto) {
        Map<String, Object> creationResult = sushiService.createSushi(sushiDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creationResult);
    }
    // Delete a sushi item by ID

    @DeleteMapping("/admin/delete/{sushiId}")
    public ResponseEntity<Map<String, Object>> deleteSushi(@PathVariable String sushiId) {
        Map<String, Object> deletionResult = sushiService.deleteSushi(sushiId);

        if (deletionResult.get("message").equals("Sushi entity deleted successfully.")) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();

        }
    }
    // Filter sushi items by price range, category, and stars
    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterSushi(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer stars) {

        Map<String, Object> filterResult = sushiService.filterSushi(minPrice, maxPrice, category, stars);

        if (filterResult.get("filteredSushi") != null && !((List<?>) filterResult.get("filteredSushi")).isEmpty()) {
            return ResponseEntity.ok(filterResult);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
