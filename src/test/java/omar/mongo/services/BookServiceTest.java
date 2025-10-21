package omar.mongo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import omar.mongo.models.Book;
import omar.mongo.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @ParameterizedTest
    @CsvSource({
        "book1 , 150.50",
        "book2 , 23.596",
        "book3 , 44.20",
    })
    void shouldSaveBookWhenValid(String name, double price) {
        var newBook = new Book(name, price);
        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        var resultBook = bookService.addBook(newBook);

        assertThat(resultBook.getName()).isEqualTo(name);
        assertThat(resultBook.getPrice()).isEqualTo(price);

        // verify(bookRepository).save(argThat(book -> book.getName() == name &&
        // book.getPrice() == price));

        verify(bookRepository).save(bookCaptor.capture());

        assertThat(bookCaptor.getValue().getName()).isEqualTo(name);
        assertThat(bookCaptor.getValue().getPrice()).isEqualTo(price);
    }

    @Test
    void shouldThrowWhenPriceSmallerThanZero() {
        var newBook = new Book("book2", 0);

        assertThatThrownBy(() -> bookService.addBook(newBook))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than zero");

    }

    @Test
    void shouldHandleNullPassedData() {
        assertThatThrownBy(() -> bookService.addBook(null))
                .isInstanceOf(NullPointerException.class);
    }

}
