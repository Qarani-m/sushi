package shushi.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.entity.UserProfile;
import shushi.auth.repository.UserRepository;
import shushi.cart.dto.NewCartDto;
import shushi.cart.dto.NewItemDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.repository.CartRepository;
import shushi.exceptions.UserNotFoundException;
import shushi.sushi.entity.SushiEntity;
import shushi.sushi.repository.SushiRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SushiRepository sushiRepository;

    @Override
    public CartEntity addItemToCart(String userId, String itemId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(itemId, "CartItem cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();

        Optional<CartItem> existingCartItem = existingItems.stream()
                .filter(item -> item.getSushi().getId().equals(itemId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            increaseQuantity(userId, itemId);
        } else {
            SushiEntity sushiItem = sushiRepository.findById(itemId).orElseThrow();
            CartItem cartItem = CartItem.builder()
                    .sushi(sushiItem)
                    .quantity(1)
                    .build();
            existingItems.add(cartItem);
            cartEntity.setItems(existingItems);
            cartRepository.save(cartEntity);
        }

        return cartEntity;
    }



    @Override
    public CartEntity removeItemFromCart(String userId, String itemId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        Objects.requireNonNull(itemId, "ItemId cannot be null");

        CartEntity cartEntity = cartRepository.findByUserId(userId);
        List<CartItem> existingItems = cartEntity.getItems();
        Optional<CartItem> itemToRemove = existingItems.stream()
                .filter(cartItem -> cartItem.getSushi().getId().equals(itemId))
                .findFirst();
        itemToRemove.ifPresent(existingItems::remove);

        cartEntity.setItems(existingItems);

        return cartRepository.save(cartEntity);
    }



    @Override
    public CartEntity getCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");


        return cartRepository.findByUserId(userId);
    }
    @Override
    public void clearCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");



        CartEntity cart = cartRepository.findByUserId(userId);

        if (cart != null) {
            cart.setItems(new ArrayList<>()); // Clear the items in the cart
            cartRepository.save(cart);
        }
    }
    @Override
    public CartEntity createCart(NewCartDto newCartDto) {
        CartEntity cart = CartEntity.builder()
                .items(new ArrayList<>())
                .userId(newCartDto.getUserId())
                .build();
        return cartRepository.save(cart);
    }


    private Optional<UserProfile> findUser(String userId){
        Objects.requireNonNull(userId, "User ID cannot be null");
        return userRepository.findById(userId);
    }
    @Override
    public int getNumberOfItemsInCart(String userId) {
        Objects.requireNonNull(userId, "UserId cannot be null");
        CartEntity cartEntity = cartRepository.findByUserId(userId);

        if (cartEntity != null) {
            List<CartItem> items = cartEntity.getItems();
            return items.size();
        } else {
            return 0;
        }
    }


        public CartEntity increaseQuantity(String userId, String itemId) {
            CartEntity cartEntity = cartRepository.findByUserId(userId);
            List<CartItem> existingItems = cartEntity.getItems();

            Optional<CartItem> itemToIncrease = existingItems.stream()
                    .filter(cartItem -> cartItem.getSushi().getId().equals(itemId))
                    .findFirst();

            System.out.println(itemToIncrease);




            itemToIncrease.ifPresent(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + 1));

            cartEntity.setItems(existingItems);

            return cartRepository.save(cartEntity);
        }

        public CartEntity reduceQuantity(String userId, String itemId) {
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
            return cartRepository.save(cartEntity);
        }
    }
