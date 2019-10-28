// Adrián Navarro Gabino

/*
 * Create a new version of exercise 12, which also allows you to know the 
 * average number of students per course, with an SQL function, which you will 
 * call with an "executeQuery".
 */

/*
CREATE OR REPLACE FUNCTION FN_GET_AVERAGE()
RETURNS NUMERIC
AS
	'SELECT((SELECT CAST(COUNT(*) AS DECIMAL)
	FROM ALUMNOS_CURSOS) / (SELECT CAST(COUNT(*) AS DECIMAL)
	FROM CURSOS))'
LANGUAGE SQL;
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0213 {
	
	public static void main(String[] args) {
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
			
			ps = conn.prepareStatement("SELECT FN_GET_AVERAGE();");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Result: " + rs.getFloat(1));
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
