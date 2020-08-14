package sg.gov.tech.sao.customs.intranet.coststmt.web.rest;

import sg.gov.tech.sao.customs.intranet.coststmt.CostStmtIntranetApplicationApp;
import sg.gov.tech.sao.customs.intranet.coststmt.domain.ManufactringCostStmt;
import sg.gov.tech.sao.customs.intranet.coststmt.repository.ManufactringCostStmtRepository;
import sg.gov.tech.sao.customs.intranet.coststmt.service.ManufactringCostStmtService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ManufactringCostStmtResource} REST controller.
 */
@SpringBootTest(classes = CostStmtIntranetApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ManufactringCostStmtResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUE_ENTITY_NUMBER_UEN = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_ENTITY_NUMBER_UEN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPORTER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EXPORTER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EU_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EU_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_RADIO_1 = "AAAAAAAAAA";
    private static final String UPDATED_RADIO_1 = "BBBBBBBBBB";

    @Autowired
    private ManufactringCostStmtRepository manufactringCostStmtRepository;

    @Autowired
    private ManufactringCostStmtService manufactringCostStmtService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManufactringCostStmtMockMvc;

    private ManufactringCostStmt manufactringCostStmt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufactringCostStmt createEntity(EntityManager em) {
        ManufactringCostStmt manufactringCostStmt = new ManufactringCostStmt()
            .companyName(DEFAULT_COMPANY_NAME)
            .uniqueEntityNumberUen(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN)
            .address(DEFAULT_ADDRESS)
            .nameOfContactPerson(DEFAULT_NAME_OF_CONTACT_PERSON)
            .designation(DEFAULT_DESIGNATION)
            .contactNo(DEFAULT_CONTACT_NO)
            .email(DEFAULT_EMAIL)
            .exporterCompanyName(DEFAULT_EXPORTER_COMPANY_NAME)
            .exporterAddress(DEFAULT_EXPORTER_ADDRESS)
            .euCompanyName(DEFAULT_EU_COMPANY_NAME)
            .country(DEFAULT_COUNTRY)
            .radio1(DEFAULT_RADIO_1);
        return manufactringCostStmt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufactringCostStmt createUpdatedEntity(EntityManager em) {
        ManufactringCostStmt manufactringCostStmt = new ManufactringCostStmt()
            .companyName(UPDATED_COMPANY_NAME)
            .uniqueEntityNumberUen(UPDATED_UNIQUE_ENTITY_NUMBER_UEN)
            .address(UPDATED_ADDRESS)
            .nameOfContactPerson(UPDATED_NAME_OF_CONTACT_PERSON)
            .designation(UPDATED_DESIGNATION)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .exporterCompanyName(UPDATED_EXPORTER_COMPANY_NAME)
            .exporterAddress(UPDATED_EXPORTER_ADDRESS)
            .euCompanyName(UPDATED_EU_COMPANY_NAME)
            .country(UPDATED_COUNTRY)
            .radio1(UPDATED_RADIO_1);
        return manufactringCostStmt;
    }

    @BeforeEach
    public void initTest() {
        manufactringCostStmt = createEntity(em);
    }

    @Test
    @Transactional
    public void createManufactringCostStmt() throws Exception {
        int databaseSizeBeforeCreate = manufactringCostStmtRepository.findAll().size();
        // Create the ManufactringCostStmt
        restManufactringCostStmtMockMvc.perform(post("/api/manufactring-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactringCostStmt)))
            .andExpect(status().isCreated());

        // Validate the ManufactringCostStmt in the database
        List<ManufactringCostStmt> manufactringCostStmtList = manufactringCostStmtRepository.findAll();
        assertThat(manufactringCostStmtList).hasSize(databaseSizeBeforeCreate + 1);
        ManufactringCostStmt testManufactringCostStmt = manufactringCostStmtList.get(manufactringCostStmtList.size() - 1);
        assertThat(testManufactringCostStmt.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getUniqueEntityNumberUen()).isEqualTo(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN);
        assertThat(testManufactringCostStmt.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testManufactringCostStmt.getNameOfContactPerson()).isEqualTo(DEFAULT_NAME_OF_CONTACT_PERSON);
        assertThat(testManufactringCostStmt.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testManufactringCostStmt.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testManufactringCostStmt.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testManufactringCostStmt.getExporterCompanyName()).isEqualTo(DEFAULT_EXPORTER_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getExporterAddress()).isEqualTo(DEFAULT_EXPORTER_ADDRESS);
        assertThat(testManufactringCostStmt.getEuCompanyName()).isEqualTo(DEFAULT_EU_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testManufactringCostStmt.getRadio1()).isEqualTo(DEFAULT_RADIO_1);
    }

    @Test
    @Transactional
    public void createManufactringCostStmtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufactringCostStmtRepository.findAll().size();

        // Create the ManufactringCostStmt with an existing ID
        manufactringCostStmt.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufactringCostStmtMockMvc.perform(post("/api/manufactring-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactringCostStmt)))
            .andExpect(status().isBadRequest());

        // Validate the ManufactringCostStmt in the database
        List<ManufactringCostStmt> manufactringCostStmtList = manufactringCostStmtRepository.findAll();
        assertThat(manufactringCostStmtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManufactringCostStmts() throws Exception {
        // Initialize the database
        manufactringCostStmtRepository.saveAndFlush(manufactringCostStmt);

        // Get all the manufactringCostStmtList
        restManufactringCostStmtMockMvc.perform(get("/api/manufactring-cost-stmts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufactringCostStmt.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].uniqueEntityNumberUen").value(hasItem(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].nameOfContactPerson").value(hasItem(DEFAULT_NAME_OF_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].exporterCompanyName").value(hasItem(DEFAULT_EXPORTER_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].exporterAddress").value(hasItem(DEFAULT_EXPORTER_ADDRESS)))
            .andExpect(jsonPath("$.[*].euCompanyName").value(hasItem(DEFAULT_EU_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].radio1").value(hasItem(DEFAULT_RADIO_1)));
    }
    
    @Test
    @Transactional
    public void getManufactringCostStmt() throws Exception {
        // Initialize the database
        manufactringCostStmtRepository.saveAndFlush(manufactringCostStmt);

        // Get the manufactringCostStmt
        restManufactringCostStmtMockMvc.perform(get("/api/manufactring-cost-stmts/{id}", manufactringCostStmt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manufactringCostStmt.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.uniqueEntityNumberUen").value(DEFAULT_UNIQUE_ENTITY_NUMBER_UEN))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.nameOfContactPerson").value(DEFAULT_NAME_OF_CONTACT_PERSON))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.exporterCompanyName").value(DEFAULT_EXPORTER_COMPANY_NAME))
            .andExpect(jsonPath("$.exporterAddress").value(DEFAULT_EXPORTER_ADDRESS))
            .andExpect(jsonPath("$.euCompanyName").value(DEFAULT_EU_COMPANY_NAME))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.radio1").value(DEFAULT_RADIO_1));
    }
    @Test
    @Transactional
    public void getNonExistingManufactringCostStmt() throws Exception {
        // Get the manufactringCostStmt
        restManufactringCostStmtMockMvc.perform(get("/api/manufactring-cost-stmts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManufactringCostStmt() throws Exception {
        // Initialize the database
        manufactringCostStmtService.save(manufactringCostStmt);

        int databaseSizeBeforeUpdate = manufactringCostStmtRepository.findAll().size();

        // Update the manufactringCostStmt
        ManufactringCostStmt updatedManufactringCostStmt = manufactringCostStmtRepository.findById(manufactringCostStmt.getId()).get();
        // Disconnect from session so that the updates on updatedManufactringCostStmt are not directly saved in db
        em.detach(updatedManufactringCostStmt);
        updatedManufactringCostStmt
            .companyName(UPDATED_COMPANY_NAME)
            .uniqueEntityNumberUen(UPDATED_UNIQUE_ENTITY_NUMBER_UEN)
            .address(UPDATED_ADDRESS)
            .nameOfContactPerson(UPDATED_NAME_OF_CONTACT_PERSON)
            .designation(UPDATED_DESIGNATION)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .exporterCompanyName(UPDATED_EXPORTER_COMPANY_NAME)
            .exporterAddress(UPDATED_EXPORTER_ADDRESS)
            .euCompanyName(UPDATED_EU_COMPANY_NAME)
            .country(UPDATED_COUNTRY)
            .radio1(UPDATED_RADIO_1);

        restManufactringCostStmtMockMvc.perform(put("/api/manufactring-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedManufactringCostStmt)))
            .andExpect(status().isOk());

        // Validate the ManufactringCostStmt in the database
        List<ManufactringCostStmt> manufactringCostStmtList = manufactringCostStmtRepository.findAll();
        assertThat(manufactringCostStmtList).hasSize(databaseSizeBeforeUpdate);
        ManufactringCostStmt testManufactringCostStmt = manufactringCostStmtList.get(manufactringCostStmtList.size() - 1);
        assertThat(testManufactringCostStmt.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getUniqueEntityNumberUen()).isEqualTo(UPDATED_UNIQUE_ENTITY_NUMBER_UEN);
        assertThat(testManufactringCostStmt.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testManufactringCostStmt.getNameOfContactPerson()).isEqualTo(UPDATED_NAME_OF_CONTACT_PERSON);
        assertThat(testManufactringCostStmt.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testManufactringCostStmt.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testManufactringCostStmt.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testManufactringCostStmt.getExporterCompanyName()).isEqualTo(UPDATED_EXPORTER_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getExporterAddress()).isEqualTo(UPDATED_EXPORTER_ADDRESS);
        assertThat(testManufactringCostStmt.getEuCompanyName()).isEqualTo(UPDATED_EU_COMPANY_NAME);
        assertThat(testManufactringCostStmt.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testManufactringCostStmt.getRadio1()).isEqualTo(UPDATED_RADIO_1);
    }

    @Test
    @Transactional
    public void updateNonExistingManufactringCostStmt() throws Exception {
        int databaseSizeBeforeUpdate = manufactringCostStmtRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufactringCostStmtMockMvc.perform(put("/api/manufactring-cost-stmts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactringCostStmt)))
            .andExpect(status().isBadRequest());

        // Validate the ManufactringCostStmt in the database
        List<ManufactringCostStmt> manufactringCostStmtList = manufactringCostStmtRepository.findAll();
        assertThat(manufactringCostStmtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManufactringCostStmt() throws Exception {
        // Initialize the database
        manufactringCostStmtService.save(manufactringCostStmt);

        int databaseSizeBeforeDelete = manufactringCostStmtRepository.findAll().size();

        // Delete the manufactringCostStmt
        restManufactringCostStmtMockMvc.perform(delete("/api/manufactring-cost-stmts/{id}", manufactringCostStmt.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ManufactringCostStmt> manufactringCostStmtList = manufactringCostStmtRepository.findAll();
        assertThat(manufactringCostStmtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
