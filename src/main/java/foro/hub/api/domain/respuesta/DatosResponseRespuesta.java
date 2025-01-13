package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.DatosResumenTopico;

import java.time.LocalDateTime;

public record DatosResponseRespuesta(
        Long id,
        String autor,
        DatosResumenTopico topico,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status
) {
    public DatosResponseRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getUsuario().getName(), new DatosResumenTopico(respuesta.getTopico()),
                respuesta.getMensaje(), respuesta.getFecha_creacion(), respuesta.getStatus());
    }
}
