package task.internaltransfers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class MainController {

    private final DataSource dataSource;
    @GetMapping("/db")
    public String testDbConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "Подключение к БД успешно! Версия: " + conn.getMetaData().getDatabaseProductVersion();
        }
    }
}
