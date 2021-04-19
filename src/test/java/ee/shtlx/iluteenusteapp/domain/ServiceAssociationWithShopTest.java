package ee.shtlx.iluteenusteapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ee.shtlx.iluteenusteapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceAssociationWithShopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceAssociationWithShop.class);
        ServiceAssociationWithShop serviceAssociationWithShop1 = new ServiceAssociationWithShop();
        serviceAssociationWithShop1.setId(1L);
        ServiceAssociationWithShop serviceAssociationWithShop2 = new ServiceAssociationWithShop();
        serviceAssociationWithShop2.setId(serviceAssociationWithShop1.getId());
        assertThat(serviceAssociationWithShop1).isEqualTo(serviceAssociationWithShop2);
        serviceAssociationWithShop2.setId(2L);
        assertThat(serviceAssociationWithShop1).isNotEqualTo(serviceAssociationWithShop2);
        serviceAssociationWithShop1.setId(null);
        assertThat(serviceAssociationWithShop1).isNotEqualTo(serviceAssociationWithShop2);
    }
}
