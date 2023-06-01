package kz.itstep.jwtsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/path")
public class TestController {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public String get(){
        return "you are authenticated";
    }
}
