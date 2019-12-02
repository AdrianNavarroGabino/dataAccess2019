// Adrián Navarro Gabino

import java.util.List;
import java.util.Scanner;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

class Libro {
	String titulo;
	String autor;
	int paginas;
	
	public Libro(String titulo, String autor, int paginas) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + "]";
	}
}

public class Exercise3246 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		boolean exit = false;
		
		ObjectContainer db = null;
		try {
			db = Db4o.openFile("libros.dat");
			do
			{
				ShowMenu();
				switch (getOpcion()) {
				case 1:
					anyadirLibro(db);
					System.out.println();
					break;
				case 2:
					verLibros(db);
					System.out.println();
					break;
				case 3:
					buscarPorTitulo(db);
					System.out.println();
					break;
				case 4:
					buscarPorAutor(db);
					System.out.println();
					break;
				case 5:
					buscarPorPaginas(db);
					System.out.println();
					break;
				case 6:
					busquedaParcialPorTitulo(db);
					System.out.println();
					break;
				case 7:
					busquedaParcialPorAutor(db);
					System.out.println();
					break;
				case 0:
					System.out.println("Hasta otra!");
					exit = true;
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} while(!exit);
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
		System.out.print("Opción: ");
		int opcion = sc.nextInt();
		sc.nextLine();
		System.out.println();
		return opcion;
	}
	
	private static void ShowMenu()
	{
		System.out.println("1.-Añadir Libro");
		System.out.println("2.-Ver Libros");
		System.out.println("3.-Buscar Libros por Título");
		System.out.println("4.-Buscar Libros por Autor");
		System.out.println("5.-Buscar Libros por Páginas");
		System.out.println("6.-Búsqueda Parcial por Título");
		System.out.println("7.-Búsqueda Parcial por Autor");
		System.out.println("0.-Salir");
	}
	
	private static void anyadirLibro(ObjectContainer db)
	{
		System.out.print("Título: ");
		String titulo = sc.nextLine();
		System.out.print("Autor: ");
		String autor = sc.nextLine();
		System.out.print("Páginas: ");
		int paginas = sc.nextInt();
		sc.nextLine();
		
		Libro libro = new Libro(titulo, autor, paginas);
		
		if(!db.get(libro).hasNext())
		{
			db.set(libro);
			db.commit();
			System.out.println("Libro añadido");
		}
		else
		{
			System.out.println("El libro ya existe");
		}
	}
	
	private static void verLibros(ObjectContainer db)
	{
		ObjectSet resultado = db.get(new Libro(null, null, 0));
		
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
	
	private static void buscarPorTitulo(ObjectContainer db)
	{
		System.out.print("Título: ");
		String titulo = sc.nextLine();
		
		ObjectSet resultado = db.get(new Libro(titulo, null, 0));
		
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
	
	private static void buscarPorAutor(ObjectContainer db)
	{
		System.out.print("Autor: ");
		String autor = sc.nextLine();
		
		ObjectSet resultado = db.get(new Libro(null, autor, 0));
		
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
	
	private static void buscarPorPaginas(ObjectContainer db)
	{
		System.out.print("Número de páginas: ");
		int paginas = sc.nextInt();
		sc.nextLine();
		
		List<Libro> resultado = db.query(new Predicate<Libro>() {
			 public boolean match(Libro candidato) {
				 return candidato.getPaginas() < paginas;
			 }
		} );
		
		for(Libro libro: resultado)
		{
			System.out.println(libro);
		}
		
		if(resultado.size() == 0)
		{
			System.out.println("No hay resultados");
		}
	}
	
	private static void busquedaParcialPorTitulo(ObjectContainer db)
	{
		System.out.print("Título: ");
		String titulo = sc.nextLine().toLowerCase();
		
		List<Libro> resultado = db.query(new Predicate<Libro>() {
			 public boolean match(Libro candidato) {
				 return candidato.getTitulo().toLowerCase().contains(titulo);
			 }
		} );
		
		for(Libro libro: resultado)
		{
			System.out.println(libro);
		}
		
		if(resultado.size() == 0)
		{
			System.out.println("No hay resultados");
		}
	}
	
	private static void busquedaParcialPorAutor(ObjectContainer db)
	{
		System.out.print("Autor: ");
		String autor = sc.nextLine().toLowerCase();
		
		List<Libro> resultado = db.query(new Predicate<Libro>() {
			 public boolean match(Libro candidato) {
				 return candidato.getAutor().toLowerCase().contains(autor);
			 }
		} );
		
		for(Libro libro: resultado)
		{
			System.out.println(libro);
		}
		
		if(resultado.size() == 0)
		{
			System.out.println("No hay resultados");
		}
	}
}