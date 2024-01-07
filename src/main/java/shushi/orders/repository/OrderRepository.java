package shushi.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.cart.entity.CartEntity;
import shushi.orders.entity.OrderEntity;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(String userId);

}
