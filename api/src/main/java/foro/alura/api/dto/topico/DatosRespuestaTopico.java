package foro.alura.api.dto.topico;

import foro.alura.api.modelo.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        String estado) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),topico.getFechaCreacion(),
                topico.getEstado().toString());
    }
}
