package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.HuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recoveryConnection());
	}

	public void guardarHuesped(Huesped huesped, Integer reservaId) {
		huesped.setReservaId(reservaId);
		this.huespedDao.guardar(huesped);
	}

	public List<Huesped> listarHuespedes() {
		return this.huespedDao.listar();
	}

	public void buscarReserva(String buscarReserva) {
		this.huespedDao.buscar(buscarReserva);
		
	}

	public int editarHuesped(Huesped huespedEdit) {
		return this.huespedDao.editar(huespedEdit);
		
	}

	public void eliminarHuesped(Integer id) {
		this.huespedDao.eliminar(id);
		
	}
}
