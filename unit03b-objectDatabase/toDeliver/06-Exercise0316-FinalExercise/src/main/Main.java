// Adrián Navarro Gabino

package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

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
					anyadirTarea(db);
					System.out.println();
					break;
				case 2:
					buscarPorCategoria(db);
					System.out.println();
					break;
				case 3:
					tareasDeHoy(db);
					System.out.println();
					break;
				case 4:
					modificarTarea(db);
					System.out.println();
					break;
				case 5:
					buscarTarea(db);
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
		System.out.print("Option: ");
		int opcion = sc.nextInt();
		sc.nextLine();
		System.out.println();
		return opcion;
	}
	
	private static void ShowMenu()
	{
		System.out.println("1.-Añadir tarea");
		System.out.println("2.-Buscar por categoría");
		System.out.println("3.-Mostrar tareas de hoy");
		System.out.println("4.-Modificar tarea");
		System.out.println("5.-Buscar tarea");
		System.out.println("0.-Salir");
	}
	
	private static void anyadirTarea(ObjectContainer db)
	{
		System.out.print("Fecha (dd/MM/yyyy): ");
		String[] fecha = sc.nextLine().split("/");
		while(fecha.length != 3)
		{
			System.out.println("Fecha incorrecta");
			System.out.print("Fecha (dd/MM/yyyy): ");
			fecha = sc.nextLine().split("/");
		}
		
		System.out.print("Descripción: ");
		String descripcion = sc.nextLine();
		
		System.out.print("Categoría: ");
		String categoria = sc.nextLine();
		
		System.out.print("Prioridad: ");
		String prioridad = sc.nextLine();
		
		Tarea tarea = new Tarea(LocalDate.of(Integer.parseInt(fecha[2]),
				Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0])),
				descripcion, null, null);
		
		if(!db.get(tarea).hasNext())
		{
			db.set(tarea);
			db.commit();
			System.out.println("Tarea añadida");
		}
		else
		{
			System.out.println("La tarea ya existe");
		}
	}
	
	private static void buscarPorCategoria(ObjectContainer db)
	{
		System.out.print("Categoría: ");
		String buscar = sc.nextLine();
		
		ObjectSet resultado = db.get(new Tarea(null, null, buscar, null));
		
		if(resultado.hasNext())
		{
			while (resultado.hasNext())
			{
				System.out.println(resultado.next());
			}
		}
		else
		{
			System.out.println("No hay resultados");
		}
	}
	
	private static void tareasDeHoy(ObjectContainer db)
	{
		ObjectSet resultado = db.get(new Tarea(LocalDate.now(), null, null, null));
		
		if(resultado.hasNext())
		{
			while (resultado.hasNext())
			{
				System.out.println(resultado.next());
			}
		}
		else
		{
			System.out.println("No hay resultados");
		}
	}
	
	private static void modificarTarea(ObjectContainer db)
	{
		Tarea tarea = null;
		
		System.out.print("Fecha (dd/MM/yyyy): ");
		String[] fechaBuscar = sc.nextLine().split("/");
		while(fechaBuscar.length != 3)
		{
			System.out.println("Fecha incorrecta");
			System.out.print("Fecha (dd/MM/yyyy): ");
			fechaBuscar = sc.nextLine().split("/");
		}
		
		System.out.print("Descripción: ");
		String descripcionBuscar = sc.nextLine();
		
		ObjectSet resultado = db.get(new Tarea(
				LocalDate.of(Integer.parseInt(fechaBuscar[2]),
				Integer.parseInt(fechaBuscar[1]), Integer.parseInt(fechaBuscar[0])),
				descripcionBuscar, null, null));
		while (resultado.hasNext())
		{
			tarea = (Tarea)resultado.next();
		}
		
		if(tarea != null)
		{
			System.out.println();
			System.out.println(tarea);
			System.out.print("Fecha (dd/MM/yyyy): ");
			String[] fecha = sc.nextLine().split("/");
			if(fecha.length != 1 || !fecha[0].isEmpty())
			{
				while(fecha.length != 3)
				{
					System.out.println("Fecha incorrecta");
					System.out.print("Fecha (dd/MM/yyyy): ");
					fecha = sc.nextLine().split("/");
				}
				
				tarea.setFecha(LocalDate.of(Integer.parseInt(fecha[2]),
					Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0])));
			}
			
			System.out.print("Descripción: ");
			String descripcion = sc.nextLine();
			if(!descripcion.isEmpty())
			{
				tarea.setDescripcion(descripcion);
			}
			
			System.out.print("Categoría: ");
			String categoria = sc.nextLine();
			if(!categoria.isEmpty())
			{
				tarea.setCategoria(categoria);
			}
			
			System.out.print("Prioridad: ");
			String prioridad = sc.nextLine();
			if(!prioridad.isEmpty())
			{
				tarea.setPrioridad(prioridad);
			}
			
			db.set(tarea);
			db.commit();
		}
	}
	
	private static void buscarTarea(ObjectContainer db)
	{
		System.out.print("Buscar: ");
		String buscar = sc.nextLine();
		
		List<Tarea> resultado = db.query(new Predicate<Tarea>() {
			 public boolean match(Tarea candidato) {
				 return candidato.getCategoria().contains(buscar) ||
						 candidato.getDescripcion().contains(buscar) ||
						 candidato.getPrioridad().contains(buscar);
			 }
		} );
		
		for(Tarea tarea: resultado)
		{
			System.out.println(tarea);
		}
		
		if(resultado.size() == 0)
		{
			System.out.println("No hay resultados");
		}
	}
}
