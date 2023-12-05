package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import TeamHaLoi.IncomeExpenseTracker.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Transaction sampleTransaction;

    @BeforeEach
    public void setUp() {
        sampleTransaction = new Transaction();
        sampleTransaction.setAccountNumber("8258579513");
        sampleTransaction.setType("Expense");
        sampleTransaction.setAmount(BigDecimal.valueOf(-69.00));
        sampleTransaction.setDescription("OnlyFans");

    }

    @Test
    public void whenGetAllTransactions_thenReturnTransactions() throws Exception {
        List<Transaction> allTransactions = Arrays.asList(sampleTransaction);
        Mockito.when(transactionService.getAllTransactions()).thenReturn(allTransactions);

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleTransaction.getId()));
    }


    @Test
    public void whenCreateTransaction_thenReturnCreatedTransaction() throws Exception {
        Mockito.when(transactionService.createTransaction(Mockito.any(Transaction.class))).thenReturn(sampleTransaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleTransaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleTransaction.getId()));
    }

    @Test
    public void whenGetAllTransactions_thenReturnTransactionsList() throws Exception {
        Mockito.when(transactionService.getAllTransactions()).thenReturn(Collections.singletonList(sampleTransaction));

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(sampleTransaction.getId()));
    }


    @Test
    public void whenDeleteTransaction_thenRespondWithOk() throws Exception {
        Integer transactionId = 1; // Use a transaction ID that would be considered valid.

        // Mock the service layer to not perform any action (or to simulate the action without throwing exceptions)
        Mockito.doNothing().when(transactionService).deleteTransaction(transactionId);

        // Perform the delete request with the mocked transaction ID and expect a 200 OK response
        mockMvc.perform(delete("/api/transactions/{id}", transactionId))
                .andExpect(status().isOk());
    }

    // Similar tests should be written for the other endpoints, mocking the service layer responses and verifying controller behavior.

    // Example test for the endpoint to get transactions by account number
    @Test
    public void whenGetTransactionsByAccountNumber_thenReturnTransactionsList() throws Exception {
        Mockito.when(transactionService.getTransactionByAccountNumber(anyString()))
                .thenReturn(Collections.singletonList(sampleTransaction));

        mockMvc.perform(get("/api/transactions/account/{accountNumber}", sampleTransaction.getAccountNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountNumber").value(sampleTransaction.getAccountNumber()));
    }

    // Additional tests for the rest of the endpoints can be added here
}
