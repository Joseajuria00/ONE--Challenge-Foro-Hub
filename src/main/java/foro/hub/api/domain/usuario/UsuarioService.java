package foro.hub.api.domain.usuario;

import foro.hub.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(@Autowired UsuarioRepository usuarioRepository, @Autowired PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public DatosRespuestaUsuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario){
        if(usuarioRepository.existsByLogin(datosRegistroUsuario.login())){
            throw new ValidacionException("Ya existe un Usuario con ese Nombre de Usuario");
        }
        datosRegistroUsuario = new DatosRegistroUsuario(datosRegistroUsuario.name(), datosRegistroUsuario.login(),
                passwordEncoder.encode(datosRegistroUsuario.password()));
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        datosRegistroUsuario = null;
        usuario.setPassword(null);
        return new DatosRespuestaUsuario(usuario.getId(), usuario.getName(), usuario.getLogin(), usuario.getStatus());
    }

    public Page<DatosRespuestaUsuario> listadoUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(DatosRespuestaUsuario::new);
    }

    public DatosRespuestaUsuario listarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new ValidacionException("No existe un Usuario con el Id informado"));
        return new DatosRespuestaUsuario(usuario.getId(), usuario.getName(), usuario.getLogin(), usuario.getStatus());
    }

    public DatosRespuestaUsuario actualizarUsuario(Long id, DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new ValidacionException("No existe un Usuario con el Id informado"));
        usuario.actualizarDatos(datosActualizarUsuario);
        return new DatosRespuestaUsuario(usuario.getId(), usuario.getName(), usuario.getLogin(), usuario.getStatus());
    }

    public Usuario eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminarUsuario();
        return usuario;
    }
}
