package ee.shtlx.iluteenusteapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ee.shtlx.iluteenusteapp.IluteenusteSystemApp;
import ee.shtlx.iluteenusteapp.domain.Aadress;
import ee.shtlx.iluteenusteapp.repository.AadressRepository;
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
 * Integration tests for the {@link AadressResource} REST controller.
 */
@SpringBootTest(classes = IluteenusteSystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AadressResourceIT {
    private static final String DEFAULT_FULL_AADRESS = "AAAAAAAAAA";
    private static final String UPDATED_FULL_AADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_X_COORDINATE = 1D;
    private static final Double UPDATED_X_COORDINATE = 2D;

    private static final Double DEFAULT_Y_COORDINATE = 1D;
    private static final Double UPDATED_Y_COORDINATE = 2D;

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CAR_CODE = "AAA";
    private static final String UPDATED_COUNTRY_CAR_CODE = "BBB";

    @Autowired
    private AadressRepository aadressRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAadressMockMvc;

    private Aadress aadress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aadress createEntity(EntityManager em) {
        Aadress aadress = new Aadress()
            .fullAadress(DEFAULT_FULL_AADRESS)
            .zipCode(DEFAULT_ZIP_CODE)
            .xCoordinate(DEFAULT_X_COORDINATE)
            .yCoordinate(DEFAULT_Y_COORDINATE)
            .city(DEFAULT_CITY)
            .county(DEFAULT_COUNTY)
            .countryCarCode(DEFAULT_COUNTRY_CAR_CODE);
        return aadress;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aadress createUpdatedEntity(EntityManager em) {
        Aadress aadress = new Aadress()
            .fullAadress(UPDATED_FULL_AADRESS)
            .zipCode(UPDATED_ZIP_CODE)
            .xCoordinate(UPDATED_X_COORDINATE)
            .yCoordinate(UPDATED_Y_COORDINATE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .countryCarCode(UPDATED_COUNTRY_CAR_CODE);
        return aadress;
    }

    @BeforeEach
    public void initTest() {
        aadress = createEntity(em);
    }

    @Test
    @Transactional
    public void createAadress() throws Exception {
        int databaseSizeBeforeCreate = aadressRepository.findAll().size();
        // Create the Aadress
        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isCreated());

        // Validate the Aadress in the database
        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeCreate + 1);
        Aadress testAadress = aadressList.get(aadressList.size() - 1);
        assertThat(testAadress.getFullAadress()).isEqualTo(DEFAULT_FULL_AADRESS);
        assertThat(testAadress.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testAadress.getxCoordinate()).isEqualTo(DEFAULT_X_COORDINATE);
        assertThat(testAadress.getyCoordinate()).isEqualTo(DEFAULT_Y_COORDINATE);
        assertThat(testAadress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAadress.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testAadress.getCountryCarCode()).isEqualTo(DEFAULT_COUNTRY_CAR_CODE);
    }

    @Test
    @Transactional
    public void createAadressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aadressRepository.findAll().size();

        // Create the Aadress with an existing ID
        aadress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        // Validate the Aadress in the database
        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFullAadressIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setFullAadress(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setZipCode(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkxCoordinateIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setxCoordinate(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkyCoordinateIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setyCoordinate(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setCity(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountyIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setCounty(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryCarCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = aadressRepository.findAll().size();
        // set the field null
        aadress.setCountryCarCode(null);

        // Create the Aadress, which fails.

        restAadressMockMvc
            .perform(post("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAadresses() throws Exception {
        // Initialize the database
        aadressRepository.saveAndFlush(aadress);

        // Get all the aadressList
        restAadressMockMvc
            .perform(get("/api/aadresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aadress.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullAadress").value(hasItem(DEFAULT_FULL_AADRESS)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].xCoordinate").value(hasItem(DEFAULT_X_COORDINATE.doubleValue())))
            .andExpect(jsonPath("$.[*].yCoordinate").value(hasItem(DEFAULT_Y_COORDINATE.doubleValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].countryCarCode").value(hasItem(DEFAULT_COUNTRY_CAR_CODE)));
    }

    @Test
    @Transactional
    public void getAadress() throws Exception {
        // Initialize the database
        aadressRepository.saveAndFlush(aadress);

        // Get the aadress
        restAadressMockMvc
            .perform(get("/api/aadresses/{id}", aadress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aadress.getId().intValue()))
            .andExpect(jsonPath("$.fullAadress").value(DEFAULT_FULL_AADRESS))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.xCoordinate").value(DEFAULT_X_COORDINATE.doubleValue()))
            .andExpect(jsonPath("$.yCoordinate").value(DEFAULT_Y_COORDINATE.doubleValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY))
            .andExpect(jsonPath("$.countryCarCode").value(DEFAULT_COUNTRY_CAR_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingAadress() throws Exception {
        // Get the aadress
        restAadressMockMvc.perform(get("/api/aadresses/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAadress() throws Exception {
        // Initialize the database
        aadressRepository.saveAndFlush(aadress);

        int databaseSizeBeforeUpdate = aadressRepository.findAll().size();

        // Update the aadress
        Aadress updatedAadress = aadressRepository.findById(aadress.getId()).get();
        // Disconnect from session so that the updates on updatedAadress are not directly saved in db
        em.detach(updatedAadress);
        updatedAadress
            .fullAadress(UPDATED_FULL_AADRESS)
            .zipCode(UPDATED_ZIP_CODE)
            .xCoordinate(UPDATED_X_COORDINATE)
            .yCoordinate(UPDATED_Y_COORDINATE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .countryCarCode(UPDATED_COUNTRY_CAR_CODE);

        restAadressMockMvc
            .perform(
                put("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(updatedAadress))
            )
            .andExpect(status().isOk());

        // Validate the Aadress in the database
        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeUpdate);
        Aadress testAadress = aadressList.get(aadressList.size() - 1);
        assertThat(testAadress.getFullAadress()).isEqualTo(UPDATED_FULL_AADRESS);
        assertThat(testAadress.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testAadress.getxCoordinate()).isEqualTo(UPDATED_X_COORDINATE);
        assertThat(testAadress.getyCoordinate()).isEqualTo(UPDATED_Y_COORDINATE);
        assertThat(testAadress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAadress.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testAadress.getCountryCarCode()).isEqualTo(UPDATED_COUNTRY_CAR_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingAadress() throws Exception {
        int databaseSizeBeforeUpdate = aadressRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAadressMockMvc
            .perform(put("/api/aadresses").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(aadress)))
            .andExpect(status().isBadRequest());

        // Validate the Aadress in the database
        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAadress() throws Exception {
        // Initialize the database
        aadressRepository.saveAndFlush(aadress);

        int databaseSizeBeforeDelete = aadressRepository.findAll().size();

        // Delete the aadress
        restAadressMockMvc
            .perform(delete("/api/aadresses/{id}", aadress.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Aadress> aadressList = aadressRepository.findAll();
        assertThat(aadressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
