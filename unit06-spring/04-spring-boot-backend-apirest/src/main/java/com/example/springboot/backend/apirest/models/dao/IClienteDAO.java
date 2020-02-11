package com.example.springboot.backend.apirest.models.dao;

import com.example.springboot.backend.apirest.models.entity.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {
	@Query(value="SELECT * FROM clientes WHERE id NOT IN " + 
	          "(SELECT clientes.id FROM clientes, usuarios WHERE clientes.id = id_cliente);",
	          nativeQuery=true)
	List<Cliente> clientesSinUsuario();
	
	@Query(value= "SELECT * FROM clientes WHERE UPPER(nombre) LIKE '%'|| :buscar ||'%';",
	          nativeQuery=true)
	List<Cliente> filtrar(@Param("buscar") String buscar);
	
	@Query(value= "SELECT * FROM clientes WHERE clientes.id IN " +
			"(SELECT id_cliente from usuarios);",
	          nativeQuery=true)
	List<Cliente> clientesConUsuarios();
}
