package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Audited
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User  implements UserDetails {

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String nome,String cpf, String email, String password,AcessLevel level) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.acessLevel = level;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false,unique = true)
    private String cpf;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "acess_level")
    @Enumerated(EnumType.STRING)
    private AcessLevel acessLevel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.acessLevel) {
            case ADMINISTRATOR:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_MEDICO"),
                        new SimpleGrantedAuthority("ROLE_ENFERMAGEM"),
                        new SimpleGrantedAuthority("ROLE_PESQUISADOR")
                );
            case MEDICO:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_MEDICO")
                );
            case ENFERMAGEM:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ENFERMAGEM")
                );
            case PESQUISADOR:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_PESQUISADOR")
                );
            default:
                return List.of();
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
