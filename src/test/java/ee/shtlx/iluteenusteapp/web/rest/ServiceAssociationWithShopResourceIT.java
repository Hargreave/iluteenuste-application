package ee.shtlx.iluteenusteapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ee.shtlx.iluteenusteapp.IluteenusteSystemApp;
import ee.shtlx.iluteenusteapp.domain.ServiceAssociationWithShop;
import ee.shtlx.iluteenusteapp.repository.ServiceAssociationWithShopRepository;
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
 * Integration tests for the {@link ServiceAssociationWithShopResource} REST controller.
 */
@SpringBootTest(classes = IluteenusteSystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceAssociationWithShopResourceIT {
    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_TIME = 1;
    private static final Integer UPDATED_TIME = 2;

    @Autowired
    private ServiceAssociationWithShopRepository serviceAssociationWithShopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceAssociationWithShopMockMvc;

    private ServiceAssociationWithShop serviceAssociationWithShop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceAssociationWithShop createEntity(EntityManager em) {
        ServiceAssociationWithShop serviceAssociationWithShop = new ServiceAssociationWithShop().price(DEFAULT_PRICE).time(DEFAULT_TIME);
        return serviceAssociationWithShop;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceAssociationWithShop createUpdatedEntity(EntityManager em) {
        ServiceAssociationWithShop serviceAssociationWithShop = new ServiceAssociationWithShop().price(UPDATED_PRICE).time(UPDATED_TIME);
        return serviceAssociationWithShop;
    }

    @BeforeEach
    public void initTest() {
        serviceAssociationWithShop = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceAssociationWithShop() throws Exception {
        int databaseSizeBeforeCreate = serviceAssociationWithShopRepository.findAll().size();
        // Create the ServiceAssociationWithShop
        restServiceAssociationWithShopMockMvc
            .perform(
                post("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceAssociationWithShop))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceAssociationWithShop in the database
        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceAssociationWithShop testServiceAssociationWithShop = serviceAssociationWithShopList.get(
            serviceAssociationWithShopList.size() - 1
        );
        assertThat(testServiceAssociationWithShop.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testServiceAssociationWithShop.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createServiceAssociationWithShopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceAssociationWithShopRepository.findAll().size();

        // Create the ServiceAssociationWithShop with an existing ID
        serviceAssociationWithShop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceAssociationWithShopMockMvc
            .perform(
                post("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceAssociationWithShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceAssociationWithShop in the database
        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceAssociationWithShopRepository.findAll().size();
        // set the field null
        serviceAssociationWithShop.setPrice(null);

        // Create the ServiceAssociationWithShop, which fails.

        restServiceAssociationWithShopMockMvc
            .perform(
                post("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceAssociationWithShop))
            )
            .andExpect(status().isBadRequest());

        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceAssociationWithShopRepository.findAll().size();
        // set the field null
        serviceAssociationWithShop.setTime(null);

        // Create the ServiceAssociationWithShop, which fails.

        restServiceAssociationWithShopMockMvc
            .perform(
                post("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceAssociationWithShop))
            )
            .andExpect(status().isBadRequest());

        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServiceAssociationWithShops() throws Exception {
        // Initialize the database
        serviceAssociationWithShopRepository.saveAndFlush(serviceAssociationWithShop);

        // Get all the serviceAssociationWithShopList
        restServiceAssociationWithShopMockMvc
            .perform(get("/api/service-association-with-shops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceAssociationWithShop.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)));
    }

    @Test
    @Transactional
    public void getServiceAssociationWithShop() throws Exception {
        // Initialize the database
        serviceAssociationWithShopRepository.saveAndFlush(serviceAssociationWithShop);

        // Get the serviceAssociationWithShop
        restServiceAssociationWithShopMockMvc
            .perform(get("/api/service-association-with-shops/{id}", serviceAssociationWithShop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceAssociationWithShop.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME));
    }

    @Test
    @Transactional
    public void getNonExistingServiceAssociationWithShop() throws Exception {
        // Get the serviceAssociationWithShop
        restServiceAssociationWithShopMockMvc
            .perform(get("/api/service-association-with-shops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceAssociationWithShop() throws Exception {
        // Initialize the database
        serviceAssociationWithShopRepository.saveAndFlush(serviceAssociationWithShop);

        int databaseSizeBeforeUpdate = serviceAssociationWithShopRepository.findAll().size();

        // Update the serviceAssociationWithShop
        ServiceAssociationWithShop updatedServiceAssociationWithShop = serviceAssociationWithShopRepository
            .findById(serviceAssociationWithShop.getId())
            .get();
        // Disconnect from session so that the updates on updatedServiceAssociationWithShop are not directly saved in db
        em.detach(updatedServiceAssociationWithShop);
        updatedServiceAssociationWithShop.price(UPDATED_PRICE).time(UPDATED_TIME);

        restServiceAssociationWithShopMockMvc
            .perform(
                put("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedServiceAssociationWithShop))
            )
            .andExpect(status().isOk());

        // Validate the ServiceAssociationWithShop in the database
        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeUpdate);
        ServiceAssociationWithShop testServiceAssociationWithShop = serviceAssociationWithShopList.get(
            serviceAssociationWithShopList.size() - 1
        );
        assertThat(testServiceAssociationWithShop.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testServiceAssociationWithShop.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceAssociationWithShop() throws Exception {
        int databaseSizeBeforeUpdate = serviceAssociationWithShopRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceAssociationWithShopMockMvc
            .perform(
                put("/api/service-association-with-shops")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceAssociationWithShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceAssociationWithShop in the database
        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceAssociationWithShop() throws Exception {
        // Initialize the database
        serviceAssociationWithShopRepository.saveAndFlush(serviceAssociationWithShop);

        int databaseSizeBeforeDelete = serviceAssociationWithShopRepository.findAll().size();

        // Delete the serviceAssociationWithShop
        restServiceAssociationWithShopMockMvc
            .perform(
                delete("/api/service-association-with-shops/{id}", serviceAssociationWithShop.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceAssociationWithShop> serviceAssociationWithShopList = serviceAssociationWithShopRepository.findAll();
        assertThat(serviceAssociationWithShopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
