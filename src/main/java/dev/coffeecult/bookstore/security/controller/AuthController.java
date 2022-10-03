package dev.coffeecult.bookstore.security.controller;

import dev.coffeecult.bookstore.security.jwt.JwtUtils;
import dev.coffeecult.bookstore.security.model.BaseRoles;
import dev.coffeecult.bookstore.security.model.Role;
import dev.coffeecult.bookstore.security.model.User;
import dev.coffeecult.bookstore.security.payload.request.LoginRequest;
import dev.coffeecult.bookstore.security.payload.request.SignUpRequest;
import dev.coffeecult.bookstore.security.repository.UserRepository;
import dev.coffeecult.bookstore.security.service.BaseUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            return new ResponseEntity<>("Error: Email is already in use", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(signUpRequest.username())) {
            return new ResponseEntity<>("Error: Username is already taken", HttpStatus.BAD_REQUEST);
        }
        var roles = new HashSet<Role>();
        if (signUpRequest.roles() == null) {
            roles.add(new Role(BaseRoles.ROLE_READER));
        } else {
            signUpRequest.roles().forEach(r -> roles.add(new Role(BaseRoles.valueOf(r))));
        }
        var user = new User(signUpRequest.username(), signUpRequest.password(), signUpRequest.email(),roles);
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwt = jwtUtils.generateJWT(authentication);
       return new ResponseEntity<>(jwt, HttpStatus.OK);

    }
}
