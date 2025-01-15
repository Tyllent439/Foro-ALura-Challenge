package foro.alura.api.dto.curso;

import foro.alura.api.modelo.Curso;

public record DatosRespuestaCurso(
        String nombre,
        String categoria
) {
    public DatosRespuestaCurso(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
