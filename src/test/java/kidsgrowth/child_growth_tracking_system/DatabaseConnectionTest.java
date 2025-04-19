package kidsgrowth.child_growth_tracking_system;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseConnectionTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        // Kiểm tra kết nối cơ sở dữ liệu
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Unable to connect to the database.");
        }
    }
}
