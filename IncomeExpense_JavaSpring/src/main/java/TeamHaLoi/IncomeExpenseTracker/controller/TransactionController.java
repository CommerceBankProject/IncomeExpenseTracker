package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import TeamHaLoi.IncomeExpenseTracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(value = "id") Integer transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    @GetMapping("/account/{bankAccountId}")
    public List<Transaction> getTransactionsByBankAccountId(@PathVariable(value = "bankAccountId") Integer bankAccountId) {
        return transactionService.getTransactionsByBankAccountId(bankAccountId);
    }

    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionsByType(@PathVariable(value = "type") String type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/date-range")
    public List<Transaction> getTransactionsByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return transactionService.getTransactionsByDateRange(start, end);
    }

    @GetMapping("/category/{categoryId}")
    public List<Transaction> getTransactionsByCategoryId(@PathVariable(value = "categoryId") Integer categoryId) {
        return transactionService.getTransactionsByCategoryId(categoryId);
    }

    @GetMapping("/recurring/{recurring}")
    public List<Transaction> getTransactionsByRecurring(@PathVariable(value = "recurring") Boolean recurring) {
        return transactionService.getTransactionsByRecurring(recurring);
    }

    @GetMapping("/amount-greater-than/{amount}")
    public List<Transaction> getTransactionsByAmountGreaterThan(@PathVariable(value = "amount") BigDecimal amount) {
        return transactionService.getTransactionsByAmountGreaterThan(amount);
    }

    @GetMapping("/search/description")
    public List<Transaction> getTransactionsByDescriptionContaining(@RequestParam("keyword") String keyword) {
        return transactionService.getTransactionsByDescriptionContaining(keyword);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") Integer transactionId,
                                                         @RequestBody Transaction transactionDetails) {
        return ResponseEntity.ok(transactionService.updateTransaction(transactionId, transactionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(value = "id") Integer transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }
}
