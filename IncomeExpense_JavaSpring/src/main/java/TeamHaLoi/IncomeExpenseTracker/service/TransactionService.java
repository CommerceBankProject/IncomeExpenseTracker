package TeamHaLoi.IncomeExpenseTracker.service;

import TeamHaLoi.IncomeExpenseTracker.model.Transaction;
import TeamHaLoi.IncomeExpenseTracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found for this id :: " + id));
    }


    public List<Transaction> getTransactionsByBankAccountId(Integer bankAccountId) {
        return transactionRepository.findByBankAccountId(bankAccountId);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }

    public List<Transaction> getTransactionsByCategoryId(Integer categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
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
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Integer id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found for id :: " + id));
        transaction.setBankAccountId(transactionDetails.getBankAccountId());
        transaction.setType(transactionDetails.getType());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setCategoryId(transactionDetails.getCategoryId());
        transaction.setDate(transactionDetails.getDate());
        transaction.setRecurring(transactionDetails.getRecurring());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found for id :: " + id));

        transactionRepository.delete(transaction);
    }
}
