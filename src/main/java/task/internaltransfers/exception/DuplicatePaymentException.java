package task.internaltransfers.exception;

public class DuplicatePaymentException extends BusinessException {
    public DuplicatePaymentException(String message) {
        super(10, message); // 409 = Conflict
    }
}
