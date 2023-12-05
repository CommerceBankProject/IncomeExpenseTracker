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
@CrossOrigin(origins = "http://localhost:5173")
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


    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactionsByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) {
        return transactionService.getTransactionByAccountNumber(accountNumber);
    }

    @GetMapping("/account/{accountNumber}/type/{type}")
    public List<Transaction> getTransactionsByAccountNumberAndType(
            @PathVariable(value = "accountNumber") String accountNumber,
            @PathVariable(value = "type") String type) {
        return transactionService.getTransactionsByAccountNumberAndType(accountNumber, type);
    }

    @GetMapping("/account/{accountNumber}/date-range")
    public List<Transaction> getTransactionsByAccountNumberAndDateRange(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return transactionService.getTransactionsByAccountNumberAndDateRange(accountNumber, start, end);
    }

    @GetMapping("/account/{accountNumber}/date-range/expense")
    public BigDecimal getTotalExpenseByAccountNumberAndDateRange(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return transactionService.getTotalExpenseByAccountNumberAndDateRange(accountNumber, start, end);
    }

    @GetMapping("/account/{accountNumber}/date-range/deposit")
    public BigDecimal getTotalDepositByAccountNumberAndDateRange(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return transactionService.getTotalDepositByAccountNumberAndDateRange(accountNumber, start, end);
    }

    @GetMapping("/account/{accountNumber}/recurring/{recurring}")
    public List<Transaction> getTransactionsByRecurring(
            @PathVariable(value = "accountNumber") String accountNumber,
            @PathVariable(value = "recurring") Boolean recurring) {
        return transactionService.getTransactionsByRecurring(accountNumber, recurring);
    }

    @GetMapping("/account/{accountNumber}/amount-greater-than/{amount}")
    public List<Transaction> getTransactionsByAmountGreaterThan(
            @PathVariable(value = "accountNumber") String accountNumber,
            @PathVariable(value = "amount") BigDecimal amount) {
        return transactionService.getTransactionsByAmountGreaterThan(accountNumber, amount);
    }

    @GetMapping("/account/{accountNumber}/search/{descr}")
    public List<Transaction> getTransactionsByDescriptionContaining(
            @PathVariable(value = "accountNumber") String accountNumber,
            @PathVariable(value = "descr") String descr) {
        return transactionService.getTransactionsByDescriptionContaining(accountNumber, descr);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(value = "id") Integer transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }

}
