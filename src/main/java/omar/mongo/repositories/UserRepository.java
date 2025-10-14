package omar.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import omar.mongo.models.User;


@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
}
