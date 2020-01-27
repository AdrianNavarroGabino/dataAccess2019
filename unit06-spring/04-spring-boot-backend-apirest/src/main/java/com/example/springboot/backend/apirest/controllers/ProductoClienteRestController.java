package com.example.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;
import com.example.springboot.backend.apirest.models.services.IProductoClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoClienteRestController {
	@Autowired
	private IProductoClienteService productoClienteService;
	
	@GetMapping("/productos_clientes")
	public List<ProductoCliente> index()
	{
		return productoClienteService.findAll();
	}
	
	@GetMapping("/productos_clientes/{id}")
	public ResponseEntity<?> show(@PathVariable ProductoClienteId id)
	{
		ProductoCliente productoCliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			productoCliente = productoClienteService.findById(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realidar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productoCliente == null)
		{
			response.put("mensaje", "El producto_cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductoCliente>(productoCliente, HttpStatus.OK);
	}
	
	@PostMapping("/productos_clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ProductoCliente productoCliente, BindingResult result)
	{
		ProductoCliente productoClienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			productoClienteNew = productoClienteService.save(productoCliente);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El productos_clientes ha sido creado con éxito!");
		response.put("productoCliente", productoClienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos_clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody ProductoCliente productoCliente, BindingResult result, @PathVariable ProductoClienteId id)
	{
		ProductoCliente productoClienteActual = this.productoClienteService.findById(id);
		ProductoCliente productoClienteActualizado = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(productoClienteActual == null)
		{
			response.put("mensaje", "Error: no se pudo editar el producto_cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try
		{
			productoClienteActual.setCliente(productoCliente.getCliente());
			productoClienteActual.setProducto(productoCliente.getProducto());
			
			productoClienteActualizado = productoClienteService.save(productoClienteActual);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto_cliente ha sido actualizado con éxito");
		response.put("productoCliente", productoClienteActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/productos_clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable ProductoClienteId id)
	{
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			productoClienteService.delete(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al eliminar el producto_cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto_cliente ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
