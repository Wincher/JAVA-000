import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wincher
 * <p> PACKAGE_NAME <p>
 */
public class JDBCWithHikariCP {
	public static void main(String[] args) {
		String configFile = JDBCWithHikariCP.class.getClassLoader().getResource("db.properties").getPath();
		
		try (HikariDataSource ds = new HikariDataSource(new HikariConfig(configFile));
			Connection conn = ds.getConnection();
		     PreparedStatement pst = conn.prepareStatement("SELECT * FROM students");
		     ResultSet rs = pst.executeQuery()){
			while (rs.next()) {
				System.out.println(String.format("id: {}, sex: {}, firstname: {}, lastname: {}",
					rs.getInt(0),
					0 == rs.getInt(1) ? "female" : "male",
					rs.getString(3),
					rs.getString(4)));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
