package com.example.springboot.backend.apirest.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.springboot.backend.apirest.models.entity.Cliente;
import com.example.springboot.backend.apirest.models.entity.Mail;
import com.example.springboot.backend.apirest.models.entity.Producto;
import com.example.springboot.backend.apirest.models.entity.ProductoCliente;
import com.example.springboot.backend.apirest.models.entity.ProductoClienteId;
import com.example.springboot.backend.apirest.models.entity.Usuario;
import com.example.springboot.backend.apirest.models.services.ClienteServiceImpl;
import com.example.springboot.backend.apirest.models.services.MailServiceImpl;
import com.example.springboot.backend.apirest.models.services.ProductoClienteImpl;
import com.example.springboot.backend.apirest.models.services.ProductoServiceImpl;
import com.example.springboot.backend.apirest.models.services.UsuarioServiceImpl;

@Controller
public class IndexController {

	@Autowired
	ClienteServiceImpl clienteService = new ClienteServiceImpl();
	
	@Autowired
	ProductoServiceImpl productoService = new ProductoServiceImpl();
	
	@Autowired
	ProductoClienteImpl productoClienteService = new ProductoClienteImpl();
	
	@Autowired
	UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
	
	@Autowired
	MailServiceImpl mailService = new MailServiceImpl();
	
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
	
	@GetMapping("/clientes/listarclientesusuarios")
	public String listarClientes2(Model model)
	{
		model.addAttribute("titulo", "Listar clientes y usuarios");
		model.addAttribute("clientes", clienteService.findAll());
		return "clientes/listar2";
	}
	
	@GetMapping("/clientes/filtrar")
	public String listarClientes3(Model model)
	{
		model.addAttribute("titulo", "Listar clientes filtrados");
		model.addAttribute("buscar", "Buscar:");
		model.addAttribute("clientes", clienteService.findAll());
		return "clientes/listar3";
	}
	
	@RequestMapping("/clientes/filtrar")
	public String listarClientes3Aux(Model model, @RequestParam("buscar") String buscar)
	{
		model.addAttribute("titulo", "Listar clientes filtrados");
		model.addAttribute("buscar", "Buscar:");
		model.addAttribute("clientes", clienteService.filtrar(buscar));
		return "clientes/listar3";
	}
	
	@GetMapping("/clientes/listarsinusu")
	public String listarClientesSinUsuario(Model model)
	{
		model.addAttribute("titulo", "Listar clientes sin usuario");
		model.addAttribute("clientes", clienteService.clientesSinUsuario());
		return "clientes/listarsinusu";
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
    public ModelAndView clienteEditado(
    		@RequestParam("id") Integer id,
    		@RequestParam("nombre") String nombre,
    		@RequestParam("apellido") String apellido,
    		@RequestParam("email") String email,
    		@RequestParam("createAt") String createAt,
    		Model mod) {
        boolean found = false;
        ModelAndView model = new ModelAndView();
        
        Cliente c = clienteService.findById(id + 0L);

    	if(c != null)
    	{
    		found = true;
    		if(!nombre.equals(""))
    		{
    			c.setNombre(nombre);
    		}
    		
    		if(!email.equals(""))
    		{
    			c.setEmail(email);
    		}
    		
    		if(!apellido.equals(""))
    		{
    			c.setApellido(apellido);
    		}
    		
    		if(!createAt.equals(""))
    		{
    			Date fechaParseada;
				try {
					fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(createAt);
					c.setCreateAt(fechaParseada);
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}
    		
    		clienteService.save(c);
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
        boolean enVentas = false;
        
        List<ProductoCliente> pc = productoClienteService.findAll();
        
        for(ProductoCliente venta: pc)
        {
        	if(venta.getCliente().getId() == cliente.getId())
        	{
        		enVentas = true;
        		model.setViewName("ready");
                mod.addAttribute("titulo", "Borrar cliente");
                mod.addAttribute("resultado", "No se puede borrar a un cliente con ventas");
                break;
        	}
        }
        
        if(!enVentas)
        {
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
    public ModelAndView productoEditado(
    		@RequestParam("codProducto") Integer codProducto,
    		@RequestParam("descripcion") String descripcion,
    		@RequestParam("precio") Double precio,
    		@RequestParam("fechaAlta") String fechaAlta,
    		@RequestParam(value="dispCheck", defaultValue="noDisponible") String disponibilidad,
    		Model mod) {
		ModelAndView model = new ModelAndView();
        boolean found = false;
        System.out.println(disponibilidad);
        Producto p = productoService.findById(codProducto + 0L);
        
    	if(p != null)
    	{
    		found = true;
    		if(!descripcion.equals(""))
    		{
    			p.setDescripcion(descripcion);
    		}
    		
    		if(precio != 0)
    		{
    			p.setPrecio(precio);
    		}
    		
    		if(!fechaAlta.equals(""))
    		{
    			Date fechaParseada;
				try {
					fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(fechaAlta);
					p.setFechaAlta(fechaParseada);
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}
    		
    		p.setDisponibilidad(disponibilidad.equals("disponible"));
    		
    		productoService.save(p);
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
        boolean enVentas = false;
        
        List<ProductoCliente> pc = productoClienteService.findAll();
        
        for(ProductoCliente venta: pc)
        {
        	if(venta.getCliente().getId() == producto.getCodProducto())
        	{
        		enVentas = true;
        		model.setViewName("ready");
                mod.addAttribute("titulo", "Borrar producto");
                mod.addAttribute("resultado", "No se puede borrar un producto con ventas");
                break;
        	}
        }
        
        if(!enVentas)
        {
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
        }
        return model;
    }
	
	@GetMapping("/ventas")
	public String indexVentas(Model model)
	{
		model.addAttribute("titulo", "Ventas");
		return "ventas/index";
	}
	
	@GetMapping("/ventas/listar")
	public String listarVentas(Model model)
	{
		model.addAttribute("titulo", "Listar ventas");
		model.addAttribute("ventas", productoClienteService.findAll());
		return "ventas/listar";
	}
	
	@GetMapping("/ventas/anyadir")
	public String addVenta(Model model)
	{
		model.addAttribute("titulo", "Añadir venta");
		model.addAttribute("clienteId", "Id cliente:");
        model.addAttribute("productoId", "Id producto:");
		model.addAttribute("fecha", "Fecha:");
		model.addAttribute("productoClienteId", new ProductoClienteId());
		return "ventas/anyadir";
	}
	
	@RequestMapping("/ventas/create")
    public ModelAndView createVenta(@Valid ProductoClienteId productoClienteId, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        model.addObject("productoClienteId", productoClienteId);
        
        if(!result.hasErrors())
        {
        	for(ProductoCliente p: productoClienteService.findAll())
        	{
        		if(p.getCliente().getId() == productoClienteId.getClienteId() &&
        				p.getProducto().getCodProducto() == productoClienteId.getProductoId() &&
        				p.getFecha() == productoClienteId.getFecha())
        		{
        			exists = true;
        			break;
        		}
        	}
        	
        	model.setViewName("ready");
        	
        	if(!exists)
        	{
        		ProductoCliente p = new ProductoCliente();
        		p.setId(productoClienteId);
        		p.setCliente(clienteService.findById(productoClienteId.getClienteId()));
        		p.setProducto(productoService.findById(productoClienteId.getProductoId()));
        		mod.addAttribute("resultado", "Venta creada");
        		productoClienteService.save(p);
        		System.out.println("guarda");
        	}
        	else
        	{
        		System.out.println("exists if");
        		mod.addAttribute("resultado", "El id ya existe");
        	}
        }
        else
        {
        	model.setViewName("ventas/anyadir");
        }
        mod.addAttribute("titulo", "Añadir venta");
		mod.addAttribute("clienteId", "Id cliente:");
        mod.addAttribute("productoId", "Id producto:");
		mod.addAttribute("fecha", "Fecha:");
        return model;
    }
	
	@GetMapping("/ventas/editar")
	public String editarVenta(Model model)
	{
		model.addAttribute("titulo", "Editar venta");
		model.addAttribute("clienteId", "Id cliente:");
        model.addAttribute("productoId", "Id producto:");
		model.addAttribute("fecha", "Fecha:");
		model.addAttribute("clienteId2", "");
		model.addAttribute("productoId2", "");
		model.addAttribute("fecha2", "");
		model.addAttribute("productoClienteId", new ProductoClienteId());
		return "ventas/editar";
	}
	
	@RequestMapping("/ventas/editado")
    public ModelAndView ventaEditada(
    		@Valid ProductoClienteId productoClienteId,
    		@RequestParam("clienteId2") Integer clienteId2,
    		@RequestParam("productoId2") Integer productoId2,
    		@RequestParam("fecha2") String fecha2,
    		BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("productoClienteId", productoClienteId);
        boolean found = false;
        
        ProductoCliente p = productoClienteService.findById(productoClienteId);
        ProductoClienteId pId = new ProductoClienteId();
        
    	if(p != null)
    	{
    		found = true;
    		
    		if(clienteId2 != 0)
    		{
    			pId.setClienteId(clienteId2 + 0L);
    		}
    		else
    		{
    			pId.setClienteId(p.getCliente().getId());
    		}
    		
    		if(productoId2 != 0)
    		{
    			pId.setProductoId(productoId2 + 0L);
    		}
    		else
    		{
    			pId.setProductoId(p.getProducto().getCodProducto());
    		}
    		
    		if(!fecha2.equals(""))
    		{
    			Date fechaParseada;
				try {
					fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2);
					pId.setFecha(fechaParseada);
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}
    		else
    		{
    			pId.setFecha(p.getFecha());
    		}
    		
    		ProductoCliente pUpdated = new ProductoCliente();
    		pUpdated.setId(pId);
    		pUpdated.setCliente(clienteService.findById(pId.getClienteId()));
    		pUpdated.setProducto(productoService.findById(pId.getProductoId()));
    		
    		productoClienteService.delete(productoClienteId);
    		productoClienteService.save(pUpdated);
    	}
        
        model.setViewName("ready");
        mod.addAttribute("titulo", "Editar venta");
        if(found)
        {
        	mod.addAttribute("resultado", "Venta modificada");
        }
        else
        {
        	mod.addAttribute("resultado", "Venta no encontrada");
        }
        return model;
    }
	
	@GetMapping("ventas/borrar")
	public String borrarVenta(Model model)
	{
		model.addAttribute("productoClienteId", new ProductoClienteId());
		model.addAttribute("titulo", "Borrar venta");
		model.addAttribute("clienteId", "Id cliente:");
		model.addAttribute("productoId", "Id producto:");
		model.addAttribute("fecha", "Fecha:");
		return "/ventas/borrar";
	}
	
	@RequestMapping("ventas/borrado")
    public ModelAndView ventaBorrada(@Valid ProductoClienteId productoClienteId, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("productoClienteId", productoClienteId);
        boolean found = false;
        
        ProductoCliente p = productoClienteService.findById(productoClienteId);
        
    	if(p != null)
    	{
    		found = true;
    		productoClienteService.delete(p.getId());
    	}
    	
        model.setViewName("ready");
        mod.addAttribute("titulo", "Borrar venta");
        if(found)
        {
        	mod.addAttribute("resultado", "Venta borrada");
        }
        else
        {
        	mod.addAttribute("resultado", "Venta no encontrada");
        }
        return model;
    }

	@GetMapping("/usuarios")
	public String indexUsuarios(Model model)
	{
		model.addAttribute("titulo", "Usuarios");
		return "usuarios/index";
	}
	
	@GetMapping("/usuarios/listar")
	public String listarUsuarios(Model model)
	{
		model.addAttribute("titulo", "Listar usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "usuarios/listar";
	}
	
	@GetMapping("/usuarios/usuariosproductos")
	public String listarUsuariosProductos(Model model)
	{
		model.addAttribute("titulo", "Listar usuarios con productos");
		model.addAttribute("clientes", clienteService.clientesConUsuarios());
		return "usuarios/usuariosproductos";
	}
	
	@GetMapping("/usuarios/anyadir")
	public String addUsuario(Model model)
	{
		model.addAttribute("titulo", "Añadir usuario");
		model.addAttribute("idCliente", "Id cliente:");
        model.addAttribute("nombreUsuario", "Usuario:");
		model.addAttribute("password", "Contraseña:");
		model.addAttribute("email", "Email:");
		model.addAttribute("objetoUsuario", new Usuario());
		return "usuarios/anyadir";
	}
	
	@RequestMapping("/usuarios/create")
    public ModelAndView createUsuario(
    		@Valid Usuario objetoUsuario,
    		@RequestParam("idCliente") long idCliente,
    		BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        boolean mailExists = false;
        model.addObject("objetoUsuario", objetoUsuario);
        
        if(!result.hasErrors())
        {
        	Cliente c = clienteService.findById(idCliente);
        	
        	if(c != null)
        	{
        		objetoUsuario.setCliente(c);
	        	for(Usuario u: usuarioService.findAll())
	        	{
	        		if(u.getCliente() == objetoUsuario.getCliente() || u.getNombreUsuario().equals(objetoUsuario.getNombreUsuario()))
	        		{
	        			exists = true;
	        			break;
	        		}
	        	}
	        	
	        	for(Mail m: mailService.findAll())
	    		{
	    			if(m.getEmail().equals(objetoUsuario.getEmail()))
	    			{
	    				mailExists = true;
	    				break;
	    			}
	    		}
	        	
	        	model.setViewName("ready");
	        	
	        	if(!exists && !mailExists)
	        	{
	        		mod.addAttribute("resultado", "Usuario creado");
	        		usuarioService.save(objetoUsuario);
	        	}
	        	else if(exists)
	        	{
	        		mod.addAttribute("resultado", "El id ya existe");
	        	}
	        	else
	        	{
	        		mod.addAttribute("resultado", "El mail ya existe");
	        	}
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El cliente no existe");
        	}
        }
        else
        {
        	model.setViewName("usuarios/anyadir");
        }
        mod.addAttribute("titulo", "Añadir usuario");
		mod.addAttribute("idCliente", "Id cliente:");
        mod.addAttribute("nombreUsuario", "Usuario:");
		mod.addAttribute("password", "Contraseña:");
		mod.addAttribute("email", "Email:");
        return model;
    }
	
	@GetMapping("/usuarios/editar")
	public String editarUsuario(Model model)
	{
		model.addAttribute("titulo", "Editar usuario");
		model.addAttribute("id", "Id para modificar:");
		model.addAttribute("nombreUsuario", "Nombre usuario:");
        model.addAttribute("idCliente", "Id cliente:");
		model.addAttribute("password", "Contraseña:");
		return "usuarios/editar";
	}
	
	@RequestMapping("/usuarios/editado")
    public ModelAndView productoEditado(
    		@RequestParam("id") Long id,
    		@RequestParam("nombreUsuario") String nombreUsuario,
    		@RequestParam("idCliente") Long idCliente,
    		@RequestParam("password") String password,
    		Model mod) {
		ModelAndView model = new ModelAndView();
        boolean found = false;
        Usuario u = usuarioService.findById(id);
        
    	if(u != null)
    	{
    		found = true;
    		if(!nombreUsuario.equals(""))
    		{
    			u.setNombreUsuario(nombreUsuario);
    		}
    		
    		if(idCliente != 0)
    		{
    			u.setCliente(clienteService.findById(idCliente));
    		}
    		
    		if(!password.equals(""))
    		{
    			u.setPassword(password);
    		}
    		
    		usuarioService.save(u);
    	}
        
        model.setViewName("ready");
        mod.addAttribute("titulo", "Editar usuario");
        if(found)
        {
        	mod.addAttribute("resultado", "Usuario modificado");
        }
        else
        {
        	mod.addAttribute("resultado", "Usuario no encontrado");
        }
        return model;
    }
	
	@GetMapping("usuarios/borrar")
	public String borrarUsuario(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("titulo", "Borrar usuario");
		model.addAttribute("id", "Id usuario:");
		return "/usuarios/borrar";
	}
	
	@RequestMapping("usuarios/borrado")
    public ModelAndView usuarioBorrado(@Valid Usuario usuario, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", usuario);
        
        Usuario u = usuarioService.findById(usuario.getId());
	        
        model.setViewName("ready");
        mod.addAttribute("titulo", "Borrar producto");
        
        if(u != null)
        {
    		usuarioService.delete(usuario.getId());
    		mod.addAttribute("resultado", "Usuario borrado");
        }
        else
        {
        	mod.addAttribute("resultado", "Usuario no encontrado");
        }
        
        return model;
    }
	
	@GetMapping("/mails")
	public String indexMails(Model model)
	{
		model.addAttribute("titulo", "Mail");
		return "mails/index";
	}
	
	@GetMapping("/mails/anyadir")
	public String addMail(Model model)
	{
		model.addAttribute("titulo", "Añadir mail");
		model.addAttribute("email", "Mail:");
        model.addAttribute("usuarioId", "Id usuario:");
		model.addAttribute("mail", new Mail());
		return "mails/anyadir";
	}
	
	@RequestMapping("/mails/create")
    public ModelAndView createMail(@Valid Mail mail, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        model.addObject("mail", mail);
        
        if(!result.hasErrors())
        {
        	for(Mail m: mailService.findAll())
        	{
        		if(m.getEmail() == mail.getEmail())
        		{
        			exists = true;
        			break;
        		}
        	}
        	model.setViewName("ready");
        	
        	if(!exists)
        	{
        		mailService.save(mail);
        		mod.addAttribute("resultado", "Mail creado");
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El mail ya existe");
        	}
        }
        else
        {
        	model.setViewName("mails/anyadir");
        }
        mod.addAttribute("titulo", "Añadir mail");
		mod.addAttribute("email", "Mail:");
        mod.addAttribute("usuarioId", "Id usuario:");
        return model;
    }
	
	@GetMapping("/mails/editar")
	public String editarMail(Model model)
	{
		model.addAttribute("titulo", "Editar mail");
		model.addAttribute("id", "Id para modificar:");
		model.addAttribute("email", "Email:");
        model.addAttribute("idUsuario", "Id usuario:");
		return "mails/editar";
	}
	
	@RequestMapping("/mails/editado")
    public ModelAndView mailEditado(
    		@RequestParam("id") Long id,
    		@RequestParam("email") String email,
    		@RequestParam("idUsuario") Long idUsuario,
    		Model mod) {
		ModelAndView model = new ModelAndView();
        boolean found = false;
        Mail m = mailService.findById(id);
        
    	if(m != null)
    	{
    		found = true;
    		if(!email.equals(""))
    		{
    			m.setEmail(email);
    		}
    		
    		if(idUsuario != 0)
    		{
    			m.setUsuario(usuarioService.findById(idUsuario));
    		}
    		
    		mailService.save(m);
    	}
        
        model.setViewName("ready");
        mod.addAttribute("titulo", "Editar mail");
        if(found)
        {
        	mod.addAttribute("resultado", "Mail modificado");
        }
        else
        {
        	mod.addAttribute("resultado", "Mail no encontrado");
        }
        return model;
    }
	
	@GetMapping("mails/borrar")
	public String borrarMail(Model model)
	{
		model.addAttribute("mail", new Mail());
		model.addAttribute("titulo", "Borrar mail");
		model.addAttribute("id", "Id mail:");
		return "/mails/borrar";
	}
	
	@RequestMapping("mails/borrado")
    public ModelAndView mailBorrado(@Valid Mail mail, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("mail", mail);
        
        Mail m = mailService.findById(mail.getId());
	        
        model.setViewName("ready");
        mod.addAttribute("titulo", "Borrar mail");
        
        if(m != null)
        {
    		mailService.delete(mail.getId());
    		mod.addAttribute("resultado", "Mail borrado");
        }
        else
        {
        	mod.addAttribute("resultado", "Mail no encontrado");
        }
        
        return model;
    }
}
