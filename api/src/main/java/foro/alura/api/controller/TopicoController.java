package foro.alura.api.controller;

import foro.alura.api.dto.topico.DatosActualizarTopico;
import foro.alura.api.dto.topico.DatosListadoTopico;
import foro.alura.api.dto.topico.DatosRegistroTopico;
import foro.alura.api.dto.topico.DatosRespuestaTopico;
import foro.alura.api.dto.usuario.DatosListadoUsuario;
import foro.alura.api.dto.usuario.DatosRegistroUsuario;
import foro.alura.api.modelo.Curso;
import foro.alura.api.modelo.Topico;
import foro.alura.api.modelo.Usuario;
import foro.alura.api.repository.CursoRepository;
import foro.alura.api.repository.TopicoRepository;
import foro.alura.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Usuario autor = usuarioRepository.getReferenceById(datosRegistroTopico.idAutor());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.idCurso());
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico,autor,curso));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity  actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Usuario autor = usuarioRepository.getReferenceById(datosActualizarTopico.idAutor());
        Curso curso = cursoRepository.getReferenceById(datosActualizarTopico.idCurso());
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico,autor,curso);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}
