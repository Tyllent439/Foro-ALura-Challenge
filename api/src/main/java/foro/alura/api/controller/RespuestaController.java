package foro.alura.api.controller;

import foro.alura.api.dto.respuesta.DatosRegistroRespuesta;
import foro.alura.api.dto.respuesta.DatosRetornoRespuesta;
import foro.alura.api.dto.topico.DatosRegistroTopico;
import foro.alura.api.dto.usuario.DatosListadoUsuario;
import foro.alura.api.dto.usuario.DatosRegistroUsuario;
import foro.alura.api.modelo.Curso;
import foro.alura.api.modelo.Respuesta;
import foro.alura.api.modelo.Topico;
import foro.alura.api.modelo.Usuario;
import foro.alura.api.repository.CursoRepository;
import foro.alura.api.repository.RespuestaRepository;
import foro.alura.api.repository.TopicoRepository;
import foro.alura.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRetornoRespuesta> registrarRespuesta(@RequestBody DatosRegistroRespuesta datosRegistroRespuesta,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.getReferenceById(datosRegistroRespuesta.idTopico());
        Usuario autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.idAutor());
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta,topico,autor));
        topico.agregarRespuesta(respuesta);
        DatosRetornoRespuesta datosRespuesta = new DatosRetornoRespuesta(respuesta);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }
}
