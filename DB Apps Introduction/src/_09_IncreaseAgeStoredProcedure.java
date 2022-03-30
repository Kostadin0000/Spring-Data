import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int id = Integer.parseInt(scanner.nextLine());
        CallableStatement statement = connection.prepareCall("{CALL usp_get_older(?)}");
        statement.setInt(1, id);
        statement.execute();

        PreparedStatement statement1 = connection.prepareStatement("SELECT name,age FROM minions_db.minions" +
                " WHERE id = ?");
        statement1.setInt(1, id);

        ResultSet resultSet = statement1.executeQuery();
        resultSet.next();

        System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
    }
}
