package kz.itstep.jwtsecurity.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id;
    private String name;
    private String author;
    private int size;
    private Date creationTime;



    public void copyBook(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.size = book.getSize();
        this.creationTime = book.getCreationTime();
    }


}
