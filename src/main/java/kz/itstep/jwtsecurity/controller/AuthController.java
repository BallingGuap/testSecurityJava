package kz.itstep.jwtsecurity.controller;

import kz.itstep.jwtsecurity.model.AuthorizationRequest;
import kz.itstep.jwtsecurity.model.RegisterRequest;
import kz.itstep.jwtsecurity.model.AuthorizationResponse;
import kz.itstep.jwtsecurity.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthorizationService authorizationService;

    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthorizationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authorizationService.register(registerRequest));
    }
    @PostMapping("/authorize")
    public ResponseEntity<AuthorizationResponse> authorize(@RequestBody AuthorizationRequest authorizationRequest){
        return ResponseEntity.ok(authorizationService.authorize(authorizationRequest));
    }
}
