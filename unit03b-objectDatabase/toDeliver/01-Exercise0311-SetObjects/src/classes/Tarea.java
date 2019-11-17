// Adrián Navarro Gabino

package classes;

import java.time.LocalDate;

public class Tarea {
	private LocalDate fecha;
	private String descripcion;
	private String categoria;
	private String prioridad;
	
	public Tarea(LocalDate fecha, String descripcion, String categoria, String prioridad) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.prioridad = prioridad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public String toString() {
		return "Tarea [fecha=" + fecha + ", descripcion=" + descripcion + ", categoria=" + categoria + ", prioridad="
				+ prioridad + "]";
	}
	
	public boolean equals(Object otro) {
		 if (otro == this)
			 return true;
		 if (!(otro instanceof Tarea))
			 return false;
		 Tarea other = (Tarea)otro;
		 return (this.fecha.equals(other.fecha) &&
			 this.descripcion.equals(other.descripcion) &&
			 this.categoria == other.categoria &&
			 this.prioridad == other.prioridad);
	 }
}
