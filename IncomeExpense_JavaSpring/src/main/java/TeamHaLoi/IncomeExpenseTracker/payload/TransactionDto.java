package TeamHaLoi.IncomeExpenseTracker.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private String accountNumber;
    private String type;
    private BigDecimal amount;
    private String description;

    private LocalDate date;
    private Boolean recurring;

    // Getters
    public String getAccountNumber() {
        return accountNumber;
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


    public LocalDate getDate() {
        return date;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    // Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }}
