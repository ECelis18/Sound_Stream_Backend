package com.soundstream.api.service;

import com.soundstream.api.model.Usuario;
import com.soundstream.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario editarUsuario(Long id, Usuario datos) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

        // Actualizar campos obligatorios
        usuario.setNombre(datos.getNombre());
        usuario.setNombreUsuario(datos.getNombreUsuario());
        usuario.setCorreo(datos.getCorreo());
        usuario.setAvatar(datos.getAvatar());
        usuario.setRol(datos.getRol());
        // Actualizar contraseña solo si se envió un valor no vacío
        String nuevaPassword = datos.getContrasena();
        if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
            usuario.setContrasena(nuevaPassword);
        }
        // Si no se envió, conservar la existente
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            // Capturar violación de unicidad (nombreUsuario o correo duplicado)
            throw new RuntimeException("El nombre de usuario o correo ya está en uso", e);
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}