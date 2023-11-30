package TeamHaLoi.IncomeExpenseTracker.service;

import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import TeamHaLoi.IncomeExpenseTracker.model.BankAccount;
import TeamHaLoi.IncomeExpenseTracker.repository.TransactionRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.BankAccountRepository;
import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import TeamHaLoi.IncomeExpenseTracker.exception.BankAccountNotFoundException;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found for this id :: " + id));
    }


    public List<Transaction> getTransactionByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }


    public List<Transaction> getTransactionsByRecurring(Boolean recurring) {
        return transactionRepository.findByRecurring(recurring);
    }

    public List<Transaction> getTransactionsByAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }

    public List<Transaction> getTransactionsByDescriptionContaining(String description) {
        return transactionRepository.findByDescriptionContaining(description);
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction.setAccountNumber(transaction.getAccountNumber());
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(transaction.getAccountNumber());
        transaction.setType(transaction.getType());
        transaction.setAmount(transaction.getAmount());
        BigDecimal currentBalance = bankAccount.getBalance();
        BigDecimal newBalance = currentBalance.add(transaction.getAmount());
        bankAccount.setBalance(newBalance);
        transaction.setDescription(transaction.getDescription());
        transaction.setDate(LocalDate.now());
        transaction.setRecurring(transaction.getRecurring());
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }


    public void deleteTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found for id :: " + id));

        transactionRepository.delete(transaction);
    }
}
