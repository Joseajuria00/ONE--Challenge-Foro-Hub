package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.ValidacionException;
import foro.hub.api.domain.topico.DatosResumenTopico;
import foro.hub.api.domain.topico.TopicoRepository;
import foro.hub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final RespuestaRepository respuestaRepository;

    public RespuestaService(@Autowired UsuarioRepository usuarioRepository,
                            @Autowired TopicoRepository topicoRepository,
                            @Autowired RespuestaRepository respuestaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.respuestaRepository = respuestaRepository;
    }

    public DatosResponseRespuesta registrarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta){
        var usuario = usuarioRepository.findById(datosRegistroRespuesta.idUsuario())
                .orElseThrow(() -> new ValidacionException("No existe un Usuario con el Id informado"));
        var topico = topicoRepository.findById(datosRegistroRespuesta.idTopico())
                .orElseThrow(() -> new ValidacionException("No existe un Tópico con el Id informado"));
        if (!usuario.getStatus()) {
            throw new ValidacionException("El Usuario ingresado fue dado de baja");
        }
        if (!topico.getStatus()) {
            throw new ValidacionException("El Topico ingresado fue dado de baja");
        }
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        return new DatosResponseRespuesta(respuesta.getId(), usuario.getName(), new DatosResumenTopico(topico),
                respuesta.getMensaje(), respuesta.getFecha_creacion(), respuesta.getStatus());
    }
    public Page<DatosResponseRespuesta> listadoRespuestas(@PageableDefault(size = 10) Pageable pageable){
        return respuestaRepository.findAll(pageable).map(DatosResponseRespuesta::new);
    }
    public DatosResponseRespuesta listarRespuesta(Long id){
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(()->new ValidacionException("No existe una Respuesta con el Id informado"));
        return new DatosResponseRespuesta(respuesta);
    }
    public DatosResponseRespuesta actualizarRespuesta(Long id, DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(()->new ValidacionException("No existe una Respuesta con el Id informado"));
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return new DatosResponseRespuesta(respuesta);
    }
    public Respuesta eliminarRespuesta(Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.eliminarRespuesta();
        return respuesta;
    }
}
