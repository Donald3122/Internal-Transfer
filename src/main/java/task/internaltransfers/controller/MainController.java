package task.internaltransfers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.CheckService;
import task.internaltransfers.service.PayService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final CheckService checkService;
    private final PayService payService;

    @GetMapping("/check")
    public ResponseDto check(@RequestParam int customerId) {
        try {
            return checkService.execute(customerId);
        } catch (Exception e) {
            throw e;
        }
    }


    @PostMapping("/transfers")
    public ResponseDto pay(@RequestBody RequestDto requestDto) {
        try {
            return payService.execute(requestDto);
        } catch (Exception e) {
            throw e;
        }
    }
}