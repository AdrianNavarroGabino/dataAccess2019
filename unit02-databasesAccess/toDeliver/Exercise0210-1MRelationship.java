// Adrián Navarro Gabino

/*
 * As a contact with 1: M relationships, we will write down courses (id, title,
 * dateIni) and users who attend (id, name). In each course there can be 
 * multiple users, and we will assume that each user can only participate in 
 * one course. Your Java program should allow you to add courses, add 
 * participants to a course and view all the data of a course (including list 
 * of participants).
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Exercise0210
{
	static String url = "jdbc:postgresql://localhost:5432/dia03";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Scanner sc = new Scanner(System.in);
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void createCourses()
	{
		try
		{
			ps = conn.prepareStatement("CREATE TABLE CURSO(" +
										"id serial PRIMARY KEY," +
										"titulo VARCHAR(20)," +
										"fechaIni DATE);");
			ps.executeUpdate();
			
		}
		catch (SQLException e)
		{
		}
	}
	
	public static void createStudents()
	{
		try
		{
			ps = conn.prepareStatement("CREATE TABLE ALUMNO(" +
										"id SERIAL PRIMARY KEY," +
										"nombre VARCHAR(50)," +
										"idCurso INTEGER REFERENCES " +
										"CURSO(ID));");
			ps.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertCourse(String title, int year, int month, int day)
	{
		try
		{
			ps = conn.prepareStatement("INSERT INTO CURSO (titulo, fechaIni)" +
										"VALUES (?, make_date(?,?,?));");
			ps.setString(1, title);
			ps.setInt(2, year);
			ps.setInt(3, month);
			ps.setInt(4, day);
			
			ps.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertStudent(String name, int courseId)
	{
		try
		{
			ps = conn.prepareStatement("INSERT INTO ALUMNO (nombre, idCurso)" +
										"VALUES (?, ?);");
			ps.setString(1, name);
			ps.setInt(2, courseId);
			
			ps.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void seeCourseData(int id)
	{
		try
		{
			ps = conn.prepareStatement(
					"SELECT titulo FROM CURSO WHERE id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Course: " + rs.getString("titulo"));
			}
			
			ps = conn.prepareStatement(
					"SELECT nombre FROM ALUMNO WHERE idCurso = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Student: " + rs.getString("nombre"));
			}
		} catch (SQLException e)
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
			
			createCourses();
			createStudents();
			
			int option;
			
			do
			{
				System.out.println("1. Insert course");
				System.out.println("2. Insert student");
				System.out.println("3. See data");
				System.out.println("0. Exit");
				System.out.print("Choose an option: ");
				option = sc.nextInt();
				
				switch(option)
				{
					case 1:
						System.out.print("Enter course name: ");
						String name = sc.nextLine();
						System.out.print("Enter initial date (dd/mm/yyyy): ");
						String date = sc.nextLine();
						
						insertCourse(name,
								Integer.parseInt(date.split(";")[2]),
								Integer.parseInt(date.split(";")[1]),
								Integer.parseInt(date.split(";")[0]));
						
						break;
					case 2:
						System.out.print("Enter student name: ");
						String studentName = sc.nextLine();
						System.out.print("Enter course id: ");
						int id = sc.nextInt();
						
						insertStudent(studentName, id);
						
						break;
					case 3:
						System.out.print("Enter course id: ");
						int idSee = sc.nextInt();
						
						seeCourseData(idSee);
						
						break;
					case 0:
						System.out.println("Bye");
						break;
					default:
						System.out.println("Wrong option");
						break;
				}
				System.out.println();
			}while(option != 0);
		}
		catch (SQLException ex) { }
		catch (ClassNotFoundException e) { }
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

