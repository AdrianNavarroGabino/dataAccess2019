package com.example.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class ProductoClienteId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cliente_id", nullable = false)
	private Long clienteId;
	
	@Column(name = "producto_id", nullable = false)
	private Long productoId;
	
	@Column(name = "fecha", nullable = false, length = 13)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	public ProductoClienteId()
	{
		
	}
	
	public ProductoClienteId(Long clienteId, Long productoId, Date fecha)
	{
		this.clienteId = clienteId;
		this.productoId = productoId;
		this.fecha = fecha;
	}
	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ProductoClienteId [clienteId=" + clienteId + ", productoId=" + productoId + ", fecha=" + fecha + "]";
	}
	
	
}
