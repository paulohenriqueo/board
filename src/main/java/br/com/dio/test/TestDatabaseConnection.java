package br.com.dio.test;

import br.com.dio.persistence.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        System.out.println("Testando conexão com MySQL Docker...");
        
        try {
            Connection connection = ConnectionConfig.getConnection();
            System.out.println("CONEXÃO BEM-SUCEDIDA!");
            
            // Teste 1: Verificar se o banco existe
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DATABASE() as db_name, NOW() as server_time");
            
            if (rs.next()) {
                System.out.println("Banco conectado: " + rs.getString("db_name"));
                System.out.println("Hora do servidor: " + rs.getString("server_time"));
            }
            
            // Teste 2: Criar uma tabela teste
            stmt.execute("CREATE TABLE IF NOT EXISTS test_table (id INT PRIMARY KEY, name VARCHAR(50))");
            System.out.println("Tabela teste criada/verificada");
            
            // Teste 3: Inserir um registro
            stmt.execute("INSERT INTO test_table (id, name) VALUES (1, 'Teste Java-Docker')");
            System.out.println("Registro inserido com sucesso");
            
            // Teste 4: Ler o registro
            rs = stmt.executeQuery("SELECT * FROM test_table");
            while (rs.next()) {
                System.out.println("Dados: ID=" + rs.getInt("id") + ", Nome=" + rs.getString("name"));
            }
            
            connection.close();
            System.out.println("\nTUDO FUNCIONANDO PERFEITAMENTE! MySQL + Docker + Java!");
            
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }
}