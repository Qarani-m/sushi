package shushi.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.service.CartService;

@RestController
@RequestMapping("/api/private/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Endpoint to create a new cart
    @PostMapping("/create")
    public ResponseEntity<CartEntity> createCart() {
        CartEntity cart = cartService.createCart();
        return ResponseEntity.ok(cart);
    }

    // Endpoint to add an item to the cart
    @PostMapping("/{cartId}/addItem")
    public ResponseEntity<CartEntity> addItemToCart(@PathVariable String cartId, @RequestBody CartItem item) {
        CartEntity updatedCart = cartService.addItemToCart(cartId, item);
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to remove an item from the cart
    @PostMapping("/{cartId}/removeItem")
    public ResponseEntity<CartEntity> removeItemFromCart(@PathVariable String cartId, @RequestBody CartItem item) {
        CartEntity updatedCart = cartService.removeItemFromCart(cartId, item);
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to get the contents of the cart
    @GetMapping("/{cartId}")
    public ResponseEntity<CartEntity> getCart(@PathVariable String cartId) {
        CartEntity cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }



    // Endpoint to clear the contents of the cart
    @PostMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }
}
