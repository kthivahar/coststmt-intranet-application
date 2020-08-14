package sg.gov.tech.sao.customs.intranet.coststmt.web.rest;

import sg.gov.tech.sao.customs.intranet.coststmt.CostStmtIntranetApplicationApp;
import sg.gov.tech.sao.customs.intranet.coststmt.domain.Item;
import sg.gov.tech.sao.customs.intranet.coststmt.repository.ItemRepository;
import sg.gov.tech.sao.customs.intranet.coststmt.service.ItemService;

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
 * Integration tests for the {@link ItemResource} REST controller.
 */
@SpringBootTest(classes = CostStmtIntranetApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemResourceIT {

    private static final String DEFAULT_DESCRIPTION_OF_GOODS = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_OF_GOODS = "BBBBBBBBBB";

    private static final String DEFAULT_HS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;

    private static final String DEFAULT_MANUFACTRING_COST_STMT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTRING_COST_STMT_ID = "BBBBBBBBBB";

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemMockMvc;

    private Item item;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createEntity(EntityManager em) {
        Item item = new Item()
            .descriptionOfGoods(DEFAULT_DESCRIPTION_OF_GOODS)
            .hsCode(DEFAULT_HS_CODE)
            .unit(DEFAULT_UNIT)
            .quantity(DEFAULT_QUANTITY)
            .value(DEFAULT_VALUE)
            .manufactringCostStmtId(DEFAULT_MANUFACTRING_COST_STMT_ID);
        return item;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createUpdatedEntity(EntityManager em) {
        Item item = new Item()
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .hsCode(UPDATED_HS_CODE)
            .unit(UPDATED_UNIT)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE)
            .manufactringCostStmtId(UPDATED_MANUFACTRING_COST_STMT_ID);
        return item;
    }

    @BeforeEach
    public void initTest() {
        item = createEntity(em);
    }

    @Test
    @Transactional
    public void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();
        // Create the Item
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getDescriptionOfGoods()).isEqualTo(DEFAULT_DESCRIPTION_OF_GOODS);
        assertThat(testItem.getHsCode()).isEqualTo(DEFAULT_HS_CODE);
        assertThat(testItem.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testItem.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testItem.getManufactringCostStmtId()).isEqualTo(DEFAULT_MANUFACTRING_COST_STMT_ID);
    }

    @Test
    @Transactional
    public void createItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item with an existing ID
        item.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get all the itemList
        restItemMockMvc.perform(get("/api/items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId().intValue())))
            .andExpect(jsonPath("$.[*].descriptionOfGoods").value(hasItem(DEFAULT_DESCRIPTION_OF_GOODS)))
            .andExpect(jsonPath("$.[*].hsCode").value(hasItem(DEFAULT_HS_CODE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].manufactringCostStmtId").value(hasItem(DEFAULT_MANUFACTRING_COST_STMT_ID)));
    }
    
    @Test
    @Transactional
    public void getItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId().intValue()))
            .andExpect(jsonPath("$.descriptionOfGoods").value(DEFAULT_DESCRIPTION_OF_GOODS))
            .andExpect(jsonPath("$.hsCode").value(DEFAULT_HS_CODE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.manufactringCostStmtId").value(DEFAULT_MANUFACTRING_COST_STMT_ID));
    }
    @Test
    @Transactional
    public void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItem() throws Exception {
        // Initialize the database
        itemService.save(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findById(item.getId()).get();
        // Disconnect from session so that the updates on updatedItem are not directly saved in db
        em.detach(updatedItem);
        updatedItem
            .descriptionOfGoods(UPDATED_DESCRIPTION_OF_GOODS)
            .hsCode(UPDATED_HS_CODE)
            .unit(UPDATED_UNIT)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE)
            .manufactringCostStmtId(UPDATED_MANUFACTRING_COST_STMT_ID);

        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedItem)))
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getDescriptionOfGoods()).isEqualTo(UPDATED_DESCRIPTION_OF_GOODS);
        assertThat(testItem.getHsCode()).isEqualTo(UPDATED_HS_CODE);
        assertThat(testItem.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testItem.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testItem.getManufactringCostStmtId()).isEqualTo(UPDATED_MANUFACTRING_COST_STMT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc.perform(put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItem() throws Exception {
        // Initialize the database
        itemService.save(item);

        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Delete the item
        restItemMockMvc.perform(delete("/api/items/{id}", item.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
