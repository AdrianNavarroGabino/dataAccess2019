// Adrián Navarro Gabino

/*
 * Create a new version of Exercise 11, which also allows you to know the 
 * number of people in a certain course (from your "id"). Use a PL / pgSQL 
 * function, which you will call with a "callableStatement".
 */

/*
CREATE OR REPLACE FUNCTION FN_PEOPLE_PER_COURSE(COURSE INT)
RETURNS INTEGER
AS
$$
BEGIN
	RETURN (SELECT COUNT(*)
	FROM ALUMNOS_CURSOS
	WHERE ID_CURSO = COURSE);
END
$$
LANGUAGE PLPGSQL;
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0212 {
	public static int GetNumberOfPeople(Connection conn, int courseId)
	{
		CallableStatement cStmt;
		try
		{
			cStmt = conn.prepareCall(
			"{call FN_PEOPLE_PER_COURSE(?)}");
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.setInt(1, courseId);
			cStmt.execute();
			return cStmt.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		
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
			System.out.println("Result: " + GetNumberOfPeople(conn, courseId));
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
}