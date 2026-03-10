package task.internaltransfers.exception;

public class InsufficientFundsException extends BusinessException {
    public InsufficientFundsException(String message) {
        super(400, message); // 400 = Bad Request
    }
}
