package ee.shtlx.iluteenusteapp.web.rest;

import ee.shtlx.iluteenusteapp.domain.ServiceAssociationWithShop;
import ee.shtlx.iluteenusteapp.repository.ServiceAssociationWithShopRepository;
import ee.shtlx.iluteenusteapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ee.shtlx.iluteenusteapp.domain.ServiceAssociationWithShop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ServiceAssociationWithShopResource {
    private final Logger log = LoggerFactory.getLogger(ServiceAssociationWithShopResource.class);

    private static final String ENTITY_NAME = "serviceAssociationWithShop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceAssociationWithShopRepository serviceAssociationWithShopRepository;

    public ServiceAssociationWithShopResource(ServiceAssociationWithShopRepository serviceAssociationWithShopRepository) {
        this.serviceAssociationWithShopRepository = serviceAssociationWithShopRepository;
    }

    /**
     * {@code POST  /service-association-with-shops} : Create a new serviceAssociationWithShop.
     *
     * @param serviceAssociationWithShop the serviceAssociationWithShop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceAssociationWithShop, or with status {@code 400 (Bad Request)} if the serviceAssociationWithShop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-association-with-shops")
    public ResponseEntity<ServiceAssociationWithShop> createServiceAssociationWithShop(
        @Valid @RequestBody ServiceAssociationWithShop serviceAssociationWithShop
    )
        throws URISyntaxException {
        log.debug("REST request to save ServiceAssociationWithShop : {}", serviceAssociationWithShop);
        if (serviceAssociationWithShop.getId() != null) {
            throw new BadRequestAlertException("A new serviceAssociationWithShop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceAssociationWithShop result = serviceAssociationWithShopRepository.save(serviceAssociationWithShop);
        return ResponseEntity
            .created(new URI("/api/service-association-with-shops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-association-with-shops} : Updates an existing serviceAssociationWithShop.
     *
     * @param serviceAssociationWithShop the serviceAssociationWithShop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceAssociationWithShop,
     * or with status {@code 400 (Bad Request)} if the serviceAssociationWithShop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceAssociationWithShop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-association-with-shops")
    public ResponseEntity<ServiceAssociationWithShop> updateServiceAssociationWithShop(
        @Valid @RequestBody ServiceAssociationWithShop serviceAssociationWithShop
    )
        throws URISyntaxException {
        log.debug("REST request to update ServiceAssociationWithShop : {}", serviceAssociationWithShop);
        if (serviceAssociationWithShop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceAssociationWithShop result = serviceAssociationWithShopRepository.save(serviceAssociationWithShop);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceAssociationWithShop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-association-with-shops} : get all the serviceAssociationWithShops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceAssociationWithShops in body.
     */
    @GetMapping("/service-association-with-shops")
    public List<ServiceAssociationWithShop> getAllServiceAssociationWithShops() {
        log.debug("REST request to get all ServiceAssociationWithShops");
        return serviceAssociationWithShopRepository.findAll();
    }

    /**
     * {@code GET  /service-association-with-shops/:id} : get the "id" serviceAssociationWithShop.
     *
     * @param id the id of the serviceAssociationWithShop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceAssociationWithShop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-association-with-shops/{id}")
    public ResponseEntity<ServiceAssociationWithShop> getServiceAssociationWithShop(@PathVariable Long id) {
        log.debug("REST request to get ServiceAssociationWithShop : {}", id);
        Optional<ServiceAssociationWithShop> serviceAssociationWithShop = serviceAssociationWithShopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serviceAssociationWithShop);
    }

    /**
     * {@code DELETE  /service-association-with-shops/:id} : delete the "id" serviceAssociationWithShop.
     *
     * @param id the id of the serviceAssociationWithShop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-association-with-shops/{id}")
    public ResponseEntity<Void> deleteServiceAssociationWithShop(@PathVariable Long id) {
        log.debug("REST request to delete ServiceAssociationWithShop : {}", id);
        serviceAssociationWithShopRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
