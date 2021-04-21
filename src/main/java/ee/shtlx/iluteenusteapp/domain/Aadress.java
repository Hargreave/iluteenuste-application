package ee.shtlx.iluteenusteapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Aadress.
 */
@Entity
@Table(name = "aadress")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Aadress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 10)
    @Column(name = "full_aadress", nullable = false)
    private String fullAadress;

    @NotNull
    @Size(max = 20)
    @Column(name = "zip_code", length = 20, nullable = false)
    private String zipCode;

    @NotNull
    @Column(name = "x_coordinate", nullable = false)
    private Double xCoordinate;

    @NotNull
    @Column(name = "y_coordinate", nullable = false)
    private Double yCoordinate;

    @NotNull
    @Size(min = 2, max = 3)
    @Column(name = "country", length = 3, nullable = false)
    private String country;

    @OneToOne(mappedBy = "aadress")
    @JsonIgnore
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullAadress() {
        return fullAadress;
    }

    public Aadress fullAadress(String fullAadress) {
        this.fullAadress = fullAadress;
        return this;
    }

    public void setFullAadress(String fullAadress) {
        this.fullAadress = fullAadress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Aadress zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public Aadress xCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public Aadress yCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getCountry() {
        return country;
    }

    public Aadress country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Shop getShop() {
        return shop;
    }

    public Aadress shop(Shop shop) {
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
        if (!(o instanceof Aadress)) {
            return false;
        }
        return id != null && id.equals(((Aadress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Aadress{" +
            "id=" + getId() +
            ", fullAadress='" + getFullAadress() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", xCoordinate=" + getxCoordinate() +
            ", yCoordinate=" + getyCoordinate() +
            ", country='" + getCountry() + "'" +
            "}";
    }
}
