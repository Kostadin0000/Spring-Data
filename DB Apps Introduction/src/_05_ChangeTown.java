import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTown {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement selectCountries = connection.prepareStatement("SELECT name FROM minions_db.towns WHERE country = ?;");
        selectCountries.setString(1, country);

        if (!selectCountries.executeQuery().next()) {
            System.out.println("No town names were affected.");
            return;
        }

        PreparedStatement statement = connection.prepareStatement("UPDATE minions_db.towns" +
                " SET name = upper(name)\n" +
                " WHERE country = ?;");
        statement.setString(1, country);
        int count = statement.executeUpdate();

        List<String> list = new ArrayList<>();

        ResultSet resultSet = selectCountries.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }

        System.out.printf("%d town names were affected.",count);
        System.out.println(list);
    }
}
