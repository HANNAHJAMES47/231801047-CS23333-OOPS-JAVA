import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_manager", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
