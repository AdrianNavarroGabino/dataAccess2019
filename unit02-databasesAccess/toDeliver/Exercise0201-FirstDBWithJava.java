// Adrián Navarro Gabino

/*
 * Install PostgreSQL.
 * Using the "psql" tool, create a "dia01" database, with an "articulos" table,
 * which will have two fields: "id" (numeric, primary key) and "name"
 * (text, up to 100 characters).
 * Add two sample data. Show existing data.
 */

package com.adriannavarrogabino.exercisespostgresql;

import java.sql.*;

public class Exercise0201
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void createArticles()
	{
		try
		{
			ps = conn.prepareStatement("CREATE TABLE articulos(" +
					"id serial PRIMARY KEY," +
					"nombre VARCHAR(100));");
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
		}
	}
	
	public static void insertArticle(String name)
	{
		try
		{
			ps = conn.prepareStatement("SELECT * FROM articulos " +
										"WHERE nombre = ?;");
			ps.setString(1, name);
			
			if(ps.executeQuery() == null)
			{
				ps = conn.prepareStatement("INSERT INTO articulos (nombre)" +
											" VALUES (?)");
				ps.setString(1, name);
				ps.executeUpdate();
				System.out.println(name + " inserted");
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
			createArticles();
			insertArticle("Table");
			insertArticle("Chair");
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