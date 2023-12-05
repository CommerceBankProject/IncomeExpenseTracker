package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.exception.BankAccountNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private BankAccount sampleBankAccount;

    @BeforeEach
    public void setUp() {
        sampleBankAccount = new BankAccount();
        sampleBankAccount.setId(1);
        sampleBankAccount.setAccountNumber("123456789");
        // Set other necessary fields
    }

    @Test
    public void whenGetAllBankAccounts_thenReturnBankAccounts() throws Exception {
        List<BankAccount> allBankAccounts = new ArrayList<>();
        allBankAccounts.add(sampleBankAccount);

        Mockito.when(bankAccountRepository.findAll()).thenReturn(allBankAccounts);

        mockMvc.perform(get("/bank_accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber").value(sampleBankAccount.getAccountNumber()));
    }

    @Test
    public void whenCreateBankAccount_thenReturnCreatedBankAccount() throws Exception {
        Mockito.when(bankAccountRepository.save(Mockito.any(BankAccount.class))).thenReturn(sampleBankAccount);

        mockMvc.perform(post("/bank_accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleBankAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(sampleBankAccount.getAccountNumber()));
    }

    @Test
    public void whenGetBankAccountById_thenReturnBankAccount() throws Exception {
        Mockito.when(bankAccountRepository.findById(sampleBankAccount.getId())).thenReturn(Optional.of(sampleBankAccount));

        mockMvc.perform(get("/bank_accounts/" + sampleBankAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(sampleBankAccount.getAccountNumber()));
    }

    @Test
    public void whenUpdateBankAccount_thenReturnUpdatedBankAccount() throws Exception {
        BankAccount updatedBankAccount = new BankAccount();
        updatedBankAccount.setAccountNumber("987654321");
        // Set other fields

        Mockito.when(bankAccountRepository.findById(sampleBankAccount.getId())).thenReturn(Optional.of(sampleBankAccount));
        Mockito.when(bankAccountRepository.save(Mockito.any(BankAccount.class))).thenReturn(updatedBankAccount);

        mockMvc.perform(put("/bank_accounts/" + sampleBankAccount.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBankAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(updatedBankAccount.getAccountNumber()));
    }

    @Test
    public void whenDeleteBankAccount_thenRespondWithOk() throws Exception {
        Mockito.when(bankAccountRepository.findById(sampleBankAccount.getId())).thenReturn(Optional.of(sampleBankAccount));
        Mockito.doNothing().when(bankAccountRepository).delete(sampleBankAccount);

        mockMvc.perform(delete("/bank_accounts/" + sampleBankAccount.getId()))
                .andExpect(status().isOk());
    }

    // Additional tests for handling exceptions and edge cases can be added here
}
