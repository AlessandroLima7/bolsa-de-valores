package A3.bolsa.mappers;

import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.entities.CarteiraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarteiraMapper {

    CarteiraMapper INSTANCE = Mappers.getMapper(CarteiraMapper.class);

    @Bean
    Carteira entityToModel(CarteiraEntity carteira);

    @Bean
    CarteiraEntity modelToEntity(Carteira carteira);

    @Bean
    List<Carteira> listEntityToModel(List<CarteiraEntity> investidores);

    @Bean
    List<CarteiraEntity> listModelToEntity(List<Carteira> investidores);


}
