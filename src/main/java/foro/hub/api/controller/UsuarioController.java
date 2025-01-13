package foro.hub.api.controller;

import foro.hub.api.domain.usuario.DatosActualizarUsuario;
import foro.hub.api.domain.usuario.DatosRegistroUsuario;
import foro.hub.api.domain.usuario.DatosRespuestaUsuario;
import foro.hub.api.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaUsuario datosRespuestaUsuario = usuarioService.registrarUsuario(datosRegistroUsuario);
        URI url = uriComponentsBuilder.path("/usuarios{id}").buildAndExpand(datosRespuestaUsuario.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listadoUsuarios(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(usuarioService.listadoUsuarios(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> listarUsuario(@PathVariable @Valid Long id){
        DatosRespuestaUsuario datosRespuestaUsuario = usuarioService.listarUsuario(id);
        return ResponseEntity.ok(datosRespuestaUsuario);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarUsuario(@PathVariable @Valid Long id,
                                           @RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, datosActualizarUsuario));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }

}
