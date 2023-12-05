package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import TeamHaLoi.IncomeExpenseTracker.repository.UserBankAccountLinkRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.payload.BankAccountCreationRequestDto;
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
@WebMvcTest(UserBankAccountLinkController.class)
public class UserBankAccountLinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBankAccountLinkRepository userBankAccountLinkRepository;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @MockBean
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserBankAccountLink sampleLink;

    private final String testPayload = "{\"userId\":1,\"bankName\":\"SomeBank\",\"accountType\":\"SAVINGS\",\"initialBalance\":420.69,\"accountAlias\":\"Jojo:savings\"}";


    @BeforeEach
    public void setUp() {
        sampleLink = new UserBankAccountLink();
        // Initialize your sampleLink with appropriate values
    }

    @Test
    public void whenGetAllLinks_thenReturnLinks() throws Exception {
        List<UserBankAccountLink> allLinks = new ArrayList<>();
        allLinks.add(sampleLink);

        Mockito.when(userBankAccountLinkRepository.findAll()).thenReturn(allLinks);

        mockMvc.perform(get("/user_bank_account_links"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCreateAccountAndLink_thenReturnCreatedLink() throws Exception {
        // Arrange
        BankAccountCreationRequestDto requestDto = objectMapper.readValue(testPayload, BankAccountCreationRequestDto.class);
        UserAccount userAccount = new UserAccount();
        userAccount.setId(requestDto.getUserId());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankName(requestDto.getBankName());
        bankAccount.setAccountType(requestDto.getAccountType());
        bankAccount.setBalance(requestDto.getInitialBalance());
        UserBankAccountLink link = new UserBankAccountLink(userAccount, bankAccount, requestDto.getAccountAlias());

        Mockito.when(userAccountRepository.findById(requestDto.getUserId())).thenReturn(Optional.of(userAccount));
        Mockito.when(bankAccountRepository.save(Mockito.any(BankAccount.class))).thenReturn(bankAccount);
        Mockito.when(userBankAccountLinkRepository.save(Mockito.any(UserBankAccountLink.class))).thenReturn(link);

        // Act & Assert
        mockMvc.perform(post("/user_bank_account_links/create_account_and_link")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userAccount.id").value(requestDto.getUserId()))
                .andExpect(jsonPath("$.bankAccount.accountNumber").value(requestDto.getAccountNumber()));
    }

    // Test for the /{id} endpoint
    @Test
    public void whenGetLinkById_thenReturnLink() throws Exception {
        Integer linkId = 1;
        Mockito.when(userBankAccountLinkRepository.findById(linkId)).thenReturn(Optional.of(sampleLink));

        mockMvc.perform(get("/user_bank_account_links/" + linkId))
                .andExpect(status().isOk());
    }

    // Test for the /{id} delete endpoint
    @Test
    public void whenDeleteLink_thenRespondWithOk() throws Exception {
        Integer linkId = 1;
        Mockito.when(userBankAccountLinkRepository.findById(linkId)).thenReturn(Optional.of(sampleLink));
        Mockito.doNothing().when(userBankAccountLinkRepository).delete(sampleLink);

        mockMvc.perform(delete("/user_bank_account_links/" + linkId))
                .andExpect(status().isOk());
    }

    // Additional tests for getLinkById, createCheckingAndSavingsAccountAndLink, deleteLink, and exception handling can be added here
}
