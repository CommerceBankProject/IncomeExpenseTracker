package TeamHaLoi.IncomeExpenseTracker.repository;


import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Find transactions by bank account ID
    List<Transaction> findByAccountNumber(String accountNumber);

    // Find transactions by type
    List<Transaction> findByAccountNumberAndType(String accountNumber, String type);


    // Find transactions by recurring status
    List<Transaction> findByAccountNumberAndRecurring(String accountNumber, Boolean recurring);

    // Find transactions with an amount greater than a specified value
    List<Transaction> findByAccountNumberAndAmountGreaterThan(String accountNumber, BigDecimal amount);

    List<Transaction> findByAccountNumberAndDateBetween(String accountNumber, LocalDate startDate, LocalDate endDate);

    // Custom query using JPQL for finding transactions with a specific description pattern
    @Query("SELECT t FROM Transaction t WHERE t.description LIKE :description AND t.accountNumber LIKE :accountNumber")
    List<Transaction> findByDescriptionContainingAndAccountNumberLike(String description, String accountNumber);


}
