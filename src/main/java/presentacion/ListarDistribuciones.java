package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import datatypes.Barrio;
import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;
import interfaces.IControlador;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ListarDistribuciones extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JRadioButton rdbtnTodas;
	private JRadioButton rdbtnPendientes;
	private JRadioButton rdbtnEnCamino;
	private JRadioButton rdbtnEntregadas;
	private ButtonGroup grupo_de_rdbtn;
	private JTextField txtDonacion;
	private JTextField txtBeneficiario;
	private JTextField txtFechaP;
	private JTextField txtFechaE;
	private JTextField txtEstado;
	private JComboBox<Integer> comboBoxDistribuciones;
	private JComboBox<String> comboBoxZona;

	
	public ListarDistribuciones(IControlador icon) {
		this.icon = icon;
		//setBounds(100, 100, 800, 550);
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("LISTAR DISTRIBUCIONES");
		setClosable(true);
		
		rdbtnTodas = new JRadioButton("Todas");
		rdbtnPendientes = new JRadioButton("Pendientes");
		rdbtnEnCamino = new JRadioButton("En camino");
		rdbtnEntregadas = new JRadioButton("Entregadas");
		
		grupo_de_rdbtn = new ButtonGroup();
		grupo_de_rdbtn.add(rdbtnTodas);
		grupo_de_rdbtn.add(rdbtnPendientes);
		grupo_de_rdbtn.add(rdbtnEnCamino);
		grupo_de_rdbtn.add(rdbtnEntregadas);
		
		comboBoxDistribuciones = new JComboBox<Integer>();

		comboBoxZona = new JComboBox<String>();
		comboBoxZona.setModel(new DefaultComboBoxModel<String>(new String[] {"No filtrar por zona", "Ciudad Vieja", "Cordón", "Parque Rodó", "Centro", "Palermo"}));
		comboBoxZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDistribuciones();
			}
		});
		
		comboBoxZona.setBounds(148, 61, 338, 19);
		comboBoxZona.setSelectedItem(null);
		getContentPane().add(comboBoxZona);
		
		//Todas
		rdbtnTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDistribucion estado = null;
				Barrio zona = extraerZona();
				DefaultComboBoxModel<Integer> listaDistTodas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, zona));
				comboBoxDistribuciones.setModel(listaDistTodas);
			}
		});
		rdbtnTodas.setBounds(148, 18, 63, 23);
		getContentPane().add(rdbtnTodas);
		
		//Pendientes
		rdbtnPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDistribucion estado = EstadoDistribucion.PENDIENTE;
				Barrio zona = extraerZona();
				DefaultComboBoxModel<Integer> listaDistPendientes = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, zona)); 
				comboBoxDistribuciones.setModel(listaDistPendientes);
			}
		});
		rdbtnPendientes.setBounds(210, 18, 90, 23);
		getContentPane().add(rdbtnPendientes);
		
		//EnCamino
		rdbtnEnCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDistribucion estado = EstadoDistribucion.EN_CAMINO;
				Barrio zona = extraerZona();
				DefaultComboBoxModel<Integer> listaDistEnCamino = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, zona)); 
				comboBoxDistribuciones.setModel(listaDistEnCamino);
				
			}
		});
		rdbtnEnCamino.setBounds(306, 18, 85, 23);
		getContentPane().add(rdbtnEnCamino);
		
		//Entregadas
		rdbtnEntregadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDistribucion estado = EstadoDistribucion.ENTREGADO;
				Barrio zona = extraerZona();
				DefaultComboBoxModel<Integer> listaDistEntregadas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, zona)); 
				comboBoxDistribuciones.setModel(listaDistEntregadas);
			}
		});
		rdbtnEntregadas.setBounds(396, 18, 90, 23);
		getContentPane().add(rdbtnEntregadas);
		
		comboBoxDistribuciones = new JComboBox<Integer>();
		comboBoxDistribuciones.setBounds(148, 108, 150, 19);
		getContentPane().add(comboBoxDistribuciones);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(148, 92, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblZona = new JLabel("Zona");
		lblZona.setBounds(148, 45, 46, 15);
		getContentPane().add(lblZona);
		
		JLabel lblDonacin = new JLabel("Donación");
		lblDonacin.setBounds(148, 139, 70, 15);
		getContentPane().add(lblDonacin);
		
		JLabel lblBeneficiario = new JLabel("Beneficiario");
		lblBeneficiario.setBounds(148, 186, 112, 15);
		getContentPane().add(lblBeneficiario);
		
		JLabel lblFechaDePreparacin = new JLabel("Fecha de preparación");
		lblFechaDePreparacin.setBounds(148, 233, 184, 15);
		getContentPane().add(lblFechaDePreparacin);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega");
		lblFechaDeEntrega.setBounds(148, 280, 174, 15);
		getContentPane().add(lblFechaDeEntrega);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(148, 327, 132, 15);
		getContentPane().add(lblEstado);
		
		txtDonacion = new JTextField();
		txtDonacion.setBounds(148, 155, 338, 19);
		getContentPane().add(txtDonacion);
		txtDonacion.setColumns(10);
		
		txtBeneficiario = new JTextField();
		txtBeneficiario.setBounds(148, 202, 338, 19);
		getContentPane().add(txtBeneficiario);
		txtBeneficiario.setColumns(10);
		
		txtFechaP = new JTextField();
		txtFechaP.setBounds(148, 249, 338, 19);
		getContentPane().add(txtFechaP);
		txtFechaP.setColumns(10);
		
		txtFechaE = new JTextField();
		txtFechaE.setBounds(148, 296, 338, 19);
		getContentPane().add(txtFechaE);
		txtFechaE.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(148, 343, 338, 19);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnVerInformacin = new JButton("› Ver información");
		btnVerInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesActionPerformed(e);
			}
		});
		btnVerInformacin.setBounds(336, 108, 150, 19);
		getContentPane().add(btnVerInformacin);
		
		JButton btnCancelar = new JButton("✘ Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(258, 385, 117, 23);
		getContentPane().add(btnCancelar);

	}
	
	public void listarDistribucionesActionPerformed(ActionEvent e){
		if (checkFormulario()) {
			DtDistribucion dist;
			String strId = this.comboBoxDistribuciones.getSelectedItem().toString();
			int id = Integer.parseInt(strId);
			dist = this.icon.getDistribucion(id);
			txtBeneficiario.setText(dist.getBeneficiario().getEmail());
			txtDonacion.setText(dist.getDonacion().getId().toString());
			txtFechaE.setText(convertirFechaADiaMesAnio(dist.getFechaEntrega()));
			txtFechaP.setText(convertirFechaADiaMesAnio(dist.getFechaPreparacion()));
			txtEstado.setText(dist.getEstado().toString());
		}
	}
	
	private void actualizarDistribuciones() {
	    EstadoDistribucion estado = null;
	    if (rdbtnPendientes.isSelected()) {
	        estado = EstadoDistribucion.PENDIENTE;
	    } else if (rdbtnEnCamino.isSelected()) {
	        estado = EstadoDistribucion.EN_CAMINO;
	    } else if (rdbtnEntregadas.isSelected()) {
	        estado = EstadoDistribucion.ENTREGADO;
	    }

	    Barrio zona = extraerZona();
	    
	    DefaultComboBoxModel<Integer> listaDistribuciones = new DefaultComboBoxModel<>(icon.listarLasDistribucionesFiltradas(estado, zona));
	    comboBoxDistribuciones.setModel(listaDistribuciones);
	}
	
	private Barrio extraerZona() {
		Barrio zona = null;
		if (comboBoxZona.getSelectedItem() != null) {
		    if (comboBoxZona.getSelectedItem().equals("Ciudad Vieja")) {
		    	zona = Barrio.CIUDAD_VIEJA;
		    }else if (comboBoxZona.getSelectedItem().equals("Cordón")) {
		    	zona = Barrio.CORDON;
		    } else if (comboBoxZona.getSelectedItem().equals("Parque Rodó")) {
		    	zona = Barrio.PARQUE_RODO;
		    } else if (comboBoxZona.getSelectedItem().equals("Centro")) {
		    	zona = Barrio.CENTRO;
		    } else if (comboBoxZona.getSelectedItem().equals("Palermo")) {
		    	zona = Barrio.PALERMO;
		    } 
	    }
		return zona;
	}

	private void limpiarFormulario() {
		grupo_de_rdbtn.clearSelection();
		comboBoxDistribuciones.setSelectedItem(null);
		//comboBoxDistribuciones.setModel(new DefaultComboBoxModel<Integer>());
		comboBoxZona.setSelectedItem(null);
		txtBeneficiario.setText("");
		txtDonacion.setText("");
		txtFechaE.setText("");
		txtFechaP.setText("");
		txtEstado.setText("");
	}
	
	protected void cancelarActionPerformed(ActionEvent e) {
        limpiarFormulario();
        setVisible(false);
	} 
	
	private boolean checkFormulario() {
        if (this.comboBoxDistribuciones.getSelectedItem()==null) {
        	JOptionPane.showMessageDialog(this, "Se debe seleccionar una distribución", "Listar Distribuciones",
                    JOptionPane.ERROR_MESSAGE);
        	return false;
        } 
        return true;
    }
	
    public String convertirFechaADiaMesAnio(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
}