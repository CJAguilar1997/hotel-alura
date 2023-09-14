package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.alura.hotel.model.Huesped;

public class HuespedDAO {
	
	private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try {
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes "
					+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, new java.sql.Date(huesped.getFechaDeNacimiento().getTime()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getReservaId());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while(resultSet.next()) {
						con.commit();
					}
				} catch (Exception e) {
					con.rollback();
					throw new RuntimeException(e);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar() {
		List<Huesped> lista = new ArrayList<>();
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes");
			try (statement) {
				ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while(resultSet.next()) {
						var huespedes = new Huesped(resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								new Date(resultSet.getDate("fecha_nacimiento").getTime()),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));
						lista.add(huespedes);
					}
					return lista;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void buscar(String buscarReserva) {
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas r INNER JOIN "
					+ "huespedes h ON r.id = h.id_reserva WHERE h.nombre = ? OR r.id = ? ORDER BY h.nombre");
			try (statement) {
				statement.setString(1, buscarReserva);
				statement.setString(2, buscarReserva);
				
				ResultSet resultSet = statement.executeQuery();
				StringBuilder datosHuesped = new StringBuilder();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				try (resultSet) {
					while(resultSet.next()) {
						datosHuesped(resultSet, datosHuesped, formato);
					}
					if (datosHuesped.length() > 0) {
						JOptionPane.showMessageDialog(null , datosHuesped.toString(), "Resultados de la busqueda", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con ese nombre o id", "Resultados de la busqueda", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int editar(Huesped huespedEdit) {
		try {
			PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET"
					+ " nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id = ?");
			try (statement) {
				statement.setString(1, huespedEdit.getNombre());
				statement.setString(2, huespedEdit.getApellido());
				statement.setDate(3, new java.sql.Date(huespedEdit.getFechaDeNacimiento().getTime()));
				statement.setString(4, huespedEdit.getNacionalidad());
				statement.setString(5, huespedEdit.getTelefono());
				statement.setInt(6, huespedEdit.getId());
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				JOptionPane.showMessageDialog(null, "Se modifico el huesped con id = " + huespedEdit.getId(), "Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
				
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void datosHuesped(ResultSet resultSet, StringBuilder datosHuesped, SimpleDateFormat formato)
			throws SQLException {
		int idReserva = resultSet.getInt("id_reserva");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		Date fechaEntrada = new Date(resultSet.getDate("fecha_entrada").getTime());
		Date fechaSalida = new Date(resultSet.getDate("fecha_salida").getTime());
		String telefonoHuesped = resultSet.getString("telefono");
		
		datosHuesped.append("ID de reserva: ").append(idReserva).append("\n")
		.append("Nombre del huesped: ").append(nombre).append("\n")
		.append("Apellido: ").append(apellido).append("\n")
		.append("Fecha de entrada: ").append(formato.format(fechaEntrada)).append("\n")
		.append("Fecha de salida: ").append(formato.format(fechaSalida)).append("\n")
		.append("Teléfono del huesped: ").append(telefonoHuesped).append("\n");
	}

	public void eliminar(Integer id) {
		try {
			PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");
			try (statement) {
				statement.setInt(1, id);
				
				statement.execute();
				
				JOptionPane.showMessageDialog(null, "Se eliminó el huesped con id = " + id, "Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	

	
}
