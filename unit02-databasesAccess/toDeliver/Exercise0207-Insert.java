// Adrián Navarro Gabino

/*
 * From Java, update the database "dia02", adding two example "proveedores". 
 * Have records affected by console displayed.
 * 
 * Check from psql or pgAdmin that they have been inserted correctly.
 * 
 * What happens if you repeat a primary key?
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercise0207
{
	static String url = "jdbc:postgresql://localhost:5432/dia02";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	
    public static void main( String[] args )
    {
        try
		{
        	sql = "INSERT INTO PROVEEDORES (CODIGO, NOMBRE) VALUES " +
        			"('1', 'Antonio'), ('2', 'Andres');";
        	
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			int affectedRows = statement.executeUpdate(sql);
			System.out.println("Affected rows: " + affectedRows);
			conn.close();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
}
