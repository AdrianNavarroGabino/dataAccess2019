// Adrián Navarro Gabino

/*
 * Create an improved variant of exercise 8 with a new "3-Modify" option,
 * which will ask the user for the "id" of an item and, if it exists, the new
 * value for its name. You must use the UPDATE SQL command.
 */

package com.adriannavarrogabino.exercisesPostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0209
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Scanner sc = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	Connection conn = null;
    	Statement statement = null;
    	
    	try
		{
	    	Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			
	    	int option;
	    	do
	    	{
		        System.out.println("1. Search article");
		        System.out.println("2. Add article");
		        System.out.println("3. Modify");
		        System.out.println("4. Exit");
		        System.out.print("Choose an option: ");
		        option = sc.nextInt();
		        sc.nextLine();
		        
		        switch(option)
		        {
		        	case 1: searchArticle(statement); break;
		        	case 2: addArticle(statement); break;
		        	case 3: modifyArticle(statement); break;
		        	case 4: System.out.println("Bye"); break;
		        	default: System.out.println("Wrong option"); break;
		        }
		        System.out.println();
	    	} while(option != 4);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	finally
    	{
    		DbUtils.closeQuietly(statement);
    		DbUtils.closeQuietly(conn);
    	}
    }
    
    public static void searchArticle(Statement ps)
    {
    	ResultSet rs = null;
    	
    	System.out.print("Article to search: ");
    	String search = sc.nextLine().toUpperCase();
    	
    	boolean results = false;
    	sql = "SELECT * FROM ARTICULOS WHERE UPPER(NOMBRE) LIKE '%" + 
    			search + "%';";
    	try
		{
			rs = ps.executeQuery(sql);
			while(rs.next())
			{
				results = true;
				String name = rs.getString("NOMBRE");
				double price = rs.getDouble("PRECIO");
				System.out.println("Name: " + name + " - Price: " + price + 
						" €");
			}
			
			if(!results)
			{
				System.out.println("There is no data");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	finally
    	{
    		DbUtils.closeQuietly(rs);
    	}
    }
    
    public static void addArticle(Statement ps)
    {
    	System.out.print("Enter name: ");
    	String name = sc.nextLine();
    	System.out.print("Enter price: ");
    	double price = Double.parseDouble(sc.nextLine().replace(',', '.'));
    	
    	sql = "INSERT INTO ARTICULOS (NOMBRE, PRECIO) VALUES ('" + name +
    			"', '" + price + "');";
    	
    	try
		{
			int affectedRows = ps.executeUpdate(sql);
			if(affectedRows == 1)
			{
				System.out.println("Article inserted");
			}
		}
    	catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    public static void modifyArticle(Statement ps)
    {
    	ResultSet rs = null;
    	
    	System.out.print("Enter id: ");
    	int id = sc.nextInt();
    	sc.nextLine();
    	
    	sql = "SELECT * FROM ARTICULOS WHERE id = " + id;
    	
    	try
		{
			rs = ps.executeQuery(sql);
			while(rs.next())
			{
				System.out.print("Enter name: ");
		    	String name = sc.nextLine();
		    	name = name.equals("") ? rs.getString("NOMBRE") : name;
		    	System.out.print("Enter price: ");
		    	double price = Double.parseDouble(
		    			sc.nextLine().replace(',', '.'));
		    	
		    	sql = "UPDATE ARTICULOS SET NOMBRE = '" + name + 
		    			"', PRECIO = " + price + " WHERE ID = " + id + ";";
		    	
		    	ps.executeUpdate(sql);
			}
		} catch (SQLException e)
		{
		}
    	finally
    	{
    		DbUtils.closeQuietly(rs);
    	}
    }
}
