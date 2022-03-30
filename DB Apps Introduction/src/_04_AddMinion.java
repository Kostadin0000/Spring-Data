import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] tokens = scanner.nextLine().split(" ");

        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String townName = tokens[3];

        int town_id = 0;
        int villain_id = 0;

        String[] tokens2 = scanner.nextLine().split(" ");
        String villainName = tokens2[1];

        PreparedStatement statementTown = connection.prepareStatement("SELECT id FROM towns WHERE name = ?;");

        statementTown.setString(1, townName);

        PreparedStatement statementVillain = connection.prepareStatement("SELECT id FROM villains WHERE name = ?;");

        statementVillain.setString(1, villainName);

        if (!statementTown.executeQuery().next()) {
            PreparedStatement insertTown = connection.prepareStatement("INSERT INTO towns(name) VALUES (?);");
            insertTown.setString(1, townName);
            insertTown.executeUpdate();
            ResultSet resultSet = statementTown.executeQuery();
            resultSet.next();
            town_id = resultSet.getInt(1);
            System.out.printf("Town %s was added to the database.%n", townName);

        } else {
            ResultSet rs = statementTown.executeQuery();
            rs.next();
            town_id = rs.getInt(1);
        }

        if (!statementVillain.executeQuery().next()) {
            PreparedStatement insertVilain = connection.prepareStatement("INSERT INTO villains(name,evilness_factor) VALUES (?,?);");
            insertVilain.setString(1, villainName);
            insertVilain.setString(2, "evil");
            insertVilain.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villainName);
            ResultSet resultSet = statementVillain.executeQuery();
            resultSet.next();
            villain_id = resultSet.getInt(1);

        } else {
            ResultSet rs = statementVillain.executeQuery();
            rs.next();
            villain_id = rs.getInt(1);
        }

        PreparedStatement addMinion = connection.prepareStatement("INSERT INTO minions(name,age,town_id) VALUES (?,?,?);");
        addMinion.setString(1, minionName);
        addMinion.setInt(2, minionAge);
        addMinion.setInt(3, town_id);
        addMinion.executeUpdate();

        PreparedStatement getMinion = connection.prepareStatement("SELECT id FROM minions WHERE name = ?;");
        getMinion.setString(1, minionName);

        ResultSet result = getMinion.executeQuery();
        result.next();
        int minion_id = result.getInt(1);

        PreparedStatement statement = connection.prepareStatement("INSERT INTO minions_villains (minion_id,villain_id) VALUES (?,?);");
        statement.setInt(1, minion_id);
        statement.setInt(2, villain_id);
        statement.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }
}
