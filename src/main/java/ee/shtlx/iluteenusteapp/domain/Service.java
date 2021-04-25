package ee.shtlx.iluteenusteapp.domain;

import ee.shtlx.iluteenusteapp.domain.enumeration.Sex;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Service.
 */
@Entity
@Table(name = "service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "for_child", nullable = false)
    private Boolean forChild;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "service")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "service")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ServiceAssociationWithShop> serviceAssociationWithShops = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Service name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public Service sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Service status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isForChild() {
        return forChild;
    }

    public Service forChild(Boolean forChild) {
        this.forChild = forChild;
        return this;
    }

    public void setForChild(Boolean forChild) {
        this.forChild = forChild;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Service bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Service addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setService(this);
        return this;
    }

    public Service removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setService(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<ServiceAssociationWithShop> getServiceAssociationWithShops() {
        return serviceAssociationWithShops;
    }

    public Service serviceAssociationWithShops(Set<ServiceAssociationWithShop> serviceAssociationWithShops) {
        this.serviceAssociationWithShops = serviceAssociationWithShops;
        return this;
    }

    public Service addServiceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.serviceAssociationWithShops.add(serviceAssociationWithShop);
        serviceAssociationWithShop.setService(this);
        return this;
    }

    public Service removeServiceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.serviceAssociationWithShops.remove(serviceAssociationWithShop);
        serviceAssociationWithShop.setService(null);
        return this;
    }

    public void setServiceAssociationWithShops(Set<ServiceAssociationWithShop> serviceAssociationWithShops) {
        this.serviceAssociationWithShops = serviceAssociationWithShops;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Service)) {
            return false;
        }
        return id != null && id.equals(((Service) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Service{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sex='" + getSex() + "'" +
            ", forChild='" + isForChild() + "'" +
            "}";
    }
}
