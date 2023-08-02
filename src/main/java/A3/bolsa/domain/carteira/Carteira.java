package A3.bolsa.domain.carteira;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Carteira {

    private Long id;
    private Long fkInvestidor;
    private String nomePapel;
    private String sigla;
    private Double valor;
    private Double valorAnterior;
    private String descricaoDoPapel;
    private Integer quantidade;

    public Carteira(String nomePapel, String sigla, Double valor, String descricaoDoPapel, Integer quantidade){
        this.nomePapel = nomePapel;
        this.sigla = sigla;
        this.valor = valor;
        this.descricaoDoPapel = descricaoDoPapel;
        this.quantidade = quantidade;
    }

}
