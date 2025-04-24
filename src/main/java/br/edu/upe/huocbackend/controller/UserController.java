package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.login.UserLoginDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "login")
public class UserController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid UserLoginDTO dto, HttpServletResponse response){
        Authentication auth = new UsernamePasswordAuthenticationToken(dto.email,dto.password);
        auth = authenticationManager.authenticate(auth);

        Cookie cookie = new Cookie("JWT",tokenService.generateToken(dto.email));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
    }
}
