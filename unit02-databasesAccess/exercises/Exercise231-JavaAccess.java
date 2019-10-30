// Adrián Navarro Gabino

/*
 * 2.3.1: Check from Java that the two data you entered in the section
 * 2.2.2 are accessible.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class Exercise231 {
	
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/pruebas1";
		String user = "postgres";
		String password = "1234";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			ps = conn.prepareStatement("SELECT * FROM PROGRAMAS;");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Name: " + rs.getString("nombre") +
						" - Device of backup copy: " +
						rs.getString("dispositivo_copia"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
}