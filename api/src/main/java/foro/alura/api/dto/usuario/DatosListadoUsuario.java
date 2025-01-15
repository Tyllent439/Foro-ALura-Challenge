package foro.alura.api.dto.usuario;

import foro.alura.api.modelo.Usuario;

public record DatosListadoUsuario(
        String nombre,
        String email,
        String perfil) {

    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getNombre(), usuario.getEmail(), usuario.getPerfil().toString());
    }
}
