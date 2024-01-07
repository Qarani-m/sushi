package shushi.payments.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.payments.entity.PaymentEntity;


@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
}
