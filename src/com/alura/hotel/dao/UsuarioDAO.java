package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alura.hotel.model.Usuario;

public class UsuarioDAO {

	Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public Boolean login(Usuario usuario) {
		try {
			PreparedStatement statement = con
					.prepareStatement("SELECT usuario, password FROM usuarios " + "WHERE usuario = ? AND password = ?");
			try (statement) {
				statement.setString(1, usuario.getUsuario());
				statement.setString(2, usuario.getPassword());
				ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					if (resultSet.next()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}
