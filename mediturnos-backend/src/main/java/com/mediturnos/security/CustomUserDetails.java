package com.mediturnos.security;

import com.mediturnos.entity.security.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre())
        );

    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return usuario.getActivo();
    }

    @Override
    public boolean isAccountNonLocked() {
        return usuario.getActivo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return usuario.getActivo();
    }

    @Override
    public boolean isEnabled() {
        return usuario.getActivo();
    }

    public Usuario getUsuario() {
        return usuario;
    }

}