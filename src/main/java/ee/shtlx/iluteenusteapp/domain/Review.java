package ee.shtlx.iluteenusteapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 5)
    @Column(name = "comment", nullable = false)
    private String comment;

    @Min(value = 0)
    @Max(value = 5)
    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JsonIgnoreProperties(value = "reviews", allowSetters = true)
    private ServiceAssociationWithShop erviceAssociationWithShop;

    @ManyToOne
    @JsonIgnoreProperties(value = "reviews", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public Review comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Review rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ServiceAssociationWithShop getErviceAssociationWithShop() {
        return erviceAssociationWithShop;
    }

    public Review erviceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.erviceAssociationWithShop = serviceAssociationWithShop;
        return this;
    }

    public void setErviceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.erviceAssociationWithShop = serviceAssociationWithShop;
    }

    public Client getClient() {
        return client;
    }

    public Review client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        return id != null && id.equals(((Review) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", rating=" + getRating() +
            "}";
    }
}
