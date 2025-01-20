package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.DatosActualizarRespuesta;
import foro.hub.api.domain.respuesta.DatosRegistroRespuesta;
import foro.hub.api.domain.respuesta.DatosResponseRespuesta;
import foro.hub.api.domain.respuesta.RespuestaService;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    private final RespuestaService respuestaService;


    public RespuestaController(@Autowired RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DatosResponseRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosResponseRespuesta datosResponseRespuesta = respuestaService.registrarRespuesta(datosRegistroRespuesta);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(datosResponseRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosResponseRespuesta);
    }
    @GetMapping
    public ResponseEntity<Page<DatosResponseRespuesta>> listadoRespuestas(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(respuestaService.listadoRespuestas(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosResponseRespuesta> listarRespuesta(@PathVariable @Valid Long id){
        DatosResponseRespuesta datosResponseRespuesta = respuestaService.listarRespuesta(id);
        return ResponseEntity.ok(datosResponseRespuesta);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarRespuesta(@PathVariable @Valid Long id,
                                           @RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        return ResponseEntity.ok(respuestaService.actualizarRespuesta(id, datosActualizarRespuesta));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.ok().build();
    }
}
