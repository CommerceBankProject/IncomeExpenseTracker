package TeamHaLoi.IncomeExpenseTracker.repository;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Repository
@RequestMapping("/api/users")
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

}