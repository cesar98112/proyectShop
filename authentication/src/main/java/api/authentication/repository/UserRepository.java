package api.authentication.repository;

import api.authentication.config.security.user.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    public Optional<UserModel> findUserModelByUsername(String username);
}
