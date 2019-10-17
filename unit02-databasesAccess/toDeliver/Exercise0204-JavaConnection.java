// Adrián Navarro Gabino

/*
 * From Java, connect to database "dia01" and display all item data
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercise0204 
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	
    public static void main( String[] args )
    {
        try
		{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM ARTICULOS");
			while(rs.next())
			{
				//String name = rs.getString(1);
				String name = rs.getString("NOMBRE");
				double price = rs.getDouble("PRECIO");
				System.out.println("Name: " + name + " - Price: " + price + 
						" €");
				
			}
			rs.close();
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

