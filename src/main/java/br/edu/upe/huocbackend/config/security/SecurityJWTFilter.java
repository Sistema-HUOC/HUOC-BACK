package br.edu.upe.huocbackend.config.security;

import br.edu.upe.huocbackend.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityJWTFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final IUserRepository userRepository;

    public SecurityJWTFilter(TokenService tokenService, IUserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(Objects.nonNull(cookies))
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JWT")){
                    String email = tokenService.validateToken(cookie.getValue());
                    if(email != null) {
                        UserDetails user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));

                        var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
        filterChain.doFilter(request,response);
    }

}
