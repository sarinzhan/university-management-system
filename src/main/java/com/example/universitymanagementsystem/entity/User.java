package com.example.universitymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name = "users")
@Data
public class User extends BaseEntity implements UserDetails{

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    private PersonData personData;

    @Column(name = "isactive")
    private Boolean isActive;

    private String password;
    private String login;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
//    @Override
//    public String getPassword(){
//        return password;
//    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
