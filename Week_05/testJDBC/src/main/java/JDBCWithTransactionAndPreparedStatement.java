import java.sql.*;

/**
 * @author wincher
 * <p> PACKAGE_NAME <p>
 */
public class JDBCWithTransactionAndPreparedStatement {
	
	static final String JDBC_DRIVER = "";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "1";
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String sql = "UPDATE student SET lastname = ? WHERE id = ?";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			try (
				PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
				conn.setAutoCommit(false);
				preparedStatement.setString(1, "Messi");
				preparedStatement.setInt(2, 1);
				preparedStatement.executeUpdate();
				preparedStatement.setString(1, "Ronaldo");
				preparedStatement.setInt(2, 2);
				preparedStatement.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
					e.printStackTrace();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		
	}
}
