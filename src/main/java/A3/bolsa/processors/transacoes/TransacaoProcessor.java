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
            this.validacoes.forEach(v -> v.validar(investidor, transacao, papeModel));
            updateSaldoInvestidor(investidor, papeModel, transacao.quantidade());
            addPapelToCarteira(investidor, papeModel, transacao.quantidade());
            updateQuatidadePapeis(transacao.quantidade(), papeModel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();


    }

    public void updateSaldoInvestidor(Investidor investidor, Papeis papel, Integer quantidade){
        var valorDaTransacao = papel.getValor() * quantidade;
        investidor.setSaldo(investidor.getSaldo() - valorDaTransacao);
        investidorRepository.save(investidorMapper.modelToEntity(investidor));
    }

    public void addPapelToCarteira(Investidor investidor, Papeis papel, Integer quantidade){
        var carteira = new Carteira(investidor.getId(), papel.getNomePapel(), papel.getSigla(), papel.getValor(), papel.getDescricaoDoPapel(), quantidade);
        var carteiraToSave = carteiraMapper.modelToEntity(carteira);
        carteiraRepository.save(carteiraToSave);

    }

    public void updateQuatidadePapeis(Integer quantidade, Papeis papel){
        var papelModel = papeisMapper.mapToPapeis(papelRepository.findById(papel.getId()).get());
        var quantidadeAntiga = papelModel.getQuantidade();
        var quantidadeNova = quantidadeAntiga - quantidade;
        papelModel.setQuantidade(quantidadeNova);
        papelRepository.save(papeisMapper.modelToEntity(papelModel));
    }

}
