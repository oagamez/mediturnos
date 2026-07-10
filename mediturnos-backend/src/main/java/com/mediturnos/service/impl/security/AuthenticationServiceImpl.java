package com.mediturnos.service.impl.security;

import com.mediturnos.dto.security.request.LoginRequest;
import com.mediturnos.dto.security.response.LoginResponse;
import com.mediturnos.entity.security.Usuario;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.repository.security.UsuarioRepository;
import com.mediturnos.security.JwtService;
import com.mediturnos.service.interfaces.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new BusinessException("Usuario o contraseña incorrectos."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {

            throw new BusinessException(
                    "Usuario o contraseña incorrectos.");
        }

        String token = jwtService.generateToken(usuario.getUsername());

        return new LoginResponse(
                token,
                usuario.getUsername(),
                usuario.getRol().getNombre());

    }

}