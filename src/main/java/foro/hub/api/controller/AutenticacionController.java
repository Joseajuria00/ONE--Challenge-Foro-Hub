package foro.hub.api.controller;

import foro.hub.api.domain.ValidacionException;
import foro.hub.api.domain.usuario.DatosAutenticacionUsuario;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import foro.hub.api.infra.security.DatosJWTToken;
import foro.hub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    public AutenticacionController(@Autowired AuthenticationManager authenticationManager,
                                   @Autowired TokenService tokenService,
                                   @Autowired UsuarioRepository usuarioRepository){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        if(!usuarioRepository.findStatusByLogin(datosAutenticacionUsuario.login())){
            throw new ValidacionException("El Usuario ingresado fue dado de baja");
        }
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTToken =tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(JWTToken);
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
