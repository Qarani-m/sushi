package shushi.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.item.dto.ItemDto;
import shushi.item.entity.ItemEntity;
import shushi.item.service.ItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;



    // Get all item items
    @GetMapping("/public/all")
    public ResponseEntity<Map<String, Object>> getAllSushi() {
        Map<String, Object> sushiResult = itemService.findAll();

        if (sushiResult.get("sushiList") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sushiResult);
        } else {
            return ResponseEntity.ok(sushiResult);
        }
    }

    // Get a specific item item by ID
    @GetMapping("/public/one/{sushiId}")
    public ResponseEntity<Map<String, Object>> getSushiById(@PathVariable String sushiId) {
        Map<String, Object> sushiResult = itemService.getSushiById(sushiId);
        if (sushiResult.get("item") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sushiResult);
        } else {
            return ResponseEntity.ok(sushiResult);
        }
    }

    // Update a item item by ID
    @PutMapping("/public/admin/update/{sushiId}")
    public ResponseEntity<Map<String, Object>> updateSushi(@PathVariable String sushiId, @RequestBody ItemEntity updatedSushi) {
        Map<String, Object> updateResult = itemService.updateSushi(sushiId, updatedSushi);
        if (updateResult.get("updatedSushi") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateResult);
        } else {
            return ResponseEntity.ok(updateResult);
        }
    }

    // Create a new item item

    @PostMapping("/admin/create")
    public ResponseEntity<Map<String, Object>> createSushi(@RequestBody ItemDto itemDto) {
        Map<String, Object> creationResult = itemService.createSushi(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creationResult);
    }
    // Delete a item item by ID

    @DeleteMapping("/admin/delete/{sushiId}")
    public ResponseEntity<Map<String, Object>> deleteSushi(@PathVariable String sushiId) {
        Map<String, Object> deletionResult = itemService.deleteSushi(sushiId);

        if (deletionResult.get("message").equals("Sushi entity deleted successfully.")) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();

        }
    }
    // Filter item items by price range, category, and stars
    @GetMapping("/public/filter")
    public ResponseEntity<Map<String, Object>> filterSushi(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer stars) {

        Map<String, Object> filterResult = itemService.filterSushi(minPrice, maxPrice, category, stars);

        if (filterResult.get("filteredSushi") != null && !((List<?>) filterResult.get("filteredSushi")).isEmpty()) {
            return ResponseEntity.ok(filterResult);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
