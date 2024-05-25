package com.grace.jwt_authentication.authentication;

import com.grace.jwt_authentication.config.JwtService;
import com.grace.jwt_authentication.user.User;
import com.grace.jwt_authentication.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken  authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }
}
