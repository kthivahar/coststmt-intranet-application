package sg.gov.tech.sao.customs.intranet.coststmt.service.impl;

import sg.gov.tech.sao.customs.intranet.coststmt.service.ManufactringCostStmtService;
import sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt;
import sg.gov.tech.sao.customs.intranet.coststmt.repository.ManufactringCostStmtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ManufactringCostStmt}.
 */
@Service
@Transactional
public class ManufactringCostStmtServiceImpl implements ManufactringCostStmtService {

    private final Logger log = LoggerFactory.getLogger(ManufactringCostStmtServiceImpl.class);

    private final ManufactringCostStmtRepository manufactringCostStmtRepository;

    public ManufactringCostStmtServiceImpl(ManufactringCostStmtRepository manufactringCostStmtRepository) {
        this.manufactringCostStmtRepository = manufactringCostStmtRepository;
    }

    @Override
    public ManufactringCostStmt save(ManufactringCostStmt manufactringCostStmt) {
        log.debug("Request to save ManufactringCostStmt : {}", manufactringCostStmt);
        return manufactringCostStmtRepository.save(manufactringCostStmt);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ManufactringCostStmt> findAll(Pageable pageable) {
        log.debug("Request to get all ManufactringCostStmts");
        return manufactringCostStmtRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManufactringCostStmt> findOne(Long id) {
        log.debug("Request to get ManufactringCostStmt : {}", id);
        return manufactringCostStmtRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ManufactringCostStmt : {}", id);
        manufactringCostStmtRepository.deleteById(id);
    }
}
