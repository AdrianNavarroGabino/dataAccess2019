package com.example.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long> {

}
