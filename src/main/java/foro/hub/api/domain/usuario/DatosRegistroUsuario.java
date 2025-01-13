package foro.hub.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String name,
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
