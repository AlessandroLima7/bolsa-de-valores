package A3.bolsa.controllers;

import A3.bolsa.domain.transacao.TransacaoDeCompraDto;
import A3.bolsa.domain.usuario.Usuario;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.processors.transacoes.TransacaoProcessor;
import A3.bolsa.repositories.InvestidorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @PostMapping("/comprar")
    @Transactional
    public ResponseEntity buyPapel(@Valid @RequestBody TransacaoDeCompraDto transacao, @AuthenticationPrincipal Usuario usuarioLogado){
        return processor.buyPapelInvestidor(transacao, usuarioLogado);
    }

    @PostMapping("/vender")
    @Transactional
    public ResponseEntity sellPapel(@Valid @RequestBody TransacaoDeCompraDto transacao, @AuthenticationPrincipal Usuario usuarioLogado){
        return processor.sellPapelInvestidor(transacao, usuarioLogado);
    }



}
