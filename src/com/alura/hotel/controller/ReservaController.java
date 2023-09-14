package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.ReservaDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Reserva;

public class ReservaController {
	
	ReservaDAO reservaDao;
	
	public ReservaController() {
		this.reservaDao = new ReservaDAO(new ConnectionFactory().recoveryConnection());
	}
	
	public void guardarReserva(Reserva reserva) {
		this.reservaDao.guardar(reserva);
	}

	public List<Reserva> listarReservas() {
		return reservaDao.listar();
	}

	public void editarReserva(Reserva reservaEdit) {
		this.reservaDao.editar(reservaEdit);	
	}

	public void eliminarReserva(Integer id) {
		this.reservaDao.eliminar(id);
	}
}
