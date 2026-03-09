package task.internaltransfers.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import task.internaltransfers.dto.AccountDto;
import task.internaltransfers.dto.ResponseDto;

import java.util.List;

@Slf4j
@UtilityClass
public class ResponseUtil {
    public ResponseDto builderCustomResponse(int status, String message){
        ResponseDto.Response response = new ResponseDto.Response(status,message);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(response);
        log.info("Response: = {}", responseDto);
        return responseDto;
    }
    public ResponseDto builderResponse(int status, String message, String description, List<AccountDto> account){
        ResponseDto.Response response = new ResponseDto.Response(status,message,description,account);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(response);
        log.info("Response: = {}", responseDto);
        return responseDto;
    }
}
