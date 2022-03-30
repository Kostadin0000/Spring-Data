import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement statementVillains = connection.prepareStatement("SELECT name FROM villains WHERE id = ?;");

        int id = Integer.parseInt(scanner.nextLine());
        statementVillains.setInt(1, id);

        ResultSet rs = statementVillains.executeQuery();

        if (!rs.next()) {
            System.out.printf("No villain with ID %d exists in the database.",id);
            return;
        } else {
            System.out.println("Villain: " + rs.getString(1));
        }


        PreparedStatement statementMinions = connection.prepareStatement("SELECT \n" +
                "    m.name, m.age\n" +
                "FROM\n" +
                "    minions_villains AS mv\n" +
                "        JOIN\n" +
                "    villains AS v ON v.id = mv.villain_id\n" +
                "        JOIN\n" +
                "    minions AS m ON m.id = mv.minion_id\n" +
                "WHERE\n" +
                "    v.id = ?;");
        statementMinions.setInt(1, id);

        ResultSet rs2 = statementMinions.executeQuery();

        int index = 1;


        while (rs2.next()) {
            System.out.println(index++ + ". " + rs2.getString(1) + " " + rs2.getString(2));
        }
    }
}
