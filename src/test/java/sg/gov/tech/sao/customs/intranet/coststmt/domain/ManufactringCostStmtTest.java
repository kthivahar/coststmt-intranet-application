package sg.gov.tech.sao.customs.intranet.coststmt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sg.gov.tech.sao.customs.intranet.coststmt.web.rest.TestUtil;

public class ManufactringCostStmtTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufactringCostStmt.class);
        ManufactringCostStmt manufactringCostStmt1 = new ManufactringCostStmt();
        manufactringCostStmt1.setId(1L);
        ManufactringCostStmt manufactringCostStmt2 = new ManufactringCostStmt();
        manufactringCostStmt2.setId(manufactringCostStmt1.getId());
        assertThat(manufactringCostStmt1).isEqualTo(manufactringCostStmt2);
        manufactringCostStmt2.setId(2L);
        assertThat(manufactringCostStmt1).isNotEqualTo(manufactringCostStmt2);
        manufactringCostStmt1.setId(null);
        assertThat(manufactringCostStmt1).isNotEqualTo(manufactringCostStmt2);
    }
}
