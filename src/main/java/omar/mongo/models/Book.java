package omar.mongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "books")
public class Book {
    
    @Id
    public String id;
    public final String name;
    public final String price;
}
