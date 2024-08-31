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
		setBounds(100, 100, 580, 410);
		getContentPane().setLayout(null);
		
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
		
		comboBoxZona.setBounds(64, 85, 109, 22);
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
		rdbtnTodas.setBounds(21, 39, 83, 23);
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
		rdbtnPendientes.setBounds(101, 39, 108, 23);
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
		rdbtnEnCamino.setBounds(210, 39, 92, 23);
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
		rdbtnEntregadas.setBounds(310, 39, 149, 23);
		getContentPane().add(rdbtnEntregadas);
		
		comboBoxDistribuciones = new JComboBox<Integer>();
		comboBoxDistribuciones.setBounds(64, 125, 108, 24);
		getContentPane().add(comboBoxDistribuciones);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(21, 130, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblZona = new JLabel("Zona");
		lblZona.setBounds(21, 90, 46, 15);
		getContentPane().add(lblZona);
		
		JLabel lblDonacin = new JLabel("Donación");
		lblDonacin.setBounds(21, 176, 70, 15);
		getContentPane().add(lblDonacin);
		
		JLabel lblBeneficiario = new JLabel("Beneficiario");
		lblBeneficiario.setBounds(21, 216, 112, 15);
		getContentPane().add(lblBeneficiario);
		
		JLabel lblFechaDePreparacin = new JLabel("Fecha de preparación");
		lblFechaDePreparacin.setBounds(21, 256, 184, 15);
		getContentPane().add(lblFechaDePreparacin);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega");
		lblFechaDeEntrega.setBounds(21, 296, 174, 15);
		getContentPane().add(lblFechaDeEntrega);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(21, 336, 132, 15);
		getContentPane().add(lblEstado);
		
		txtDonacion = new JTextField();
		txtDonacion.setBounds(170, 176, 114, 19);
		getContentPane().add(txtDonacion);
		txtDonacion.setColumns(10);
		
		txtBeneficiario = new JTextField();
		txtBeneficiario.setBounds(170, 216, 114, 19);
		getContentPane().add(txtBeneficiario);
		txtBeneficiario.setColumns(10);
		
		txtFechaP = new JTextField();
		txtFechaP.setBounds(170, 256, 114, 19);
		getContentPane().add(txtFechaP);
		txtFechaP.setColumns(10);
		
		txtFechaE = new JTextField();
		txtFechaE.setBounds(170, 296, 114, 19);
		getContentPane().add(txtFechaE);
		txtFechaE.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(170, 336, 114, 19);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblListarDistribuciones = new JLabel("Listar Distribuciones");
		lblListarDistribuciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListarDistribuciones.setBounds(21, 11, 176, 15);
		getContentPane().add(lblListarDistribuciones);
		
		JButton btnVerInformacin = new JButton("Ver información");
		btnVerInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesActionPerformed(e);
			}
		});
		btnVerInformacin.setBounds(360, 85, 167, 25);
		getContentPane().add(btnVerInformacin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(360, 139, 117, 25);
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