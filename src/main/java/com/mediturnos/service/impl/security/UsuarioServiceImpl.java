package com.mediturnos.service.impl.security;

import com.mediturnos.dto.security.request.UsuarioRequest;
import com.mediturnos.dto.security.response.UsuarioResponse;
import com.mediturnos.entity.security.Rol;
import com.mediturnos.entity.security.Usuario;
import com.mediturnos.exception.BusinessException;
import com.mediturnos.exception.ResourceNotFoundException;
import com.mediturnos.mapper.security.UsuarioMapper;
import com.mediturnos.repository.security.RolRepository;
import com.mediturnos.repository.security.UsuarioRepository;
import com.mediturnos.service.interfaces.security.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse crear(UsuarioRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("El nombre de usuario ya existe.");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("El correo electrónico ya existe.");
        }

        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(guardado);
    }

    @Override
    public UsuarioResponse actualizar(Long id, UsuarioRequest request) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));

        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        if (!usuario.getUsername().equals(request.getUsername())
            && usuarioRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("El nombre de usuario ya existe.");
        }

        if (!usuario.getEmail().equals(request.getEmail())
            && usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("El correo electrónico ya existe.");
        }

        usuarioMapper.updateEntity(request, usuario);
        usuario.setRol(rol);

        Usuario actualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(actualizado); 
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));

        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> obtenerTodos() {

        return usuarioRepository.findByActivoTrue()
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));

        usuario.setActivo(false);

        usuarioRepository.save(usuario);
    }
}