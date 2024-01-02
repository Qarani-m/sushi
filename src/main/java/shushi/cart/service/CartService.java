package shushi.cart.service;

import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;

public interface CartService {
    CartEntity addItemToCart(String userId, CartItem item);

    CartEntity removeItemFromCart(String userId, CartItem item);

    CartEntity getCart(String userId);

    void clearCart(String userId);

    CartEntity createCart();
}
