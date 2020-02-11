package com.example.springboot.backend.apirest.models.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springboot.backend.apirest.models.services.ClienteServiceImpl;
import com.example.springboot.backend.apirest.models.services.ProductoServiceImpl;

@Entity
@Table(name = "productos_clientes")
public class ProductoCliente {
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "clienteId", column = @Column(name = "cliente_id", nullable = false)),
		@AttributeOverride(name = "productoId", column = @Column(name = "producto_id", nullable = false)),
		@AttributeOverride(name = "fecha", column = @Column(name = "fecha", nullable = false, length = 13))
	})
    private ProductoClienteId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false, insertable = false, updatable = false)
    private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false, insertable = false, updatable = false)
    private Producto producto;
	
	public ProductoCliente()
	{
		
	}
	
	public ProductoCliente(ProductoClienteId id)
	{
		this.id = id;
		cliente = (new ClienteServiceImpl()).findById(id.getClienteId());
		producto = (new ProductoServiceImpl()).findById(id.getProductoId());
	}

	public ProductoClienteId getId() {
		return id;
	}

	public void setId(ProductoClienteId id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Date getFecha()
	{
		return id.getFecha();
	}

	@Override
	public String toString() {
		return "ProductoCliente [id=" + id + ", cliente=" + cliente + ", producto=" + producto + "]";
	}
}
