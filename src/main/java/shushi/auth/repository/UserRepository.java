package shushi.auth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.auth.entity.UserProfile;

@Repository
public interface UserRepository  extends MongoRepository<UserProfile, String> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String email);

    UserProfile findByUsername(String userName);
    UserProfile findByEmail(String userName);
}

