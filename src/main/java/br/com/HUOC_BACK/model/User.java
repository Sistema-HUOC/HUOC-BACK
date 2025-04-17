package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Entity
@Audited
@Table(name = "users")
public class User  implements UserDetails {
    public User(){

    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    public UUID getId() {
        return id;
    }
}
