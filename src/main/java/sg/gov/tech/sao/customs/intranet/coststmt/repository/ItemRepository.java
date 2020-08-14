package sg.gov.tech.sao.customs.intranet.coststmt.repository;

import sg.gov.tech.sao.customs.intranet.coststmt.domain.Item;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Item entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
