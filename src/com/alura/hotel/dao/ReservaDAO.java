package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.alura.hotel.model.Reserva;

public class ReservaDAO {
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			con.setAutoCommit(false);
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (fecha_entrada, fecha_salida, valor_reserva, forma_pago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setDate(1, new java.sql.Date(reserva.getFechaEntrada().getTime()));
				statement.setDate(2, new java.sql.Date(reserva.getFechaSalida().getTime()));
				statement.setFloat(3, reserva.getValorReserva());
				statement.setString(4, reserva.getFormaPago());
			
				statement.execute();
			
				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
					con.commit();
				} catch (Exception e) {
					con.rollback();
					throw new RuntimeException(e);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Reserva> listar() {
		List<Reserva> lista = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor_reserva, forma_pago FROM reservas");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while(resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("id"),
								new Date(resultSet.getDate("fecha_entrada").getTime()),
								new Date(resultSet.getDate("fecha_salida").getTime()),
								resultSet.getFloat("valor_reserva"),
								resultSet.getString("forma_pago"));
						lista.add(reserva);
					}
					return lista;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void editar(Reserva reservaEdit) {
		try {
			PreparedStatement statement = con.prepareStatement("UPDATE reservas SET"
					+ " fecha_entrada = ?, fecha_salida = ?, valor_reserva = ?, forma_pago = ? WHERE id = ?");
			try (statement) {
				statement.setDate(1, new java.sql.Date(reservaEdit.getFechaEntrada().getTime()));
				statement.setDate(2, new java.sql.Date(reservaEdit.getFechaSalida().getTime()));
				statement.setFloat(3, reservaEdit.getValorReserva());
				statement.setString(4, reservaEdit.getFormaPago());
				statement.setInt(5, reservaEdit.getId());
				
				statement.execute();
				
				JOptionPane.showMessageDialog(null, "Se modifico la reserva con id = " + reservaEdit.getId(), "Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void eliminar(Integer id) {
		try {
			PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");
			try (statement) {
				statement.setInt(1, id);
				
				statement.execute();
				
				JOptionPane.showMessageDialog(null, "Se eliminó la reserva con id = " + id, "Modificacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
