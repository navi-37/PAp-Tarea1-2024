package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import interfaces.IControlador;


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
		//setBounds(100, 100, 800, 550);
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("MODIFICAR USUARIO");
		setClosable(true);
		
		JLabel lblTipoUsuario = new JLabel("Elegir tipo de Usuario");
		//lblTipoUsuario.setForeground(new Color(128, 128, 128));
		//lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoUsuario.setBounds(148, 52, 158, 13);
		getContentPane().add(lblTipoUsuario);
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setBounds(148, 114, 338, 30);
		getContentPane().add(comboBoxUsuarios);
		
		// caso beneficiarios 
		
		rdbtnBeneficiario = new JRadioButton("Beneficiario");
		//rdbtnBeneficiario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		rdbtnBeneficiario.setBounds(316, 48, 94, 20);
		getContentPane().add(rdbtnBeneficiario);
		
		
		// caso repartidor 
		
		rdbtnRepartidor = new JRadioButton("Repartidor");
		//rdbtnRepartidor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		
		rdbtnRepartidor.setBounds(413, 48, 100, 20);
		getContentPane().add(rdbtnRepartidor);
		///////////////////////////////////////////////////////////////////////////////////////
		
		
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario:");
		//lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreUsuario.setBounds(148, 223, 131, 13);
		getContentPane().add(lblNombreUsuario);
		
		JLabel lblemailUsuario = new JLabel("Correo electrónico:");
		//lblemailUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblemailUsuario.setBounds(148, 283, 201, 13);
		getContentPane().add(lblemailUsuario);
		
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setBounds(148, 242, 338, 21);
		getContentPane().add(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(148, 302, 338, 21);
		getContentPane().add(textFieldCorreo);
		
		JButton btnModificar = new JButton("✔ Modificar");
		//btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnModificar.setBounds(188, 364, 115, 25);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatosUsuario(e);
				borrarSeleccion();
			}
		});
		getContentPane().add(btnModificar);
		
		// mostrar info del usuario seleccionado
		JButton btnMostrarInfo = new JButton("› Ver información");
		//btnMostrarInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMostrarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInfo(e); 
			}
		});
		btnMostrarInfo.setBounds(148, 171, 338, 21);
		getContentPane().add(btnMostrarInfo);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(148, 95, 45, 13);
		getContentPane().add(lblNewLabel);
		
		
		JButton btnCancelar = new JButton("✘ Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarSeleccion();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(329, 364, 117, 25);
		getContentPane().add(btnCancelar);
	}
	
	public void mostrarInfo(ActionEvent e) {
	    if (this.comboBoxUsuarios.getSelectedItem() != null) { // verificar que hay algo seleccionado
	        DtUsuario dtu = (DtUsuario) this.comboBoxUsuarios.getSelectedItem();
	        
	        // Actualizar los campos de texto con la información del usuario seleccionado
	        textFieldNombreUsuario.setText(dtu.getNombre());
	        textFieldCorreo.setText(dtu.getEmail());
	    }
	}
	
	public void borrarSeleccion() {
		
		rdbtnBeneficiario.setSelected(false);
		rdbtnRepartidor.setSelected(false);
		textFieldNombreUsuario.setText("");
		textFieldCorreo.setText("");
		comboBoxUsuarios.setSelectedItem(null);
	}
	
	private void actualizarComboBoxUsuarios() {
	    if (rdbtnBeneficiario.isSelected()) {
	        // Actualizo combobox para beneficiarios
	        List<DtBeneficiario> beneficiarios = icon.ListaBeneficiarios();
	        DefaultComboBoxModel<DtUsuario> listaUsuarios = new DefaultComboBoxModel<>();
	        
	        for (DtBeneficiario beneficiario : beneficiarios) {
	            listaUsuarios.addElement((DtUsuario) beneficiario); 
	        }
	        comboBoxUsuarios.setModel(listaUsuarios);
	        
	    } else if (rdbtnRepartidor.isSelected()) {
	    	// para repartidores
	        List<DtRepartidor> repartidores = icon.ListaRepartidores();
	        DefaultComboBoxModel<DtUsuario> listaUsuarios = new DefaultComboBoxModel<>();
	        
	        for (DtRepartidor repartidor : repartidores) {
	            listaUsuarios.addElement((DtUsuario) repartidor); 
	        }
	        comboBoxUsuarios.setModel(listaUsuarios);
	    }
	}
	

	public void modificarDatosUsuario(ActionEvent e) {
	    if (this.comboBoxUsuarios.getSelectedItem() != null) {
	        DtUsuario dtu = (DtUsuario) this.comboBoxUsuarios.getSelectedItem();
	        
	        String email = textFieldCorreo.getText();
	        String nombre = textFieldNombreUsuario.getText();
	        if ((!dtu.getEmail().equals(email)) || (!dtu.getNombre().equals(nombre))) { //evaluar si cambió algo
		        this.icon.modificarUsuario(dtu, email, nombre); // modificar usuario
	
		        // Actualizar el ComboBox
		        actualizarComboBoxUsuarios();
		        
		        // checkear si el email es el mismo. sino, busca el nuevo email
		        DtUsuario usuarioActualizado = icon.getUsuario(email);
		        if (usuarioActualizado != null) {
		            this.comboBoxUsuarios.setSelectedItem(usuarioActualizado);
		        }
		        
		        JOptionPane.showMessageDialog(this, "Los datos del usuario han sido modificados con éxito.", 
		        		"Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);	
	        } else {
	        	JOptionPane.showMessageDialog(this, "No ha ingresado nuevos datos para el usuario seleccionado", "Sin Modificación", JOptionPane.INFORMATION_MESSAGE);
	        }             
	    }
	}
}
