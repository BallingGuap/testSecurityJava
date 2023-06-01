package kz.itstep.jwtsecurity.repository;

import kz.itstep.jwtsecurity.model.Book;
import kz.itstep.jwtsecurity.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepositroy extends JpaRepository<Book, Long> {

}
