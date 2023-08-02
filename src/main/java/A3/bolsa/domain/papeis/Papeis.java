package A3.bolsa.domain.papeis;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
@AllArgsConstructor
public class Papeis  {

    private Long id;

    private String nomePapel;

    private String sigla;

    private Double valor;

    private Double valorAnterior;

    private String descricaoDoPapel;

    private Integer quantidade;

    public Papeis(String nome, String sigla, Double valor, String descricao, Integer quantidade){
        this.nomePapel = nome;
        this.sigla = sigla;
        this.valor = valor;
        this.descricaoDoPapel = descricao;
        this.quantidade = quantidade;
    }

}
