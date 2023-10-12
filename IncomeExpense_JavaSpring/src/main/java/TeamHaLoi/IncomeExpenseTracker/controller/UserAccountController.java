package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.exception.UserAccountNotFoundException; // Ensure you create this exception class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user_accounts")  // Set the base URL for all endpoints in this controller
public class UserAccountController {

    @Autowired
    UserAccountRepository userAccountRepository;

    // Get All UserAccounts
    @GetMapping
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    // Create a new UserAccount
    @PostMapping
    public UserAccount createUserAccount(@Valid @RequestBody UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    // Get a Single UserAccount
    @GetMapping("/{id}")
    public UserAccount getUserAccountById(@PathVariable(value = "id") Long userId) throws UserAccountNotFoundException {
        return userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));
    }

    // Update a UserAccount
    @PutMapping("/{id}")
    public UserAccount updateUserAccount(@PathVariable(value = "id") Long userId,
                                         @Valid @RequestBody UserAccount userAccountDetails) throws UserAccountNotFoundException {

        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));

        userAccount.setEmail(userAccountDetails.getEmail());
        String hashedPassword = PasswordUtil.hashPassword(userAccountDetails.getPassword(), userAccount.getSalt());
        userAccount.setPassword(hashedPassword);
        userAccount.setFirstName(userAccountDetails.getFirstName());
        userAccount.setLastName(userAccountDetails.getLastName());
        userAccount.setUpdatedAt(LocalDateTime.now());

        return userAccountRepository.save(userAccount);
    }

    // Delete a UserAccount
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable(value = "id") Long userId) throws UserAccountNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new UserAccountNotFoundException(userId));

        userAccountRepository.delete(userAccount);

        return ResponseEntity.ok().build();
    }
}
