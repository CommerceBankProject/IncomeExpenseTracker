package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserBankAccountLinkRepository extends JpaRepository<UserBankAccountLink, Integer> {
    List<UserBankAccountLink> findByUserAccount_Id(Integer userAccountId);
    List<UserBankAccountLink> findByBankAccount_Id(Integer bankAccountId);

}
