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
public class User extends BaseEntity implements UserDetails {
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    private PersonData personData;

//    @Column(name = "is_active")
//    private Boolean isActive;

    private String password;
    private String login;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.role);
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
