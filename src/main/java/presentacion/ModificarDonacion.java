package presentacion;

import java.util.Date;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtDonacion;
import excepciones.DonacionNoExisteExc;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("MODIFICAR DONACIÓN");
		setClosable(true);
		
		comboBoxDonaciones = new JComboBox<Integer>();
		comboBoxDonaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarInfoDonacion();
			}
		});
		comboBoxDonaciones.setBounds(148, 48, 338, 19);
		getContentPane().add(comboBoxDonaciones);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(148, 32, 70, 13);
		getContentPane().add(lblId);
		
		JLabel lblFechaIngresada = new JLabel("Fecha ingresada");
		lblFechaIngresada.setBounds(148, 126, 158, 13);
		getContentPane().add(lblFechaIngresada);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(148, 173, 129, 13);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(148, 220, 175, 13);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(148, 267, 70, 13);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(148, 314, 129, 13);
		getContentPane().add(lblDimensiones);
		
		textFieldFechaIng = new JTextField();
		textFieldFechaIng.setBounds(148, 142, 338, 19);
		getContentPane().add(textFieldFechaIng);
		textFieldFechaIng.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(148, 189, 338, 19);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldCantElem = new JTextField();
		textFieldCantElem.setBounds(148, 236, 338, 19);
		getContentPane().add(textFieldCantElem);
		textFieldCantElem.setColumns(10);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setBounds(148, 283, 338, 19);
		getContentPane().add(textFieldPeso);
		textFieldPeso.setColumns(10);
		
		textFieldDimensiones = new JTextField();
		textFieldDimensiones.setBounds(148, 330, 338, 19);
		getContentPane().add(textFieldDimensiones);
		textFieldDimensiones.setColumns(10);
		
		JLabel lblTipoDonacion = new JLabel("Tipo de donación");
		lblTipoDonacion.setBounds(148, 79, 109, 13);
		getContentPane().add(lblTipoDonacion);
		
		textFieldTipoDonacion = new JTextField();
		textFieldTipoDonacion.setEditable(false);
		textFieldTipoDonacion.setBounds(148, 95, 338, 19);
		getContentPane().add(textFieldTipoDonacion);
		textFieldTipoDonacion.setColumns(10);
		
		JButton btnModificar = new JButton("✔ Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatos();
			}
		});
		btnModificar.setBounds(188, 374, 117, 25);
		getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("✘ Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(329, 374, 117, 25);
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
			DtDonacion donacion = null;
			try {
				donacion = icon.getDonacion(id);
			} catch (DonacionNoExisteExc ee) {
				JOptionPane.showMessageDialog(this, "La donación no existe", "Error",
	                    JOptionPane.ERROR_MESSAGE);
				return;
			}
			textFieldFechaIng.setText(convertirFechaADiaMesAnio(donacion.getFechaIngresada()));
			if (donacion instanceof DtAlimento) {
				textFieldTipoDonacion.setText("Alimento");
				DtAlimento donacionAlimento = (DtAlimento) donacion;
				textFieldDescripcion.setText(donacionAlimento.getDescripcionProductos());
				textFieldCantElem.setText(String.valueOf(donacionAlimento.getCantElementos()));
				//anulamos los texfields que no corresponden al dtAlimento
				textFieldPeso.setEnabled(false);
				textFieldDimensiones.setEnabled(false);
			} else if (donacion instanceof DtArticulo) {
				textFieldTipoDonacion.setText("Artículo");
				DtArticulo donacionArticulo = (DtArticulo) donacion;
				textFieldDescripcion.setText(donacionArticulo.getDescripcion());
				textFieldPeso.setText(String.valueOf(donacionArticulo.getPeso()));
				textFieldDimensiones.setText(donacionArticulo.getDimensiones());
				//same pero para artículo
				textFieldCantElem.setEnabled(false);
			}
		}
	}
	
	//en este caso se convierte LocalDateTime a string
	public String convertirFechaADiaMesAnio(LocalDateTime fecha) {
	    Date date = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return formatoFecha.format(date);
	}
	
	public void limpiarFormulario() {
		textFieldTipoDonacion.setText("");
		textFieldFechaIng.setText("");
		textFieldDescripcion.setText("");
		textFieldCantElem.setText("");
		textFieldPeso.setText("");
		textFieldDimensiones.setText("");
		textFieldCantElem.setEnabled(true);
	    textFieldPeso.setEnabled(true);
	    textFieldDimensiones.setEnabled(true);
	}
	
	
	public void modificarDatos() {
		if (checkFormulario()) {
		
			// id y fecha no se ven afectados por el tipo de donación
			Integer id = (Integer) comboBoxDonaciones.getSelectedItem();
			
			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime nuevaFechaHoraIngreso = null;
			try {
		        nuevaFechaHoraIngreso = LocalDateTime.parse(textFieldFechaIng.getText(), formatoFecha);
		    } catch (DateTimeParseException e) {
		        JOptionPane.showMessageDialog(this, "La fecha debe ser ingresada en formato dd/MM/yyyy HH:mm:ss", "Modificar donación",
	                    JOptionPane.ERROR_MESSAGE);
		        return;
		    }
			
			//descripción tienen ambos aunque con distinto nombre, acá se pueden compartir
			String nuevaDescripcion = textFieldDescripcion.getText();
			if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía", "Modificar donación",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }
			
			String tipoDonacion = textFieldTipoDonacion.getText();
			
			if (tipoDonacion.equals("Alimento")) {
			    Integer nuevaCantElem = null;
			    try {
				    if (!textFieldCantElem.getText().isEmpty()) {
				        nuevaCantElem = Integer.parseInt(textFieldCantElem.getText());
				        if (nuevaCantElem < 0) {
	                        throw new NumberFormatException("La cantidad de elementos no puede ser negativa.");
	                    }
				    }
			    } catch (NumberFormatException e) {
			    	 JOptionPane.showMessageDialog(this, "La cantidad de elementos debe ser un número natural", "Modificar donación",
		                        JOptionPane.ERROR_MESSAGE);
		                return;
			    }
			    
				DtAlimento donacion = new DtAlimento(id, nuevaFechaHoraIngreso, nuevaDescripcion, nuevaCantElem);
				this.icon.modificarDonacion(donacion);
				JOptionPane.showMessageDialog(this, "Modificación realizada con éxito", "Modificar donación",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario(); 
				actualizarDonaciones(); //para que la combobox deje de mostrar el id luego de haber modificado
			    
			} else if (tipoDonacion.equals("Artículo")) {
				Float nuevoPeso = null;
				try {
				    if (!textFieldPeso.getText().isEmpty()) {
				        nuevoPeso = Float.parseFloat(textFieldPeso.getText());
				        if (nuevoPeso < 0) {
	                        throw new NumberFormatException("El peso no puede ser negativo.");
	                    }
	                }
				} catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "El peso debe ser un número decimal positivo", "Modificar donación",
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }
				
				String nuevasDimensiones = textFieldDimensiones.getText();
				if (nuevasDimensiones == null || nuevasDimensiones.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Las dimensiones no pueden estar vacías", "Modificar donación",
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }
				
				DtArticulo donacion = new DtArticulo(id, nuevaFechaHoraIngreso, nuevaDescripcion, nuevoPeso, nuevasDimensiones);
				this.icon.modificarDonacion(donacion);
				JOptionPane.showMessageDialog(this, "Modificación realizada con éxito", "Modificar donación",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				actualizarDonaciones(); //para que la combobox deje de mostrar el id luego de haber modificado
			}
		}
	
	}


	private boolean checkFormulario() {
	    if (this.comboBoxDonaciones.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(this, "Se debe seleccionar una donación", "Modificar donación",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	
}
