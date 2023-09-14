package com.alura.hotel.model;

import java.util.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer reservaId;
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	public Huesped(int id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, int reservaId) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}
	public Huesped(Integer reservaId, String nombre) {
		this.nombre = nombre;
		this.reservaId = reservaId;
	}
	public Huesped(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad,
			String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		
	}
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public Integer getReservaId() {
		return reservaId;
	}
	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}
}
