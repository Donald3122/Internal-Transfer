package task.internaltransfers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.LogServiceEntity;
import task.internaltransfers.config.repo.LogServiceRepository;
import task.internaltransfers.service.BCLogger;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BCLoggerImpl implements BCLogger {
    private final LogServiceRepository logService;
    @Async
    @Override
    public void saveLogService(String command, String request, String response, Long transactionId, String requisite, String status, String innerCode, String innerMessage) {
        LogServiceEntity log = new LogServiceEntity();
        log.setCommand(Objects.toString(command, "null"));
        log.setRequest(Objects.toString(request, "null"));
        log.setResponse(Objects.toString(response, "null"));
        log.setTransactionId(transactionId);
        log.setRequisite(Objects.toString(requisite, "null"));
        log.setStatus(Objects.toString(status, "null"));
        log.setInnerCode(Objects.toString(innerCode, "null"));
        log.setInnerMessage(Objects.toString(innerMessage, "null"));

        logService.save(log);
    }
}
