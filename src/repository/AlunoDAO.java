package repository;

import connection.ConnectDAO;
import models.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDAO {
    private final Connection connection;

    public AlunoDAO () {
        this.connection = new ConnectDAO().connectDB();
    }

    public List<Aluno> listar() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(resultado.getInt("matricula"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setTelefone(resultado.getString("telefone"));
                retorno.add(aluno);
            }
        } catch (SQLException err) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, err);
        }
        return retorno;
    }

    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, email, telefone) VALUES (?, ? ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }

    public boolean alterar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, email=?, telefone=? WHERE matricula=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setInt(4, aluno.getMatricula());
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }

    public boolean remover(Integer id) {
        String sql = "DELETE FROM alunos WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }
}
