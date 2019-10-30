// Adrián Navarro Gabino

/*
 * 2.6.1: Creates a function in PL / pgSQL, which given the code of a copy 
 * device of Security, return your name.
 */

/*
CREATE OR REPLACE FUNCTION FN_GET_DEVICE_NAME(DEV_ID VARCHAR)
RETURNS VARCHAR
AS
$$
BEGIN
	RETURN (SELECT NOMBRE
		   FROM PRUEBA1
		   WHERE CODIGO = DEV_ID);
END
$$
LANGUAGE PLPGSQL;
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise261 {
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/pruebas1";
		String user = "postgres";
		String password = "1234";
		
		Connection conn = null;
		CallableStatement cs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			cs = conn.prepareCall("{call FN_GET_DEVICE_NAME(?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(1, "Device1");
			cs.execute();
			System.out.println("Name: " + cs.getString(1));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(cs);
			DbUtils.closeQuietly(conn);
		}
	}
}
