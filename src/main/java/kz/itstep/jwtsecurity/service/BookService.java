package kz.itstep.jwtsecurity.service;

import kz.itstep.jwtsecurity.model.Book;
import kz.itstep.jwtsecurity.repository.BookRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositroy bookRepository;


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public <T>List<T> getBooksDetail(Function<Book, T> detail){
        return bookRepository.findAll()
                .stream()
                .map(b -> detail.apply(b))
                .toList();
    }

    public void createBook(Book book){
        bookRepository.save(book);
    }
    public void updateBook(Long bookId, Book newBook){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("No Such Book"));
        book.copyBook(newBook);
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

}
