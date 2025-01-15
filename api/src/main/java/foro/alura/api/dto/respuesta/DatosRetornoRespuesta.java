package foro.alura.api.dto.respuesta;

import foro.alura.api.modelo.Respuesta;

public record DatosRetornoRespuesta(
        String mensaje,
        String topico,
        String autor) {

    public DatosRetornoRespuesta(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
