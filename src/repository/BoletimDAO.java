package repository;
import models.Aluno;
import models.Boletim;

import connection.ConnectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoletimDAO {

    private final Connection connection;

    public BoletimDAO() {
        this.connection = new ConnectDAO().connectDB();
    }

    public List<Boletim> listar() {
        String sql = "SELECT * FROM boletins";
        List<Boletim> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Boletim boletim  = new Boletim();
                boletim.setCodigoBoletim(resultado.getInt("codigo_boletim"));
                boletim.setMatricula(resultado.getInt("matricula"));
                boletim.setNota1(resultado.getFloat("nota_1"));
                boletim.setNota2(resultado.getFloat("nota_2"));
                boletim.setNota3(resultado.getFloat("nota_3"));
                boletim.setNota4(resultado.getFloat("nota_4"));
                boletim.setQuantFaltas(resultado.getInt("quantidade_faltas"));
                boletim.setSituacao(resultado.getString("situacao"));
                retorno.add(boletim);
            }
        } catch (SQLException err) {
            Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, err);
        }

        return retorno;
    }

    public boolean inserir(Boletim boletim) {
        try {
            String sql = "SELECT matricula FROM alunos WHERE matricula=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, boletim.getMatricula());
            ResultSet aluno = stmt.executeQuery();

            if (!aluno.next()) {
                System.out.println("Esse aluno não existe, por favor insira um aluno válido!");
                return false;
            }

            sql = "INSERT INTO (matricula, nota_1, nota_2, nota_3, nota_4, quantidade_faltas)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, boletim.getMatricula());
            stmt.setFloat(2, boletim.getNota1());
            stmt.setFloat(3, boletim.getNota2());
            stmt.setFloat(4, boletim.getNota3());
            stmt.setFloat(5, boletim.getNota4());
            stmt.setInt(6, boletim.getQuantFaltas());

            float media = (boletim.getNota1() + boletim.getNota2() + boletim.getNota3() + boletim.getNota4()) / 4;

            if (media >= 7 && boletim.getQuantFaltas() < 15) {
                stmt.setString(7, "Aprovado");
            } else {
                stmt.setString(7, "Reprovado");
            }

            stmt.execute();
            return true;
        } catch (SQLException err) {
            Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }

}
