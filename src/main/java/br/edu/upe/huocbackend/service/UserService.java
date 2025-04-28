package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.login.UserDtoReponse;
import br.edu.upe.huocbackend.model.User;
import br.edu.upe.huocbackend.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Usuário ou senha inválidos"));
    }

    public UserDtoReponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuário ou senha inválidos"));
        UserDtoReponse userDto = new UserDtoReponse();
        userDto.userId=user.getId();
        userDto.username = user.getUsername();
        userDto.accessLevel = user.getAcessLevel();
        return userDto;
    }
}
