// Adrián Navarro Gabino

package com.adriannavarrogabino.exercise0310;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio0310 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException{
		ManejadorBaseDatos manejador = new ManejadorBaseDatos();
		
		boolean exit = false;
		
		do
		{
			ShowMenu();
			switch (getOption()) {
			case 1:
				addPersona(manejador);
				System.out.println();
				break;
			case 2:
				addCliente(manejador);
				System.out.println();
				break;
			case 3:
				addFuncionario(manejador);
				System.out.println();
				break;
			case 4:
				modifyPersona(manejador);
				System.out.println();
				break;
			case 5:
				modifyCliente(manejador);
				System.out.println();
				break;
			case 6:
				modifyFuncionario(manejador);
				System.out.println();
				break;
			case 7:
				showPersonas(manejador);
				System.out.println();
				break;
			case 8:
				showClientes(manejador);
				System.out.println();
				break;
			case 9:
				showFuncionarios(manejador);
				System.out.println();
				break;
			case 0:
				System.out.println("Hasta otra!");
				exit = true;
				break;
			default:
				break;
			}
		}
		while(!exit);
	}
	
	private static int getOption() {
		System.out.println("Option:");
		int option = sc.nextInt();
		sc.nextLine();
		return option;
	}

	private static void ShowMenu()
	{
		System.out.println("1.-Añadir persona");
		System.out.println("2.-Añadir cliente");
		System.out.println("3.-Añadir funcionario");
		System.out.println("4.-Modificar persona");
		System.out.println("5.-Modificar cliente");
		System.out.println("6.-Modificar funcionario");
		System.out.println("7.-Ver personas");
		System.out.println("8.-Ver clientes");
		System.out.println("9.-Ver funcionarios");
		System.out.println("0.-Salir");
	}
	
	
	private static void addPersona(ManejadorBaseDatos manejador) 
			throws ClassNotFoundException, SQLException 
		{
			List<String> persona = getPersona();
			
			String sqlInsert = "insert into personas " +
					"(nombres, apellidos, direccion, telefono, fecha_nacim) " +
					"values ('" + persona.get(0) + "', '" + persona.get(1) + "', '" +
					persona.get(2) + "', " + Integer.parseInt(persona.get(3)) + ", date('" +
					persona.get(6) + "-" + persona.get(5) + "-" + persona.get(4) + "'));";
					
			manejador.update(sqlInsert);
		}

	private static void addCliente(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		List<String> cliente = getCliente();
		
		String sqlInsert = "insert into clientes " +
				"(nombres, apellidos, direccion, telefono, fecha_nacim, nro_cuenta, " +
				"estado, tipo_cliente) values ('" +
				cliente.get(0) + "', '" + cliente.get(1) + "', '" +
				cliente.get(2) + "', " + Integer.parseInt(cliente.get(3)) + ", date('" +
				cliente.get(6) + "-" + cliente.get(5) + "-" + cliente.get(4) + "'), '" + 
				cliente.get(7) + "', '" + cliente.get(8) + "', '" + cliente.get(9) + "');";
				
		manejador.update(sqlInsert);
	}
	
	private static void addFuncionario(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		List<String> funcionario = getFuncionario();
		
		String sqlInsert = "insert into funcionarios " +
				"(nombres, apellidos, direccion, telefono, fecha_nacim, " +
				"cargo, departamento, fecha_ingreso) values ('" +
				funcionario.get(0) + "', '" +
				funcionario.get(1) + "', '" + funcionario.get(2) + "', " + 
				Integer.parseInt(funcionario.get(3)) + ", date('" +
				funcionario.get(6) + "-" + funcionario.get(5) + "-" +
				funcionario.get(4) + "'), ('" + funcionario.get(7) + "', '" +
				funcionario.get(8) + "'), '" + funcionario.get(9) +
				"', date('" + funcionario.get(12) + "-" + funcionario.get(11) +
				"-" + funcionario.get(10) + "'));";
				
		manejador.update(sqlInsert);
	}
	
	private static void modifyPersona(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		System.out.print("Introduce el número de la persona a modificar: ");
		int numero = sc.nextInt();
		sc.nextLine();
		
		String sqlSelect = "select * from only personas where numero = " +
				numero;
		
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				
				System.out.println("Datos antiguos");
				System.out.println("Nombre: " + rs.getString("nombres"));
				System.out.println("Apellidos: " + rs.getString("apellidos"));
				System.out.println("Dirección: " + rs.getString("direccion"));
				System.out.println("Teléfono: " + rs.getInt("telefono"));
				System.out.println("Fecha de nacimiento: " + 
						nacim.getDayOfMonth() + "/" + nacim.getMonthValue() +
						"/" + nacim.getYear());
				
				System.out.println();
				System.out.println("Nuevos datos (enter para conservar el dato antiguo)");
				
				String sqlModify = "update personas set ";
				
				String aux = modifyPersonaAux();
				
				if(aux.length() > 0)
				{
					sqlModify += aux;
					sqlModify += " where numero = " + numero + ";";
					manejador.update(sqlModify);
				}
			}
		}
		else
		{
			System.out.println("El usuario no existe");
		}
	}
	
	private static void modifyCliente(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		System.out.print("Introduce el número del cliente a modificar: ");
		int numero = sc.nextInt();
		sc.nextLine();
		
		String sqlSelect = "select * from clientes where numero = " +
				numero;
		
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				
				System.out.println("Datos antiguos");
				System.out.println("Nombre: " + rs.getString("nombres"));
				System.out.println("Apellidos: " + rs.getString("apellidos"));
				System.out.println("Dirección: " + rs.getString("direccion"));
				System.out.println("Teléfono: " + rs.getInt("telefono"));
				System.out.println("Fecha de nacimiento: " + 
						nacim.getDayOfMonth() + "/" + nacim.getMonthValue() +
						"/" + nacim.getYear());
				System.out.println("Número de cuenta: " +
						rs.getString("nro_cuenta"));
				System.out.println("Estado: " + rs.getString("estado"));
				System.out.println("Tipo de cliente: " +
						rs.getString("tipo_cliente"));
				
				System.out.println();
				System.out.println(
						"Nuevos datos (enter para conservar el dato antiguo)");
				
				String sqlModify = "update clientes set ";
				
				String aux = modifyPersonaAux();
				String aux2 = modifyClienteAux();
				
				if(aux.length() > 0)
				{
					sqlModify += aux;
					if(aux2.length() > 0)
					{
						sqlModify += ", " + aux2;
					}
					sqlModify += " where numero = " + numero + ";";
					manejador.update(sqlModify);
				}
				else
				{
					if(aux2.length() > 0)
					{
						sqlModify += aux2;
						sqlModify += " where numero = " + numero + ";";
						manejador.update(sqlModify);
					}
				}
			}
		}
		else
		{
			System.out.println("El usuario no existe");
		}
	}
	
	private static void modifyFuncionario(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		System.out.print("Introduce el número del funcionario a modificar: ");
		int numero = sc.nextInt();
		sc.nextLine();
		
		String sqlSelect = "select * from funcionarios where numero = " +
				numero;
		
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				LocalDate ingreso = rs.getDate("fecha_ingreso").toLocalDate();
				
				System.out.println("Datos antiguos");
				System.out.println("Nombre: " + rs.getString("nombres"));
				System.out.println("Apellidos: " + rs.getString("apellidos"));
				System.out.println("Dirección: " + rs.getString("direccion"));
				System.out.println("Teléfono: " + rs.getInt("telefono"));
				System.out.println("Fecha de nacimiento: " + 
						nacim.getDayOfMonth() + "/" + nacim.getMonthValue() +
						"/" + nacim.getYear());
				System.out.println("Cargo: " + rs.getArray("cargo"));
				System.out.println("Departamento: " + rs.getString("departamento"));
				System.out.println("Fecha de ingreso: " + 
						ingreso.getDayOfMonth() + "/" +
						ingreso.getMonthValue() +
						"/" + ingreso.getYear());
				
				System.out.println();
				System.out.println(
						"Nuevos datos (enter para conservar el dato antiguo)");
				
				String sqlModify = "update funcionarios set ";
				
				String aux = modifyPersonaAux();
				String aux2 = modifyFuncionarioAux();
				
				if(aux.length() > 0)
				{
					sqlModify += aux;
					if(aux2.length() > 0)
					{
						sqlModify += ", " + aux2;
					}
					sqlModify += " where numero = " + numero + ";";
					manejador.update(sqlModify);
				}
				else
				{
					if(aux2.length() > 0)
					{
						sqlModify += aux2;
						sqlModify += " where numero = " + numero + ";";
						manejador.update(sqlModify);
					}
				}
			}
		}
		else
		{
			System.out.println("El usuario no existe");
		}
	}
	
	private static void showPersonas(ManejadorBaseDatos manejador) 
		throws SQLException, ClassNotFoundException 
	{
		String sqlSelect = "select * from only personas";
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			System.out.println(String.format("%-15s", "Nombre") +
					String.format("%-20s", "Apellidos") +
					String.format("%-20s", "Dirección") +
					String.format("%-11s", "Teléfono") +
					String.format("%-12s", "Nacimiento") +
					"Número");
			for(int i = 0; i < 84; i++)
				System.out.print("-");
			System.out.println();
			while(rs.next())
			{
				String nombre = rs.getString("nombres").length() <= 13 ?
						rs.getString("nombres") :
							rs.getString("nombres").substring(0, 12);
				String apellidos = rs.getString("apellidos").length() <= 18 ?
						rs.getString("apellidos") :
							rs.getString("apellidos").substring(0, 17);
				String direccion = rs.getString("direccion").length() <= 18 ?
						rs.getString("direccion") :
							rs.getString("direccion").substring(0, 17);
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				System.out.println(
						String.format("%-15s", nombre) +
						String.format("%-20s", apellidos) +
						String.format("%-20s", direccion) +
						String.format("%-11s", rs.getInt("telefono")) +
						String.format("%-12s", nacim.getDayOfMonth() + "/" +
						nacim.getMonthValue() +
						"/" + nacim.getYear()) + rs.getInt("numero"));
			}
		}
		else
		{
			System.out.println("No hay registros");
		}
	}
	
	private static void showClientes(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		String sqlSelect = "select * from clientes";
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			System.out.println(String.format("%-10s", "Nombre") +
					String.format("%-16s", "Apellidos") +
					String.format("%-16s", "Dirección") +
					String.format("%-11s", "Teléfono") +
					String.format("%-12s", "Nacimiento") +
					String.format("%-22s", "Número de cuenta") +
					String.format("%-11s", "Estado") +
					String.format("%-9s", "Tipo") + "Número");
			for(int i = 0; i < 113; i++)
				System.out.print("-");
			System.out.println();
			while(rs.next())
			{
				String nombre = rs.getString("nombres").length() <= 8 ?
						rs.getString("nombres") :
							rs.getString("nombres").substring(0, 7);
				String apellidos = rs.getString("apellidos").length() <= 14 ?
						rs.getString("apellidos") :
							rs.getString("apellidos").substring(0, 13);
				String direccion = rs.getString("direccion").length() <= 14 ?
						rs.getString("direccion") :
							rs.getString("direccion").substring(0, 13);
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				
				System.out.println(
						String.format("%-10s", nombre) +
						String.format("%-16s", apellidos) +
						String.format("%-16s", direccion) +
						String.format("%-11s", rs.getInt("telefono")) +
						String.format("%-12s", nacim.getDayOfMonth() + "/" +
						nacim.getMonthValue() +
						"/" + nacim.getYear()) +
						String.format("%-22s", rs.getString("nro_cuenta")) +
						String.format("%-11s", rs.getString("estado")) +
						String.format("%-9s", rs.getString("tipo_cliente")) +
						rs.getInt("numero"));
			}
		}
		else
		{
			System.out.println("No hay registros");
		}
	}
	
	private static void showFuncionarios(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		String sqlSelect = "select * from funcionarios";
		ResultSet rs = manejador.select(sqlSelect);
		
		if(rs.isBeforeFirst())
		{
			System.out.println(String.format("%-15s", "Nombre") +
					String.format("%-16s", "Apellidos") +
					String.format("%-16s", "Dirección") +
					String.format("%-11s", "Teléfono") +
					String.format("%-12s", "Nacimiento") +
					String.format("%-12s", "Cargo") +
					String.format("%-14s", "Departamento") +
					String.format("%-12s", "Ingreso") + "Número");
			for(int i = 0; i < 114; i++)
				System.out.print("-");
			System.out.println();
			while(rs.next())
			{
				String nombre = rs.getString("nombres").length() <= 13 ?
						rs.getString("nombres") :
							rs.getString("nombres").substring(0, 12);
				String apellidos = rs.getString("apellidos").length() <= 16 ?
						rs.getString("apellidos") :
							rs.getString("apellidos").substring(0, 15);
				String direccion = rs.getString("direccion").length() <= 16 ?
						rs.getString("direccion") :
							rs.getString("direccion").substring(0, 15);
				String departamento = rs.getString("departamento").length() <= 12 ?
						rs.getString("departamento") :
							rs.getString("departamento").substring(0, 10);
				LocalDate nacim = rs.getDate("fecha_nacim").toLocalDate();
				LocalDate ingreso = rs.getDate("fecha_ingreso").toLocalDate();
				System.out.println(
						String.format("%-15s", nombre) +
						String.format("%-16s", apellidos) +
						String.format("%-16s", direccion) +
						String.format("%-11s", rs.getInt("telefono")) +
						String.format("%-12s", nacim.getDayOfMonth() + "/" +
								nacim.getMonthValue() +
								"/" + nacim.getYear()) +
						String.format("%-12s", rs.getString("cargo")) +
						String.format("%-14s", departamento) +
						String.format("%-12s", ingreso.getDayOfMonth() + "/" +
								ingreso.getMonthValue() +
								"/" + ingreso.getYear()) +
						rs.getInt("numero"));
			}
		}
		else
		{
			System.out.println("No hay registros");
		}
	}
	
	private static List<String> getPersona()
	{
		List<String> persona = new ArrayList<String>();
		
		System.out.print("Nombre: ");
		persona.add(sc.nextLine());
		System.out.print("Apellidos: ");
		persona.add(sc.nextLine());
		System.out.print("Dirección: ");
		persona.add(sc.nextLine());
		System.out.print("Teléfono: ");
		int telefono = sc.nextInt();
		while(telefono < 100000000 || telefono > 999999999)
		{
			System.out.println("Teléfono incorrecto");
			System.out.print("Teléfono: ");
			telefono = sc.nextInt();
		}
		persona.add(String.valueOf(telefono));
		System.out.print("Día de nacimiento: ");
		int dia = sc.nextInt();
		while(dia < 1 || dia > 31)
		{
			System.out.println("Día incorrecto");
			System.out.print("Día de nacimiento: ");
			dia = sc.nextInt();
		}
		persona.add(String.valueOf(dia));
		System.out.print("Mes de nacimiento: ");
		int mes = sc.nextInt();
		while(mes < 1 || mes > 12)
		{
			System.out.println("Mes incorrecto");
			System.out.print("Mes de nacimiento: ");
			mes = sc.nextInt();
		}
		persona.add(String.valueOf(mes));
		System.out.print("Año de nacimiento: ");
		int anyo = sc.nextInt();
		while(anyo < 1900)
		{
			System.out.println("Año incorrecto");
			System.out.print("Año de nacimiento: ");
			anyo = sc.nextInt();
		}
		sc.nextLine();
		persona.add(String.valueOf(anyo));
		
		return persona;
	}
	
	private static List<String> getCliente()
	{
		List<String> cliente = getPersona();
		
		System.out.print("Número de cuenta: ");
		String numCuenta = sc.nextLine();
		
		while(numCuenta.length() != 20)
		{
			System.out.println("Número de cuenta incorrecto");
			System.out.print("Número de cuenta: ");
			numCuenta = sc.nextLine();
		}
		
		cliente.add(numCuenta);
		
		if(!checkIBAN(numCuenta))
		{
			System.out.println("El número de cuenta no ha sido verificado.");
			cliente.add("pendiente");
		}
		else
		{
			cliente.add("activo");
		}
		
		String opcion;
		
		do
		{
			System.out.println("Tipo de cliente:");
			System.out.println("1. Normal");
			System.out.println("2. Premium");
			System.out.print("Elige una opción: ");
			opcion = sc.nextLine();
			
			if(opcion.equals("1"))
			{
				cliente.add("normal");
			}
			else if(opcion.equals("2"))
			{
				cliente.add("premium");
			}
			else
			{
				System.out.println("Opción incorrecta");
			}
		} while(!opcion.equals("1") && !opcion.equals("2"));
		
		return cliente;
	}
	
	private static boolean checkIBAN(String numCuenta)
	{
		String cuenta = numCuenta.trim();
		
		cuenta = cuenta.substring(4) + cuenta.substring(0, 4);
		
		StringBuilder cuentaNumerica = new StringBuilder();
		for(int i = 0; i < cuenta.length(); i++)
		{
			cuentaNumerica.append(Character.getNumericValue(cuenta.charAt(i)));
		}
		
		BigInteger numIBAN = new BigInteger(cuentaNumerica.toString());
		return numIBAN.mod(new BigInteger("97")).intValue() == 1;
	}
	
	private static List<String> getFuncionario()
	{
		List<String> funcionario = getPersona();
		
		String opcion;
		
		do
		{
			System.out.println("Grupo:");
			System.out.println("1. A1");
			System.out.println("2. A2");
			System.out.println("3. C1");
			System.out.println("4. C2");
			System.out.println("5. AP");
			System.out.print("Selecciona el grupo: ");
			opcion = sc.nextLine();
			
			switch(opcion)
			{
				case "1": funcionario.add("A1"); break;
				case "2": funcionario.add("A2"); break;
				case "3": funcionario.add("C1"); break;
				case "4": funcionario.add("C2"); break;
				case "5": funcionario.add("AP"); break;
				default: System.out.println("Grupo incorrecto"); break;
			}
		} while(!opcion.equals("1") && !opcion.equals("2") &&
				!opcion.equals("3") && !opcion.equals("4") &&
				!opcion.equals("5"));
		
		System.out.print("Codigo del cuerpo (5 caracteres): ");
		String codCuerpo = sc.nextLine();
		while(codCuerpo.length() != 5)
		{
			System.out.println("Codigo incorrecto");
			System.out.print("Codigo del cuerpo (5 caracteres): ");
			codCuerpo = sc.nextLine();
		}
		funcionario.add(codCuerpo);
		
		System.out.print("Departamento: ");
		funcionario.add(sc.nextLine());
		
		System.out.print("Día de ingreso: ");
		int dia = sc.nextInt();
		while(dia < 1 || dia > 31)
		{
			System.out.println("Día incorrecto");
			System.out.print("Día de ingreso: ");
			dia = sc.nextInt();
		}
		funcionario.add(String.valueOf(dia));
		System.out.print("Mes de ingreso: ");
		int mes = sc.nextInt();
		while(mes < 1 || mes > 12)
		{
			System.out.println("Mes incorrecto");
			System.out.print("Mes de ingreso: ");
			mes = sc.nextInt();
		}
		funcionario.add(String.valueOf(mes));
		System.out.print("Año de ingreso: ");
		int anyo = sc.nextInt();
		while(anyo < 1900)
		{
			System.out.println("Año incorrecto");
			System.out.print("Año de ingreso: ");
			anyo = sc.nextInt();
		}
		sc.nextLine();
		funcionario.add(String.valueOf(anyo));
		
		return funcionario;
	}
	
	private static String modifyPersonaAux()
	{
		String sqlModify = "";
		String aux = "";
		boolean isChanged = false;
		
		System.out.print("Nombre: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			isChanged = true;
			sqlModify += "nombres = '" + aux + "'";
			aux = "";
		}
		System.out.print("Apellidos: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "apellidos = '" + aux + "'";
			aux = "";
		}
		System.out.print("Dirección: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "direccion = '" + aux + "'";
			aux = "";
		}
		System.out.print("Teléfono: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			int phone = Integer.parseInt(aux);
			while(phone < 100000000)
			{
				System.out.println("Teléfono incorrecto");
				System.out.print("Teléfono: ");
				phone = sc.nextInt();
			}

			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "telefono = " + aux;
			aux = "";
		}
		System.out.print("Día de nacimiento: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			int dia = Integer.parseInt(aux);
			while(dia < 1 || dia > 31)
			{
				System.out.println("Día incorrecto");
				System.out.print("Día de nacimiento: ");
				dia = sc.nextInt();
			}
			System.out.print("Mes de nacimiento: ");
			int mes = sc.nextInt();
			while(mes < 1 || dia > 12)
			{
				System.out.println("Mes incorrecto");
				System.out.print("Mes de nacimiento: ");
				mes = sc.nextInt();
			}
			System.out.print("Año de nacimiento: ");
			int anyo = sc.nextInt();
			while(anyo < 1900)
			{
				System.out.println("Año incorrecto");
				System.out.print("Año de nacimiento: ");
				anyo = sc.nextInt();
			}
			sc.nextLine();
			
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "fecha_nacim = date('" + anyo + "-" + mes +
					"-" + dia + "')";
			aux = "";
		}
		
		return sqlModify;
	}
	
	private static String modifyClienteAux()
	{
		String sqlModify = "";
		String aux = "";
		boolean isChanged = false;
		
		System.out.print("Número de cuenta: ");
		aux = sc.nextLine();
		
		if(!aux.equals(""))
		{
			while(aux.length() != 20)
			{
				System.out.println("Número de cuenta incorrecto");
				System.out.print("Número de cuenta: ");
				aux = sc.nextLine();
			}
			
			isChanged = true;
			sqlModify += "nro_cuenta = '" + aux + "'";
			
			if(!checkIBAN(aux))
			{
				System.out.println("El número de cuenta no ha sido verificado.");
				sqlModify += ", estado = 'pendiente'";
			}
			else
			{
				sqlModify += ", estado = 'activo'";
			}
			aux = "";
		}
		
		String opcion;
		
		do
		{
			System.out.println("Tipo de cliente:");
			System.out.println("1. Normal");
			System.out.println("2. Premium");
			System.out.print("Elige una opción: ");
			opcion = sc.nextLine();
			
			if(opcion.equals("1"))
			{
				aux = "normal";
			}
			else if(opcion.equals("2"))
			{
				aux = "premium";
			}
			else if(opcion.equals(""))
			{
				aux = "";
			}
			else
			{
				System.out.println("Opción incorrecta");
			}
		} while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals(""));
		
		if(!aux.equals(""))
		{
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "tipo_cliente = '" + aux + "'";
		}
		
		return sqlModify;
	}
	
	private static String modifyFuncionarioAux()
	{
		String sqlModify = "";
		String aux = "";
		boolean isChanged = false;
		
		String opcion;
		
		do
		{
			System.out.println("Grupo:");
			System.out.println("1. A1");
			System.out.println("2. A2");
			System.out.println("3. C1");
			System.out.println("4. C2");
			System.out.println("5. AP");
			System.out.print("Selecciona el grupo: ");
			opcion = sc.nextLine();
			
			switch(opcion)
			{
				case "1": aux = "A1"; break;
				case "2": aux = "A2"; break;
				case "3": aux = "C1"; break;
				case "4": aux = "C2"; break;
				case "5": aux = "AP"; break;
				case "": aux = ""; break;
				default: System.out.println("Grupo incorrecto"); break;
			}
		} while(!opcion.equals("1") && !opcion.equals("2") &&
				!opcion.equals("3") && !opcion.equals("4") &&
				!opcion.equals("5") && !opcion.equals(""));
		
		if(!aux.equals(""))
		{
			isChanged = true;
			sqlModify += "cargo.grupo = '" + aux + "'";
		}
		
		System.out.print("Codigo del cuerpo (5 caracteres): ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			while(aux.length() != 5)
			{
				System.out.println("Codigo incorrecto");
				System.out.print("Codigo del cuerpo (5 caracteres): ");
				aux = sc.nextLine();
			}
			
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "cargo.codigo_cuerpo = '" + aux + "'";
			aux = "";
		}
		
		System.out.print("Departamento: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "departamento = '" + aux + "'";
			aux = "";
		}
		
		System.out.print("Día de ingreso: ");
		aux = sc.nextLine();
		if(!aux.equals(""))
		{
			int dia = Integer.parseInt(aux);
			while(dia < 1 || dia > 31)
			{
				System.out.println("Día incorrecto");
				System.out.print("Día de ingreso: ");
				dia = sc.nextInt();
			}
			System.out.print("Mes de ingreso: ");
			int mes = sc.nextInt();
			while(mes < 1 || dia > 12)
			{
				System.out.println("Mes incorrecto");
				System.out.print("Mes de ingreso: ");
				mes = sc.nextInt();
			}
			System.out.print("Año de ingreso: ");
			int anyo = sc.nextInt();
			while(anyo < 1900)
			{
				System.out.println("Año incorrecto");
				System.out.print("Año de ingreso: ");
				anyo = sc.nextInt();
			}
			sc.nextLine();
			
			if(isChanged)
			{
				sqlModify += ", ";
			}
			
			isChanged = true;
			sqlModify += "fecha_ingreso = date('" + anyo + "-" + mes +
					"-" + dia + "')";
			aux = "";
		}
		
		return sqlModify;
	}
}