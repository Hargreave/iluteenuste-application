package ee.shtlx.iluteenusteapp.repository;

import ee.shtlx.iluteenusteapp.domain.Service;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Service entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Modifying
    @Query(value = "update service set status = 'Not active' where id = :id", nativeQuery = true)
    public void deleteByServiceId(@Param("id") Long id);

    public List<Service> findByStatus(String status);
}
