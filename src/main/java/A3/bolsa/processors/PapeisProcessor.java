package A3.bolsa.processors;

import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.papeis.dto.DadosPapeisDto;
import A3.bolsa.mappers.PapeisMapper;
import A3.bolsa.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PapeisProcessor {

    @Autowired
    private PapelRepository repository;

    @Autowired
    private PapeisMapper mapper;

    public List<DadosPapeisDto> getAllPapeis(){
        var papeis = mapper.entityToModel(repository.findAll());
        var dto = mapper.modelToSiglaValoresDto(papeis);
        return dto;
    }


    public DadosPapeisDto getOnePapel(Long id) {
        var papel = mapper.mapToPapeis(repository.findById(id).get());
        var dto = mapper.mapToDadosPapeisDto(papel);
        return dto;
    }

    public DadosPapeisDto getOnePapelBySigla(String sigla) {
        var papelEntity = repository.findBySigla(sigla);
        var dto = mapper.mapToDadosPapeisDto(mapper.mapToPapeis(papelEntity));
        return dto;
    }
}
