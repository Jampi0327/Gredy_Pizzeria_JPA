package com.Practica.JPA.web.controler;

import com.Practica.JPA.service.dto.LoginDTO;
import com.Practica.JPA.web.config.JwtUtil; // 1. Import agregado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthControler {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil; // 2. Tipo con 'J' mayúscula

    @Autowired
    // 3. Inyección de JwtUtil en los parámetros del constructor
    public AuthControler(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPasswork()
        );

        Authentication authentication = this.authenticationManager.authenticate(login);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDTO.getUsername());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .build();
    }
}