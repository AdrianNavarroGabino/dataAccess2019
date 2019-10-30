// Adrián Navarro Gabino

/*
 * 2.6.3: Create a function in PL / pgSQL, which given a location, return the
 * amount of backup devices that are stored in it.
 */

/*
CREATE OR REPLACE FUNCTION FN_GET_DEVICES_AMOUNT(DEV_LOCATION VARCHAR)
RETURNS INTEGER
AS
$$
BEGIN
	RETURN (SELECT COUNT(*)
		   FROM PRUEBA1
		   WHERE SITUACION = DEV_LOCATION);
END
$$
LANGUAGE PLPGSQL;
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise263 {
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
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter device location: ");
			String location = sc.nextLine();
			
			cs = conn.prepareCall("{ call FN_GET_DEVICES_AMOUNT(?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(1, location);
			cs.execute();
			
			System.out.println("Location: " + cs.getInt(1));
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