package A3.bolsa.processors.transacoes;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoValores implements ValidacaoTransacoes {


    @Override
    public void validar(Investidor investidor, TransacaoDeCompraDto transacao, Papeis papel) {
        var capitalInvestidor = investidor.getSaldo();
        var valorTransacao = transacao.quantidade() * papel.getValor();

        if(capitalInvestidor < valorTransacao){
            throw new ValidacaoException("Saldo insuficiente para a transação.");
        }

    }
}
