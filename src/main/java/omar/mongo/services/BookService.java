package omar.mongo.services;

import org.springframework.beans.factory.annotation.Autowired;

import omar.mongo.models.Book;
import omar.mongo.repositories.BookRepository;

public class BookService {
    
    @Autowired
    private BookRepository bookRepository; 
    

    public Book addBook(Book book){
        if(book.getPrice() <= 0)
            throw new IllegalArgumentException("Price must be greater than zero");
        
        var newBook = new Book(book.getName(), book.getPrice());
        return bookRepository.save(newBook);
    }
}
