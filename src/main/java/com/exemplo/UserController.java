package com.exemplo;

import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
public class UserController {

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return "Login bem-sucedido para: " + username;
            } else {
                return "Usuário ou senha inválidos.";
            }
        } catch (SQLException e) {
            return "Erro: " + e.getMessage();
        }
    }
}
