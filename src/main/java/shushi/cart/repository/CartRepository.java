package shushi.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.auth.entity.UserProfile;
import shushi.cart.entity.CartEntity;


@Repository
public interface CartRepository extends MongoRepository<CartEntity, String> {

    CartEntity findByUserId(String userId);

}
