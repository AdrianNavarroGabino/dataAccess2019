package com.example.springboot.backend.apirest.models.dao;

import com.example.springboot.backend.apirest.models.entity.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {
	@Query(value="SELECT * FROM clientes " + 
	          "EXCEPT " + 
	          "SELECT cl.* FROM clientes cl, usuarios usu WHERE cl.id = usu.id_cliente;",
	          nativeQuery=true)
	List<Cliente> findClientesSinUsuario();
	
	@Query(value= "SELECT cl.* FROM clientes cl WHERE UPPER(cl.nombre) LIKE '%'|| :buscar ||'%';",
	          nativeQuery=true)
	  List<Cliente> filtrar(@Param("buscar") String buscar);
}
