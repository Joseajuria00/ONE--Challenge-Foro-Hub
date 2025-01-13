package foro.hub.api.domain.topico;

public record DatosResumenTopico(
        Long id,
        String Autor,
        String titulo,
        String mensaje
) {
    public DatosResumenTopico(Topico topico){
        this(topico.getId(), topico.getUsuario().getName(), topico.getTitulo(), topico.getMensaje());
    }
}
