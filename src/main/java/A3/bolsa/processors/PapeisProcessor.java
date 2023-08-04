package A3.bolsa.processors;

import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.papeis.dto.CadastroPapelDto;
import A3.bolsa.domain.papeis.dto.DadosPapeisDto;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class PapeisProcessor {

    @Autowired
    private PapelRepository repository;

    @Autowired
    private PapeisMapper mapper;

    public ResponseEntity getAllPapeis(){
        var papeis = mapper.entityToModel(repository.findAll());
        var dto = mapper.modelToSiglaValoresDto(papeis);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<DadosPapeisDto> getOnePapel(Long id) {
        var papel = repository.findById(id);
        if(papel.isPresent()){
            var dto = mapper.mapToDadosPapeisDto(mapper.mapToPapeis(papel.get()));
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity getOnePapelBySigla(String sigla) {
        var papel = repository.findBySigla(sigla);
        if(papel.isPresent()){
            var dto = mapper.mapToDadosPapeisDto(mapper.mapToPapeis(papel.get()));
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }


    public ResponseEntity addPapel(CadastroPapelDto papel, UriComponentsBuilder uriBuilder){
        var papelToSave = mapper.modelToEntity(mapper.cadastroDtoToModel(papel));
        var papelSaved = repository.save(papelToSave);
        var uri = uriBuilder.path("/papeis/id/{id}").buildAndExpand(papelSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.mapToDadosPapeisDto(mapper.mapToPapeis(papelSaved)));

    }



}
