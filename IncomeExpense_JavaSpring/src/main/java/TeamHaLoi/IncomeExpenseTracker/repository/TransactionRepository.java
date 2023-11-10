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
    List<Transaction> findByBankAccountId(Integer bankAccountId);

    // Find transactions by type
    List<Transaction> findByType(String type);

    // Find transactions within a certain date range
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Find transactions by category ID
    List<Transaction> findByCategoryId(Integer categoryId);

    // Find transactions by recurring status
    List<Transaction> findByRecurring(Boolean recurring);

    // Find transactions with an amount greater than a specified value
    List<Transaction> findByAmountGreaterThan(BigDecimal amount);

    // Custom query using JPQL for finding transactions with a specific description pattern
    @Query("SELECT t FROM Transaction t WHERE t.description LIKE %:description%")
    List<Transaction> findByDescriptionContaining(String description);

    // Custom query to find all transactions ordered by amount in descending order
    @Query("SELECT t FROM Transaction t ORDER BY t.amount DESC")
    List<Transaction> findAllOrderByAmountDesc();

    // Custom query using native SQL
    @Query(value = "SELECT * FROM transactions WHERE amount >= :amount", nativeQuery = true)
    List<Transaction> findByAmountGreaterThanOrEqualNative(BigDecimal amount);
}
