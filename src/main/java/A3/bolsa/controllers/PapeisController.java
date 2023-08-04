package A3.bolsa.controllers;

import A3.bolsa.domain.papeis.dto.CadastroPapelDto;
import A3.bolsa.processors.PapeisProcessor;
import A3.bolsa.repositories.CarteiraRepository;
import A3.bolsa.repositories.InvestidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/papeis")
public class PapeisController {


    @Autowired
    private InvestidorRepository repository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    private final PapeisProcessor processor;

    public PapeisController(PapeisProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity getAllPapeis(){
        return processor.getAllPapeis();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getOnePapel(@PathVariable Long id){return processor.getOnePapel(id);}


    @GetMapping("/sigla/{sigla}")
    public ResponseEntity getOnePapelBySigla(@PathVariable String sigla){return processor.getOnePapelBySigla(sigla);}


    @PostMapping("/adicionar")
    @Transactional
    public ResponseEntity addPapel(@RequestBody CadastroPapelDto papel, UriComponentsBuilder uriBuilder){
        return processor.addPapel(papel, uriBuilder);
    }



}















