package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BankAccountRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    // Sample bank account for testing
    private BankAccount sampleBankAccount;

    @BeforeEach
    public void setUp() {
        // Initialize a sample bank account and persist it for testing
        sampleBankAccount = new BankAccount();
        sampleBankAccount.setAccountNumber("123456789");
        sampleBankAccount.setBalance(BigDecimal.valueOf(1000.00));
        // Add other necessary fields
        entityManager.persist(sampleBankAccount);
        entityManager.flush();
    }

    @Test
    public void whenFindByAccountNumber_thenReturnBankAccount() {
        // Given setup

        // When
        BankAccount found = bankAccountRepository.findByAccountNumber(sampleBankAccount.getAccountNumber());

        // Then
        assertThat(found.getAccountNumber()).isEqualTo(sampleBankAccount.getAccountNumber());
        assertThat(found.getBalance()).isEqualTo(sampleBankAccount.getBalance());
    }

    // Additional tests can be added here, such as testing for a non-existent account number
}
