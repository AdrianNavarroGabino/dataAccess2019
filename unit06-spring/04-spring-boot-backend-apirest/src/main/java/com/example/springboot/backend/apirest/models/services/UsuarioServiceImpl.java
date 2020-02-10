package com.example.springboot.backend.apirest.models.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.backend.apirest.models.dao.IMailDAO;
import com.example.springboot.backend.apirest.models.dao.IUsuarioDAO;
import com.example.springboot.backend.apirest.models.entity.Mail;
import com.example.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@Autowired
	private IMailDAO mailDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		Iterator<Mail> it = usuario.getMails().iterator();
		while(it.hasNext())
			mailDao.save(it.next());
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Usuario u = usuarioDao.findById(id).orElse(null);
		
		if(u != null)
		{
			for(Mail m: u.getMails())
			{
				mailDao.delete(m);
			}
			usuarioDao.deleteById(id);
		}
	}
}
