package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtDonacion;
import datatypes.EstadoDistribucion;
import excepciones.DistribucionNoEncontradaExc;
import interfaces.IControlador;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ModificarDistribucion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JRadioButton rdbtnTodas;
	private JRadioButton rdbtnPendientes;
	private JRadioButton rdbtnEnCamino;
	private JRadioButton rdbtnEntregadas;
	private JTextField txtDonacion;
	private JTextField txtBeneficiario;
	private JTextField txtFechaP;
	private JTextField txtFechaE;
	private JComboBox<Integer> comboBoxDistribuciones;
	private JComboBox<EstadoDistribucion> comboEstado;

	
	public ModificarDistribucion(IControlador icon) {
		this.icon = icon;
		//setBounds(100, 100, 800, 550);
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("MODIFICAR DISTRIBUCIÓN");
		setClosable(true);
		
		rdbtnTodas = new JRadioButton("Todas");
		rdbtnPendientes = new JRadioButton("Pendientes");
		rdbtnEnCamino = new JRadioButton("En camino");
		rdbtnEntregadas = new JRadioButton("Entregadas");
		
		//Todas
		rdbtnTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				rdbtnTodas.setSelected(true);
				EstadoDistribucion estado = null;
				DefaultComboBoxModel<Integer> listaDistTodas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, null));
				comboBoxDistribuciones.setModel(listaDistTodas);
			}
		});
		rdbtnTodas.setBounds(148, 18, 63, 23);
		getContentPane().add(rdbtnTodas);
		
		//Pendientes
		rdbtnPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				rdbtnPendientes.setSelected(true);
				EstadoDistribucion estado = EstadoDistribucion.PENDIENTE;
				DefaultComboBoxModel<Integer> listaDistPendientes = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, null)); 
				comboBoxDistribuciones.setModel(listaDistPendientes);
			}
		});
		rdbtnPendientes.setBounds(210, 18, 90, 23);
		getContentPane().add(rdbtnPendientes);
		
		//EnCamino
		rdbtnEnCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				rdbtnEnCamino.setSelected(true);
				EstadoDistribucion estado = EstadoDistribucion.EN_CAMINO;
				DefaultComboBoxModel<Integer> listaDistEnCamino = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, null)); 
				comboBoxDistribuciones.setModel(listaDistEnCamino);
				
			}
		});
		rdbtnEnCamino.setBounds(306, 18, 85, 23);
		getContentPane().add(rdbtnEnCamino);
		
		//Entregadas
		rdbtnEntregadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				rdbtnEntregadas.setSelected(true);
				EstadoDistribucion estado = EstadoDistribucion.ENTREGADO;
				DefaultComboBoxModel<Integer> listaDistEntregadas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado, null)); 
				comboBoxDistribuciones.setModel(listaDistEntregadas);
			}
		});
		rdbtnEntregadas.setBounds(396, 18, 90, 23);
		getContentPane().add(rdbtnEntregadas);
		
		comboBoxDistribuciones = new JComboBox<Integer>();
		comboBoxDistribuciones.setBounds(148, 61, 338, 19);
		getContentPane().add(comboBoxDistribuciones);
		
		comboEstado = new JComboBox<EstadoDistribucion>();
		comboEstado.addItem(null); 
		for (EstadoDistribucion estado : EstadoDistribucion.values()) {
		    comboEstado.addItem(estado);
		}
		comboEstado.setBounds(148, 343, 338, 19);
		getContentPane().add(comboEstado);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(148, 45, 98, 13);
		getContentPane().add(lblId);
		
		JLabel lblDonacin = new JLabel("Donación");
		lblDonacin.setBounds(148, 139, 98, 13);
		getContentPane().add(lblDonacin);
		
		JLabel lblBeneficiario = new JLabel("Beneficiario");
		lblBeneficiario.setBounds(148, 186, 98, 13);
		getContentPane().add(lblBeneficiario);
		
		JLabel lblFechaDePreparacin = new JLabel("Fecha de preparación");
		lblFechaDePreparacin.setBounds(148, 233, 184, 13);
		getContentPane().add(lblFechaDePreparacin);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega");
		lblFechaDeEntrega.setBounds(148, 280, 174, 13);
		getContentPane().add(lblFechaDeEntrega);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(148, 327, 70, 13);
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
		
		JButton btnVerInformacin = new JButton("› Ver información");
		btnVerInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesActionPerformed(e);
			}
		});
		btnVerInformacin.setBounds(148, 104, 338, 25);
		getContentPane().add(btnVerInformacin);
		
		JButton btnCancelar = new JButton("✘ Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(329, 385, 117, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnModificar = new JButton("✔ Modificar");
		btnModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        modificarDistribucionActionPerformed(e);
		    }
		});
		btnModificar.setBounds(188, 385, 117, 25);
		getContentPane().add(btnModificar);


	}
	
	public void listarDistribucionesActionPerformed(ActionEvent e) {
	    if (checkFormulario()) {
	        DtDistribucion dist;
	        String strId = this.comboBoxDistribuciones.getSelectedItem().toString();
	        int id = Integer.parseInt(strId);

	        dist = this.icon.getDistribucion(id);

	        txtBeneficiario.setText(dist.getBeneficiario().getEmail());
	        txtDonacion.setText(dist.getDonacion().getId().toString());
	        txtFechaE.setText(convertirFechaADiaMesAnio(dist.getFechaEntrega()));
	        txtFechaP.setText(convertirFechaADiaMesAnio(dist.getFechaPreparacion()));
	        comboEstado.setSelectedItem(dist.getEstado()); 
	        }
	}

	protected void modificarDistribucionActionPerformed(ActionEvent e) {
	    if (checkFormulario()) {
	        try {
	            String strId = this.comboBoxDistribuciones.getSelectedItem().toString();
	            int id = Integer.parseInt(strId);
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	            Date fechaP = formatoFecha.parse(txtFechaP.getText());
	            Date fechaE = formatoFecha.parse(txtFechaE.getText());
	            EstadoDistribucion estado = (EstadoDistribucion) comboEstado.getSelectedItem(); 

	            //DtDistribucion dist = this.icon.getDistribucion(id);
	            //DtBeneficiario beneficiario = dist.getBeneficiario();
	            //DtDonacion donacion = dist.getDonacion();
	            DtBeneficiario beneficiario = this.icon.getBeneficiario(txtBeneficiario.getText());
	            System.out.println(beneficiario.getEmail());
	            
	            DtDonacion donacion = this.icon.getDonacion(Integer.valueOf(txtDonacion.getText()));
	            System.out.println(donacion.getId());

	            DtDistribucion nuevaDistribucion = new DtDistribucion(id, fechaP, fechaE, estado, beneficiario, donacion);

	            this.icon.modificarDistribucion(nuevaDistribucion);

	            JOptionPane.showMessageDialog(this, "Distribución modificada con éxito.", "Modificar Distribución",
	                    JOptionPane.INFORMATION_MESSAGE);

	            limpiarFormulario();

	        } catch (DistribucionNoEncontradaExc ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Estado de distribución inválido.", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error al parsear la fecha. Por favor, usa el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void limpiarFormulario() {
		rdbtnTodas.setSelected(false);
		rdbtnPendientes.setSelected(false);
		rdbtnEnCamino.setSelected(false);
		rdbtnEntregadas.setSelected(false);
		comboBoxDistribuciones.setSelectedItem(null);
		txtBeneficiario.setText("");
		txtDonacion.setText("");
		txtFechaE.setText("");
		txtFechaP.setText("");
		comboEstado.setSelectedItem(null);
	}
	private String convertirFechaADiaMesAnio(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
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
	
}
