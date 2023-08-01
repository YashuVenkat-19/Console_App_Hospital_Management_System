import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public Connection getJdbcConnection() {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/hms";

        // Database credentials
        String username = "root";
        String password = "tiger";

        try {
            // Step 1: Load JDBC driver
            Class.forName(jdbcDriver);
            // Step 2: Establish connection to the database
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while connecting to the database.");
        }

        return null;
    }
}
