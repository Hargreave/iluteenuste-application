package ee.shtlx.iluteenusteapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    @Column(name = "aadress", nullable = false)
    private String aadress;

    @NotNull
    @Column(name = "x_coordinate", nullable = false)
    private Double xCoordinate;

    @NotNull
    @Column(name = "y_coordinate", nullable = false)
    private Double yCoordinate;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "shop_client",
               joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
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

    public String getAadress() {
        return aadress;
    }

    public Shop aadress(String aadress) {
        this.aadress = aadress;
        return this;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public Shop xCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public Shop yCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
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
            ", aadress='" + getAadress() + "'" +
            ", xCoordinate=" + getxCoordinate() +
            ", yCoordinate=" + getyCoordinate() +
            "}";
    }
}
