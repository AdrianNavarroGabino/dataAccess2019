// Adrián Navarro Gabino

/*
 * 2.4.1: Create a new table from the "prueba1" database from Java, which
 * allow to save data on each backup device: code, name and situation.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class Exercise241 {
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
			
			ps = conn.prepareStatement("CREATE TABLE PRUEBA1 (" +
					"CODIGO SERIAL PRIMARY KEY," +
					"NOMBRE VARCHAR(50)," +
					"SITUACION VARCHAR(100));");
			ps.executeUpdate();
			
			System.out.println("Table has been created");
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
