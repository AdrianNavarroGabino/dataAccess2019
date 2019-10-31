// Adrián Navarro Gabino

/*
 * Create a "Deporte" database in PostgreSQL.
 * 
 * Create a "deportes" table (cod, name) and add at least 3 sample data (for 
 * example, football, basketball and bow).
 * 
 * Create in a table "deportistas" (cod, name, codDeporte) and add 5 sample  
 * data, not all related to sports (for example, 3 who play football, 1 who 
 * plays basketball and another who does not practice any sport -even- ).
 * 
 * Save the Script to create the database and tables so it can be run again.
 * 
 * Create two functions in PostgreSql:
 * 
 *     1. Sports for which no athlete has been indicated. This will be in SQL 
 *     language.
 *     2. Name of each athlete, his code and name of the sport he practices 
 *     (maybe empty). This will be in plpgsql language.
 * 
 * Create a Java application that allows:
 * 
 *     Add a sport. With the use of Statement.
 *     Add an athlete. With the use of PreparedStatement.
 *     Search by athlete name (partial search, not case sensitive), showing the 
 *     name, athlete code and name of the sport you practice. To do this, 
 *     use the function you created and call it with CallableStatement.
 *     Empty sports. To do this, use the function you created and call it with 
 *     CallableStatement.
 * 
 * Deliver the PostgreSql Creation Script and the project's Java class in a 
 * compressed file.
 */

/*
DROP FUNCTION IF EXISTS GET_DEPORTISTAS;
DROP FUNCTION IF EXISTS DEPORTES_SIN_DEPORTISTA;
DROP TABLE IF EXISTS DEPORTISTAS;
DROP TABLE IF EXISTS DEPORTES;

CREATE TABLE DEPORTES
(
	COD SERIAL PRIMARY KEY,
	NOMBRE VARCHAR(20)
);

INSERT INTO DEPORTES
(NOMBRE)
VALUES
('FUTBOL');

INSERT INTO DEPORTES
(NOMBRE)
VALUES
('BALONCESTO');

INSERT INTO DEPORTES
(NOMBRE)
VALUES
('ARCO');

CREATE TABLE DEPORTISTAS
(
	COD SERIAL PRIMARY KEY,
	NOMBRE VARCHAR(50),
	COD_DEPORTE INTEGER,
	CONSTRAINT FK_DEPORTISTAS_DEPORTES FOREIGN KEY
	(COD_DEPORTE) REFERENCES DEPORTES(COD)
);

INSERT INTO DEPORTISTAS
(NOMBRE, COD_DEPORTE)
VALUES
('Antonio Sala', 1);

INSERT INTO DEPORTISTAS
(NOMBRE, COD_DEPORTE)
VALUES
('Juan Olivares', 1);

INSERT INTO DEPORTISTAS
(NOMBRE, COD_DEPORTE)
VALUES
('Ramon Llopis', 1);

INSERT INTO DEPORTISTAS
(NOMBRE, COD_DEPORTE)
VALUES
('Pepe Pomares', 2);

INSERT INTO DEPORTISTAS
(NOMBRE)
VALUES
('Javier Mora');

CREATE OR REPLACE FUNCTION DEPORTES_SIN_DEPORTISTA()
RETURNS SETOF DEPORTES
AS
	'SELECT DEPO.COD, DEPO.NOMBRE
	FROM DEPORTES DEPO LEFT OUTER JOIN DEPORTISTAS DEP
	ON DEPO.COD = COD_DEPORTE
	WHERE COD_DEPORTE IS NULL;'
LANGUAGE sql;

CREATE FUNCTION GET_DEPORTISTAS(ID_AUX INTEGER)
RETURNS TABLE(COD INTEGER, NOMBRE VARCHAR(50), DEPORTE VARCHAR(20))
AS $$
DECLARE
	REG RECORD;
BEGIN
	FOR REG IN SELECT DEPO.COD AS COD_DEPORTISTA, DEPO.NOMBRE AS DEPORTISTA, DEP.NOMBRE AS DEPORTE
				FROM DEPORTISTAS AS DEPO LEFT OUTER JOIN DEPORTES AS DEP
				ON DEPO.COD_DEPORTE = DEP.COD
				WHERE ID_AUX = DEPO.COD LOOP
		COD := REG.COD_DEPORTISTA;
		NOMBRE := REG.DEPORTISTA;
		DEPORTE := REG.DEPORTE;
		RETURN NEXT;
	END LOOP;
	RETURN;
END
$$
LANGUAGE 'plpgsql';
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;

public class Exercise0215
{
	public static void addSport(Connection conn)
	{
		Statement statement = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter sport name: ");
		String sportName = sc.nextLine();
		
		String sql = "INSERT INTO DEPORTES (NOMBRE) VALUES ('" +
				sportName + "')";
		
		try
		{
			statement = conn.createStatement();
			int affectedRows = statement.executeUpdate(sql);
			System.out.println("Affected rows: " + affectedRows);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(statement);
		}
	}
	
	public static void addSportist(Connection conn)
	{
		PreparedStatement ps = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter sportist name: ");
		String sportistName = sc.nextLine();
		System.out.print("Enter sport id (press enter to leave empty): ");
		String id = sc.nextLine();
		
		try
		{
			
			
			if(id.contentEquals(""))
			{
				ps = conn.prepareStatement("INSERT INTO DEPORTISTAS " +
						"(NOMBRE) VALUES (?);");
			}
			else
			{
				ps = conn.prepareStatement("INSERT INTO DEPORTISTAS " +
						"(NOMBRE, COD_DEPORTE) VALUES (?,?);");
				ps.setInt(2, Integer.parseInt(id));
			}
			ps.setString(1, sportistName);
			int affectedRows2 = ps.executeUpdate();
			System.out.println("Affected rows: " + affectedRows2);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(ps);
		}
	}
	
	public static void searchSportist(Connection conn)
	{
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter sportist name: ");
		String search = sc.nextLine();
		search = search.toUpperCase();
		
		try
		{
			ps = conn.prepareStatement("SELECT COD FROM DEPORTISTAS " +
								"WHERE UPPER(NOMBRE) = ?");
			ps.setString(1, search);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				cs = conn.prepareCall(
				"{call GET_DEPORTISTAS(?)}");
				cs.setInt(1, rs.getInt("COD"));
				rs = cs.executeQuery();
				while(rs.next())
				{
					System.out.println(rs.getInt(1) + " - " + rs.getString(2) +
							" - " + rs.getString(3));
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(rs2);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(cs);
		}
	}
	
	public static void getEmptySports(Connection conn)
	{
		CallableStatement cs = null;
		ResultSet rsSports = null;
		
		try
		{
			cs = conn.prepareCall(
			"{call DEPORTES_SIN_DEPORTISTA()}");
			rsSports = cs.executeQuery();
			while(rsSports.next())
			{
				System.out.println(rsSports.getInt(1) + " - " +
								rsSports.getString(2));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(rsSports);
			DbUtils.closeQuietly(cs);
		}
	}
	
	public static void main(String[] args)
	{
		Connection conn = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Deporte";
			String usuario = "postgres";
			String password = "1234";
			conn = DriverManager.getConnection(url, usuario, password);
			Scanner sc = new Scanner(System.in);
			
			int option;
	    	do
	    	{
		        System.out.println("1. Add sport");
		        System.out.println("2. Add sportist");
		        System.out.println("3. Search sportist by name");
		        System.out.println("4. Show empty sports");
		        System.out.println("5. Exit");
		        System.out.print("Choose an option: ");
		        option = sc.nextInt();
		        sc.nextLine();
		        
		        switch(option)
		        {
		        	case 1: addSport(conn); break;
		        	case 2: addSportist(conn); break;
		        	case 3: searchSportist(conn); break;
		        	case 4: getEmptySports(conn); break;
		        	case 5: System.out.println("Bye"); break;
		        	default: System.out.println("Wrong option"); break;
		        }
		        System.out.println();
	    	} while(option != 5);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
}
