package A3.bolsa.repositories;

import A3.bolsa.entities.InvestidorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestidorRepository extends JpaRepository<InvestidorEntity, Long> {
}
