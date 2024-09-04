import connection.ConnectDAO;

public class Main {
    public static void main(String[] args) {
        ConnectDAO connection = new ConnectDAO();
        connection.connectDB();
    }
}