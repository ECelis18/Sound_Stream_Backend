package com.soundstream.api.service;

import com.soundstream.api.model.Playlist;
import com.soundstream.api.model.PlaylistCancion;
import com.soundstream.api.model.Usuario;
import com.soundstream.api.repository.PlaylistCancionRepository;
import com.soundstream.api.repository.PlaylistRepository;
import com.soundstream.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepo;

    @Autowired
    private UsuarioRepository  usuarioRepo;

    public Playlist crear(Playlist playlist, Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
        if (usuario == null) return null;
        playlist.setUsuario(usuario);
        return playlistRepo.save(playlist);
    }

    public List<Playlist> listar(Long usuarioId) {
        return playlistRepo.findByUsuarioId(usuarioId);
    }

    public Playlist editar(Long id, Playlist datos) {
        Playlist playlist = playlistRepo.findById(id).orElse(null);
        if (playlist == null) return null;
        if (datos.getNombre() != null) playlist.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) playlist.setDescripcion(datos.getDescripcion());
        if (datos.getPortada() != null) playlist.setPortada(datos.getPortada());
        return playlistRepo.save(playlist);
    }

    public boolean eliminar(Long id){
        if(playlistRepo.existsById(id)){
            playlistRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Esta seccion es para canciones
    @Autowired
    private PlaylistCancionRepository playlistCancionRepo;

    public PlaylistCancion agregarCancion(Long playlistId, PlaylistCancion cancion) {
        Playlist playlist = playlistRepo.findById(playlistId).orElse(null);
        if (playlist == null) return null;
        if (playlistCancionRepo.existsByPlaylistIdAndTrackId(playlistId, cancion.getTrackId())) {
            return null; // ya existe
        }
        cancion.setPlaylist(playlist);
        return playlistCancionRepo.save(cancion);
    }

    public List<PlaylistCancion> listarCanciones(Long playlistId) {
        return playlistCancionRepo.findByPlaylistId(playlistId);
    }

    public void eliminarCancion(Long playlistId, Long trackId) {
        playlistCancionRepo.deleteByPlaylistIdAndTrackId(playlistId, trackId);
    }
}
