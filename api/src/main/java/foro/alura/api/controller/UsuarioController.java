package foro.alura.api.controller;

import foro.alura.api.dto.topico.DatosListadoTopico;
import foro.alura.api.dto.topico.DatosRegistroTopico;
import foro.alura.api.dto.topico.DatosRespuestaTopico;
import foro.alura.api.dto.usuario.DatosListadoUsuario;
import foro.alura.api.dto.usuario.DatosRegistroUsuario;
import foro.alura.api.modelo.Curso;
import foro.alura.api.modelo.Topico;
import foro.alura.api.modelo.Usuario;
import foro.alura.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosListadoUsuario> registrarUsuario(@RequestBody DatosRegistroUsuario datosRegistroUsuario,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>>  listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }
}
