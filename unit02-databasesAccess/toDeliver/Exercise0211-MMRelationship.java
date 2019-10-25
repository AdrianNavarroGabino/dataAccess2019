// Adrián Navarro Gabino

/*
 * Create a new version of exercise 10, using an M: M relationship (each user 
 * can participate in several courses). Your Java program should allow you to 
 * add courses, add participants to a course and view all the data of a course 
 * (including list of participants) or a participant (including their courses).
 */

/*
DROP TABLE IF EXISTS alumnos_cursos;
DROP TABLE IF EXISTS alumnos;
DROP TABLE IF EXISTS cursos;

CREATE TABLE alumnos
(
    id SERIAL PRIMARY KEY,
    nombre character varying(50)
);

CREATE TABLE cursos
(
    id SERIAL PRIMARY KEY,
    titulo character varying(20),
    fechaini date
);

CREATE TABLE alumnos_cursos
(
	id_alumno INTEGER REFERENCES alumnos(id),
	id_curso INTEGER REFERENCES cursos(id),
	CONSTRAINT PK_ALUMNOS_CURSOS PRIMARY KEY (id_alumno, id_curso)
);
*/

import java.sql.*;
import java.util.*;

public class Exercise0211
{
	static String url = "jdbc:postgresql://localhost:5432/dia04";
	static String user = "postgres";
	static String password = "1234";
	static String sql = "";
	static Scanner sc = new Scanner(System.in);
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public static void insertCourse(String title, int year, int month, int day)
	{
		try
		{
			ps = conn.prepareStatement("INSERT INTO CURSOS (titulo, fechaIni)" +
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
			ps = conn.prepareStatement("INSERT INTO ALUMNOS (nombre)" +
										"VALUES (?);");
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void assignCourse(int studentId, int courseId)
	{
		try
		{
			ps = conn.prepareStatement("INSERT INTO alumnos_cursos " +
					"(id_alumno, id_curso) VALUES (?, ?)");
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void seeCourseData(int id)
	{
		try
		{
			ps = conn.prepareStatement(
					"SELECT titulo FROM CURSOS WHERE id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Course: " + rs.getString("titulo"));
			}
			
			ps = conn.prepareStatement(
					"SELECT nombre FROM ALUMNOS, ALUMNOS_CURSOS " +
					"WHERE id_curso = ? AND id_alumno = id;");
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
	
	public static void seeStudentData(int id)
	{
		try
		{
			ps = conn.prepareStatement(
					"SELECT nombre FROM ALUMNOS WHERE id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Student: " + rs.getString("nombre"));
			}
			
			ps = conn.prepareStatement(
					"SELECT titulo FROM CURSOS, ALUMNOS_CURSOS " +
					"WHERE id_alumno = ? AND id_curso = id;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Course: " + rs.getString("titulo"));
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
			
			int option;
			
			do
			{
				System.out.println("1. Insert course");
				System.out.println("2. Insert student");
				System.out.println("3. Assign student to a course");
				System.out.println("4. See course data");
				System.out.println("5. See student data");
				System.out.println("0. Exit");
				System.out.print("Choose an option: ");
				option = sc.nextInt();
				sc.nextLine();
				
				switch(option)
				{
					case 1:
						System.out.print("Enter course name: ");
						String name = sc.nextLine();
						System.out.print("Enter initial date (dd/mm/yyyy): ");
						String date = sc.nextLine();
						
						insertCourse(name,
								Integer.parseInt(date.split("/")[2]),
								Integer.parseInt(date.split("/")[1]),
								Integer.parseInt(date.split("/")[0]));
						
						break;
					case 2:
						System.out.print("Enter student name: ");
						String studentName = sc.nextLine();
						System.out.print("Enter course id: ");
						int id = sc.nextInt();
						
						insertStudent(studentName, id);
						
						break;
					case 3:
						System.out.print("Enter student id: ");
						int studentId = sc.nextInt();
						System.out.print("Enter course id: ");
						int courseId = sc.nextInt();
						
						assignCourse(studentId, courseId);
						break;
					case 4:
						System.out.print("Enter course id: ");
						int idSee = sc.nextInt();
						
						seeCourseData(idSee);
						
						break;
					case 5:
						System.out.print("Enter student id: ");
						int studentIdSee = sc.nextInt();
						
						seeStudentData(studentIdSee);
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
