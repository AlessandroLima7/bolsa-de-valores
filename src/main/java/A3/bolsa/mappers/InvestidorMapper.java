package A3.bolsa.mappers;

import A3.bolsa.domain.investidor.Investidor;
import A3.bolsa.domain.investidor.dtos.BasicosInvestidor;
import A3.bolsa.domain.investidor.dtos.CadastroInvestidorDto;
import A3.bolsa.domain.investidor.dtos.DadosInvestidor;
import A3.bolsa.entities.InvestidorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvestidorMapper {



    InvestidorMapper INSTANCE = Mappers.getMapper(InvestidorMapper.class);


    Investidor entityToModel(InvestidorEntity investidor);


    InvestidorEntity modelToEntity(Investidor investidor);

    BasicosInvestidor modelToDto(Investidor investidor);

    List<Investidor> listEntityToModel(List<InvestidorEntity> investidores);


    List<InvestidorEntity> listModelToEntity(List<Investidor> investidores);

    List<BasicosInvestidor> listModelToDto(List<Investidor> investidores);

    Investidor dtoToModel(CadastroInvestidorDto investidorDto);

    DadosInvestidor modelToCarteiraDto(Investidor investidor);
}
