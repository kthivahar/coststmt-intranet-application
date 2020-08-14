package sg.gov.tech.sao.customs.intranet.coststmt.web.rest;

import sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt;
import sg.gov.tech.sao.customs.intranet.coststmt.service.ManufactringCostStmtService;
import sg.gov.tech.sao.customs.intranet.coststmt.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt}.
 */
@RestController
@RequestMapping("/api")
public class ManufactringCostStmtResource {

    private final Logger log = LoggerFactory.getLogger(ManufactringCostStmtResource.class);

    private static final String ENTITY_NAME = "manufactringCostStmt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufactringCostStmtService manufactringCostStmtService;

    public ManufactringCostStmtResource(ManufactringCostStmtService manufactringCostStmtService) {
        this.manufactringCostStmtService = manufactringCostStmtService;
    }

    /**
     * {@code POST  /manufactring-cost-stmts} : Create a new manufactringCostStmt.
     *
     * @param manufactringCostStmt the manufactringCostStmt to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufactringCostStmt, or with status {@code 400 (Bad Request)} if the manufactringCostStmt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufactring-cost-stmts")
    public ResponseEntity<ManufactringCostStmt> createManufactringCostStmt(@RequestBody ManufactringCostStmt manufactringCostStmt) throws URISyntaxException {
        log.debug("REST request to save ManufactringCostStmt : {}", manufactringCostStmt);
        if (manufactringCostStmt.getId() != null) {
            throw new BadRequestAlertException("A new manufactringCostStmt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufactringCostStmt result = manufactringCostStmtService.save(manufactringCostStmt);
        return ResponseEntity.created(new URI("/api/manufactring-cost-stmts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufactring-cost-stmts} : Updates an existing manufactringCostStmt.
     *
     * @param manufactringCostStmt the manufactringCostStmt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufactringCostStmt,
     * or with status {@code 400 (Bad Request)} if the manufactringCostStmt is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufactringCostStmt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufactring-cost-stmts")
    public ResponseEntity<ManufactringCostStmt> updateManufactringCostStmt(@RequestBody ManufactringCostStmt manufactringCostStmt) throws URISyntaxException {
        log.debug("REST request to update ManufactringCostStmt : {}", manufactringCostStmt);
        if (manufactringCostStmt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManufactringCostStmt result = manufactringCostStmtService.save(manufactringCostStmt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufactringCostStmt.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manufactring-cost-stmts} : get all the manufactringCostStmts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufactringCostStmts in body.
     */
    @GetMapping("/manufactring-cost-stmts")
    public ResponseEntity<List<ManufactringCostStmt>> getAllManufactringCostStmts(Pageable pageable) {
        log.debug("REST request to get a page of ManufactringCostStmts");
        Page<ManufactringCostStmt> page = manufactringCostStmtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /manufactring-cost-stmts/:id} : get the "id" manufactringCostStmt.
     *
     * @param id the id of the manufactringCostStmt to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufactringCostStmt, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufactring-cost-stmts/{id}")
    public ResponseEntity<ManufactringCostStmt> getManufactringCostStmt(@PathVariable Long id) {
        log.debug("REST request to get ManufactringCostStmt : {}", id);
        Optional<ManufactringCostStmt> manufactringCostStmt = manufactringCostStmtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufactringCostStmt);
    }

    /**
     * {@code DELETE  /manufactring-cost-stmts/:id} : delete the "id" manufactringCostStmt.
     *
     * @param id the id of the manufactringCostStmt to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufactring-cost-stmts/{id}")
    public ResponseEntity<Void> deleteManufactringCostStmt(@PathVariable Long id) {
        log.debug("REST request to delete ManufactringCostStmt : {}", id);
        manufactringCostStmtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
