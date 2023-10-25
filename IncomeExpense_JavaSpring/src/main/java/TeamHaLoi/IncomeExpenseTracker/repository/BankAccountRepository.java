package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByAccountNumber(String accountNumber);

}
