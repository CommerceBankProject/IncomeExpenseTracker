package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.TransactionCategory;
import TeamHaLoi.IncomeExpenseTracker.service.TransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-categories")
public class TransactionCategoryController {

    private final TransactionCategoryService transactionCategoryService;

    @Autowired
    public TransactionCategoryController(TransactionCategoryService transactionCategoryService) {
        this.transactionCategoryService = transactionCategoryService;
    }

    @GetMapping
    public List<TransactionCategory> getAllTransactionCategories() {
        return transactionCategoryService.getAllTransactionCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategory> getTransactionCategoryById(@PathVariable(value = "id") Integer transactionCategoryId) {
        TransactionCategory transactionCategory = transactionCategoryService.getTransactionCategoryById(transactionCategoryId)
                .orElseThrow(() -> new RuntimeException("Transaction category not found for this id :: " + transactionCategoryId));
        return ResponseEntity.ok().body(transactionCategory);
    }

    @PostMapping
    public TransactionCategory createTransactionCategory(@RequestBody TransactionCategory transactionCategory) {
        return transactionCategoryService.createTransactionCategory(transactionCategory);
    }

}
