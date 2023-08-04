package A3.bolsa.domain.investidor.dtos;

import A3.bolsa.domain.carteira.Carteira;

import java.util.List;

public record DadosInvestidor(
        Long id,
        String firstName,
        String lastName,
        String email,
        List<Carteira> carteira,
        Double saldo
        ) {
}
