package foro.hub.api.domain.topico;

import foro.hub.api.domain.ValidacionException;
import foro.hub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;

    public TopicoService(@Autowired UsuarioRepository usuarioRepository, @Autowired TopicoRepository topicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
    }

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico){
        var usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario())
                .orElseThrow(() -> new ValidacionException("No existe un Usuario con el Id informado"));
        if(topicoRepository.existsByTitulo(datosRegistroTopico.titulo())){
            throw new ValidacionException("Ya existe un Tópico con ese Titulo");
        }
        if(topicoRepository.existsByMensaje(datosRegistroTopico.mensaje())){
            throw new ValidacionException("Ya existe un Tópico con ese Mensaje");
        }
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        return new DatosRespuestaTopico(topico.getId(), usuario.getName(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(),
                topico.getFecha_creacion(), topico.getStatus());
    }
    public Page<DatosRespuestaTopico> listadoTopicos(@PageableDefault(size = 5) Pageable pageable){
        return topicoRepository.findAll(pageable).map(DatosRespuestaTopico::new);
    }
    public DatosRespuestaTopico listarTopico(Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("No existe un Tópico con el Id informado"));
        if(!topico.getStatus()){
            throw new ValidacionException("El Tópico informado fue dado de baja");
        }
        return new DatosRespuestaTopico(topico.getId(), topico.getUsuario().getName(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(),
                topico.getFecha_creacion(), topico.getStatus());
    }
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("No existe un Tópico con el Id informado"));
        if(topicoRepository.existsByTitulo(datosActualizarTopico.titulo())){
            throw new ValidacionException("Ya existe un Tópico con ese Titulo");
        }
        if(topicoRepository.existsByMensaje(datosActualizarTopico.mensaje())){
            throw new ValidacionException("Ya existe un Tópico con ese Mensaje");
        }
        topico.actualizarDatos(datosActualizarTopico);
        return new DatosRespuestaTopico(topico.getId(), topico.getUsuario().getName(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(),
                topico.getFecha_creacion(), topico.getStatus());
    }
    public Topico eliminarTopico(Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminarTopico();
        return topico;
    }

}
