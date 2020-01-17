package com.example.ejemplo1.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

import com.example.ejemplo1.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoListar;
	
	@GetMapping ({"/index", "/", "", "/home"})
	public String index(Model model)
	{
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping(value = "/perfil")
	public String perfil(Model model)
	{
		Usuario usuario = new Usuario();
		usuario.setNombre("Adrián");
		usuario.setApellido("Navarro");
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		return "perfil";
	}
	
	@RequestMapping(value = "/listar")
	public String listar(Model model)
	{
		model.addAttribute("titulo", textoListar);
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios()
	{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Adrián", "Navarro", "adrian@adrian.com"));
		usuarios.add(new Usuario("Dani", "García", "dani@dani.com"));
		usuarios.add(new Usuario("Consuelo", "López", "consu@consu.com"));
		return usuarios;
	}
}
