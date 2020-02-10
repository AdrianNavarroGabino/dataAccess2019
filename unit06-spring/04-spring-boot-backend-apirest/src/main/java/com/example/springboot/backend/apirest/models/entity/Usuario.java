package com.example.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El usuario no puede estar vacío")
	@Size(min=4, max=12, message="El tamaño del usuario tiene que estar entre 4 y 12")
	@Column(nullable=false, name="usuario")
	private String nombreUsuario;
	
	@NotEmpty(message="La contraseña no puede estar vacía")
	@Size(min=6, message="El tamaño de la contraseña tiene que ser mayor de 5")
	@Column(nullable=false)
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy="usuario")
	private Set<Mail> mails  = new HashSet<>(0);
	
	@Transient
	private String email;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Mail> getMails() {
		return mails;
	}

	public void setMails(Set<Mail> mails) {
		this.mails = mails;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		Mail mail = new Mail();
		mail.setEmail(email);
		mail.setUsuario(this);
		mails.add(mail);
	}
}
