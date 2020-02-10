package com.example.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;

public interface IProductoClienteDAO extends JpaRepository<ProductoCliente, ProductoClienteId> {

}
