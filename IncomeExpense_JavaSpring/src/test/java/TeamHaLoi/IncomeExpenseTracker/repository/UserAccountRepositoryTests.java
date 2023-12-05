package TeamHaLoi.IncomeExpenseTracker.repository;

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserAccountRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Sample user account for testing
    private UserAccount sampleUser;

    @BeforeEach
    public void setUp() {
        // Initialize a sample user account and persist it for testing
        sampleUser = new UserAccount();
        sampleUser.setEmail("test@example.com");
        sampleUser.setPassword("testPassword");
        // Add other necessary fields
        entityManager.persist(sampleUser);
        entityManager.flush();
    }

    @Test
    public void whenFindByEmail_thenReturnUserAccount() {
        // Given setup

        // When
        UserAccount found = userAccountRepository.findByEmail(sampleUser.getEmail());

        // Then
        assertThat(found.getEmail()).isEqualTo(sampleUser.getEmail());
    }

}
