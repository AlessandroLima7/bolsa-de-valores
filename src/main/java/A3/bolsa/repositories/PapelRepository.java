package A3.bolsa.repositories;

import A3.bolsa.entities.PapeisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<PapeisEntity, Long> {
    PapeisEntity findBySigla(String sigla);
}
