package presentacion;

import java.util.Arrays;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import datatypes.DtDonacion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ModificarDonacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
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
		
		actualizarDonaciones();
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 32, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblFechaIngresada = new JLabel("Fecha ingresada");
		lblFechaIngresada.setBounds(22, 89, 158, 15);
		getContentPane().add(lblFechaIngresada);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(22, 139, 129, 15);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(22, 183, 175, 15);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(22, 232, 70, 15);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(22, 273, 129, 15);
		getContentPane().add(lblDimensiones);
		
		textFieldFechaIng = new JTextField();
		textFieldFechaIng.setBounds(154, 87, 114, 19);
		getContentPane().add(textFieldFechaIng);
		textFieldFechaIng.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(341, 84, 117, 25);
		getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(341, 134, 117, 25);
		getContentPane().add(btnCancelar);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(123, 137, 114, 19);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldCantElem = new JTextField();
		textFieldCantElem.setBounds(199, 181, 114, 19);
		getContentPane().add(textFieldCantElem);
		textFieldCantElem.setColumns(10);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setBounds(75, 230, 114, 19);
		getContentPane().add(textFieldPeso);
		textFieldPeso.setColumns(10);
		
		textFieldDimensiones = new JTextField();
		textFieldDimensiones.setBounds(137, 271, 114, 19);
		getContentPane().add(textFieldDimensiones);
		textFieldDimensiones.setColumns(10);
		

	}
	
	public void actualizarDonaciones() {
	    Integer[] donacionesArray = icon.listarDonaciones();
	    DefaultComboBoxModel<Integer> listaDonaciones = new DefaultComboBoxModel<>(donacionesArray);
	    comboBoxDonaciones.setModel(listaDonaciones);
	}
	
	public void listarInfoDonacion() {
		if (comboBoxDonaciones.getSelectedItem() != null) {
			
			Integer id = (Integer) comboBoxDonaciones.getSelectedItem();
			DtDonacion donacion;
			donacion = this.icon.getDonacion(id);
	
			// como estoy trabajando con dts distintos (la donacion puede ser alimento o artículo)
			// tengo que resolver el tipo previo a llamar métodos ya que estos dependen del tipo
			// #####################################################
			
			//textFieldFechaIng.setText(donacion.getFechaIngresada());
			//textFieldDescripcion.setText(donacion.get);
			//textFieldCantElem
			//textFieldPeso
			//textFieldDimensiones
					
		}
	}
}
