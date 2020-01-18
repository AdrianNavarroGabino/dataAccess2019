package com.example.usuarios.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {
	
	@Min(value = 1, message = "debe introducir un id válido")
    @NotNull
	int id;
	
	@Size(min = 3, max = 20, message = "el nombre debe tener mas de 3 letras y menos de 20.")
	@NotBlank(message = "debe indicar el nombre del usuario.")
	String nombre;
	
	@Email
	String mail;
	
	@Pattern(regexp = "^[a-zA-Z]\\w{3,14}$", message = "debe contener letras seguidos de numeros")
	String password;
	
	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, String mail, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
