package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil.generateSalt;
import static TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil.hashPassword;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserBankAccountLinkRepositoryTests {

    @Autowired
    private UserBankAccountLinkRepository userBankAccountLinkRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private UserAccount userAccount;
    private BankAccount bankAccount;
    private UserBankAccountLink userBankAccountLink;

    @BeforeEach
    public void setUp() {
        // Setup UserAccount
        userAccount = new UserAccount();
        userAccount.setId(1);
        userAccount.setEmail("test@example.com");
        userAccount.setSalt(generateSalt());
        userAccount.setPassword(hashPassword("Password123", userAccount.getSalt()));
        // Set other necessary fields
        userAccount = userAccountRepository.save(userAccount);

        // Setup BankAccount
        bankAccount = new BankAccount();
        // Set properties for the bankAccount, assuming these fields exist in your BankAccount entity
        bankAccount.setAccountNumber("1234567890");
        bankAccount.setBalance(new BigDecimal("1000.00"));
        bankAccount.setBankName("Dummy Bank");
        bankAccount.setAccountType("CHECKING");
        bankAccount.setStatus("ACTIVE");
        bankAccount = bankAccountRepository.save(bankAccount);

        // Create and save a UserBankAccountLink for use in tests
        userBankAccountLink = new UserBankAccountLink();
        userBankAccountLink.setUserAccount(userAccount);
        userBankAccountLink.setBankAccount(bankAccount);
        userBankAccountLinkRepository.save(userBankAccountLink);
    }

    @Test
    public void whenFindByUserAccountId_thenReturnLinks() {
        List<UserBankAccountLink> foundLinks = userBankAccountLinkRepository.findByUserAccount_Id(userAccount.getId());
        assertThat(foundLinks).contains(userBankAccountLink);
    }

    @Test
    public void whenFindByBankAccountId_thenReturnLinks() {
        List<UserBankAccountLink> foundLinks = userBankAccountLinkRepository.findByBankAccount_Id(bankAccount.getId());
        assertThat(foundLinks).contains(userBankAccountLink);
    }
}
