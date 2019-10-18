// Adrián Navarro Gabino

/*
 * Create a Java program, which connects to the "dia01" database, and allows 
 * both adding new articles and displaying those that contain a certain text 
 * entered by the user.
 * 
 * You should show a menu with the options: 1-Search, 2-Add, 3-Exit
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exercise0208
{
	static String url = "jdbc:postgresql://localhost:5432/dia01";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Scanner sc = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	try
		{
	    	Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			
	    	int option;
	    	do
	    	{
		        System.out.println("1. Search article");
		        System.out.println("2. Add article");
		        System.out.println("3. Exit");
		        System.out.print("Choose an option: ");
		        option = sc.nextInt();
		        sc.nextLine();
		        
		        if(option == 1)
		        {
		        	searchArticle(statement);
		        }
		        else if(option == 2)
		        {
		        	addArticle(statement);
		        }
		        else if(option == 3)
		        {
		        	System.out.println("Bye");
		        }
		        else
		        {
		        	System.out.println("Wrong option");
		        }
		        System.out.println();
	    	} while(option != 3);
	    	conn.close();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    public static void searchArticle(Statement statement)
    {
    	System.out.print("Article to search: ");
    	String search = sc.nextLine().toUpperCase();
    	
    	boolean results = false;
    	sql = "SELECT * FROM ARTICULOS WHERE UPPER(NOMBRE) LIKE '%" + 
    			search + "%';";
    	try
		{
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				results = true;
				String name = rs.getString("NOMBRE");
				double price = rs.getDouble("PRECIO");
				System.out.println("Name: " + name + " - Price: " + price + 
						" €");
			}
			rs.close();
			
			if(!results)
			{
				System.out.println("There is no data");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    public static void addArticle(Statement statement)
    {
    	System.out.print("Enter name: ");
    	String name = sc.nextLine();
    	System.out.print("Enter price: ");
    	double price = Double.parseDouble(sc.nextLine().replace(',', '.'));
    	
    	sql = "INSERT INTO ARTICULOS (NOMBRE, PRECIO) VALUES ('" + name +
    			"', '" + price + "');";
    	
    	try
		{
			int affectedRows = statement.executeUpdate(sql);
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
}
