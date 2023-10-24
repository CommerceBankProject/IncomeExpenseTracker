package TeamHaLoi.IncomeExpenseTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

@Entity
@Table(name="user_bank_account_link")
public class UserBankAccountLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Integer linkId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;

    @Column(name = "account_alias")
    private String accountAlias;

    @Column(name = "date_linked")
    private LocalDateTime dateLinked;

    public UserBankAccountLink(){
        super();
    }

    public UserBankAccountLink(UserAccount userAccount, BankAccount bankAccount, String accountAlias) {
        this.userAccount = userAccount;
        this.bankAccount = bankAccount;
        this.accountAlias = accountAlias;
        this.dateLinked = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public LocalDateTime getDateLinked() {
        return dateLinked;
    }

    public void setDateLinked(LocalDateTime linkedAt) {
        this.dateLinked = linkedAt;
    }
}
