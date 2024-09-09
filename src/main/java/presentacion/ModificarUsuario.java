package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.EstadoDistribucion;
import interfaces.IControlador;
import logica.Usuario; 

public class ModificarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	
	private JTextField textFieldNombreUsuario;
	private JTextField textFieldCorreo;
	private JRadioButton rdbtnBeneficiario;
	private JRadioButton rdbtnRepartidor;
	private JComboBox<DtUsuario> comboBoxUsuarios;
	private JButton btnModificar;
	

	public ModificarUsuario(IControlador icon) {
		this.icon = icon;
		setTitle("Modificar Usuario existente");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 640, 401);
		getContentPane().setLayout(null);
		
		JLabel lblTipoUsuario = new JLabel("Elegir tipo de Usuario a modificar");
		lblTipoUsuario.setForeground(new Color(128, 128, 128));
		lblTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoUsuario.setBounds(20, 12, 265, 32);
		getContentPane().add(lblTipoUsuario);
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setBounds(30, 54, 315, 32);
		getContentPane().add(comboBoxUsuarios);
		
		// caso beneficiarios 
		
		rdbtnBeneficiario = new JRadioButton("Beneficiario");
		rdbtnBeneficiario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdbtnBeneficiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnRepartidor.setSelected(false);
				textFieldNombreUsuario.setText("");
				textFieldCorreo.setText("");  
				rdbtnBeneficiario.setSelected(true);
				ComboBoxModel<DtBeneficiario> listaBeneficiarios = new DefaultComboBoxModel<DtBeneficiario>();
				List<DtBeneficiario> beneficiarios = icon.ListaBeneficiarios();
				DefaultComboBoxModel<DtUsuario> listaUsuarios = new DefaultComboBoxModel<>();
				
				for (DtBeneficiario beneficiario : beneficiarios) {
			            listaUsuarios.addElement((DtUsuario) beneficiario); 
			        }
				comboBoxUsuarios.setModel(listaUsuarios);
			}
		});
		rdbtnBeneficiario.setBounds(310, 18, 103, 21);
		getContentPane().add(rdbtnBeneficiario);
		
		
		// caso repartidor 
		
		rdbtnRepartidor = new JRadioButton("Repartidor");
		rdbtnRepartidor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdbtnRepartidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnBeneficiario.setSelected(false);
				textFieldNombreUsuario.setText("");
				textFieldCorreo.setText(""); 
				rdbtnRepartidor.setSelected(true);
				ComboBoxModel<DtRepartidor> listaRepartidores = new DefaultComboBoxModel<DtRepartidor>();
				List<DtRepartidor> repartidores = icon.ListaRepartidores();
				DefaultComboBoxModel<DtUsuario> listaUsuarios = new DefaultComboBoxModel<>();
				
				for (DtRepartidor repartidor : repartidores) {
			            listaUsuarios.addElement((DtUsuario) repartidor); 
			        }
				comboBoxUsuarios.setModel(listaUsuarios);
			}
		});
		
		rdbtnRepartidor.setBounds(475, 18, 103, 21);
		getContentPane().add(rdbtnRepartidor);
		///////////////////////////////////////////////////////////////////////////////////////
		
		
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreUsuario.setBounds(48, 161, 131, 32);
		getContentPane().add(lblNombreUsuario);
		
		JLabel lblemailUsuario = new JLabel("Correo electrónico:");
		lblemailUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblemailUsuario.setBounds(47, 203, 201, 32);
		getContentPane().add(lblemailUsuario);
		
		JLabel lblDatosUsuario = new JLabel("Datos del usuario");
		lblDatosUsuario.setForeground(new Color(128, 128, 128));
		lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosUsuario.setBounds(20, 125, 240, 26);
		getContentPane().add(lblDatosUsuario);
		
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setBounds(189, 170, 325, 21);
		getContentPane().add(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(189, 212, 325, 21);
		getContentPane().add(textFieldCorreo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModificar.setBounds(281, 259, 115, 32);
		getContentPane().add(btnModificar);
		
		// mostrar info del usuario seleccionado
		JButton btnMostrarInfo = new JButton("mostrar información");
		btnMostrarInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMostrarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInfo(e); 
			}
		});
		btnMostrarInfo.setBounds(200, 130, 145, 21);
		getContentPane().add(btnMostrarInfo);
		
	}
	
	public void mostrarInfo(ActionEvent e) {
		if (this.comboBoxUsuarios.getSelectedItem() != null) { // verificar que hay algo seleccionado
			DtUsuario dtu = (DtUsuario) this.comboBoxUsuarios.getSelectedItem();
		
			textFieldNombreUsuario.setText(dtu.getNombre());
			textFieldNombreUsuario.setText(dtu.getEmail());
		}
	}
	
	public void borrarSeleccion() {
		
		rdbtnBeneficiario.setSelected(false);
		rdbtnRepartidor.setSelected(false);
		textFieldNombreUsuario.setText("");
		textFieldCorreo.setText("");
		comboBoxUsuarios.setSelectedItem(null);
	}
	
	public void mostrarInfoUsuario(ActionEvent e) {}
}
