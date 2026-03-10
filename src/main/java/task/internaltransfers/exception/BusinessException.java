package task.internaltransfers.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
    private final int code; // код ошибки для ResponseDto

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
