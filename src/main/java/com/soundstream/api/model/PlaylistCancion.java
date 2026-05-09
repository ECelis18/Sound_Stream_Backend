package com.soundstream.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "playlist_canciones")
public class PlaylistCancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    // Datos de iTunes
    private Long trackId;
    private String trackName;
    private String artistName;
    private String artworkUrl;
    private String previewUrl;
    private Long trackTimeMillis;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Playlist getPlaylist() { return playlist; }
    public void setPlaylist(Playlist playlist) { this.playlist = playlist; }

    public Long getTrackId() { return trackId; }
    public void setTrackId(Long trackId) { this.trackId = trackId; }

    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getArtworkUrl() { return artworkUrl; }
    public void setArtworkUrl(String artworkUrl) { this.artworkUrl = artworkUrl; }

    public String getPreviewUrl() { return previewUrl; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }

    public Long getTrackTimeMillis() { return trackTimeMillis; }
    public void setTrackTimeMillis(Long trackTimeMillis) { this.trackTimeMillis = trackTimeMillis; }
}