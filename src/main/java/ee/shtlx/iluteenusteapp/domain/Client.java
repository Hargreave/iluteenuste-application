package ee.shtlx.iluteenusteapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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