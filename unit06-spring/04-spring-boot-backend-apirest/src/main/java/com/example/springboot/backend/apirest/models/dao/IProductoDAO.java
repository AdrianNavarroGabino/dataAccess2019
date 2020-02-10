package com.example.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long> {

}
