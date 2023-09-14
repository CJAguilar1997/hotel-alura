package com.alura.hotel.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.HuespedController;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		cargarReservas();
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		
		cargarHuespedes();
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtBuscar.getText() != null) {
					huespedController.buscarReserva(txtBuscar.getText());
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable tablaActual = null;
				Component componenteSeleccionado = panel.getSelectedComponent();
				tablaActual = verificacionDeTablaActual(tablaActual, componenteSeleccionado);
				if (tablaActual == tbReservas) {
					System.out.println(tablaActual);
					tablaActual = tbReservas;
					editarReserva();
					limpiarTabla(tablaActual);
					cargarReservas();
				} else if (tablaActual == tbHuespedes) {
					tablaActual = tbHuespedes;
					editarHuesped();
					limpiarTabla(tablaActual);
					cargarHuespedes();
				}
				if (tablaActual != null && valorSeleccionado(tablaActual)) {
					System.out.println(valorSeleccionado(componenteSeleccionado));
					JOptionPane.showMessageDialog(null, "Por favor seleccione un item", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}			
		});
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable tablaActual = null;
				Component componenteSeleccionado = panel.getSelectedComponent();
				tablaActual = verificacionDeTablaActual(tablaActual, componenteSeleccionado);
				if (tablaActual == tbReservas) {
					tablaActual = tbReservas;
					eliminarReserva();
					limpiarTabla(tablaActual);
					cargarReservas();
					
				} else if (tablaActual == tbHuespedes) {
					tablaActual = tbHuespedes;
					eliminarHuesped();
					limpiarTabla(tablaActual);
					cargarHuespedes();
				}
				if (tablaActual != null && valorSeleccionado(tablaActual)) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione un item", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	
	private void limpiarTabla(JTable tablaActual) {
		if (tablaActual == tbReservas) {
			modelo.getDataVector().clear();
		} else if (tablaActual == tbHuespedes) {
			modeloHuesped.getDataVector().clear();
		}
	}
	
	private void cargarReservas() {
		var reservas = this.reservaController.listarReservas();
		
		reservas.forEach(reserva -> modelo.addRow(new Object[] {
				reserva.getId(),
				formato.format(reserva.getFechaEntrada()),
				formato.format(reserva.getFechaSalida()),
				reserva.getValorReserva(),
				reserva.getFormaPago()
		}));
	}
	
	private void cargarHuespedes() {
		var huespedes = this.huespedController.listarHuespedes();
		
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] {
				huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				formato.format(huesped.getFechaDeNacimiento()),
				huesped.getNacionalidad(),
				huesped.getTelefono(),
				huesped.getReservaId()
		}));
	}
	
	private boolean valorSeleccionado(Component tablaActual) {
		if (tablaActual == tbReservas) {
			return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
		} else if (tablaActual == tbHuespedes){
			return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
		} else {
			return true;
		}
	}
	
	private JTable verificacionDeTablaActual(JTable tablaActual, Component componenteSeleccionado) {
		if (componenteSeleccionado instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane) componenteSeleccionado;
			Component vista = scrollPane.getViewport().getView();
			if (vista instanceof JTable) {
				tablaActual = (JTable) vista;
			}
		}
		return tablaActual;
	}
	
	private void editarReserva() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			String fechaEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
			String fechaSalida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
			Float valorReserva = Float.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
			String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
			
			Date fechaEntradaDate = null;
			Date fechaSalidaDate = null;
			
			try {
				fechaEntradaDate = formato.parse(fechaEntrada);
				fechaSalidaDate = formato.parse(fechaSalida);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La fecha tiene que ir en formato dd/MM/aaaa \n !Las fechas introducidas no son validas¡", "Error", JOptionPane.ERROR_MESSAGE);
				throw new RuntimeException(e);
			}
			
			Reserva reservaEdit = new Reserva(id, fechaEntradaDate, fechaSalidaDate, valorReserva, formaPago);
			
			this.reservaController.editarReserva(reservaEdit);
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	

	private void editarHuesped() {
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
			String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
			String fechaNacimiento = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
			String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
			String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
			
			Date fechaNacimientoDate = null;
			
			try {
				fechaNacimientoDate = formato.parse(fechaNacimiento);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La fecha tiene que ir en formato dd/MM/aaaa \n !Las fechas introducidas no son validas¡", "Error", JOptionPane.ERROR_MESSAGE);
				throw new RuntimeException(e);
			}
			
			Huesped huespedEdit = new Huesped(id, nombre, apellido, fechaNacimientoDate, nacionalidad, telefono);
			
			this.huespedController.editarHuesped(huespedEdit);
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	private void eliminarReserva() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			
			this.reservaController.eliminarReserva(id);
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	private void eliminarHuesped() {
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			
			this.huespedController.eliminarHuesped(id);
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
}
