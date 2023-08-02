package A3.bolsa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "investidor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestidorEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany
    @JoinColumn(name = "fk_investidor")
    private List<CarteiraEntity> carteira;

    @Column(name = "saldo")
    private Double saldo;




}
