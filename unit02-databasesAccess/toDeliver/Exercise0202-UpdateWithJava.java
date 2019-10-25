// Adrián Navarro Gabino

/*
 * Using "pgAdmin", connect to your database "dia01". Shows the existing data
 * in the articles table. Add a new article. It shows those that have a
 * price lower than 10 euros.
 */

import java.sql.*;

public class Exercise0202 {
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void addPriceColumn()
	{
		try {
			ps = conn.prepareStatement(
					"ALTER TABLE ARTICULOS ADD COLUMN precio REAL DEFAULT 0;");
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public static void insertArticle(String name, double price)
	{
		try
		{
			ps = conn.prepareStatement("SELECT * FROM articulos " +
										"WHERE nombre = ?;");
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			
			if(!rs.next())
			{
				ps = conn.prepareStatement("INSERT INTO articulos " +
											"(nombre, precio) VALUES (?, ?)");
				ps.setString(1, name);
				ps.setDouble(2, price);
				ps.executeUpdate();
				System.out.println(name + " inserted");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void seeArticlesByPrice(double price)
	{
		try
		{
			ps = conn.prepareStatement("SELECT * FROM articulos " +
										"WHERE precio < ?;");
			ps.setDouble(1, price);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs.getString("nombre") + " - " +
						rs.getDouble("precio"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			addPriceColumn();
			insertArticle("Bed", 12.5);
			seeArticlesByPrice(10);
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e) { }
			}
			if (ps != null)
			{
				try
				{
					ps.close();
				} 
				catch (SQLException e) { }
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e) { }
			}
		}
	}
}
