import models.Boletim;
import repository.BoletimDAO;

public class Main {
    public static void main(String[] args) {
        Boletim b1 = new Boletim();
        b1.setMatricula(1);
        b1.setNota1(8.9f);
        b1.setNota2(3.7f);
        b1.setNota3(4.3f);
        b1.setNota4(9.2f);
        b1.setQuantFaltas(13);
        BoletimDAO b1dao = new BoletimDAO();

        b1dao.inserir(b1);
    }
}