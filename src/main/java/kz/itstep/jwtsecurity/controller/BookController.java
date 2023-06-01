package kz.itstep.jwtsecurity.controller;

import jakarta.annotation.security.PermitAll;
import kz.itstep.jwtsecurity.model.AuthorizationRequest;
import kz.itstep.jwtsecurity.model.AuthorizationResponse;
import kz.itstep.jwtsecurity.model.Book;
import kz.itstep.jwtsecurity.model.RegisterRequest;
import kz.itstep.jwtsecurity.service.AuthorizationService;
import kz.itstep.jwtsecurity.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService ;

    @PermitAll
    @GetMapping
    public ResponseEntity<List<Book>> register(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @PermitAll
    @GetMapping("/{detail}")
    public ResponseEntity<List<Object>> authorize(@PathVariable("detail") String detail){
        switch (detail){
            case "Name":
                return ResponseEntity.ok(bookService.getBooksDetail(Book::getName));
            case "Author":
                return  ResponseEntity.ok(bookService.getBooksDetail(Book::getAuthor));
            case "Time":
                return ResponseEntity.ok(bookService.getBooksDetail(Book::getCreationTime));
            case "Size":
                return  ResponseEntity.ok(bookService.getBooksDetail(Book::getSize));
            default:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }

    }

    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book newBook){
        try {
            bookService.createBook(newBook);
            return ResponseEntity.ok("Succeed");
        }
        catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    @PutMapping
    public ResponseEntity<String> updateBook(@RequestParam("id") Long id , @RequestBody Book newBook){
        try {
            bookService.updateBook(id, newBook);
            return ResponseEntity.ok("Succeed");
        }
        catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteBook( @RequestBody Book book){
        try {
            bookService.deleteBook(book);
            return ResponseEntity.ok("Succeed");
        }
        catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }




}
