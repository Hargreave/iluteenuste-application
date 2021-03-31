package ee.shtlx.iluteenusteapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ee.shtlx.iluteenusteapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AadressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aadress.class);
        Aadress aadress1 = new Aadress();
        aadress1.setId(1L);
        Aadress aadress2 = new Aadress();
        aadress2.setId(aadress1.getId());
        assertThat(aadress1).isEqualTo(aadress2);
        aadress2.setId(2L);
        assertThat(aadress1).isNotEqualTo(aadress2);
        aadress1.setId(null);
        assertThat(aadress1).isNotEqualTo(aadress2);
    }
}
