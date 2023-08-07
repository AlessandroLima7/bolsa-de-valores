package A3.bolsa.controllers;

import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.processors.transacoes.TransacaoProcessor;
import A3.bolsa.repositories.InvestidorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @Autowired
    private TransacaoProcessor processor;

    @Autowired
    private InvestidorRepository repository;

    @Autowired
    private InvestidorMapper mapper;


    @PostMapping("/comprar/{investidor}")
    @Transactional
    public ResponseEntity buyPapel(@Valid @RequestBody TransacaoDeCompraDto transacao, @PathVariable Long investidor){

        var usuarioLogado = repository.findById(investidor);
        if(usuarioLogado.isPresent()){
            return processor.buyPapelInvestidor(transacao, mapper.entityToModel(usuarioLogado.get()));
        }
        return ResponseEntity.badRequest().build();
    }

}
