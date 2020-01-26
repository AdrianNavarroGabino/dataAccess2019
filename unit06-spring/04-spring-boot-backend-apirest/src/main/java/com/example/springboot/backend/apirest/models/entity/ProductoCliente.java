package com.example.springboot.backend.apirest.models.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
}
