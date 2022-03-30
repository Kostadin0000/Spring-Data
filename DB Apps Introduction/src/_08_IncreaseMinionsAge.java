import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] tokens = scanner.nextLine().split(" ");

        PreparedStatement statement = connection.prepareStatement("UPDATE minions_db.minions \n" +
                "SET \n" +
                "    age = age + 1,\n" +
                "    name = LOWER(name)\n" +
                "WHERE\n" +
                "    id IN (?,?,?);");

        statement.setInt(1, Integer.parseInt(tokens[0]));
        statement.setInt(2, Integer.parseInt(tokens[1]));
        statement.setInt(3, Integer.parseInt(tokens[2]));
        statement.executeUpdate();

        PreparedStatement print = connection.prepareStatement("SELECT name,age FROM minions_db.minions;");
        ResultSet resultSet = print.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }

    }
}
