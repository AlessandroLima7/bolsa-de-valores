package A3.bolsa.mappers;

import A3.bolsa.domain.admin.Admin;
import A3.bolsa.domain.usuario.Usuario;
import A3.bolsa.entities.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);


    AdminEntity modelToEntity(Admin admin);

    Usuario entityToModelUsuario(AdminEntity adminEntity);

}
