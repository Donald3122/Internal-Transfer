package task.internaltransfers.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.util.ResponseUtil;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

    // Обработка кастомных бизнес-исключений
    @ExceptionHandler(BusinessException.class)
    public ResponseDto handleBusinessException(BusinessException ex){
        return ResponseUtil.builderCustomResponse(ex.getCode(), ex.getMessage());
    }

    // Общая обработка системных ошибок
    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(Exception ex){
        return ResponseUtil.builderCustomResponse(
                500,
                "Internal server error"
        );
    }
}
