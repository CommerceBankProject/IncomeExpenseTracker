package TeamHaLoi.IncomeExpenseTracker.payload;
import java.math.BigDecimal;

public class BankAccountCreationRequestDto {
    private Integer userId;
    private String accountNumber;
    private String bankName;
    private String accountType;
    private BigDecimal initialBalance;
    private String accountAlias;

    // Constructors
    public BankAccountCreationRequestDto() {
    }

    public BankAccountCreationRequestDto(Integer userId, String accountNumber, String bankName, String accountType, BigDecimal initialBalance, String accountAlias) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.accountAlias = accountAlias;
    }

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public String getAccountType(){
        return accountType;

    }
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }
}
