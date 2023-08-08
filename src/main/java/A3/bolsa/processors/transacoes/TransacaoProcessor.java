package A3.bolsa.processors.transacoes;


import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.domain.usuario.Usuario;
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

    public ResponseEntity buyPapelInvestidor(TransacaoDeCompraDto transacao, Usuario usuarioLogado){
        var investidor = investidorMapper.entityToModel(investidorRepository.findById(usuarioLogado.getId()).get());
        var papel = papelRepository.findById(transacao.idPapel());
        if(papel.isPresent()){

            var papeModel = papeisMapper.mapToPapeis(papel.get());
            this.validacoes.forEach(v -> v.validarCompra(investidor, transacao, papeModel));
            updateSaldoInvestidorBuy(investidor, papeModel, transacao.quantidade());
            addPapelToCarteira(investidor, papeModel, transacao.quantidade());
            updateQuatidadePapeisBuy(transacao.quantidade(), papeModel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();


    }

    public ResponseEntity sellPapelInvestidor(TransacaoDeCompraDto transacao, Usuario usuarioLogado){
        var investidor = investidorMapper.entityToModel(investidorRepository.findById(usuarioLogado.getId()).get());
        var acao = carteiraRepository.findById(transacao.idPapel());
        if(acao.isPresent()){
            var modeloAcao = carteiraMapper.entityToModel(acao.get());
            this.validacoes.forEach(v -> v.validarVenda(investidor, transacao, modeloAcao));
            var papel = papelRepository.findById(acao.get().getIdPapel()).get();
            updateSaldoInvestidorSell(investidor, modeloAcao, transacao.quantidade());
            removePapelFromCarteira(investidor, modeloAcao, transacao.quantidade());
            updateQuatidadePapeisSell(transacao.quantidade(), papeisMapper.mapToPapeis(papel));
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }





    private void updateSaldoInvestidorBuy(Investidor investidor, Papeis papel, Integer quantidade){
        var valorDaTransacao = papel.getValor() * quantidade;
        investidor.setSaldo(investidor.getSaldo() - valorDaTransacao);
        investidorRepository.save(investidorMapper.modelToEntity(investidor));
    }


    private void updateSaldoInvestidorSell(Investidor investidor, Carteira acao, Integer quantidade){
        var papel = papelRepository.findById(acao.getIdPapel()).get();
        var valorToRetrieve = papel.getValor() * quantidade;
        investidor.setSaldo(investidor.getSaldo() + valorToRetrieve);
        investidorRepository.save(investidorMapper.modelToEntity(investidor));


    }

    private void addPapelToCarteira(Investidor investidor, Papeis papel, Integer quantidade){
        var acaoDaCarteira = carteiraRepository.findByfkInvestidorAndIdPapel(investidor.getId(), papel.getId());

        if(acaoDaCarteira.isPresent()){
            var acao = carteiraMapper.entityToModel(acaoDaCarteira.get());
            acao.setQuantidade(acao.getQuantidade() + quantidade);
            carteiraRepository.save(carteiraMapper.modelToEntity(acao));
        }
        else {
            var acao = new Carteira(investidor.getId(), papel.getNomePapel(), papel.getSigla(), papel.getValor(), papel.getDescricaoDoPapel(), quantidade, papel.getId());
            carteiraRepository.save(carteiraMapper.modelToEntity(acao));
        }




    }

    private void removePapelFromCarteira(Investidor investidor, Carteira acao, Integer quantidade){
        if(acao.getQuantidade() == quantidade){
            carteiraRepository.delete(carteiraMapper.modelToEntity(acao));

        }
        else{
            var quantidadeNova = acao.getQuantidade() - quantidade;
            acao.setQuantidade(quantidadeNova);
            var carteiraToSave = carteiraMapper.modelToEntity(acao);
            carteiraRepository.save(carteiraToSave);
        }



    }
    private void updateQuatidadePapeisSell(Integer quantidade, Papeis papel){
        var papelModel = papeisMapper.mapToPapeis(papelRepository.findById(papel.getId()).get());
        var quantidadeAntiga = papelModel.getQuantidade();
        var quantidadeNova = quantidadeAntiga + quantidade;
        papelModel.setQuantidade(quantidadeNova);
        var papelSaved = papelRepository.save(papeisMapper.modelToEntity(papelModel));
        updateValoresPapeisSell(papeisMapper.mapToPapeis(papelSaved), quantidade);
    }
    private void updateQuatidadePapeisBuy(Integer quantidade, Papeis papel){
        var papelModel = papeisMapper.mapToPapeis(papelRepository.findById(papel.getId()).get());
        var quantidadeAntiga = papelModel.getQuantidade();
        var quantidadeNova = quantidadeAntiga - quantidade;
        papelModel.setQuantidade(quantidadeNova);
        var papelSaved = papelRepository.save(papeisMapper.modelToEntity(papelModel));
        updateValoresPapeisBuy(papeisMapper.mapToPapeis(papelSaved), quantidade);
    }

    private void updateValoresPapeisBuy(Papeis papel, Integer quantidade){
        var valorAnterior = papel.getValor();
        var novoValor = valorAnterior + (valorAnterior * (0.00005 * quantidade));
        papel.updateValor(novoValor);
        var papelToSave = papeisMapper.modelToEntity(papel);
        papelRepository.save(papelToSave);
    }

    private void updateValoresPapeisSell(Papeis papel, Integer quantidade){
        var valorAnterior = papel.getValor();
        var novoValor = valorAnterior - (valorAnterior * (0.00005 * quantidade));
        papel.updateValor(novoValor);
        var papelToSave = papeisMapper.modelToEntity(papel);
        papelRepository.save(papelToSave);
    }

}
