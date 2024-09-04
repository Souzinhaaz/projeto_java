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

    private Connection connection;

    public BoletimDAO() {
        this.connection = new ConnectDAO().connectDB();
    }

    public List<Boletim> listar() {
        String sql = "SELECT * FROM boletims";
        List<Boletim> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Boletim boletim  = new Boletim();
                boletim.setCodigoBoletim(resultado.getInt("codigo_boletim"));
                boletim.setQuantFaltas(resultado.getInt("quantidade_faltas"));
                boletim.setAprovado(resultado.getBoolean("aprovado"));

                sql = "SELECT valor FROM notas WHERE codigo_boletim=?";
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, boletim.getCodigoBoletim());
                ResultSet resultadoNotas = stmt.executeQuery();

                List<Float> notas = new ArrayList<>();
                while (resultadoNotas.next()) {
                    notas.add(resultadoNotas.getFloat("valor"));
                }

                boletim.setNotas(notas);

                retorno.add(boletim);
            }
        } catch (SQLException err) {
            Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, err);
        }
        return retorno;
    }

}
