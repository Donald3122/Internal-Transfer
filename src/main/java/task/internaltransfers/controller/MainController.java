package task.internaltransfers.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import task.internaltransfers.config.repo.StatementsRepository;
import task.internaltransfers.dto.AccountStatementDto;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.CheckService;
import task.internaltransfers.service.PayService;
import task.internaltransfers.service.StatementService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final CheckService checkService;
    private final PayService payService;
    private final StatementService statementService;

    @GetMapping("/check")
    public ResponseDto check(@RequestParam String customerId) {
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