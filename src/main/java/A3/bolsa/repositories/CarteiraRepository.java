package A3.bolsa.repositories;

import A3.bolsa.entities.CarteiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraEntity, Long> {

    Optional<CarteiraEntity> findByfkInvestidorAndIdPapel(Long fkInvestidor, Long idPapel);
}
