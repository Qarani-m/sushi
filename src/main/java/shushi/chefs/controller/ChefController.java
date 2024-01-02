package shushi.chefs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;
import shushi.chefs.servcie.ChefService;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;
    @GetMapping("/one/{chefId}")
    public ResponseEntity<ChefEntity> getChef(@PathVariable String chefId) {
        ChefEntity chef = chefService.getChefByName(chefId);
        if (chef != null) {
            return ResponseEntity.ok(chef);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/top")
    public ResponseEntity<ChefEntity> getTopChef() {
        ChefEntity chefs = chefService.getTopChef();
        if (chefs != null) {
            return ResponseEntity.ok(chefs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ChefEntity>> getAllChefs() {
        List<ChefEntity> chefs = chefService.getAllChefs();
        if (!chefs.isEmpty()) {
            return ResponseEntity.ok(chefs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/private/create")
    public ResponseEntity<ChefEntity> createChef(@RequestBody ChefDto chefDto) {
        ChefEntity createdChef = chefService.createChef(chefDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChef);
    }
}
