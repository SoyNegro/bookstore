package dev.coffeecult.bookstore.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.coffeecult.bookstore.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BaseUserDetails implements UserDetails {
    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private boolean isAccountExpired = false;

    private boolean isCredentialsExpired = false;

    private boolean isAccountLocked = false;

    private boolean isEnabled = true;

    public BaseUserDetails(String id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static BaseUserDetails build(User user){
        List<GrantedAuthority> authorityList = user.roles().stream().map(
                role -> new SimpleGrantedAuthority(role.name().name())
        ).collect(Collectors.toList());

        return new BaseUserDetails(
                user.id(),
                user.username(),
                user.email(),
                user.password(),
                authorityList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        isAccountExpired = accountExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        isCredentialsExpired = credentialsExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
