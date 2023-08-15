package A3.bolsa.controllers;

import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import A3.bolsa.processors.InvestidorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/investidor")
public class InvestidorController {

    @Autowired
    private InvestidorProcessor processor;

    @GetMapping("/all")
    public ResponseEntity getAllInvestidor(){
        return processor.getAllInvestidor();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getInvestidorById(@PathVariable Long id){
        return processor.getInvestidorById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getInvestidorById(@PathVariable String email ){
        return processor.getInvestidorByEmail(email);
    }

    @GetMapping("/id/{id}/carteira")
    public ResponseEntity getCarteiraInvestidorById(@PathVariable Long id){
        return processor.getCarteiraInvestidorById(id);
    }

    @PostMapping("/adicionar")
    public ResponseEntity addInvestidor(@RequestBody DadosCadastroUsuario investidorDto, UriComponentsBuilder uriBuilder){
        return processor.addInvestidor(investidorDto, uriBuilder);
    }



}
