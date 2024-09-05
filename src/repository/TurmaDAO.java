package repository;

import connection.ConnectDAO;
import models.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurmaDAO {

    private final Connection connection;

    public TurmaDAO () {
        this.connection = new ConnectDAO().connectDB();
    }

    public List<Turma> listar() {
        String sql = "SELECT * FROM turmas";
        List<Turma> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Turma turma = new Turma();
                turma.setCodigoTurma(resultado.getInt("codigo_turma"));
                turma.setNome(resultado.getString("nome"));
                turma.setAno(resultado.getInt("ano"));
                turma.setTurno(resultado.getString("turno"));
                retorno.add(turma);
            }
        } catch (SQLException err) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, err);
        }
        return retorno;
    }

    public boolean inserir(Turma turma) {
        String sql = "INSERT INTO turmas (nome, turno, ano) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, turma.getNome());
            stmt.setString(2, turma.getTurno());
            stmt.setInt(3, turma.getAno());
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }

    public boolean alterar(Turma turma) {
        String sql = "UPDATE turmas SET nome=?, turno=?, ano=? WHERE codigo_turma=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, turma.getNome());
            stmt.setString(2, turma.getTurno());
            stmt.setInt(3, turma.getAno());
            stmt.setInt(4, turma.getCodigoTurma());
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }

    public boolean remover(Integer id) {
        String sql = "DELETE FROM turmas WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }
}
