package A3.bolsa.processors.transacoes;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;

public interface ValidacaoTransacoes {

    void validar(Investidor investidor, Papeis papel);
}
