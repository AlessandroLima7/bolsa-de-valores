package A3.bolsa.domain.transacao;

import jakarta.validation.constraints.Min;

public record TransacaoDeCompraDto(
        Long idPapel,
        @Min(1)
        Integer quantidade

) {
}
