// Adrián Navarro Gabino

/*
 * Install PostgreSQL.
 * Using the "psql" tool, create a "dia01" database, with an "articulos" table,
 * which will have two fields: "id" (numeric, primary key) and "name"
 * (text, up to 100 characters).
 * Add two sample data. Show existing data.
 */

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0201
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void createArticles(Connection conn)
	{
		PreparedStatement ps = null;
		
		try
		{
			ps = conn.prepareStatement("CREATE TABLE articulos(" +
					"id serial PRIMARY KEY," +
					"nombre VARCHAR(100));");
			
			ps.executeUpdate();
			
			System.out.println("Table articulos created");
		}
		catch (SQLException e)
		{
			System.out.println("Table articulos already exists");
		}
		finally
		{
			DbUtils.closeQuietly(ps);
		}
	}
	
	public static void insertArticle(Connection conn, String name)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("INSERT INTO articulos (nombre)" +
										" VALUES (?)");
			ps.setString(1, name);
			ps.executeUpdate();
			System.out.println(name + " inserted");
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
	}
	
	public static void main(String[] args)
	{
		Connection conn = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			createArticles(conn);
			insertArticle(conn, "Table");
			insertArticle(conn, "Chair");
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
