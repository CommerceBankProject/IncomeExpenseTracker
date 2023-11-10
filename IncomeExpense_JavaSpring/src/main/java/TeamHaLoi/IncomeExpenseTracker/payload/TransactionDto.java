package TeamHaLoi.IncomeExpenseTracker.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private Integer bankAccountId;
    private String type;
    private BigDecimal amount;
    private String description;
    private Integer categoryId;
    private LocalDate date;
    private Boolean recurring;

    // Getters
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    // Setters
    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }}
