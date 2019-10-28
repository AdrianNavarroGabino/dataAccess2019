// Adrián Navarro Gabino

/*
 * Create a new version of exercise 13, which lets you know the names of the 
 * students of a course, with an SQL function, which you will consult with a 
 * "prepared statement".
 */

/*
CREATE OR REPLACE FUNCTION FN_GET_STUDENTS_NAME(COURSE INT)
RETURNS TABLE(NOMBRE VARCHAR(50))
AS
	'SELECT NOMBRE
	FROM ALUMNOS, ALUMNOS_CURSOS
	WHERE ID = ID_ALUMNO
	  AND ID_CURSO = COURSE'
LANGUAGE SQL;
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0214
{
	public static void main(String[] args)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/dia04";
			String usuario = "postgres";
			String password = "1234";
			conn = DriverManager.getConnection(url, usuario, password);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter course id: ");
			int courseId = sc.nextInt();
			
			ps = conn.prepareStatement(
					"SELECT FN_GET_STUDENTS_NAME(?);");
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Student: " + rs.getString(1));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
}

