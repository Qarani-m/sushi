package shushi.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.cart.dto.AddItemDto;
import shushi.cart.dto.NewCartDto;
import shushi.cart.dto.NewItemDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.service.CartService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/private/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCart(@RequestBody NewCartDto newCart) {
        Map<String, Object> cart = cartService.createCart(newCart);

        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cart);
        }
    }


    // Endpoint to add an item to the cart
    @PostMapping("/addItem/{userId}")
    public ResponseEntity<Map<String, Object>> addItemToCart(@PathVariable String userId, @RequestBody AddItemDto itemIdDto) {
        Map<String, Object> updatedCart = cartService.addItemToCart(userId, itemIdDto.getItemId());

        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCart);
        }
    }

    // Endpoint to remove an item from the cart
    @PostMapping("/removeItem/{userId}")
    public ResponseEntity<Map<String, Object>> removeItemFromCart(@PathVariable String userId, @RequestBody AddItemDto itemIdDto) {
        Map<String, Object> updatedCart = cartService.removeItemFromCart(userId, itemIdDto.getItemId());

        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCart);
        }
    }


    // Endpoint to get the contents of the cart
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable String userId) {
        Map<String, Object> cart = cartService.getCart(userId);

        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cart);
        }
    }



    // Endpoint to clear the contents of the cart
    @GetMapping("/clear-cart/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/numberOfItems/{userId}")
    public ResponseEntity<Map<String, Object>> getNumberOfItemsInCart(@PathVariable String userId) {
        Map<String, Object> numberOfItems = cartService.getNumberOfItemsInCart(userId);
        return ResponseEntity.ok(numberOfItems);
    }


    @PostMapping("/increaseQuantity/{userId}")
    public ResponseEntity<Map<String, Object>> increaseQuantity(@PathVariable String userId, @RequestBody AddItemDto addItemDto) {
        Map<String, Object> updatedCart = cartService.increaseQuantity(userId, addItemDto.getItemId());

        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCart);
        }
    }


    @PostMapping("/reduceQuantity/{userId}")
    public ResponseEntity<Map<String, Object>> reduceQuantity(@PathVariable String userId, @RequestBody AddItemDto addItemDto) {
        Map<String, Object> updatedCart = cartService.reduceQuantity(userId, addItemDto.getItemId());

        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCart);
        }
    }


}
