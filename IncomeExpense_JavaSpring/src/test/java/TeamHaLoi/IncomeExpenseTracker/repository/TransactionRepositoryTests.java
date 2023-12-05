package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TransactionRepositoryTests {

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction sampleTransaction;

    @BeforeEach
    public void setUp() {

        // SAMPLE EXPENSE
        sampleTransaction = new Transaction();
        sampleTransaction.setAccountNumber("8258579513");
        sampleTransaction.setType("Expense");
        sampleTransaction.setAmount(BigDecimal.valueOf(-69.00));
        sampleTransaction.setDescription("OnlyFans");
        sampleTransaction.setDate(LocalDate.now()); // Set the date field
        sampleTransaction.setCreatedAt(LocalDateTime.now()); // Set the created_at field if required
        sampleTransaction.setUpdatedAt(LocalDateTime.now()); // Set the updated_at field if required
        // ... set other non-nullable fields as required

        // Save the transaction and use the returned instance
        sampleTransaction = transactionRepository.save(sampleTransaction);
        assertNotNull(sampleTransaction.getId()); // Ensure the transaction is saved and has an ID
    }



    @Test
    public void whenFindByAccountNumber_thenReturnTransactions() {
        List<Transaction> foundTransactions = transactionRepository.findByAccountNumber(sampleTransaction.getAccountNumber());
        assertThat(foundTransactions).contains(sampleTransaction);
    }

    @Test
    public void whenFindByAccountNumberAndType_thenReturnTransactions() {
        List<Transaction> foundTransactions = transactionRepository.findByAccountNumberAndType(sampleTransaction.getAccountNumber(), sampleTransaction.getType());
        assertThat(foundTransactions).contains(sampleTransaction);
    }

    @Test
    public void whenFindByAccountNumberAndRecurring_thenReturnTransactions() {
        List<Transaction> foundTransactions = transactionRepository.findByAccountNumberAndRecurring(sampleTransaction.getAccountNumber(), sampleTransaction.getRecurring());
        assertThat(foundTransactions).contains(sampleTransaction);
    }

    @Test
    public void whenFindByAccountNumberAndAmountGreaterThan_thenReturnTransactions() {
        BigDecimal thresholdAmount = sampleTransaction.getAmount().subtract(BigDecimal.ONE);
        List<Transaction> foundTransactions = transactionRepository.findByAccountNumberAndAmountGreaterThan(sampleTransaction.getAccountNumber(), thresholdAmount);
        assertThat(foundTransactions).contains(sampleTransaction);
    }

    @Test
    public void whenFindByAccountNumberAndDateBetween_thenReturnTransactions() {
        LocalDate startDate = sampleTransaction.getDate().minusDays(1);
        LocalDate endDate = sampleTransaction.getDate().plusDays(1);
        List<Transaction> foundTransactions = transactionRepository.findByAccountNumberAndDateBetween(sampleTransaction.getAccountNumber(), startDate, endDate);
        assertThat(foundTransactions).contains(sampleTransaction);
    }

    @Test
    public void whenFindTotalExpenseByAccountNumberAndDateBetween_thenReturnSum() {
        BigDecimal totalExpense = transactionRepository.findTotalExpenseByAccountNumberAndDateBetween(sampleTransaction.getAccountNumber(), LocalDate.now(), LocalDate.now().plusDays(1));
        assertThat(totalExpense).isNotNull();
    }

    @Test
    public void whenFindTotalDepositByAccountNumberAndDateBetween_thenReturnSum() {
        // SAMPLE EXPENSE
        sampleTransaction = new Transaction();
        sampleTransaction.setAccountNumber("8258579513");
        sampleTransaction.setType("Deposit");
        sampleTransaction.setAmount(BigDecimal.valueOf(69.00));
        sampleTransaction.setDescription("OnlyFans");
        sampleTransaction.setDate(LocalDate.now()); // Set the date field
        sampleTransaction.setCreatedAt(LocalDateTime.now()); // Set the created_at field if required
        sampleTransaction.setUpdatedAt(LocalDateTime.now()); // Set the updated_at field if required
        // ... set other non-nullable fields as required

        // Save the transaction and use the returned instance
        sampleTransaction = transactionRepository.save(sampleTransaction);
        assertNotNull(sampleTransaction.getId()); // Ensure the transaction is saved and has an ID
        BigDecimal totalDeposit = transactionRepository.findTotalDepositByAccountNumberAndDateBetween(sampleTransaction.getAccountNumber(), LocalDate.now(), LocalDate.now().plusDays(1));
        assertThat(totalDeposit).isNotNull();
    }

    @Test
    public void whenFindByDescriptionContainingAndAccountNumberLike_thenReturnTransactions() {
        List<Transaction> foundTransactions = transactionRepository.findByDescriptionContainingAndAccountNumberLike("%" + sampleTransaction.getDescription() + "%", sampleTransaction.getAccountNumber());
        assertThat(foundTransactions).contains(sampleTransaction);
    }
}
