package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.UserBankAccountLinkRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.exception.UserAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user_accounts")  // Set the base URL for all endpoints in this controller ; localhost/user_accounts
public class UserAccountController {

    @Autowired
    UserAccountRepository userAccountRepository;


    // Get All UserAccounts
    @GetMapping
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }


    // Get a Single UserAccount
    @GetMapping("/{id}")
    public UserAccount getUserAccountById(@PathVariable(value = "id") Integer userId) throws UserAccountNotFoundException {
        return userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));
    }

    @Autowired
    private UserBankAccountLinkRepository userBankAccountLinkRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @GetMapping("/{user_id}/bank_accounts")
    public List<BankAccount> getUserBankAccounts(@PathVariable(value = "user_id") Integer userId)
            throws UserAccountNotFoundException {

        // Fetch all links for this user
        List<UserBankAccountLink> links = userBankAccountLinkRepository.findByUserAccount_Id(userId);

        // Fetch all bank accounts for these links
        List<BankAccount> accounts = links.stream()
                .map(link -> bankAccountRepository.findById(link.getBankAccount().getId())
                        .orElseThrow(() -> new RuntimeException("Bank Account not found: " + link.getBankAccount().getId())))
                .collect(Collectors.toList());

        return accounts;
    }


    // Update a UserAccount
    @PutMapping("/{id}")
    public UserAccount updateUserAccount(@PathVariable(value = "id") Integer userId,
                                         @Valid @RequestBody UserAccount userAccountDetails) throws UserAccountNotFoundException {

        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));

        if (userAccountDetails.getEmail() != null && !userAccountDetails.getEmail().isEmpty()) {
            userAccount.setEmail(userAccountDetails.getEmail());
        }

        if (userAccountDetails.getPassword() != null && !userAccountDetails.getPassword().isEmpty()) {
            String hashedPassword = PasswordUtil.hashPassword(userAccountDetails.getPassword(), userAccount.getSalt());
            userAccount.setPassword(hashedPassword);
        }

        if (userAccountDetails.getFirstName() != null && !userAccountDetails.getFirstName().isEmpty()) {
            userAccount.setFirstName(userAccountDetails.getFirstName());
        }

        if (userAccountDetails.getLastName() != null && !userAccountDetails.getLastName().isEmpty()) {
            userAccount.setLastName(userAccountDetails.getLastName());
        }

        userAccount.setUpdatedAt(LocalDateTime.now());

        return userAccountRepository.save(userAccount);
    }


    // Delete a UserAccount
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable(value = "id") Integer userId) throws UserAccountNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));

        userAccountRepository.delete(userAccount);

        return ResponseEntity.ok().build();
    }
}
