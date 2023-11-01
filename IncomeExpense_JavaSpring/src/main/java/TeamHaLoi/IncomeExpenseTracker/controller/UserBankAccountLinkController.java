package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.UserBankAccountLinkRepository;
import TeamHaLoi.IncomeExpenseTracker.payload.BankAccountCreationRequestDto;
import TeamHaLoi.IncomeExpenseTracker.exception.UserBankAccountLinkNotFoundException; // You should have an appropriate exception or reuse from your existing ones.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user_bank_account_links")
public class UserBankAccountLinkController {

    @Autowired
    private UserBankAccountLinkRepository userBankAccountLinkRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    // Get All Links
    @GetMapping
    public List<UserBankAccountLink> getAllLinks() {
        return userBankAccountLinkRepository.findAll();
    }

    // Create a new Link
    @PostMapping
    public UserBankAccountLink createLink(@Valid @RequestBody UserBankAccountLink userBankAccountLink) {
        return userBankAccountLinkRepository.save(userBankAccountLink);
    }

    // Get a Single Link by ID
    @GetMapping("/{id}")
    public UserBankAccountLink getLinkById(@PathVariable(value = "id") String linkIdStr) {
        try {
            Integer linkId = Integer.parseInt(linkIdStr);
            return userBankAccountLinkRepository.findById(linkId)
                    .orElseThrow(() -> new UserBankAccountLinkNotFoundException("UserBankAccountLink not found with id: " + linkId));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid link ID format: " + linkIdStr);
        }
    }

    // Create a new Bank Account (either Checking or Savings) and Link with a User
    @PostMapping("/create_account_and_link")
    public UserBankAccountLink createCheckingAndSavingsAccountAndLink(@Valid @RequestBody BankAccountCreationRequestDto request) {
        // Validate the account type
        if(!"CHECKING".equalsIgnoreCase(request.getAccountType()) && !"SAVINGS".equalsIgnoreCase(request.getAccountType())) {
            throw new IllegalArgumentException("Invalid account type provided. Must be either CHECKING or SAVINGS.");
        }

        // Fetch user account from the database
        UserAccount userAccount = userAccountRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        // Generate a random account number
        String accountNumber = generateRandomAccountNumber();

        // Create the bank account
        BankAccount newAccount = new BankAccount();
        newAccount.setAccountNumber(accountNumber);  // Set the generated account number
        newAccount.setBankName(request.getBankName());
        newAccount.setStatus("active");
        newAccount.setAccountType(request.getAccountType().toUpperCase());
        newAccount.setBalance(request.getInitialBalance());
        // Set other fields like creation and update timestamps if necessary
        BankAccount savedAccount = bankAccountRepository.save(newAccount);

        // Create and link the new account to the user
        UserBankAccountLink link = new UserBankAccountLink(userAccount, savedAccount, request.getAccountAlias());
        return userBankAccountLinkRepository.save(link);
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        long num = 1_000_000_000L + random.nextLong(9_000_000_000L);  // Generate a random number between 1_000_000_000 and 9_999_999_999
        return String.valueOf(num);
    }

    // Delete a Link
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "id") String linkIdStr) {
        try {
            Integer linkId = Integer.parseInt(linkIdStr);
            UserBankAccountLink link = userBankAccountLinkRepository.findById(linkId)
                    .orElseThrow(() -> new UserBankAccountLinkNotFoundException("UserBankAccountLink not found with id: " + linkIdStr));

            userBankAccountLinkRepository.delete(link);

            return ResponseEntity.ok().build();
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid link ID format: " + linkIdStr);
        }
    }
}
