package TeamHaLoi.IncomeExpenseTracker.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="user_account")
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String salt;
    private String full_name;
    private String date_created;
    private String last_updated;
    public UserAccount(){
        super();
    }
    public UserAccount(Long id, String email, String password, String full_name){
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getFull_name(){
        return full_name;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFull_name(String full_name){
        this.full_name = full_name;
    }
}