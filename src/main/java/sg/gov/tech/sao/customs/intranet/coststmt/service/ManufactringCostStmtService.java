package sg.gov.tech.sao.customs.intranet.coststmt.service;

import sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ManufactringCostStmt}.
 */
public interface ManufactringCostStmtService {

    /**
     * Save a manufactringCostStmt.
     *
     * @param manufactringCostStmt the entity to save.
     * @return the persisted entity.
     */
    ManufactringCostStmt save(ManufactringCostStmt manufactringCostStmt);

    /**
     * Get all the manufactringCostStmts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ManufactringCostStmt> findAll(Pageable pageable);


    /**
     * Get the "id" manufactringCostStmt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ManufactringCostStmt> findOne(Long id);

    /**
     * Delete the "id" manufactringCostStmt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
