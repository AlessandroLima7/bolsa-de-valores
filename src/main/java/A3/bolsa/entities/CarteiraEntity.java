package A3.bolsa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carteira")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_investidor")
    private Long fkInvestidor;

    @Column(name = "id_papel")
    private Long idPapel;

    @Column(name = "nome")
    private String nomePapel;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "valor_de_compra")
    private Double valorDeCompra;

    @Column(name = "descricao_papel")
    private String descricaoDoPapel;

    @Column(name = "quantidade")
    private Integer quantidade;


}
