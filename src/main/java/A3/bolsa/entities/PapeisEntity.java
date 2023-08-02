package A3.bolsa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "papeis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PapeisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nomePapel;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "valor_passado")
    private Double valorAnterior;

    @Column(name = "descricao_papel")
    private String descricaoDoPapel;

    @Column(name = "quantidade")
    private Integer quantidade;


}
