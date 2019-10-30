// Adrián Navarro Gabino

/*
 * 2.5.1: Create from Java a program that adds three rows in the device table of
 * backup. Check the return value, to make sure that all have been
 * inserted correctly.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class Exercise251 {
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/pruebas1";
		String user = "postgres";
		String password = "1234";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		int count = 0;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			ps = conn.prepareStatement(
					"INSERT INTO PRUEBA1 " + 
					"(CODIGO, NOMBRE, TAMANYO) VALUES (?,?,?);");
			
			ps.setString(1, "Device1");
			ps.setString(2, "Device1");
			ps.setInt(3, 1000);
			count += ps.executeUpdate();
			
			ps.setString(1, "Device2");
			ps.setString(2, "Device2");
			ps.setInt(3, 3000);
			count += ps.executeUpdate();
			
			ps.setString(1, "Device3");
			ps.setString(2, "Device3");
			ps.setInt(3, 5000);
			count += ps.executeUpdate();
			
			if(count == 3)
				System.out.println("Devices inserted");
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