package shushi.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.cart.dto.NewCartDto;
import shushi.cart.dto.NewItemDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.service.CartService;

@RestController
@RequestMapping("/api/private/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<CartEntity> createCart(@RequestBody NewCartDto newCart) {
        CartEntity cart = cartService.createCart(newCart);
        return ResponseEntity.ok(cart);
    }

    // Endpoint to add an item to the cart
    @PostMapping("/addItem/{userId}")
    public ResponseEntity<CartEntity> addItemToCart(@PathVariable String userId, @RequestBody String itemId) {
        CartEntity updatedCart = cartService.addItemToCart(userId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to remove an item from the cart
    @PostMapping("/removeItem/{cartId}")
    public ResponseEntity<CartEntity> removeItemFromCart(@PathVariable String userId, @RequestBody String itemId) {
        CartEntity updatedCart = cartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to get the contents of the cart
    @GetMapping("/{cartId}")
    public ResponseEntity<CartEntity> getCart(@PathVariable String cartId) {
        CartEntity cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }



    // Endpoint to clear the contents of the cart
    @PostMapping("/clear-cart/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/numberOfItems/{userId}")
    public ResponseEntity<Integer> getNumberOfItemsInCart(@PathVariable String userId) {
        int numberOfItems = cartService.getNumberOfItemsInCart(userId);
        return ResponseEntity.ok(numberOfItems);
    }

    @PostMapping("/increaseQuantity/{userId}")
    public ResponseEntity<CartEntity> increaseQuantity(@PathVariable String userId, @RequestBody String itemId) {
        CartEntity updatedCart = cartService.increaseQuantity(userId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping("/reduceQuantity/{userId}")
    public ResponseEntity<CartEntity> reduceQuantity(@PathVariable String userId, @RequestBody String itemId) {
        CartEntity updatedCart = cartService.reduceQuantity(userId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

}
