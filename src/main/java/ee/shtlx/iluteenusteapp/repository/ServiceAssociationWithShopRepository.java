package ee.shtlx.iluteenusteapp.repository;

import ee.shtlx.iluteenusteapp.domain.ServiceAssociationWithShop;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceAssociationWithShop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceAssociationWithShopRepository extends JpaRepository<ServiceAssociationWithShop, Long> {
    List<ServiceAssociationWithShop> findByShopId(Long id);
}
