package sg.gov.tech.sao.customs.intranet.coststmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.gov.tech.sao.customs.intranet.coststmt.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
