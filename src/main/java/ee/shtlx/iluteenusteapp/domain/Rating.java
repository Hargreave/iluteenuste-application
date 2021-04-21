package ee.shtlx.iluteenusteapp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Rating {
    private String shopName;
    private Double shopRating;

    Rating() {}

    Rating(String shopName, Double shopRating) {
        this.shopName = shopName;
        this.shopRating = shopRating;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getShopRating() {
        return shopRating;
    }

    public void setShopRating(Double shopRating) {
        this.shopRating = shopRating;
    }
}
