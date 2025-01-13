package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name="respuestas")
@Entity(name="Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private Boolean status;
    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        this.usuario = new Usuario(datosRegistroRespuesta.idUsuario());
        this.topico = new Topico(datosRegistroRespuesta.idTopico());
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.status = true;
    }
    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
    }
    public void eliminarRespuesta() {
        this.status = false;
    }
}
