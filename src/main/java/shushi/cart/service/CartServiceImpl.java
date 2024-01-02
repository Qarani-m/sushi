package shushi.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.entity.UserProfile;
import shushi.auth.repository.UserRepository;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.repository.CartRepository;
import shushi.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartEntity addItemToCart(String userId, CartItem item) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(item, "CartItem cannot be null");
        Optional<UserProfile> userOptional = findUser(userId);
        UserProfile user = userOptional.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        CartEntity cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new CartEntity();
//            cart.setUser(user);
        }
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }




    @Override
    public CartEntity removeItemFromCart(String userId, CartItem item) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(item, "CartItem cannot be null");
        UserProfile user = findUser(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        CartEntity cart = cartRepository.findByUser(user);
        if (cart != null && cart.getItems() != null) {
            cart.getItems().remove(item);
            return cartRepository.save(cart);
        }
        return cart;
    }


    @Override
    public CartEntity getCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");

        UserProfile user = findUser(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return cartRepository.findByUser(user);
    }
    @Override
    public void clearCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");

        UserProfile user = findUser(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        CartEntity cart = cartRepository.findByUser(user);

        if (cart != null) {
            cart.setItems(new ArrayList<>()); // Clear the items in the cart
            cartRepository.save(cart);
        }
    }

    public CartEntity createCart() {
        CartEntity cart = new CartEntity();
        return cartRepository.save(cart);
    }


    private Optional<UserProfile> findUser(String userId){
        Objects.requireNonNull(userId, "User ID cannot be null");
        return userRepository.findById(userId);
    }
}

