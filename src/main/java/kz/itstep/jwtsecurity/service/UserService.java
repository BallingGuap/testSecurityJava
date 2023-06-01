package kz.itstep.jwtsecurity.service;

import kz.itstep.jwtsecurity.model.Book;
import kz.itstep.jwtsecurity.model.User;
import kz.itstep.jwtsecurity.repository.BookRepositroy;
import kz.itstep.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAdmins(){
        List<User> admins = userRepository.findByAuthoritiesContaining("ROLE_ADMIN");
        if (admins.isEmpty()) {
            throw new RuntimeException("No Administrators");
        }
        return admins;
    }
    public void deleteUser(User user){
             userRepository.delete(user);
    }




}