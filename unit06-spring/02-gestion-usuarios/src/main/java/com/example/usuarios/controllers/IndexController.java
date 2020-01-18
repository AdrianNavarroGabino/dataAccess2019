package com.example.usuarios.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.usuarios.models.Usuario;

@Controller
public class IndexController {
	
	List<Usuario> usuarios;
	
	public IndexController()
	{
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario(1, "Adrián Navarro", "adrian@adrian.com", "1234"));
		usuarios.add(new Usuario(2, "Dani García", "dani@dani.com", "1234"));
		usuarios.add(new Usuario(3, "Consuelo López", "consu@consu.com", "1234"));
	}
	
	@Value("${texto.indexcontroller.add.nombre}")
	private String nombre;
	
	@Value("${texto.indexcontroller.add.correo}")
	private String correo;
	
	@Value("${texto.indexcontroller.add.id}")
	private String id;
	
	@Value("${texto.indexcontroller.add.idmodificar}")
	private String idModificar;
	
	@Value("${texto.indexcontroller.add.pass}")
	private String pass;
	
	@GetMapping({"/", "", "/index", "/home"})
	public String index(Model model)
	{
		model.addAttribute("titulo", "Gestión básica de usuarios");
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model)
	{
		model.addAttribute("titulo", "Usuarios");
		return "list";
	}
	
	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("titulo", "Añadir usuario");
		model.addAttribute("nombre", nombre);
		model.addAttribute("correo", correo);
		model.addAttribute("id", id);
		model.addAttribute("pass", pass);
		model.addAttribute("usuario", new Usuario());
		return "add";
	}
	
	@RequestMapping("/create")
    public ModelAndView createUser(@Valid Usuario user, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        model.addObject("usuario", user);
        
        if(!result.hasErrors())
        {
        	for(Usuario u: usuarios)
        	{
        		if(u.getId() == user.getId())
        		{
        			exists = true;
        			break;
        		}
        	}
        	
        	model.setViewName("userReady");
        	
        	if(!exists)
        	{
        		mod.addAttribute("resultado", "Usuario creado");
        		usuarios.add(user);
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El id ya existe");
        	}
        }
        else
        {
        	model.setViewName("add");
        }
        mod.addAttribute("nombre", nombre);
		mod.addAttribute("correo", correo);
		mod.addAttribute("id", id);
		mod.addAttribute("pass", pass);
        return model;
    }
	
	@GetMapping("/edit")
	public String edit(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("titulo", "Editar usuario");
		model.addAttribute("nombre", nombre);
		model.addAttribute("correo", correo);
		model.addAttribute("id", idModificar);
		model.addAttribute("pass", pass);
		return "edit";
	}
	
	@RequestMapping("/edited")
    public ModelAndView editUser(@Valid Usuario user, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", user);
        boolean found = false;
        
        for(Usuario u: usuarios)
        {
        	if(u.getId() == user.getId())
        	{
        		found = true;
        		if(!user.getNombre().equals(""))
        		{
        			u.setNombre(user.getNombre());
        		}
        		
        		if(!user.getMail().equals(""))
        		{
        			u.setMail(user.getMail());
        		}
        		
        		if(!user.getPassword().equals(""))
        		{
        			u.setPassword(user.getPassword());
        		}
        		break;
        	}
        }
        model.setViewName("removed");
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
	
	@GetMapping("/remove")
	public String remove(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("titulo", "Borrar usuario");
		model.addAttribute("id", id);
		return "remove";
	}
	
	@RequestMapping("/removed")
    public ModelAndView removeUser(@Valid Usuario user, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", user);
        boolean found = false;
        
        for(Usuario u: usuarios)
        {
        	if(u.getId() == user.getId())
        	{
        		found = true;
        		usuarios.remove(u);
        		break;
        	}
        }
        model.setViewName("removed");
        mod.addAttribute("titulo", "Borrar usuario");
        if(found)
        {
        	mod.addAttribute("resultado", "Usuario borrado");
        }
        else
        {
        	mod.addAttribute("resultado", "Usuario no encontrado");
        }
        return model;
    }
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios()
	{
		return usuarios;
	}
}
