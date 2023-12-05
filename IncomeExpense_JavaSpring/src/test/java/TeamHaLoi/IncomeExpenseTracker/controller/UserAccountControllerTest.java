package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.UserBankAccountLinkRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil;
import TeamHaLoi.IncomeExpenseTracker.exception.UserAccountNotFoundException;
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

import static TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil.generateSalt;
import static TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil.hashPassword;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserAccountController.class)
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAccountRepository userAccountRepository;

    @MockBean
    private UserBankAccountLinkRepository userBankAccountLinkRepository;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserAccount sampleUserAccount;

    @BeforeEach
    public void setUp() {
        sampleUserAccount = new UserAccount();
        sampleUserAccount.setId(1);
        sampleUserAccount.setEmail("test@example.com");
        sampleUserAccount.setSalt(generateSalt());
        sampleUserAccount.setPassword(hashPassword("Password123", sampleUserAccount.getSalt()));
        // Set other necessary fields
    }

    @Test
    public void whenGetAllUserAccounts_thenReturnUserAccounts() throws Exception {
        List<UserAccount> allUserAccounts = new ArrayList<>();
        allUserAccounts.add(sampleUserAccount);

        Mockito.when(userAccountRepository.findAll()).thenReturn(allUserAccounts);

        mockMvc.perform(get("/user_accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value(sampleUserAccount.getEmail()));
    }

    @Test
    public void whenGetUserAccountById_thenReturnUserAccount() throws Exception {
        Mockito.when(userAccountRepository.findById(sampleUserAccount.getId())).thenReturn(Optional.of(sampleUserAccount));

        mockMvc.perform(get("/user_accounts/" + sampleUserAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(sampleUserAccount.getEmail()));
    }

    @Test
    public void whenUpdateUserAccount_thenReturnUpdatedUserAccount() throws Exception {
        UserAccount updatedUserAccount = new UserAccount();
        updatedUserAccount.setEmail("updated@example.com");

        Mockito.when(userAccountRepository.findById(sampleUserAccount.getId())).thenReturn(Optional.of(sampleUserAccount));
        Mockito.when(userAccountRepository.save(Mockito.any(UserAccount.class))).thenReturn(updatedUserAccount);

        mockMvc.perform(put("/user_accounts/" + sampleUserAccount.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(updatedUserAccount.getEmail()));
    }

    @Test
    public void whenDeleteUserAccount_thenRespondWithOk() throws Exception {
        Mockito.when(userAccountRepository.findById(sampleUserAccount.getId())).thenReturn(Optional.of(sampleUserAccount));
        Mockito.doNothing().when(userAccountRepository).delete(sampleUserAccount);

        mockMvc.perform(delete("/user_accounts/" + sampleUserAccount.getId()))
                .andExpect(status().isOk());
    }

    // Additional tests for getUserBankAccounts and exceptions can be added here

}
