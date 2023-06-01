package kz.itstep.jwtsecurity;

import kz.itstep.jwtsecurity.model.User;
import kz.itstep.jwtsecurity.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            userRepository.save(new User("dias", "ospanov", "dias", passwordEncoder.encode("dias"), "USER"));
        };
    }

}
