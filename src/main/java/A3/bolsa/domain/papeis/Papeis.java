package A3.bolsa.domain.papeis;

import A3.bolsa.domain.papeis.dto.CadastroPapelDto;
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

    public Papeis(CadastroPapelDto cadastroPapelDto){
        this.nomePapel = cadastroPapelDto.nomePapel();
        this.sigla = cadastroPapelDto.sigla();
        this.valor = cadastroPapelDto.valor();
        this.descricaoDoPapel = cadastroPapelDto.descricaoPapel();
        this.quantidade = cadastroPapelDto.quantidade();
    }

}
