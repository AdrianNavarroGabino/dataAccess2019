package com.example.springboot.backend.apirest.models.services;

import java.util.List;

import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;

public interface IProductoClienteService {
	public List<ProductoCliente> findAll();
	
	public ProductoCliente save(ProductoCliente productoCliente);

	void delete(ProductoClienteId id);

	ProductoCliente findById(ProductoClienteId id);
}
