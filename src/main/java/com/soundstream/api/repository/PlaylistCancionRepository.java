package com.soundstream.api.repository;

import com.soundstream.api.model.PlaylistCancion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistCancionRepository extends JpaRepository<PlaylistCancion, Long> {
    List<PlaylistCancion> findByPlaylistId(Long playlistId);

    @Transactional
    void deleteByPlaylistIdAndTrackId(Long playlistId, Long trackId);

    boolean existsByPlaylistIdAndTrackId(Long playlistId, Long trackId);
}