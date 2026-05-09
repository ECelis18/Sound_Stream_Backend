-- ============================================================
--  SoundStream — Script de creación de base de datos MySQL
-- ============================================================

CREATE DATABASE IF NOT EXISTS soundstream
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE soundstream;

-- ------------------------------------------------------------
-- Tabla: Usuarios
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuarios (
    id             BIGINT          NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(255)    NOT NULL,
    nombre_usuario VARCHAR(255)    NOT NULL,
    correo         VARCHAR(255)    NOT NULL,
    contrasena     VARCHAR(255)    NOT NULL,
    avatar         VARCHAR(500)    DEFAULT NULL,
    rol            VARCHAR(50)     NOT NULL DEFAULT 'usuario',

    PRIMARY KEY (id),
    UNIQUE KEY uq_usuarios_correo         (correo),
    UNIQUE KEY uq_usuarios_nombre_usuario (nombre_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Tabla: listas_reproduccion (Playlists)
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS listas_reproduccion (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(255)    NOT NULL,
    descripcion VARCHAR(500)    DEFAULT NULL,
    portada     VARCHAR(500)    DEFAULT NULL,
    usuario_id  BIGINT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_playlist_usuario
        FOREIGN KEY (usuario_id) REFERENCES Usuarios (id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Tabla: playlist_canciones
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS playlist_canciones (
    id                BIGINT          NOT NULL AUTO_INCREMENT,
    playlist_id       BIGINT          NOT NULL,
    track_id          BIGINT          DEFAULT NULL,
    track_name        VARCHAR(255)    DEFAULT NULL,
    artist_name       VARCHAR(255)    DEFAULT NULL,
    artwork_url       VARCHAR(500)    DEFAULT NULL,
    preview_url       VARCHAR(500)    DEFAULT NULL,
    track_time_millis BIGINT          DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_cancion_playlist
        FOREIGN KEY (playlist_id) REFERENCES listas_reproduccion (id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Usuario admin de prueba (contraseña: admin123)
-- ------------------------------------------------------------
INSERT INTO Usuarios (nombre, nombre_usuario, correo, contrasena, rol)
VALUES ('Administrador', 'admin', 'admin@soundstream.com', 'admin123', 'admin');
