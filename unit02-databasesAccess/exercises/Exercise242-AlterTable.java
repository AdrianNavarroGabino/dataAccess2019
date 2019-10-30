// Adrián Navarro Gabino

/*
 * 2.4.2: From Java, add a new field to the program table, which will indicate 
 * your size, measured in KB.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class Exercise242 {
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/pruebas1";
		String user = "postgres";
		String password = "1234";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			ps = conn.prepareStatement("ALTER TABLE PRUEBA1 " + 
					"ADD COLUMN TAMANYO BIGINT;");
			
			ps.executeUpdate();
			
			System.out.println("Column added");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
}