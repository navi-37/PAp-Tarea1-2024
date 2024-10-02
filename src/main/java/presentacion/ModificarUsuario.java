package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsrModificar;
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import interfaces.IControlador;


public class ModificarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	
	private JTextField textFieldNombreUsuario;
	private JTextField textFieldCorreo;
	private JRadioButton rdbtnBeneficiario;
	private JRadioButton rdbtnRepartidor;
	private JComboBox<DtUsuario> comboBoxUsuarios;
	private JComboBox<EstadoBeneficiario> comboBoxEstado;
	

	public ModificarUsuario(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("MODIFICAR USUARIO");
		setClosable(true);
		
		JLabel lblTipoUsuario = new JLabel("Elegir tipo de Usuario");
		lblTipoUsuario.setBounds(148, 29, 158, 13);
		getContentPane().add(lblTipoUsuario);
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setBounds(148, 91, 338, 30);
		getContentPane().add(comboBoxUsuarios);
		
		
		// caso beneficiarios 
		
		rdbtnBeneficiario = new JRadioButton("Beneficiario");
		
		rdbtnBeneficiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxEstado.setSelectedItem(null);
				comboBoxEstado.setEnabled(true);
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
		rdbtnBeneficiario.setBounds(316, 25, 94, 20);
		getContentPane().add(rdbtnBeneficiario);
		
		
		// caso repartidor 
		
		rdbtnRepartidor = new JRadioButton("Repartidor");
		
		rdbtnRepartidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxEstado.setSelectedItem(null);
				comboBoxEstado.setEnabled(false);
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
		
		
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario");
		lblNombreUsuario.setBounds(148, 200, 200, 13);
		getContentPane().add(lblNombreUsuario);
		
		JLabel lblemailUsuario = new JLabel("Correo electrónico");
		lblemailUsuario.setBounds(148, 260, 201, 13);
		getContentPane().add(lblemailUsuario);
		
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setBounds(148, 219, 338, 21);
		getContentPane().add(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(148, 279, 338, 21);
		getContentPane().add(textFieldCorreo);
		
		JButton btnModificar = new JButton("✔ Modificar");
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
		btnMostrarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInfo(e); 
			}
		});
		btnMostrarInfo.setBounds(148, 148, 338, 21);
		getContentPane().add(btnMostrarInfo);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(148, 72, 100, 13);
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
		
		// comboBoxEstado = new JComboBox();
		comboBoxEstado = new JComboBox<EstadoBeneficiario>(EstadoBeneficiario.values());
		comboBoxEstado.setBounds(148, 322, 338, 24);
		getContentPane().add(comboBoxEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(74, 306, 70, 15);
		getContentPane().add(lblEstado);
		
		borrarSeleccion();
	}
	
	public void mostrarInfo(ActionEvent e) {
	    if (this.comboBoxUsuarios.getSelectedItem() != null) { // verificar que hay algo seleccionado
	    	if (rdbtnBeneficiario.isSelected()) {
	    		DtBeneficiario dtben = (DtBeneficiario) this.comboBoxUsuarios.getSelectedItem();
	    		textFieldNombreUsuario.setText(dtben.getNombre());
		        textFieldCorreo.setText(dtben.getEmail());
		        comboBoxEstado.setSelectedItem(dtben.getEstado());
		    } else if (rdbtnRepartidor.isSelected()) {
		    	DtRepartidor dtrep = (DtRepartidor) this.comboBoxUsuarios.getSelectedItem();	
		    	textFieldNombreUsuario.setText(dtrep.getNombre());
		        textFieldCorreo.setText(dtrep.getEmail());
		    }
	    }
	}
	
	public void borrarSeleccion() {
		rdbtnBeneficiario.setSelected(false);
		rdbtnRepartidor.setSelected(false);
		textFieldNombreUsuario.setText("");
		textFieldCorreo.setText("");
		comboBoxUsuarios.setSelectedItem(null);
		comboBoxEstado.setSelectedItem(null);
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
	    	DtUsrModificar dtu = (DtUsrModificar) this.comboBoxUsuarios.getSelectedItem();
	    	String email = textFieldCorreo.getText();
	        String nombre = textFieldNombreUsuario.getText();
	        EstadoBeneficiario estado = (EstadoBeneficiario) comboBoxEstado.getSelectedItem();
	        
	    	/*if (rdbtnBeneficiario.isSelected()) {
	    		DtBeneficiario dtben = (DtBeneficiario) this.comboBoxUsuarios.getSelectedItem();
	    		if ((!dtben.getEmail().equals(email)) || (!dtben.getNombre().equals(nombre)) || (!dtben.getEstado().equals(estado))) {
		    		textFieldNombreUsuario.setText(dtben.getNombre());
			        textFieldCorreo.setText(dtben.getEmail());
			        comboBoxEstado.setSelectedItem(dtben.getEstado());
	    		}
		    } else if (rdbtnRepartidor.isSelected()) {
		    	DtRepartidor dtrep = (DtRepartidor) this.comboBoxUsuarios.getSelectedItem();
		    	if ((!dtrep.getEmail().equals(email)) || (!dtrep.getNombre().equals(nombre))) {
			    	textFieldNombreUsuario.setText(dtrep.getNombre());
			        textFieldCorreo.setText(dtrep.getEmail());
		    	}
		    }
		    */
	        
	        
	        
	         //evaluar si cambió algo
		        this.icon.modificarUsuario(dtu, email, nombre, estado);
		        actualizarComboBoxUsuarios();
		        
		        JOptionPane.showMessageDialog(this, "Los datos del usuario han sido modificados con éxito.", 
		        		"Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);	
	        } else {
	        	JOptionPane.showMessageDialog(this, "No ha ingresado nuevos datos para el usuario seleccionado", "Sin Modificación", JOptionPane.INFORMATION_MESSAGE);
	        }             
	    }
	}

