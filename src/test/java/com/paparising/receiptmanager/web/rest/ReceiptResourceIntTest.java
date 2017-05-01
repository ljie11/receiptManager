package com.paparising.receiptmanager.web.rest;

import com.paparising.receiptmanager.ReceiptManagerApp;
import com.paparising.receiptmanager.domain.Receipt;
import com.paparising.receiptmanager.repository.ReceiptRepository;
import com.paparising.receiptmanager.service.ReceiptService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.paparising.receiptmanager.domain.enumeration.ReceiptCategory;
/**
 * Test class for the ReceiptResource REST controller.
 *
 * @see ReceiptResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReceiptManagerApp.class)
public class ReceiptResourceIntTest {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final ReceiptCategory DEFAULT_CATEGORY = ReceiptCategory.grocery;
    private static final ReceiptCategory UPDATED_CATEGORY = ReceiptCategory.entertainment;

    private static final Integer DEFAULT_CREATED_BY = 1;
    private static final Integer UPDATED_CREATED_BY = 2;

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATED_DATE);
    private static final String DEFAULT_TOTAL = "AAAAA";
    private static final String UPDATED_TOTAL = "BBBBB";

    private static final Double DEFAULT_CLAIMED_AMOUNT = 1D;
    private static final Double UPDATED_CLAIMED_AMOUNT = 2D;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ReceiptService receiptService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restReceiptMockMvc;

    private Receipt receipt;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReceiptResource receiptResource = new ReceiptResource();
        ReflectionTestUtils.setField(receiptResource, "receiptService", receiptService);
        this.restReceiptMockMvc = MockMvcBuilders.standaloneSetup(receiptResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receipt createEntity(EntityManager em) {
        Receipt receipt = new Receipt();
        receipt = new Receipt()
                .category(DEFAULT_CATEGORY)
                .created_by(DEFAULT_CREATED_BY)
                .created_date(DEFAULT_CREATED_DATE)
                .total(DEFAULT_TOTAL)
                .claimed_amount(DEFAULT_CLAIMED_AMOUNT);
        return receipt;
    }

    @Before
    public void initTest() {
        receipt = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceipt() throws Exception {
        int databaseSizeBeforeCreate = receiptRepository.findAll().size();

        // Create the Receipt

        restReceiptMockMvc.perform(post("/api/receipts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(receipt)))
                .andExpect(status().isCreated());

        // Validate the Receipt in the database
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeCreate + 1);
        Receipt testReceipt = receipts.get(receipts.size() - 1);
        assertThat(testReceipt.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testReceipt.getCreated_by()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testReceipt.getCreated_date()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testReceipt.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testReceipt.getClaimed_amount()).isEqualTo(DEFAULT_CLAIMED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllReceipts() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get all the receipts
        restReceiptMockMvc.perform(get("/api/receipts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(receipt.getId().intValue())))
                .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
                .andExpect(jsonPath("$.[*].created_by").value(hasItem(DEFAULT_CREATED_BY)))
                .andExpect(jsonPath("$.[*].created_date").value(hasItem(DEFAULT_CREATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.toString())))
                .andExpect(jsonPath("$.[*].claimed_amount").value(hasItem(DEFAULT_CLAIMED_AMOUNT.doubleValue())));
    }

    @Test
    @Transactional
    public void getReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get the receipt
        restReceiptMockMvc.perform(get("/api/receipts/{id}", receipt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(receipt.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.created_by").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.created_date").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.toString()))
            .andExpect(jsonPath("$.claimed_amount").value(DEFAULT_CLAIMED_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReceipt() throws Exception {
        // Get the receipt
        restReceiptMockMvc.perform(get("/api/receipts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceipt() throws Exception {
        // Initialize the database
        receiptService.save(receipt);

        int databaseSizeBeforeUpdate = receiptRepository.findAll().size();

        // Update the receipt
        Receipt updatedReceipt = receiptRepository.findOne(receipt.getId());
        updatedReceipt
                .category(UPDATED_CATEGORY)
                .created_by(UPDATED_CREATED_BY)
                .created_date(UPDATED_CREATED_DATE)
                .total(UPDATED_TOTAL)
                .claimed_amount(UPDATED_CLAIMED_AMOUNT);

        restReceiptMockMvc.perform(put("/api/receipts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedReceipt)))
                .andExpect(status().isOk());

        // Validate the Receipt in the database
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeUpdate);
        Receipt testReceipt = receipts.get(receipts.size() - 1);
        assertThat(testReceipt.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testReceipt.getCreated_by()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testReceipt.getCreated_date()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testReceipt.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testReceipt.getClaimed_amount()).isEqualTo(UPDATED_CLAIMED_AMOUNT);
    }

    @Test
    @Transactional
    public void deleteReceipt() throws Exception {
        // Initialize the database
        receiptService.save(receipt);

        int databaseSizeBeforeDelete = receiptRepository.findAll().size();

        // Get the receipt
        restReceiptMockMvc.perform(delete("/api/receipts/{id}", receipt.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
