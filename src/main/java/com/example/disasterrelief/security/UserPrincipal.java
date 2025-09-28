package com.example.disasterrelief.security;

import com.example.disasterrelief.model.Admin;
import com.example.disasterrelief.model.Volunteer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String id, String firstName, String lastName, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Volunteer volunteer) {
        String role = volunteer.getRole() != null ? volunteer.getRole() : "VOLUNTEER";
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
        return new UserPrincipal(
                volunteer.getId(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getEmail(), // username
                volunteer.getEmail(), // email
                volunteer.getPassword(),
                authorities
        );
    }

    public static UserPrincipal build(Volunteer volunteer, String role) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
        return new UserPrincipal(
            volunteer.getId(),
            volunteer.getFirstName(),
            volunteer.getLastName(),
            volunteer.getEmail(), // username
            volunteer.getEmail(), // email
            volunteer.getPassword(),
            authorities
        );
    }

    public static UserPrincipal build(Admin admin, String role) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
        return new UserPrincipal(
            admin.getId(),
            admin.getFirstName(),
            admin.getLastName(),
            admin.getEmail(), // username
            admin.getEmail(), // email
            admin.getPassword(),
            authorities
        );
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
