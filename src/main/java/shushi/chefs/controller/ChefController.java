package shushi.chefs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.chefs.dto.ChefDto;
import shushi.chefs.entity.ChefEntity;
import shushi.chefs.servcie.ChefService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;
    @GetMapping("/one/{chefId}")
    public ResponseEntity<Map<String, Object>> getChef(@PathVariable String chefId) {
        Map<String, Object> chef = chefService.getChefByName(chefId);

        if (chef != null) {
            return ResponseEntity.ok(chef);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chef);
        }
    }


    @GetMapping("/top")
    public ResponseEntity<Map<String, Object>> getTopChef() {
        Map<String, Object> topChef = chefService.getTopChef();
        if (topChef != null) {
            return ResponseEntity.ok(topChef);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(topChef);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllChefs() {
        Map<String, Object> chefs = chefService.getAllChefs();

        if (!chefs.isEmpty()) {
            return ResponseEntity.ok(chefs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chefs);
        }
    }

    @PostMapping("/private/create")
    public ResponseEntity<Map<String, Object>> createChef(@RequestBody ChefDto chefDto) {
        Map<String, Object> createdChef = chefService.createChef(chefDto);

        if (createdChef != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChef);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createdChef);
        }
    }

}
