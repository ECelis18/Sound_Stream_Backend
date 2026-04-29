package com.soundstream.api.service;

import com.soundstream.api.model.Usuario;
import com.soundstream.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (usuario != null) {
            usuario.setNombre(datos.getNombre());
            usuario.setNombreUsuario(datos.getNombreUsuario());
            usuario.setCorreo(datos.getCorreo());
            usuario.setContrasena(datos.getContrasena());
            usuario.setAvatar(datos.getAvatar());
            return usuarioRepository.save(usuario);
        }
        return null;
    }
}
