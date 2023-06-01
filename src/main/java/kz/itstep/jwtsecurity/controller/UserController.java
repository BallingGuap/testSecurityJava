package kz.itstep.jwtsecurity.controller;

import kz.itstep.jwtsecurity.model.Book;
import kz.itstep.jwtsecurity.model.User;
import kz.itstep.jwtsecurity.service.BookService;
import kz.itstep.jwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService ;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAdmins(){
        return ResponseEntity.ok(userService.getAdmins());
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser( @RequestBody User user){
        try {
            userService.deleteUser(user);
            return ResponseEntity.ok("Succeed");
        }
        catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }




}
