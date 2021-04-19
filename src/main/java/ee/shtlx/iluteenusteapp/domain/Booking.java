package ee.shtlx.iluteenusteapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "service_start", nullable = false)
    private LocalDate serviceStart;

    @NotNull
    @Column(name = "service_end", nullable = false)
    private LocalDate serviceEnd;

    @Column(name = "accepted_date")
    private LocalDate acceptedDate;

    @Column(name = "accepted_by")
    private LocalDate acceptedBy;

    @Column(name = "additional_desc")
    private String additionalDesc;

    @ManyToOne
    @JsonIgnoreProperties(value = "bookings", allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = "bookings", allowSetters = true)
    private Service service;

    @ManyToOne
    @JsonIgnoreProperties(value = "bookings", allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Booking createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public Booking status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getServiceStart() {
        return serviceStart;
    }

    public Booking serviceStart(LocalDate serviceStart) {
        this.serviceStart = serviceStart;
        return this;
    }

    public void setServiceStart(LocalDate serviceStart) {
        this.serviceStart = serviceStart;
    }

    public LocalDate getServiceEnd() {
        return serviceEnd;
    }

    public Booking serviceEnd(LocalDate serviceEnd) {
        this.serviceEnd = serviceEnd;
        return this;
    }

    public void setServiceEnd(LocalDate serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    public Booking acceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
        return this;
    }

    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public LocalDate getAcceptedBy() {
        return acceptedBy;
    }

    public Booking acceptedBy(LocalDate acceptedBy) {
        this.acceptedBy = acceptedBy;
        return this;
    }

    public void setAcceptedBy(LocalDate acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getAdditionalDesc() {
        return additionalDesc;
    }

    public Booking additionalDesc(String additionalDesc) {
        this.additionalDesc = additionalDesc;
        return this;
    }

    public void setAdditionalDesc(String additionalDesc) {
        this.additionalDesc = additionalDesc;
    }

    public Client getClient() {
        return client;
    }

    public Booking client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public Booking service(Service service) {
        this.service = service;
        return this;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Shop getShop() {
        return shop;
    }

    public Booking shop(Shop shop) {
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
        if (!(o instanceof Booking)) {
            return false;
        }
        return id != null && id.equals(((Booking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", serviceStart='" + getServiceStart() + "'" +
            ", serviceEnd='" + getServiceEnd() + "'" +
            ", acceptedDate='" + getAcceptedDate() + "'" +
            ", acceptedBy='" + getAcceptedBy() + "'" +
            ", additionalDesc='" + getAdditionalDesc() + "'" +
            "}";
    }
}
