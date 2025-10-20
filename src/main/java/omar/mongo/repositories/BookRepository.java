package omar.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import omar.mongo.models.Book;

public interface BookRepository extends MongoRepository<Book , String> {
    
}
