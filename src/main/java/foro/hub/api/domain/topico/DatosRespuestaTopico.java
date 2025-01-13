package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String Autor,
        String titulo,
        String mensaje,
        String curso,
        LocalDateTime fechaCreacion,
        Boolean status
) {
    public DatosRespuestaTopico(Topico topico){
        this(topico.getId(), topico.getUsuario().getName(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(),
                topico.getFecha_creacion(), topico.getStatus());
    }
}
