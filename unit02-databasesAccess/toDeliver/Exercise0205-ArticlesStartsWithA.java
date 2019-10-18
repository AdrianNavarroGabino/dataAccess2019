// Adrián Navarro Gabino

/*
 * From Java, connect to the database "dia01" and display all the data of
 * articles whose name begins with "A" (or "a"). You must notify in case there 
 * is no data that meets that requirement.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercise0205 
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	
    public static void main( String[] args )
    {
    	boolean results = false;
    	
        try
		{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM ARTICULOS WHERE UPPER(NOMBRE) LIKE 'A%'");
			while(rs.next())
			{
				results = true;
				String name = rs.getString("NOMBRE");
				double price = rs.getDouble("PRECIO");
				System.out.println("Name: " + name + " - Price: " + price + 
						" €");
			}
			rs.close();
			conn.close();
			
			if(!results)
			{
				System.out.println("There is no data");
			}
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
}


