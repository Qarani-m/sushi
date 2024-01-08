package shushi.payments.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.payments.entity.PaymentEntity;

import java.util.List;


@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
    List<PaymentEntity> findByUserId(String userId);
}
