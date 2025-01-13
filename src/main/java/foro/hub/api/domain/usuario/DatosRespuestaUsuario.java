package foro.hub.api.domain.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String name,
        String login,
        Boolean status
) {
    public DatosRespuestaUsuario (Usuario usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getLogin(), usuario.getStatus());
    }
}
