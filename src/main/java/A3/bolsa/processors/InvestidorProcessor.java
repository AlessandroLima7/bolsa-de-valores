package A3.bolsa.processors;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import A3.bolsa.mappers.InvestidorMapper;
import A3.bolsa.repositories.InvestidorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class InvestidorProcessor {

    private final InvestidorRepository investidorRepository;

    private final InvestidorMapper investidorMapper;

    public InvestidorProcessor(InvestidorRepository investidorRepository, InvestidorMapper investidorMapper) {
        this.investidorRepository = investidorRepository;
        this.investidorMapper = investidorMapper;
    }

    public ResponseEntity getAllInvestidor(){
        var investidores = investidorMapper.listEntityToModel(investidorRepository.findAll());
        var dto = investidorMapper.listModelToDto(investidores);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity getInvestidorById(Long id){
        var investidor = investidorRepository.findById(id);
        if(investidor.isPresent()){
            var dto = investidorMapper.modelToDto(investidorMapper.entityToModel(investidor.get()));
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity getInvestidorByEmail(String email){
        var investidor = investidorRepository.findByEmail(email);
        if(investidor.isPresent()){
            var dto = investidorMapper.modelToDto(investidorMapper.entityToModel(investidor.get()));
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity addInvestidor(DadosCadastroUsuario investidorDto, UriComponentsBuilder uriBuider) {
        var investidorToSave = investidorMapper.modelToEntity(new Investidor(investidorDto));
        var investidorSaved = investidorRepository.save(investidorToSave);
        var uri = uriBuider.path("/investidor/id/{id}").buildAndExpand(investidorSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(investidorMapper.modelToDto(investidorMapper.entityToModel(investidorSaved)));

    }

    public ResponseEntity getCarteiraInvestidorById(Long id) {
        var investidor = investidorRepository.findById(id);
        if(investidor.isPresent()){
            var dto = investidorMapper.modelToCarteiraDto(investidorMapper.entityToModel(investidor.get()));
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }






}















