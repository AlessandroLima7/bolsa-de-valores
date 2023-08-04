package A3.bolsa.processors.transacoes;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.exceptions.ValidacaoException;
import org.modelmapper.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoQuantidade implements ValidacaoTransacoes{
    @Override
    public void validar(Investidor investidor, TransacaoDeCompraDto transacao, Papeis papel) {
        if(transacao.quantidade() > papel.getQuantidade()){
            throw new ValidacaoException("A quantidade da transação excedeu a quantidade de ações no mercado.");
        }

    }
}
