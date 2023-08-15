package A3.bolsa.domain.usuario.dto;

import A3.bolsa.domain.usuario.ROLE;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String email,
        @NotBlank
        String senha,

        @NotBlank
        ROLE role

) {
}