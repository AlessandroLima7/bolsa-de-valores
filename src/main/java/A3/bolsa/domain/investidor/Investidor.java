package A3.bolsa.domain.investidor;


import A3.bolsa.domain.carteira.Carteira;
import A3.bolsa.domain.investidor.dtos.CadastroInvestidorDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Investidor {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String senha;
    private List<Carteira> carteira;
    private Double saldo;

    public Investidor(CadastroInvestidorDto investidorDto) {
        this.firstName = investidorDto.firstName();
        this.lastName = investidorDto.lastName();
        this.email = investidorDto.email();
        this.senha = investidorDto.senha();
        this.saldo = 5000.00;
    }
}
