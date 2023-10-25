package TeamHaLoi.IncomeExpenseTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(Integer id) {
        super("Could not find BankAccount with id: " + id);
    }
}
