package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectDAO {
    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "1234";

    public Connection connectDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS projeto_escola";
            stmt.executeUpdate(sql);

            sql = "USE projeto_escola";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS turmas (" +
                    "codigo_turma INTEGER NOT NULL AUTO_INCREMENT, " +
                    "nome VARCHAR(255) NOT NULL, " +
                    "turno VARCHAR(255) NOT NULL, " +
                    "ano YEAR NOT NULL, " +
                    "PRIMARY KEY (codigo_turma))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "matricula INTEGER NOT NULL AUTO_INCREMENT, " +
                    "nome VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    "telefone_responsavel VARCHAR(255) NOT NULL, " +
                    "codigo_turma INTEGER, " +
                    "PRIMARY KEY (matricula), " +
                    "FOREIGN KEY (codigo_turma) REFERENCES turmas(codigo_turma))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS boletins (" +
                    "codigo_boletim INTEGER NOT NULL AUTO_INCREMENT, " +
                    "matricula INTEGER NOT NULL, " +
                    "nota_1 FLOAT NOT NULL, " +
                    "nota_2 FLOAT NOT NULL, " +
                    "nota_3 FLOAT NOT NULL, " +
                    "nota_4 FLOAT NOT NULL, " +
                    "quantidade_faltas INTEGER NOT NULL, " +
                    "aprovado BOOLEAN NOT NULL, " +
                    "PRIMARY KEY (codigo_boletim), " +
                    "FOREIGN KEY (matricula) REFERENCES alunos(matricula))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS notas (" +
                    "codigo_nota INTEGER NOT NULL AUTO_INCREMENT," +
                    "codigo_boletim INTEGER NOT NULL," +
                    "valor FLOAT NOT NULL," +
                    "PRIMARY KEY (codigo_nota)," +
                    "FOREIGN KEY (codigo_boletim) REFERENCES boletins(codigo_boletim))";
            stmt.executeUpdate(sql);

            System.out.println("Database and tables created successfully");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "ConnectDAO: " + err.getMessage());
        }
        return conn;
    }
}
