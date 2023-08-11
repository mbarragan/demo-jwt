package dev.quercusdata.demojwt.infraestructure.adapter.in.controller.auth;

import dev.quercusdata.demojwt.domain.model.auth.request.AuthenticationRequest;
import dev.quercusdata.demojwt.domain.model.auth.response.AuthenticationResponse;
import dev.quercusdata.demojwt.domain.model.auth.request.RegisterRequest;
import dev.quercusdata.demojwt.domain.ports.in.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Insert a user in t_user.
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Authenticate a previously registered user passed as request.
     *
     * @param request
     * @return a valid token
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
