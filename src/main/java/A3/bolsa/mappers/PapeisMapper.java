package A3.bolsa.mappers;

import A3.bolsa.domain.papeis.Papeis;
import A3.bolsa.domain.papeis.dto.DadosPapeisDto;
import A3.bolsa.entities.CarteiraEntity;
import A3.bolsa.entities.PapeisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PapeisMapper {

    PapeisMapper INSTANCE = Mappers.getMapper(PapeisMapper.class);
    PapeisEntity modelToEntity(Papeis papel);


    Papeis mapToPapeis(PapeisEntity papeis);


    DadosPapeisDto mapToDadosPapeisDto(Papeis papeis);


    List<Papeis> entityToModel(List<PapeisEntity> papeis);


    List<DadosPapeisDto> modelToSiglaValoresDto(List<Papeis> papeis);



}
