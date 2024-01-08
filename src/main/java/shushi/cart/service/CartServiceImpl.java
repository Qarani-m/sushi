package shushi.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.repository.UserRepository;
import shushi.cart.dto.NewCartDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.repository.CartRepository;
import shushi.item.entity.ItemEntity;
import shushi.item.repository.ItemRepository;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;



    @Override
    public Map<String, Object> addItemToCart(String userId, String itemId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(itemId, "ItemId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();

        Optional<CartItem> existingCartItem = existingItems.stream()
                .filter(item -> item.getSushi().getId().equals(itemId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            increaseQuantity(userId, itemId);
        } else {
            ItemEntity sushiItem = itemRepository.findById(itemId).orElseThrow();
            CartItem cartItem = CartItem.builder()
                    .sushi(sushiItem)
                    .quantity(1)
                    .build();
            existingItems.add(cartItem);
            cartEntity.setItems(existingItems);
            cartRepository.save(cartEntity);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("cartEntity", cartEntity);
        response.put("message", "success");

        return response;
    }

    @Override
    public Map<String, Object> removeItemFromCart(String userId, String itemId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(itemId, "ItemId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();

        existingItems.removeIf(cartItem -> cartItem.getSushi().getId().equals(itemId));

        cartEntity.setItems(existingItems);
        cartRepository.save(cartEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("cartEntity", cartEntity);
        response.put("message", "success");

        return response;
    }








    @Override
    public Map<String, Object> getCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);

        Map<String, Object> response = new HashMap<>();

        if (cartEntity != null && !cartEntity.getItems().isEmpty()) {
            response.put("cart", cartEntity);
            response.put("message", "success");
        } else {
            response.put("cart", null);
            response.put("message", "Cart is empty or not found.");
        }

        return response;
    }

    @Override
    public Map<String, Object> clearCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");

        CartEntity cart = cartRepository.findByUserId(userId);

        Map<String, Object> response = new HashMap<>();

        if (cart != null) {
            cart.setItems(new ArrayList<>()); // Clear the items in the cart
            cartRepository.save(cart);
            response.put("message", "success");
        } else {
            response.put("message", "Cart not found.");
        }

        return response;
    }

    @Override
    public Map<String, Object> createCart(NewCartDto newCartDto) {
        CartEntity cart = CartEntity.builder()
                .items(new ArrayList<>())
                .userId(newCartDto.getUserId())
                .build();

        cartRepository.save(cart);

        Map<String, Object> response = new HashMap<>();
        response.put("cart", cart);
        response.put("message", "success");

        return response;
    }



    @Override
    public Map<String, Object> getNumberOfItemsInCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("numberOfItems", cartEntity != null ? cartEntity.getItems().size() : 0);
        response.put("message", "success");

        return response;
    }


    public Map<String, Object> increaseQuantity(String userId, String itemId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();

        Optional<CartItem> itemToIncrease = existingItems.stream()
                .filter(cartItem -> cartItem.getSushi().getId().equals(itemId))
                .findFirst();

        itemToIncrease.ifPresent(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + 1));

        cartEntity.setItems(existingItems);
        cartRepository.save(cartEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("cart", cartEntity);
        response.put("message", "success");

        return response;
    }


    public Map<String, Object> reduceQuantity(String userId, String itemId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(itemId, "ItemId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();

        Optional<CartItem> itemToReduce = existingItems.stream()
                .filter(cartItem -> cartItem.getSushi().getId().equals(itemId))
                .findFirst();

        itemToReduce.ifPresent(cartItem -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                existingItems.remove(cartItem);
            }
        });

        cartEntity.setItems(existingItems);
        cartRepository.save(cartEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("cart", cartEntity);
        response.put("message", "success");

        return response;
    }

    }
