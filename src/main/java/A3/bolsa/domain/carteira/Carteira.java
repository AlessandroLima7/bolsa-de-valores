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
    private Long idPapel;
    private String nomePapel;
    private String sigla;
    private Double valorDeCompra;
    private String descricaoDoPapel;
    private Integer quantidade;

    public Carteira(Long idInvestidor, String nomePapel, String sigla, Double valor, String descricaoDoPapel, Integer quantidade, Long idPapel){
        this.fkInvestidor = idInvestidor;
        this.nomePapel = nomePapel;
        this.sigla = sigla;
        this.valorDeCompra = valor;
        this.descricaoDoPapel = descricaoDoPapel;
        this.quantidade = quantidade;
        this.idPapel = idPapel;
    }

}
