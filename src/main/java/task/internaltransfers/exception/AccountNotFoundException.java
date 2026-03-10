package task.internaltransfers.exception;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException(String message) {
        super(202, message);
    }
}
