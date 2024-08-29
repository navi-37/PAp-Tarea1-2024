package presentacion;

import java.util.Date;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtDonacion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;


public class ModificarDonacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JTextField textFieldTipoDonacion;
	private JTextField textFieldFechaIng;
	private JTextField textFieldDescripcion;
	private JTextField textFieldCantElem;
	private JTextField textFieldPeso;
	private JTextField textFieldDimensiones;
	private JComboBox<Integer> comboBoxDonaciones;
	
	public ModificarDonacion(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(null);
		
		comboBoxDonaciones = new JComboBox<Integer>();
		comboBoxDonaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarInfoDonacion();
			}
		});
		comboBoxDonaciones.setBounds(63, 27, 68, 24);
		getContentPane().add(comboBoxDonaciones);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 32, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblFechaIngresada = new JLabel("Fecha ingresada");
		lblFechaIngresada.setBounds(22, 125, 158, 15);
		getContentPane().add(lblFechaIngresada);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(22, 170, 129, 15);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(22, 215, 175, 15);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(22, 260, 70, 15);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(22, 305, 129, 15);
		getContentPane().add(lblDimensiones);
		
		textFieldFechaIng = new JTextField();
		textFieldFechaIng.setBounds(154, 125, 120, 20);
		getContentPane().add(textFieldFechaIng);
		textFieldFechaIng.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(123, 170, 151, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldCantElem = new JTextField();
		textFieldCantElem.setBounds(200, 215, 74, 20);
		getContentPane().add(textFieldCantElem);
		textFieldCantElem.setColumns(10);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setBounds(204, 260, 70, 20);
		getContentPane().add(textFieldPeso);
		textFieldPeso.setColumns(10);
		
		textFieldDimensiones = new JTextField();
		textFieldDimensiones.setBounds(164, 305, 110, 20);
		getContentPane().add(textFieldDimensiones);
		textFieldDimensiones.setColumns(10);
		
		JLabel lblTipoDonacion = new JLabel("Tipo de donación");
		lblTipoDonacion.setBounds(22, 80, 109, 14);
		getContentPane().add(lblTipoDonacion);
		
		textFieldTipoDonacion = new JTextField();
		textFieldTipoDonacion.setBounds(154, 80, 120, 20);
		getContentPane().add(textFieldTipoDonacion);
		textFieldTipoDonacion.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatos();
			}
		});
		btnModificar.setBounds(341, 84, 117, 25);
		getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(341, 134, 117, 25);
		getContentPane().add(btnCancelar);

	}
	
	public void actualizarDonaciones() {
	    Integer[] donacionesArray = icon.listarDonaciones();
	    DefaultComboBoxModel<Integer> listaDonaciones = new DefaultComboBoxModel<>(donacionesArray);
	    comboBoxDonaciones.setModel(listaDonaciones);
	    comboBoxDonaciones.setSelectedItem(null);
	}
	
	public void listarInfoDonacion() {
		limpiarFormulario();
		if (comboBoxDonaciones.getSelectedItem() != null) {
			
			Integer id = (Integer) comboBoxDonaciones.getSelectedItem();
			DtDonacion donacion;
			donacion = this.icon.getDonacion(id);
			textFieldFechaIng.setText(convertirFechaADiaMesAnio(donacion.getFechaIngresada()));
			if (donacion instanceof DtAlimento) {
				textFieldTipoDonacion.setText("Alimento");
				DtAlimento donacionAlimento = (DtAlimento) donacion;
				textFieldDescripcion.setText(donacionAlimento.getDescripcionProductos());
				textFieldCantElem.setText(String.valueOf(donacionAlimento.getCantElementos()));
			} else if (donacion instanceof DtArticulo) {
				textFieldTipoDonacion.setText("Artículo");
				DtArticulo donacionArticulo = (DtArticulo) donacion;
				textFieldDescripcion.setText(donacionArticulo.getDescripcion());
				textFieldPeso.setText(String.valueOf(donacionArticulo.getPeso()));
				textFieldDimensiones.setText(donacionArticulo.getDimensiones());
			}
		}
	}
	
	//en este caso se convierte LocalDateTime a string
	public String convertirFechaADiaMesAnio(LocalDateTime fecha) {
	    Date date = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    return formatoFecha.format(date);
	}
	
	public void limpiarFormulario() {
		textFieldTipoDonacion.setText("");
		textFieldFechaIng.setText("");
		textFieldDescripcion.setText("");
		textFieldCantElem.setText("");
		textFieldPeso.setText("");
		textFieldDimensiones.setText("");
	}
	
	public void modificarDatos() {
		//acá habría que llamar un checkformulario que se fije que las cosas esten en orden
		//
		Integer id = (Integer) comboBoxDonaciones.getSelectedItem();
		String nuevaFechaHoraIngreso = textFieldFechaIng.getText();
		String nuevaDescripcion = textFieldDescripcion.getText();
	    Integer nuevaCantElem = null;
	    if (!textFieldCantElem.getText().isEmpty()) {
	        nuevaCantElem = Integer.parseInt(textFieldCantElem.getText());
	    }
	    Float nuevoPeso = null;
	    if (!textFieldPeso.getText().isEmpty()) {
	        nuevoPeso = Float.parseFloat(textFieldPeso.getText());
	    }
		String nuevasDimensiones = textFieldDimensiones.getText();
		
		//acá quedé, me fui a mimir
		
	}
}
