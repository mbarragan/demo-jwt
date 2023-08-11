package dev.quercusdata.demojwt.domain.ports.in.service;

import dev.quercusdata.demojwt.domain.model.Role;
import dev.quercusdata.demojwt.domain.model.User;
import dev.quercusdata.demojwt.domain.model.auth.request.AuthenticationRequest;
import dev.quercusdata.demojwt.domain.model.auth.request.RegisterRequest;
import dev.quercusdata.demojwt.domain.model.auth.response.AuthenticationResponse;
import dev.quercusdata.demojwt.domain.ports.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
            .firstname((request.getFirstname()))
            .lastname(request.getLastname())
            .email(request.getEmail())
            .pass(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var user = repository.findByEmail(request.getEmail())
            .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
