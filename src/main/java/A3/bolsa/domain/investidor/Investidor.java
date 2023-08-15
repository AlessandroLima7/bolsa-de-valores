package A3.bolsa.domain.investidor;


import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.domain.usuario.Usuario;
import A3.bolsa.domain.usuario.dto.DadosCadastroUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investidor extends Usuario {


    private List<Carteira> carteira;
    private Double saldo;

    public Investidor(DadosCadastroUsuario cadastroDto) {
       super(cadastroDto);
        this.saldo = 5000.00;
    }
}
