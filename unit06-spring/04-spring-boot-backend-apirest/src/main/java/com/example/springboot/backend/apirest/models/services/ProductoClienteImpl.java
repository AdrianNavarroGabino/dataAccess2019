package com.example.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.backend.apirest.models.dao.IProductoClienteDAO;
import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;

@Service
public class ProductoClienteImpl implements IProductoClienteService {
	
	@Autowired
	private IProductoClienteDAO productoClienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<ProductoCliente> findAll() {
		return (List<ProductoCliente>)productoClienteDao.findAll();
	}

	@Override
	@Transactional
	public ProductoCliente save(ProductoCliente productoCliente) {
		return productoClienteDao.save(productoCliente);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoCliente findById(ProductoClienteId id) {
		return productoClienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(ProductoClienteId id) {
		productoClienteDao.deleteById(id);
		
	}

}
