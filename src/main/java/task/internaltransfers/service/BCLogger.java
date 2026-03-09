package task.internaltransfers.service;

/**
 * Метод saveLogService сохраняет все данные об операции в таблице LogService
 */
public interface BCLogger {
    void saveLogService(String command,String request,String response,Long transactionId,String requisite,String status,String innerCode,String innerMessage);

}
