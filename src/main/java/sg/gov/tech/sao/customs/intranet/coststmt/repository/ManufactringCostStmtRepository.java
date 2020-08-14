package sg.gov.tech.sao.customs.intranet.coststmt.repository;

import sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ManufactringCostStmt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufactringCostStmtRepository extends JpaRepository<ManufactringCostStmt, Long> {
}
