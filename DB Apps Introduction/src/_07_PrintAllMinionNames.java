import java.sql.*;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Scanner;

public class _07_PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        PreparedStatement statement = connection.prepareStatement("SELECT name FROM minions_db.minions;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            arrayDeque.add(resultSet.getString(1));
        }
        while (arrayDeque.size() > 0) {
            System.out.println(arrayDeque.poll());
            if (arrayDeque.size() >= 1) {
                System.out.println(arrayDeque.pollLast());
            }
        }
    }
}
