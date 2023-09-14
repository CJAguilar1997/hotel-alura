package com.alura.hotel.controller;

import com.alura.hotel.dao.UsuarioDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Usuario;

public class UsuarioController {
	
	UsuarioDAO usuarioDao;
	
	public UsuarioController() {
		this.usuarioDao = new UsuarioDAO(new ConnectionFactory().recoveryConnection());
	}

	public Boolean loginUsuario(Usuario usuario) {
		return usuarioDao.login(usuario);
	}
}
