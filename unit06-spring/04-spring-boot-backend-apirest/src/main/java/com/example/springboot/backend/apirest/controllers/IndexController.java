package com.example.springboot.backend.apirest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.backend.apirest.models.entity.Cliente;
import com.example.springboot.backend.apirest.models.entity.Producto;
import com.example.springboot.backend.apirest.models.services.ClienteServiceImpl;
import com.example.springboot.backend.apirest.models.services.ProductoServiceImpl;

@Controller
public class IndexController {

	@Autowired
	ClienteServiceImpl clienteService = new ClienteServiceImpl();
	
	@Autowired
	ProductoServiceImpl productoService = new ProductoServiceImpl();
	
	@GetMapping({"/", "", "/index", "/home"})
	public String index(Model model)
	{
		model.addAttribute("titulo", "Springboot backend API Rest");
		return "index";
	}
	
	@GetMapping("/clientes")
	public String indexClientes(Model model)
	{
		model.addAttribute("titulo", "Clientes");
		return "clientes/index";
	}
	
	@GetMapping("/clientes/listar")
	public String listarClientes(Model model)
	{
		model.addAttribute("titulo", "Listar clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "clientes/listar";
	}
	
	@GetMapping("/clientes/anyadir")
	public String addCliente(Model model)
	{
		model.addAttribute("titulo", "Añadir cliente");
		model.addAttribute("nombre", "Nombre:");
        model.addAttribute("apellido", "Apellido:");
		model.addAttribute("email", "Correo:");
		model.addAttribute("createAt", "Fecha de creación:");
		model.addAttribute("cliente", new Cliente());
		return "clientes/anyadir";
	}
	
	@RequestMapping("/clientes/create")
    public ModelAndView createCliente(@Valid Cliente cliente, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        model.addObject("cliente", cliente);
        
        if(!result.hasErrors())
        {
        	for(Cliente c: clienteService.findAll())
        	{
        		if(c.getId() == cliente.getId())
        		{
        			exists = true;
        			break;
        		}
        	}
        	
        	model.setViewName("ready");
        	
        	if(!exists)
        	{
        		mod.addAttribute("resultado", "Cliente creado");
        		clienteService.save(cliente);
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El id ya existe");
        	}
        }
        else
        {
        	model.setViewName("clientes/anyadir");
        }
        mod.addAttribute("nombre", "Nombre:");
        mod.addAttribute("apellido", "Apellido:");
		mod.addAttribute("email", "Correo:");
		mod.addAttribute("createAt", "Fecha de creación:");
        return model;
    }
	
	@GetMapping("/clientes/editar")
	public String editarCliente(Model model)
	{
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Editar cliente");
		model.addAttribute("id", "Id para modificar:");
		model.addAttribute("nombre", "Nombre:");
        model.addAttribute("apellido", "Apellido:");
		model.addAttribute("email", "Correo:");
		model.addAttribute("createAt", "Fecha de creación:");
		return "clientes/editar";
	}
	
	@RequestMapping("/clientes/editado")
    public ModelAndView clienteEditado(@Valid Cliente cliente, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("cliente", cliente);
        boolean found = false;
        
        for(Cliente c: clienteService.findAll())
        {
        	if(c.getId() == cliente.getId())
        	{
        		found = true;
        		if(!cliente.getNombre().equals(""))
        		{
        			c.setNombre(cliente.getNombre());
        		}
        		
        		if(!cliente.getEmail().equals(""))
        		{
        			c.setEmail(cliente.getEmail());
        		}
        		
        		if(!cliente.getApellido().equals(""))
        		{
        			c.setApellido(cliente.getApellido());
        		}
        		
        		if(cliente.getCreateAt().getTime() != 0)
        		{
        			c.setCreateAt(cliente.getCreateAt());
        		}
        		
        		clienteService.save(c);
        		break;
        	}
        }
        model.setViewName("ready");
        mod.addAttribute("titulo", "Editar cliente");
        if(found)
        {
        	mod.addAttribute("resultado", "Cliente modificado");
        }
        else
        {
        	mod.addAttribute("resultado", "Cliente no encontrado");
        }
        return model;
    }
	
	@GetMapping("clientes/borrar")
	public String borrarCliente(Model model)
	{
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Borrar cliente");
		model.addAttribute("id", "Id:");
		return "/clientes/borrar";
	}
	
	@RequestMapping("clientes/borrado")
    public ModelAndView clienteBorrado(@Valid Cliente cliente, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("cliente", cliente);
        boolean found = false;
        
        for(Cliente c: clienteService.findAll())
        {
        	if(c.getId() == cliente.getId())
        	{
        		found = true;
        		clienteService.delete(c.getId());
        		break;
        	}
        }
        model.setViewName("ready");
        mod.addAttribute("titulo", "Borrar cliente");
        if(found)
        {
        	mod.addAttribute("resultado", "Cliente borrado");
        }
        else
        {
        	mod.addAttribute("resultado", "Cliente no encontrado");
        }
        return model;
    }
	
	@GetMapping("/productos")
	public String indexProductos(Model model)
	{
		model.addAttribute("titulo", "Productos");
		return "productos/index";
	}
	
	@GetMapping("/productos/listar")
	public String listarProductos(Model model)
	{
		model.addAttribute("titulo", "Listar productos");
		model.addAttribute("productos", productoService.findAll());
		return "productos/listar";
	}
	
	@GetMapping("/productos/anyadir")
	public String addProducto(Model model)
	{
		model.addAttribute("titulo", "Añadir producto");
		model.addAttribute("descripcion", "Descripción:");
        model.addAttribute("precio", "Precio:");
		model.addAttribute("fechaAlta", "Fecha de alta:");
		model.addAttribute("disponibilidad", "Disponibilidad:");
		model.addAttribute("producto", new Producto());
		return "productos/anyadir";
	}
	
	@RequestMapping("/productos/create")
    public ModelAndView createProducto(@Valid Producto producto, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        model.addObject("producto", producto);
        
        if(!result.hasErrors())
        {
        	for(Producto p: productoService.findAll())
        	{
        		if(p.getCodProducto() == producto.getCodProducto())
        		{
        			exists = true;
        			break;
        		}
        	}
        	
        	model.setViewName("ready");
        	
        	if(!exists)
        	{
        		mod.addAttribute("resultado", "Producto creado");
        		productoService.save(producto);
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El id ya existe");
        	}
        }
        else
        {
        	model.setViewName("productos/anyadir");
        }
        mod.addAttribute("titulo", "Añadir producto");
		mod.addAttribute("descripcion", "Descripción:");
        mod.addAttribute("precio", "Precio:");
		mod.addAttribute("fechaAlta", "Fecha de alta:");
		mod.addAttribute("disponibilidad", "Disponibilidad:");
        return model;
    }
	
	@GetMapping("/productos/editar")
	public String editarProducto(Model model)
	{
		model.addAttribute("producto", new Producto());
		model.addAttribute("titulo", "Editar producto");
		model.addAttribute("codProducto", "Id para modificar:");
		model.addAttribute("descripcion", "Descripción:");
        model.addAttribute("precio", "Precio:");
		model.addAttribute("fechaAlta", "Fecha de alta:");
		model.addAttribute("disponibilidad", "Disponibilidad:");
		return "productos/editar";
	}
	
	@RequestMapping("/productos/editado")
    public ModelAndView productoEditado(@Valid Producto producto, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("producto", producto);
        boolean found = false;
        
        for(Producto p: productoService.findAll())
        {
        	if(p.getCodProducto() == producto.getCodProducto())
        	{
        		found = true;
        		if(!producto.getDescripcion().equals(""))
        		{
        			p.setDescripcion(producto.getDescripcion());
        		}
        		
        		if(producto.getPrecio() != 0)
        		{
        			p.setPrecio(producto.getPrecio());
        		}
        		
        		if(producto.getFechaAlta().getTime() != 0)
        		{
        			p.setFechaAlta(producto.getFechaAlta());
        		}
        		
        		p.setDisponibilidad(producto.isDisponibilidad());
        		
        		productoService.save(p);
        		break;
        	}
        }
        model.setViewName("ready");
        mod.addAttribute("titulo", "Editar producto");
        if(found)
        {
        	mod.addAttribute("resultado", "Producto modificado");
        }
        else
        {
        	mod.addAttribute("resultado", "Producto no encontrado");
        }
        return model;
    }
	
	@GetMapping("productos/borrar")
	public String borrarProducto(Model model)
	{
		model.addAttribute("producto", new Producto());
		model.addAttribute("titulo", "Borrar cliente");
		model.addAttribute("codProducto", "Id:");
		return "/productos/borrar";
	}
	
	@RequestMapping("productos/borrado")
    public ModelAndView productoBorrado(@Valid Producto producto, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("producto", producto);
        boolean found = false;
        
        for(Producto p: productoService.findAll())
        {
        	if(p.getCodProducto() == producto.getCodProducto())
        	{
        		found = true;
        		productoService.delete(p.getCodProducto());
        		break;
        	}
        }
        model.setViewName("ready");
        mod.addAttribute("titulo", "Borrar producto");
        if(found)
        {
        	mod.addAttribute("resultado", "Producto borrado");
        }
        else
        {
        	mod.addAttribute("resultado", "Producto no encontrado");
        }
        return model;
    }
}
