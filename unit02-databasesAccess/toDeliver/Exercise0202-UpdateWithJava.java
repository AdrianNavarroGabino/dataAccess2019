// Adrián Navarro Gabino

/*
 * Using "pgAdmin", connect to your database "dia01". Shows the existing data
 * in the articles table. Add a new article. It shows those that have a
 * price lower than 10 euros.
 */

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0202 {
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	
	public static void addPriceColumn(Connection conn)
	{
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"ALTER TABLE ARTICULOS ADD COLUMN precio REAL DEFAULT 0;");
			ps.executeUpdate();
			
			System.out.println("Price column added");
		}
		catch (SQLException e)
		{
			System.out.println("Price column is already in the table");
		}
		finally
		{
			DbUtils.closeQuietly(ps);
		}
	}
	
	public static void insertArticle(Connection conn, String name, double price)
	{
		PreparedStatement psSelect = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;
		
		try
		{
			psSelect = conn.prepareStatement("SELECT * FROM articulos " +
										"WHERE nombre = ?;");
			psSelect.setString(1, name);
			
			rs = psSelect.executeQuery();
			
			if(!rs.next())
			{
				psInsert = conn.prepareStatement("INSERT INTO articulos " +
											"(nombre, precio) VALUES (?, ?)");
				psInsert.setString(1, name);
				psInsert.setDouble(2, price);
				psInsert.executeUpdate();
				System.out.println(name + " inserted");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(psSelect);
			DbUtils.closeQuietly(psInsert);
		}
	}
	
	public static void seeArticlesByPrice(Connection conn, double price)
	{
		boolean rsEmpty = true;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("SELECT * FROM articulos " +
										"WHERE precio < ?;");
			ps.setDouble(1, price);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				rsEmpty = false;
				System.out.println(rs.getString("nombre") + " - " +
						rs.getDouble("precio"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
		}
		
		if(rsEmpty)
		{
			System.out.println("No results");
		}
	}
	
	public static void main(String[] args)
	{
		Connection conn = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			addPriceColumn(conn);
			insertArticle(conn, "Bed", 12.5);
			seeArticlesByPrice(conn, 10);
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
}
