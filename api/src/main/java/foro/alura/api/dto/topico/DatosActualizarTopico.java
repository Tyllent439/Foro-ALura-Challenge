package foro.alura.api.dto.topico;

import foro.alura.api.modelo.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        Long idAutor,
        Long idCurso
) {
}
