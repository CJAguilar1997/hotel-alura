package com.alura.hotel.model;

import java.util.Date;

public class Reserva {
	
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Float valorReserva;
	private String formaPago;
	private Boolean reservaActiva;
	
	public Reserva (Date fechaEntrada, Date fechaSalida, String formaPago, Float valorReserva) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaPago = formaPago;
		this.valorReserva = valorReserva;
	}
	
	public Reserva(Integer id) {
		this.id = id;
	}

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, float valorReserva, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valorReserva = valorReserva;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public Boolean getReservaActiva() {
		return reservaActiva;
	}

	public float getValorReserva() {
		return this.valorReserva;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	
	
}
