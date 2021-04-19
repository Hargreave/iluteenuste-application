package ee.shtlx.iluteenusteapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Shop.
 */
@Entity
@Table(name = "shop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "img_aadress")
    private String imgAadress;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "shop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "shop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ServiceAssociationWithShop> serviceAssociationWithShops = new HashSet<>();

    @OneToMany(mappedBy = "shop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Aadress> aadresses = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "shop_client",
        joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private Set<Client> clients = new HashSet<>();

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

    public Shop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Shop description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgAadress() {
        return imgAadress;
    }

    public Shop imgAadress(String imgAadress) {
        this.imgAadress = imgAadress;
        return this;
    }

    public void setImgAadress(String imgAadress) {
        this.imgAadress = imgAadress;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Shop createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Shop createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Shop modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public Shop modifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Shop bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Shop addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setShop(this);
        return this;
    }

    public Shop removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setShop(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<ServiceAssociationWithShop> getServiceAssociationWithShops() {
        return serviceAssociationWithShops;
    }

    public Shop serviceAssociationWithShops(Set<ServiceAssociationWithShop> serviceAssociationWithShops) {
        this.serviceAssociationWithShops = serviceAssociationWithShops;
        return this;
    }

    public Shop addServiceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.serviceAssociationWithShops.add(serviceAssociationWithShop);
        serviceAssociationWithShop.setShop(this);
        return this;
    }

    public Shop removeServiceAssociationWithShop(ServiceAssociationWithShop serviceAssociationWithShop) {
        this.serviceAssociationWithShops.remove(serviceAssociationWithShop);
        serviceAssociationWithShop.setShop(null);
        return this;
    }

    public void setServiceAssociationWithShops(Set<ServiceAssociationWithShop> serviceAssociationWithShops) {
        this.serviceAssociationWithShops = serviceAssociationWithShops;
    }

    public Set<Aadress> getAadresses() {
        return aadresses;
    }

    public Shop aadresses(Set<Aadress> aadresses) {
        this.aadresses = aadresses;
        return this;
    }

    public Shop addAadress(Aadress aadress) {
        this.aadresses.add(aadress);
        aadress.setShop(this);
        return this;
    }

    public Shop removeAadress(Aadress aadress) {
        this.aadresses.remove(aadress);
        aadress.setShop(null);
        return this;
    }

    public void setAadresses(Set<Aadress> aadresses) {
        this.aadresses = aadresses;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Shop clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Shop addClient(Client client) {
        this.clients.add(client);
        client.getShops().add(this);
        return this;
    }

    public Shop removeClient(Client client) {
        this.clients.remove(client);
        client.getShops().remove(this);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
        return id != null && id.equals(((Shop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Shop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", imgAadress='" + getImgAadress() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
