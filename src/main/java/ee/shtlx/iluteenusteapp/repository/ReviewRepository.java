package ee.shtlx.iluteenusteapp.repository;

import ee.shtlx.iluteenusteapp.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Review entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    String ratinQuery =
        "select shop.name as shopName, TRUNC((sum(TRUNC(rev.rating, 2))/count(TRUNC(rev.rating,2))),2) as shopRating from review as rev\n" +
        "inner join service_association_with_shop as sass ON rev.service_association_with_shop_id = sass.id\n" +
        "inner join shop ON sass.shop_id = shop.id group by shop.name\n";

    @Query(value = ratinQuery, nativeQuery = true)
    public List<Object> findRatingByNativeQuery();
}
