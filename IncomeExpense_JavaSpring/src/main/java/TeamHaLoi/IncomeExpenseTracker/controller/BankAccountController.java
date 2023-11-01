package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.exception.BankAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/bank_accounts")  // Set the base URL for all endpoints in this controller; localhost/bank_accounts
public class BankAccountController {

    @Autowired
    BankAccountRepository bankAccountRepository;


    // Get All BankAccounts
    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    // Create a new BankAccount
    @PostMapping()
    public BankAccount createBankAccount(@Valid @RequestBody BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    // Get a Single BankAccount
    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable(value = "id") Integer bankAccountId) throws BankAccountNotFoundException {
        return bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountId));
    }

    // Update a BankAccount
    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable(value = "id") Integer bankAccountId,
                                         @Valid @RequestBody BankAccount bankAccountDetails) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountId));

        bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
        bankAccount.setBankName(bankAccountDetails.getBankName());
        bankAccount.setAccountType(bankAccountDetails.getAccountType());
        bankAccount.setBalance(bankAccountDetails.getBalance());
        bankAccount.setStatus(bankAccountDetails.getStatus());
        bankAccount.setUpdatedAt(bankAccountDetails.getUpdatedAt());

        return bankAccountRepository.save(bankAccount);
    }

    // Delete a BankAccount
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable(value = "id") Integer bankAccountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountId));

        bankAccountRepository.delete(bankAccount);

        return ResponseEntity.ok().build();
    }
}
