package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.login.UserDtoReponse;
import br.edu.upe.huocbackend.controller.dto.login.UserLoginDTO;
import br.edu.upe.huocbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    public UserController(TokenService tokenService, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(
            summary = "Autenticação do usuário",
            description = "Autentica um usuário com e-mail e senha, retorna informações do usuário com Cookie"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Autenticação bem-sucedida",
            content = @Content(schema = @Schema(implementation = UserLoginDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Credenciais inválidas",
            content = @Content
    )
    @SecurityRequirements
    @PostMapping("/login")
    public ResponseEntity<UserDtoReponse> login(@Valid @RequestBody UserLoginDTO dto, HttpServletResponse response){
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(dto.email,dto.password);
            auth = authenticationManager.authenticate(auth);
            if (!auth.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDtoReponse());
            }
            Cookie cookie = new Cookie("JWT",tokenService.generateToken(dto.email));
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            UserDtoReponse userDtoReponse =userService.findByEmail(dto.email);
            return ResponseEntity.ok(userDtoReponse);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
