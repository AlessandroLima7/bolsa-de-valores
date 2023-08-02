package A3.bolsa.domain.investidor;


import A3.bolsa.domain.carteira.Carteira;
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

    public Investidor(String firstName, String lastName, String email, String senha) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.senha = senha;
        this.saldo = 0.0;
    }
}
