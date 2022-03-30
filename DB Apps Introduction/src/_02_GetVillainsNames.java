import java.sql.*;
import java.util.Properties;

public class _02_GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT\n" +
                        "    v.name, COUNT(DISTINCT mv.minion_id) AS c\n" +
                        "FROM\n" +
                        "    villains AS v\n" +
                        " JOIN\n" +
                        "    minions_villains AS mv ON mv.villain_id = v.id\n" +
                        "GROUP BY v.id\n" +
                        "HAVING c > 15\n" +
                        "ORDER BY c DESC;");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
        connection.close();
    }
}
