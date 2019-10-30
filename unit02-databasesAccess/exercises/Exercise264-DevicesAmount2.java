// Adrián Navarro Gabino

/*
 * 2.6.4: Create a Java program that reads the names of all locations, the
 * Store in a list and then check the number of devices
 * stored in each one, using the previous function, to be able to show those
 * locations that are not empty.
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.commons.dbutils.DbUtils;

public class Exercise264 {
	static Connection conn = null;
	
	public static int getDevicesAmount(String devLocation,
			CallableStatement cs) throws SQLException
	{
		cs = conn.prepareCall("{ call FN_GET_DEVICES_AMOUNT(?) }");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setString(1, devLocation);
		cs.execute();
		return cs.getInt(1);
	}
	
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/pruebas1";
		String user = "postgres";
		String password = "1234";
		
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<String> locations = new ArrayList<String>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			ps = conn.prepareStatement(
					"SELECT DISTINCT SITUACION FROM PRUEBA1;");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				locations.add(rs.getString("situacion"));
			}
			
			locations.stream()
					.forEach(l -> {
						System.out.print("Location: " + l);
						try {
							System.out.println(" - Devices: " +
									getDevicesAmount(l, cs));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(cs);
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
}