package com.example.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;

public interface IProductoClienteDAO extends CrudRepository<ProductoCliente, ProductoClienteId> {

}
