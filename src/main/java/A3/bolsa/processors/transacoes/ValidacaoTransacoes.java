package A3.bolsa.processors.transacoes;

import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;

public interface ValidacaoTransacoes {

    void validarCompra(Investidor investidor, TransacaoDeCompraDto transacao, Papeis papel);

    void validarVenda(Investidor investidor, TransacaoDeCompraDto transacao, Carteira acao);
}
