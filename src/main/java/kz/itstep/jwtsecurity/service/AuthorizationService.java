package kz.itstep.jwtsecurity.service;

import kz.itstep.jwtsecurity.model.AuthorizationRequest;
import kz.itstep.jwtsecurity.model.AuthorizationResponse;
import kz.itstep.jwtsecurity.model.RegisterRequest;
import kz.itstep.jwtsecurity.model.User;
import kz.itstep.jwtsecurity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthorizationResponse register(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setAuthorities("USER");

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return new AuthorizationResponse(jwt);
    }

    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getLogin(), authorizationRequest.getPassword()));
        User user = userRepository.findUserByEmail(authorizationRequest.getLogin()).orElseThrow(()-> new RuntimeException("smth went wrong"));

        String jwt = jwtService.generateToken(user);
        return new AuthorizationResponse(jwt);
    }
}
