// Adrián Navarro Gabino

package com.adriannavarrogabino.alquileres;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;

public class App {
	private static Scanner sc;

	private static void mostrarMenu() {
		System.out.println("1. Añadir cliente");
		System.out.println("2. Añadir vehículo");
		System.out.println("3. Alquilar");
		System.out.println("4. Devolver vehículo");
		System.out.println("5. Ver historial de un vehículo");
		System.out.println("6. Buscar cliente");
		System.out.println("0.-Salir");
	}

	private static MongoClient conexion() throws UnknownHostException {
		System.out.println("Conectando a MongoDB.");
		MongoClient mongo = null;

		// Conexión con seguridad.
		MongoCredential credenciales = MongoCredential.createCredential("admin", "admin", "admin".toCharArray());
		mongo = new MongoClient(new ServerAddress("localhost"), Arrays.asList(credenciales));
		return mongo;
	}

	public int proximoId(MongoCollection<Document> collection) {
		Document ultimaTarea = collection.find().sort(Sorts.orderBy(Sorts.descending("codigo"))).first();

		if (ultimaTarea != null) {
			return ultimaTarea.getInteger("codigo") + 1;
		} else {
			return 1;
		}
	}

	public static void anyadirCliente() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> coleccion = db.getCollection("clientes");

			Document doc = new Document();

			System.out.print("DNI: ");
			String dni = sc.nextLine();
			doc.append("dni", dni);
			System.out.print("Nombre: ");
			String nombre = sc.nextLine();
			doc.append("nombre", nombre);
			System.out.print("Apellidos: ");
			String apellidos = sc.nextLine();
			doc.append("apellidos", apellidos);

			boolean finDireccion = false;
			List<String> direccionLista = new ArrayList<>();

			System.out.println("Dirección (enter para acabar): ");
			do {
				String direccion = sc.nextLine();

				if (direccion.equals("")) {
					finDireccion = true;
				} else {
					direccionLista.add(direccion);
				}
			} while (!finDireccion);

			if (direccionLista.size() > 0) {
				doc.append("direccion", direccionLista);
			}

			coleccion.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}

	}

	public static void anyadirClientePrefijado(String dni, String nombre, String apellidos,
			List<String> direccionLista) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> coleccion = db.getCollection("clientes");

			Document doc = new Document();
			doc.append("dni", dni);
			doc.append("nombre", nombre);
			doc.append("apellidos", apellidos);

			if (direccionLista != null && direccionLista.size() > 0) {
				doc.append("direccion", direccionLista);
			}

			coleccion.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}

	}

	public static void anyadirVehiculo() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> collection = db.getCollection("vehiculos");

			Document doc = new Document();

			System.out.print("Matrícula: ");
			String matricula = sc.nextLine();
			doc.append("matricula", matricula);
			System.out.print("Marca: ");
			String marca = sc.nextLine();
			doc.append("marca", marca);
			System.out.print("Modelo: ");
			String modelo = sc.nextLine();
			doc.append("modelo", modelo);
			System.out.print("Grupo: ");
			String grupo = sc.nextLine();
			doc.append("grupo", grupo);
			System.out.print("Precio por día: ");
			double precio = sc.nextDouble();
			sc.nextLine();
			doc.append("precio", precio);

			boolean finExtras = false;
			List<String> extrasLista = new ArrayList<>();

			System.out.println("Extras (enter para acabar): ");

			do {
				String extra = sc.nextLine();

				if (extra.equals("")) {
					finExtras = true;
				} else {
					extrasLista.add(extra);
				}
			} while (!finExtras);

			if (extrasLista.size() > 0) {
				doc.append("extras", extrasLista);
			}

			collection.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}
	}

	public static void anyadirVehiculoPrefijado(String matricula, String marca, String modelo, String grupo,
			double precio, List<String> extrasLista) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> collection = db.getCollection("vehiculos");

			Document doc = new Document();

			doc.append("matricula", matricula);
			doc.append("marca", marca);
			doc.append("modelo", modelo);
			doc.append("grupo", grupo);
			doc.append("precio", precio);

			if (extrasLista != null && extrasLista.size() > 0) {
				doc.append("extras", extrasLista);
			}

			collection.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}
	}

	public static void alquilar() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		List<String> lista = new ArrayList<>();
		List<Document> listaDoc = new ArrayList<>();
		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> collection = db.getCollection("alquiler");

			Document doc = new Document();

			System.out.println("DNI cliente: ");
			String dniCliente = sc.nextLine();
			doc.append("cliente", dniCliente);
			System.out.println("Matricula vehiculo: ");
			String matriculaVehiculo = sc.nextLine();
			doc.append("vehiculo", matriculaVehiculo);

			System.out.print("Fecha salida: ");

			String fechaSalida = sc.nextLine();
			Date fecha = new Date();
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSalida);
			doc.append("fechasalida", fecha);

			System.out.print("Km iniciales: ");
			Double km = sc.nextDouble();
			doc.append("kminiciales", km);

			boolean hayIncidencias = false;
			List<String> incidencias = new ArrayList<>();

			System.out.println("Incidencias (enter para acabar): ");

			do {
				String incidencia = sc.nextLine();

				if (incidencia.equals("")) {
					hayIncidencias = true;
				} else {
					incidencias.add(incidencia);
				}
			} while (!hayIncidencias);

			if (incidencias.size() > 0) {
				doc.append("incidencias", incidencias);
			}

			collection.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}

	}

	public static void alquilarPrefijado(String dniCliente, String matriculaVehiculo, Date fecha, double km,
			List<String> incidencias) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		List<String> lista = new ArrayList<>();
		List<Document> listaDoc = new ArrayList<>();
		MongoClient mongoClient = null;
		try {
			mongoClient = conexion();
			MongoDatabase db = mongoClient.getDatabase("alquileres");
			MongoCollection<Document> collection = db.getCollection("alquiler");

			Document doc = new Document();

			doc.append("cliente", dniCliente);
			doc.append("vehiculo", matriculaVehiculo);
			doc.append("fechasalida", fecha);
			doc.append("kminiciales", km);

			if (incidencias != null && incidencias.size() > 0) {
				doc.append("incidencias", incidencias);
			}

			collection.insertOne(doc);

		} catch (Exception e) {

		} finally {
			mongoClient.close();
		}

	}

	public static void buscarVehiculosFiltroPrecio(double precio) throws UnknownHostException {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {

			mongoClient = conexion();

			// Obtener la base de datos con la que trabajar.
			MongoDatabase database = mongoClient.getDatabase("alquileres");

			// Recogemos la colección 'comunidades' en una colección de documentos de
			// MongoDB.
			MongoCollection<Document> collection = database.getCollection("vehiculos");

			// Mostramos los datos.
			System.out.println("\nVehículos que valen menos de " + precio + "€/dia.");
			for (Document cur : collection.find(Filters.lt("precio", precio))) {
				System.out.println(cur.toJson());
			}

		} catch (MongoCommandException e) {
			System.err.println(e.getErrorMessage());
		}

		System.out.println("\nDesconectando de MongoDB.");

		// Desconexión de la base de datos.
		mongoClient.close();
	}

	public static void buscarClienteSinDireccion() throws UnknownHostException {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = null;
		try {

			mongoClient = conexion();

			// Obtener la base de datos con la que trabajar.
			MongoDatabase database = mongoClient.getDatabase("alquileres");

			// Recogemos la colección 'comunidades' en una colección de documentos de
			// MongoDB.
			MongoCollection<Document> collection = database.getCollection("clientes");

			// Mostramos los datos.
			System.out.println("\nMostrar los clientes sin dirección.");
			for (Document cur : collection.find(Filters.exists("direccion", false))) {
				System.out.println(cur.toJson());
			}

		} catch (MongoCommandException e) {
			System.err.println(e.getErrorMessage());
		}

		System.out.println("\nDesconectando de MongoDB.");

		// Desconexión de la base de datos.
		mongoClient.close();
	}

	public static void borrarUsuarioSinDireccion() {
		// Desactivar logs de MongoDB.
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		try (MongoClient mongoClient = conexion()) {
			try {
				// Seleccionamos la BD, si no existe la crea.
				MongoDatabase db = mongoClient.getDatabase("alquileres");

				// Seleccionamos la colección, si no existe la crea.
				MongoCollection<Document> coleccion = db.getCollection("clientes");

				// Eliminando un documento.
				coleccion.deleteOne(Filters.exists("direccion", false));

				System.out.println("Cliente borrado");

			} catch (MongoCommandException e) {
				System.err.println(e.getErrorMessage());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\nDesconectando de MongoDB.");
			// Desconexión de la base de datos, es una operación "auto-cerrable".
		}
	}

	public static void buscarCliente(String dni) {

		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("taller");
			MongoCollection<Document> collection = database.getCollection("clientes");

			BasicDBObject filtro = (BasicDBObject) JSON.parse("{clientes.dni:'" + dni + "'}");
			for (Document curso : collection.find(filtro)) {

				System.out.println("DNI: " + curso.getString("dni"));
				System.out.println("Nombre: " + curso.getString("nombre"));
				System.out.println("Apellidos: " + curso.getString("apellidos"));
				List<Document> dir = (List<Document>) curso.get("direccion");
				System.out.println("Direccion: ");
				for (Document contenido : dir) {
					System.out.println("\t" + contenido.getString("nombre"));
				}

				System.out.println();
			}
		} catch (MongoCommandException mce) {
			System.out.println("Error al leer datos");
		}
	}

	public static void devolverVehiculo() throws ParseException {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		try (MongoClient mongoClient = conexion()) {
			try {
				// Seleccionamos la BD, si no existe la crea.
				MongoDatabase db = mongoClient.getDatabase("alquileres");

				// Seleccionamos la colección, si no existe la crea.
				MongoCollection<Document> coleccion = db.getCollection("alquiler");

				System.out.println("DNI cliente: ");
				String dniCliente = sc.nextLine();
				System.out.println("Matricula vehiculo: ");
				String matriculaVehiculo = sc.nextLine();

				System.out.print("Fecha salida: ");
				String fechaSalida = sc.nextLine();
				Date fecha = new Date();
				fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSalida);

				System.out.print("Fecha devolución: ");
				String fechaDevolucion = sc.nextLine();
				Date fecha2 = new Date();
				fecha2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaDevolucion);

				// Actualizamos un sólo documento.
				coleccion
						.updateOne(
								Filters.and(Filters.eq("cliente", dniCliente),
										Filters.eq("vehiculo", matriculaVehiculo),
										Filters.eq("fechaSalida", fecha)),
								Updates.set("fechaDevolucion", fecha2));

			} catch (MongoCommandException e) {
				System.err.println(e.getErrorMessage());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\nDesconectando de MongoDB.");
			// Desconexión de la base de datos, es una operación "auto-cerrable".
		}
	}

	public static void verHistorialVehiculo() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		try (MongoClient mongoClient = conexion()) {
			try {
				MongoDatabase db = mongoClient.getDatabase("alquileres");

				MongoCollection<Document> coleccion = db.getCollection("vehiculos");

				System.out.println("Matricula vehiculo: ");
				String matriculaVehiculo = sc.nextLine();

				for(Document cur: coleccion.find(Filters.eq("matricula", matriculaVehiculo)))
				{
					System.out.println(cur.toJson());
				}

			} catch (MongoCommandException e) {
				System.err.println(e.getErrorMessage());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\nDesconectando de MongoDB.");
			// Desconexión de la base de datos, es una operación "auto-cerrable".
		}
	}
	
	public static void buscarClientePorApellidos()
	{
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		try (MongoClient mongoClient = conexion()){
			try{
				MongoDatabase db = mongoClient.getDatabase("alquileres");
				
				// Obtenemos la colección.
				MongoCollection<Document> collection = db.getCollection("clientes");
				
				System.out.print("Apellidos: ");
				String apellidos = sc.nextLine();

				BasicDBObject filtros = (BasicDBObject) JSON.parse("{comunidad:{$regex: '" + apellidos + "'}}" );
				// Recorremos el resultado obtenido.
				for (Document cur : collection.find(filtros)) {
					System.out.println(cur.toJson());
				}
					
			}catch(MongoCommandException e){
				System.err.println(e.getErrorMessage());
			}
		}catch (UnknownHostException e){
			e.printStackTrace();
		}finally{
			System.out.println("\nDesconectando de MongoDB.");
			// Desconexión de la base de datos, es una operación "auto-cerrable".
		}		
	}

	public static void main(String[] args) throws ParseException, UnknownHostException {
		
		sc = new Scanner(System.in);

		anyadirVehiculoPrefijado("1111BBB", "Fiat", "500", "B", 21.58, null);
		anyadirVehiculoPrefijado("1112BBB", "Renault", "Captur", "B", 24.18,
				new ArrayList<>(Arrays.asList("Aire acondicionado", "Elevalunas eléctrico")));
		anyadirVehiculoPrefijado("1113BBB", "Fiat", "Ducato", "C", 40.12,
				new ArrayList<>(Arrays.asList("7500 litros de volumen de carga")));
		buscarVehiculosFiltroPrecio(40);

		anyadirClientePrefijado("11111111Q", "Antonio", "Mora",
				new ArrayList<>(Arrays.asList("Lillo Juan, 128", "03690 San Vicente del Raspeig")));
		anyadirClientePrefijado("11111112R", "Pablo", "Bernal",
				new ArrayList<>(Arrays.asList("Calle", "Codigo postal", "Ciudad")));
		anyadirClientePrefijado("11111113P", "Romario", "Cerezo", new ArrayList<String>());
		buscarClienteSinDireccion();
		borrarUsuarioSinDireccion();

		boolean salir = false;

		do {
			mostrarMenu();
			System.out.print("Elige una opción: ");
			String opcion = sc.nextLine();
			System.out.println();

			switch (opcion) {
			case "1":
				anyadirCliente();
				break;
			case "2":
				anyadirVehiculo();
				break;
			case "3":
				alquilar();
				break;
			case "4":
				devolverVehiculo();
				break;
			case "5":
				verHistorialVehiculo();
				break;
			case "6":
				buscarClientePorApellidos();
				break;
			case "0":
				salir = true;
				break;
			default:
				System.out.println("Opción incorrecta");
				break;
			}
			System.out.println();
		} while (!salir);
	}
}
