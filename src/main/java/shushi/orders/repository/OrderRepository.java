package shushi.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.orders.entity.OrderEntity;


@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
