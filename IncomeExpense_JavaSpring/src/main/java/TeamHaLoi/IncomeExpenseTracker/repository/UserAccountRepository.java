package TeamHaLoi.IncomeExpenseTracker.repository;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
}
