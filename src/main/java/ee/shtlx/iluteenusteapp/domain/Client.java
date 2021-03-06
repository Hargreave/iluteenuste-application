package ee.shtlx.iluteenusteapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "is_shop_owner", nullable = false)
    private Boolean isShopOwner;

    @NotNull
    @Size(min = 0, max = 15)
    @Column(name = "tel", length = 15, nullable = false)
    private String tel;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "clients")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Shop> shops = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsShopOwner() {
        return isShopOwner;
    }

    public Client isShopOwner(Boolean isShopOwner) {
        this.isShopOwner = isShopOwner;
        return this;
    }

    public void setIsShopOwner(Boolean isShopOwner) {
        this.isShopOwner = isShopOwner;
    }

    public String getTel() {
        return tel;
    }

    public Client tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User getUser() {
        return user;
    }

    public Client user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Client bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Client addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setClient(this);
        return this;
    }

    public Client removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setClient(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Client reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Client addReview(Review review) {
        this.reviews.add(review);
        review.setClient(this);
        return this;
    }

    public Client removeReview(Review review) {
        this.reviews.remove(review);
        review.setClient(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Shop> getShops() {
        return shops;
    }

    public Client shops(Set<Shop> shops) {
        this.shops = shops;
        return this;
    }

    public Client addShop(Shop shop) {
        this.shops.add(shop);
        shop.getClients().add(this);
        return this;
    }

    public Client removeShop(Shop shop) {
        this.shops.remove(shop);
        shop.getClients().remove(this);
        return this;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", isShopOwner='" + isIsShopOwner() + "'" +
            ", tel='" + getTel() + "'" +
            "}";
    }
}
