CREATE TABLE IF NOT EXISTS topicos(
    id BIGINT AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje  VARCHAR(350) NOT NULL UNIQUE,
    fecha_creacion DATE NOT NULL,
    estado VARCHAR(30) NOT NULL,
    id_autor BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_autor) REFERENCES usuarios(id),
    FOREIGN KEY(id_curso) REFERENCES cursos(id)
);