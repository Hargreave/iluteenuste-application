package ee.shtlx.iluteenusteapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ee.shtlx.iluteenusteapp.IluteenusteSystemApp;
import ee.shtlx.iluteenusteapp.domain.Booking;
import ee.shtlx.iluteenusteapp.repository.BookingRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BookingResource} REST controller.
 */
@SpringBootTest(classes = IluteenusteSystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BookingResourceIT {
    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SERVICE_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SERVICE_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_END = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACCEPTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACCEPTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACCEPTED_BY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACCEPTED_BY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADDITIONAL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_DESC = "BBBBBBBBBB";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBookingMockMvc;

    private Booking booking;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Booking createEntity(EntityManager em) {
        Booking booking = new Booking()
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .serviceStart(DEFAULT_SERVICE_START)
            .serviceEnd(DEFAULT_SERVICE_END)
            .acceptedDate(DEFAULT_ACCEPTED_DATE)
            .acceptedBy(DEFAULT_ACCEPTED_BY)
            .additionalDesc(DEFAULT_ADDITIONAL_DESC);
        return booking;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Booking createUpdatedEntity(EntityManager em) {
        Booking booking = new Booking()
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .serviceStart(UPDATED_SERVICE_START)
            .serviceEnd(UPDATED_SERVICE_END)
            .acceptedDate(UPDATED_ACCEPTED_DATE)
            .acceptedBy(UPDATED_ACCEPTED_BY)
            .additionalDesc(UPDATED_ADDITIONAL_DESC);
        return booking;
    }

    @BeforeEach
    public void initTest() {
        booking = createEntity(em);
    }

    @Test
    @Transactional
    public void createBooking() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();
        // Create the Booking
        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isCreated());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate + 1);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBooking.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBooking.getServiceStart()).isEqualTo(DEFAULT_SERVICE_START);
        assertThat(testBooking.getServiceEnd()).isEqualTo(DEFAULT_SERVICE_END);
        assertThat(testBooking.getAcceptedDate()).isEqualTo(DEFAULT_ACCEPTED_DATE);
        assertThat(testBooking.getAcceptedBy()).isEqualTo(DEFAULT_ACCEPTED_BY);
        assertThat(testBooking.getAdditionalDesc()).isEqualTo(DEFAULT_ADDITIONAL_DESC);
    }

    @Test
    @Transactional
    public void createBookingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();

        // Create the Booking with an existing ID
        booking.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setCreatedDate(null);

        // Create the Booking, which fails.

        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setStatus(null);

        // Create the Booking, which fails.

        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServiceStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setServiceStart(null);

        // Create the Booking, which fails.

        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServiceEndIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setServiceEnd(null);

        // Create the Booking, which fails.

        restBookingMockMvc
            .perform(post("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookings() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList
        restBookingMockMvc
            .perform(get("/api/bookings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booking.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].serviceStart").value(hasItem(DEFAULT_SERVICE_START.toString())))
            .andExpect(jsonPath("$.[*].serviceEnd").value(hasItem(DEFAULT_SERVICE_END.toString())))
            .andExpect(jsonPath("$.[*].acceptedDate").value(hasItem(DEFAULT_ACCEPTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].acceptedBy").value(hasItem(DEFAULT_ACCEPTED_BY.toString())))
            .andExpect(jsonPath("$.[*].additionalDesc").value(hasItem(DEFAULT_ADDITIONAL_DESC)));
    }

    @Test
    @Transactional
    public void getBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get the booking
        restBookingMockMvc
            .perform(get("/api/bookings/{id}", booking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(booking.getId().intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.serviceStart").value(DEFAULT_SERVICE_START.toString()))
            .andExpect(jsonPath("$.serviceEnd").value(DEFAULT_SERVICE_END.toString()))
            .andExpect(jsonPath("$.acceptedDate").value(DEFAULT_ACCEPTED_DATE.toString()))
            .andExpect(jsonPath("$.acceptedBy").value(DEFAULT_ACCEPTED_BY.toString()))
            .andExpect(jsonPath("$.additionalDesc").value(DEFAULT_ADDITIONAL_DESC));
    }

    @Test
    @Transactional
    public void getNonExistingBooking() throws Exception {
        // Get the booking
        restBookingMockMvc.perform(get("/api/bookings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // Update the booking
        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();
        // Disconnect from session so that the updates on updatedBooking are not directly saved in db
        em.detach(updatedBooking);
        updatedBooking
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .serviceStart(UPDATED_SERVICE_START)
            .serviceEnd(UPDATED_SERVICE_END)
            .acceptedDate(UPDATED_ACCEPTED_DATE)
            .acceptedBy(UPDATED_ACCEPTED_BY)
            .additionalDesc(UPDATED_ADDITIONAL_DESC);

        restBookingMockMvc
            .perform(
                put("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(updatedBooking))
            )
            .andExpect(status().isOk());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBooking.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBooking.getServiceStart()).isEqualTo(UPDATED_SERVICE_START);
        assertThat(testBooking.getServiceEnd()).isEqualTo(UPDATED_SERVICE_END);
        assertThat(testBooking.getAcceptedDate()).isEqualTo(UPDATED_ACCEPTED_DATE);
        assertThat(testBooking.getAcceptedBy()).isEqualTo(UPDATED_ACCEPTED_BY);
        assertThat(testBooking.getAdditionalDesc()).isEqualTo(UPDATED_ADDITIONAL_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingBooking() throws Exception {
        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingMockMvc
            .perform(put("/api/bookings").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        int databaseSizeBeforeDelete = bookingRepository.findAll().size();

        // Delete the booking
        restBookingMockMvc
            .perform(delete("/api/bookings/{id}", booking.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
