package task.internaltransfers.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.util.ResponseUtil;

@Getter
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(Exception e){

        return ResponseUtil.builderCustomResponse(
                500,
                "Internal server error"
        );
    }
}
