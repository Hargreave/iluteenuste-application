package ee.shtlx.iluteenusteapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceAssociationWithShop.
 */
@Entity
@Table(name = "service_association_with_shop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceAssociationWithShop implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "time", nullable = false)
    private Integer time;

    @OneToMany(mappedBy = "serviceAssociationWithShop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceAssociationWithShops", allowSetters = true)
    private Service service;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceAssociationWithShops", allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceAssociationWithShop price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public ServiceAssociationWithShop time(Integer time) {
        this.time = time;
        return this;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public ServiceAssociationWithShop reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public ServiceAssociationWithShop addReview(Review review) {
        this.reviews.add(review);
        review.setServiceAssociationWithShop(this);
        return this;
    }

    public ServiceAssociationWithShop removeReview(Review review) {
        this.reviews.remove(review);
        review.setServiceAssociationWithShop(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Service getService() {
        return service;
    }

    public ServiceAssociationWithShop service(Service service) {
        this.service = service;
        return this;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Shop getShop() {
        return shop;
    }

    public ServiceAssociationWithShop shop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceAssociationWithShop)) {
            return false;
        }
        return id != null && id.equals(((ServiceAssociationWithShop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceAssociationWithShop{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", time=" + getTime() +
            "}";
    }
}
