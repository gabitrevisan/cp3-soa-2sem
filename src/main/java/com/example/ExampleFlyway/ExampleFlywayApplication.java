package com.example.ExampleFlyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ExampleFlywayApplication {
    public static void main(String[] args) {
//        # Dev (H2)
//        mvn spring-boot:run -Dspring-boot.run.profiles=dev
//
//        # Prod (Oracle)
//        mvn spring-boot:run -Dspring-boot.run.profiles=prod

        SpringApplication.run(ExampleFlywayApplication.class, args);

        String url = "jdbc:h2:mem:reservasdb;DB_CLOSE_DELAY=-1;MODE=Oracle";
        String user = "sa";
        String password = "";
        String tableName = "equipamento";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)){
             while (rs.next()) {
                System.out.println("Dado lido da tabela " + tableName);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}