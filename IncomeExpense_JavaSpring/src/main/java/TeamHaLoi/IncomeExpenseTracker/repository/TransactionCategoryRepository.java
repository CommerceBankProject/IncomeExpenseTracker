package TeamHaLoi.IncomeExpenseTracker.repository;


import TeamHaLoi.IncomeExpenseTracker.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Integer> {
    // Custom database queries if needed
}