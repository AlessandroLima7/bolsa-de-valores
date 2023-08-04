package A3.bolsa.processors.transacoes;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import org.springframework.http.ResponseEntity;

public interface ValidacaoTransacoes {

    void validar(Investidor investidor, TransacaoDeCompraDto transacao, Papeis papel);
}
