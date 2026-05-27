package com.soundstream.api.controller;


import com.soundstream.api.model.Playlist;
import com.soundstream.api.model.PlaylistCancion;
import com.soundstream.api.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@CrossOrigin(origins = "http://localhost:5173")
public class PlaylistController {

    @Autowired
    private PlaylistService  playlistService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Playlist>> listar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(playlistService.listar(usuarioId));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Playlist playlist, @RequestParam Long usuarioId) {
        Playlist nueva = playlistService.crear(playlist, usuarioId);
        if (nueva != null) {
            return ResponseEntity.ok(nueva);
        }
        return ResponseEntity.status(404).body("Usuario no encontrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Playlist datos) {
        Playlist actualizada = playlistService.editar(id, datos);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.status(404).body("Playlist no encontrada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (playlistService.eliminar(id)){
            return ResponseEntity.ok("Playlist Eliminada");
        }
        return ResponseEntity.status(404).body("Playlist no encontrada");
    }

    //EndPoints para canciones
    @PostMapping("/{playlistId}/canciones")
    public ResponseEntity<?> agregarCancion(@PathVariable Long playlistId, @RequestBody PlaylistCancion cancion) {
        PlaylistCancion nueva = playlistService.agregarCancion(playlistId, cancion);
        if (nueva != null) {
            return ResponseEntity.ok(nueva);
        }
        return ResponseEntity.status(400).body("La canción ya existe en la playlist o playlist no encontrada");
    }

    @GetMapping("/{playlistId}/canciones")
    public ResponseEntity<List<PlaylistCancion>> listarCanciones(@PathVariable Long playlistId) {
        return ResponseEntity.ok(playlistService.listarCanciones(playlistId));
    }

    @DeleteMapping("/{playlistId}/canciones/{trackId}")
    public ResponseEntity<?> eliminarCancion(@PathVariable Long playlistId, @PathVariable Long trackId) {
        playlistService.eliminarCancion(playlistId, trackId);
        return ResponseEntity.ok("Canción eliminada de la playlist");
    }

}
