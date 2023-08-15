package A3.bolsa.domain.admin;

import A3.bolsa.domain.usuario.Usuario;
import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Admin extends Usuario {

    public Admin(DadosCadastroUsuario cadastroDto){
        super(cadastroDto);
    }
}
