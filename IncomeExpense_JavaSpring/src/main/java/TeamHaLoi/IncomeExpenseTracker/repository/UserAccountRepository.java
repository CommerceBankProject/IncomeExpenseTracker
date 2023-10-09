package TeamHaLoi.IncomeExpenseTracker.repository;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
}