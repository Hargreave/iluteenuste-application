package ee.shtlx.iluteenusteapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ee.shtlx.iluteenusteapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BookingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Booking.class);
        Booking booking1 = new Booking();
        booking1.setId(1L);
        Booking booking2 = new Booking();
        booking2.setId(booking1.getId());
        assertThat(booking1).isEqualTo(booking2);
        booking2.setId(2L);
        assertThat(booking1).isNotEqualTo(booking2);
        booking1.setId(null);
        assertThat(booking1).isNotEqualTo(booking2);
    }
}
