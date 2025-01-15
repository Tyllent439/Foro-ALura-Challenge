CREATE TABLE IF NOT EXISTS respuestas(
    id BIGINT AUTO_INCREMENT,
    mensaje VARCHAR(350) NOT NULL,
    id_topico  BIGINT NOT NULL,
    fecha_creacion DATE NOT NULL,
    id_autor BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_topico) REFERENCES topicos(id),
    FOREIGN KEY(id_autor) REFERENCES usuarios(id)
);