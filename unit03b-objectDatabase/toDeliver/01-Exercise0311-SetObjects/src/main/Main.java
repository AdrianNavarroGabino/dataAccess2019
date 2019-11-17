// Adrián Navarro Gabino

package main;

import java.time.LocalDate;
import java.util.Scanner;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import classes.Tarea;

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean exit = false;
		
		ObjectContainer db = null;
		try {
			db = Db4o.openFile("tareas.dat");
			do
			{
				ShowMenu();
				switch (getOpcion()) {
				case 1:
					addTarea(db);
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
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	private static int getOpcion() {
		System.out.println("Option:");
		int opcion = sc.nextInt();
		sc.nextLine();
		return opcion;
	}
	
	private static void ShowMenu()
	{
		System.out.println("1.-Añadir tarea");
		System.out.println("0.-Salir");
	}
	
	private static void addTarea(ObjectContainer db)
	{
		int dia;
		do
		{
			System.out.print("Dia: ");
			String diaAux = sc.nextLine();
			try
			{
				dia = Integer.parseInt(diaAux);
			}
			catch(Exception e)
			{
				dia = -1;
			}
			if(dia < 1 || dia > 31)
				System.out.println("Día incorrecto");
		} while(dia < 1 || dia > 31);
		
		int mes;
		do
		{
			System.out.print("Mes: ");
			String mesAux = sc.nextLine();
			try
			{
				mes = Integer.parseInt(mesAux);
			}
			catch(Exception e)
			{
				mes = -1;
			}
			if(mes < 1 || mes > 12)
				System.out.println("Mes incorrecto");
		} while(mes < 1 || mes > 12);
		
		int anyo;
		do
		{
			System.out.print("Año: ");
			String anyoAux = sc.nextLine();
			try
			{
				anyo = Integer.parseInt(anyoAux);
			}
			catch(Exception e)
			{
				anyo = -1;
			}
			if(anyo < 1900)
				System.out.println("Año incorrecto");
		} while(anyo < 1900);
		
		System.out.println("Descripción: ");
		String descripcion = sc.nextLine();
		
		System.out.println("Categoría: ");
		String categoria = sc.nextLine();
		
		System.out.println("Prioridad: ");
		String prioridad = sc.nextLine();
		
		db.set(new Tarea(LocalDate.of(anyo, mes, dia), descripcion,
				categoria, prioridad));
                
        db.commit();
		
		System.out.println("Tarea añadida");
	}
}
