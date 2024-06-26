package com.eslam.jwtsecurity.services;

import com.eslam.jwtsecurity.Repository.UserRepository;
import com.eslam.jwtsecurity.user.Role;
import com.eslam.jwtsecurity.user.User;
import com.eslam.jwtsecurity.utils.AuthenticationResponse;
import com.eslam.jwtsecurity.utils.LoginRequest;
import com.eslam.jwtsecurity.utils.RegisterRequest;
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
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        repository.save(user);
        String jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jtwToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jtwToken).build();
    }
}
