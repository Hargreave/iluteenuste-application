package ee.shtlx.iluteenusteapp.web.rest;

import ee.shtlx.iluteenusteapp.domain.Aadress;
import ee.shtlx.iluteenusteapp.repository.AadressRepository;
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
 * REST controller for managing {@link ee.shtlx.iluteenusteapp.domain.Aadress}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AadressResource {
    private final Logger log = LoggerFactory.getLogger(AadressResource.class);

    private static final String ENTITY_NAME = "aadress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AadressRepository aadressRepository;

    public AadressResource(AadressRepository aadressRepository) {
        this.aadressRepository = aadressRepository;
    }

    /**
     * {@code POST  /aadresses} : Create a new aadress.
     *
     * @param aadress the aadress to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aadress, or with status {@code 400 (Bad Request)} if the aadress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aadresses")
    public ResponseEntity<Aadress> createAadress(@Valid @RequestBody Aadress aadress) throws URISyntaxException {
        log.debug("REST request to save Aadress : {}", aadress);
        if (aadress.getId() != null) {
            throw new BadRequestAlertException("A new aadress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aadress result = aadressRepository.save(aadress);
        return ResponseEntity
            .created(new URI("/api/aadresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aadresses} : Updates an existing aadress.
     *
     * @param aadress the aadress to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aadress,
     * or with status {@code 400 (Bad Request)} if the aadress is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aadress couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aadresses")
    public ResponseEntity<Aadress> updateAadress(@Valid @RequestBody Aadress aadress) throws URISyntaxException {
        log.debug("REST request to update Aadress : {}", aadress);
        if (aadress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aadress result = aadressRepository.save(aadress);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aadress.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aadresses} : get all the aadresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aadresses in body.
     */
    @GetMapping("/aadresses")
    public List<Aadress> getAllAadresses() {
        log.debug("REST request to get all Aadresses");
        return aadressRepository.findAll();
    }

    /**
     * {@code GET  /aadresses/:id} : get the "id" aadress.
     *
     * @param id the id of the aadress to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aadress, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aadresses/{id}")
    public ResponseEntity<Aadress> getAadress(@PathVariable Long id) {
        log.debug("REST request to get Aadress : {}", id);
        Optional<Aadress> aadress = aadressRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aadress);
    }

    /**
     * {@code DELETE  /aadresses/:id} : delete the "id" aadress.
     *
     * @param id the id of the aadress to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aadresses/{id}")
    public ResponseEntity<Void> deleteAadress(@PathVariable Long id) {
        log.debug("REST request to delete Aadress : {}", id);
        aadressRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
