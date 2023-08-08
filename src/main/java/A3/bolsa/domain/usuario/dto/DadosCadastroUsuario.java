package A3.bolsa.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String firtName,

        @NotBlank
        String lastName,

        @NotBlank
        String email,
        @NotBlank
        String senha

) {
}