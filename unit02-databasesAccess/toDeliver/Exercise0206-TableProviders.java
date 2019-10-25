// Adrián Navarro Gabino

/*
 * From Java, it expands the database "dia02", adding a table "proveedores"
 * (with structure similar to that of "clientes").
 * 
 * Check from psql or pgAdmin that it was created correctly.
 * 
 * What happens if you create it twice?
 */

import java.sql.*;

public class Exercise0206 
{
	static String url = "jdbc:postgresql://localhost:5432/dia02";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	
    public static void main( String[] args )
    {
        try
		{
        	sql = "CREATE TABLE proveedores" +
        			"(CODIGO VARCHAR(4) PRIMARY KEY, NOMBRE VARCHAR(50));";
        	
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Table created");
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

