package A3.bolsa.processors.transacoes;


import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.mappers.CarteiraMapper;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.CarteiraRepository;
import A3.bolsa.repositories.InvestidorRepository;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class TransacaoProcessor {

    private List<ValidacaoTransacoes> validacoes;

    private final PapelRepository papelRepository;
    private final CarteiraRepository carteiraRepository;
    private final InvestidorRepository investidorRepository;
    private final PapeisMapper papeisMapper;
    private final CarteiraMapper carteiraMapper;
    private final InvestidorMapper investidorMapper;

    public TransacaoProcessor(List<ValidacaoTransacoes> validacoes, PapelRepository papelRepository, CarteiraRepository carteiraRepository, InvestidorRepository investidorRepository, PapeisMapper papeisMapper, CarteiraMapper carteiraMapper, InvestidorMapper investidorMapper){
        this.validacoes = validacoes;
        this.papelRepository = papelRepository;
        this.carteiraRepository = carteiraRepository;
        this.investidorRepository = investidorRepository;
        this.papeisMapper = papeisMapper;
        this.carteiraMapper = carteiraMapper;
        this.investidorMapper = investidorMapper;
    }

    public ResponseEntity buyPapelInvestidor(TransacaoDeCompraDto transacao, Investidor investidor){
        var papel = papelRepository.findById(transacao.idPapel());
        if(papel.isPresent()){
            var papeModel = papeisMapper.mapToPapeis(papel.get());
            this.validacoes.forEach(v -> v.validarCompra(investidor, transacao, papeModel));
            updateSaldoInvestidorBuy(investidor, papeModel, transacao.quantidade());
            addPapelToCarteira(investidor, papeModel, transacao.quantidade());
            updateQuatidadePapeis(transacao.quantidade(), papeModel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();


    }



//    public ResponseEntity sellPapelInvestidor(TransacaoDeCompraDto transacao, Investidor investidor){
//        var acao = carteiraRepository.findById(transacao.idPapel());
//
//        if(acao.isPresent()){
//            var modeloAcao = carteiraMapper.entityToModel(acao.get());
//            this.validacoes.forEach(v -> v.validarVenda(investidor, transacao, modeloAcao));
//            var papel = papelRepository.findById(acao.get().getIdPapel());
//
//        }
//
//    }





    private void updateSaldoInvestidorBuy(Investidor investidor, Papeis papel, Integer quantidade){
        var valorDaTransacao = papel.getValor() * quantidade;
        investidor.setSaldo(investidor.getSaldo() - valorDaTransacao);
        investidorRepository.save(investidorMapper.modelToEntity(investidor));
    }

    private void updateSaldoInvestidorSell(Investidor investidor, Papeis papel, Integer quantidade){
        var valorDaTransacao = papel.getValor() * quantidade;

    }

    private void addPapelToCarteira(Investidor investidor, Papeis papel, Integer quantidade){
        var carteira = new Carteira(investidor.getId(), papel.getNomePapel(), papel.getSigla(), papel.getValor(), papel.getDescricaoDoPapel(), quantidade, papel.getId());
        var carteiraToSave = carteiraMapper.modelToEntity(carteira);
        carteiraRepository.save(carteiraToSave);

    }

    private void updateQuatidadePapeis(Integer quantidade, Papeis papel){
        var papelModel = papeisMapper.mapToPapeis(papelRepository.findById(papel.getId()).get());
        var quantidadeAntiga = papelModel.getQuantidade();
        var quantidadeNova = quantidadeAntiga - quantidade;
        papelModel.setQuantidade(quantidadeNova);
        var papelSaved = papelRepository.save(papeisMapper.modelToEntity(papelModel));
        updateValoresPapeis(papeisMapper.mapToPapeis(papelSaved), quantidade);
    }

    private void updateValoresPapeis(Papeis papel, Integer quantidade){
        var valorAnterior = papel.getValor();
        var novoValor = valorAnterior + (valorAnterior * (0.00005 * quantidade));
        papel.updateValor(novoValor);
        var papelToSave = papeisMapper.modelToEntity(papel);
        papelRepository.save(papelToSave);
    }

}
