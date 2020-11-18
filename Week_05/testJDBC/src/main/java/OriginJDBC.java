import java.sql.*;

/**
 * @author wincher
 * <p> PACKAGE_NAME <p>
 */
public class OriginJDBC {
	
	static final String JDBC_DRIVER = "";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "1";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT id, sex, firstname, lastname,  FROM student";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int sex = rs.getInt("sex");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				System.out.println(String.format("id: {}, sex: {}, firstname: {}, lastname: {}",
					id, 0 == sex ? "female" : "male", firstname, lastname));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != stmt) stmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (null != conn) conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
