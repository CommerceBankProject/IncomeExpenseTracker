package TeamHaLoi.IncomeExpenseTracker.service;

import TeamHaLoi.IncomeExpenseTracker.model.TransactionCategory;
import TeamHaLoi.IncomeExpenseTracker.repository.TransactionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionCategoryService {

    private final TransactionCategoryRepository transactionCategoryRepository;

    @Autowired
    public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
    }

    public List<TransactionCategory> getAllTransactionCategories() {
        return transactionCategoryRepository.findAll();
    }

    public Optional<TransactionCategory> getTransactionCategoryById(Integer id) {
        return transactionCategoryRepository.findById(id);
    }

    public TransactionCategory createTransactionCategory(TransactionCategory transactionCategory) {
        return transactionCategoryRepository.save(transactionCategory);
    }

}
