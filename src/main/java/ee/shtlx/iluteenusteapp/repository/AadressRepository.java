package ee.shtlx.iluteenusteapp.repository;

import ee.shtlx.iluteenusteapp.domain.Aadress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Aadress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AadressRepository extends JpaRepository<Aadress, Long> {}
