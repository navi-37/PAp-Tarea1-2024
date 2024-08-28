package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;
import interfaces.IControlador;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarDistribuciones extends JInternalFrame {

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
	private JTextField txtEstado;
	private JComboBox<Integer> comboBoxDistribuciones;

	
	public ListarDistribuciones(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 532, 389);
		getContentPane().setLayout(null);
		
		rdbtnTodas = new JRadioButton("Todas");
		rdbtnPendientes = new JRadioButton("Pendientes");
		rdbtnEnCamino = new JRadioButton("En camino");
		rdbtnEntregadas = new JRadioButton("Entregadas");
		
		//Todas
		rdbtnTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnPendientes.setSelected(false);
				rdbtnEnCamino.setSelected(false);
				rdbtnEntregadas.setSelected(false);
				rdbtnTodas.setSelected(true);
				EstadoDistribucion estado = null;
				DefaultComboBoxModel<Integer> listaDistTodas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado));
				comboBoxDistribuciones.setModel(listaDistTodas);
			}
		});
		rdbtnTodas.setBounds(8, 39, 83, 23);
		getContentPane().add(rdbtnTodas);
		
		//Pendientes
		rdbtnPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEnCamino.setSelected(false);
				rdbtnEntregadas.setSelected(false);
				rdbtnTodas.setSelected(false);
				EstadoDistribucion estado = EstadoDistribucion.PENDIENTE;
				DefaultComboBoxModel<Integer> listaDistPendientes = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado)); 
				comboBoxDistribuciones.setModel(listaDistPendientes);
			}
		});
		rdbtnPendientes.setBounds(97, 39, 108, 23);
		getContentPane().add(rdbtnPendientes);
		
		//EnCamino
		rdbtnEnCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTodas.setSelected(false);
				rdbtnPendientes.setSelected(false);
				rdbtnEntregadas.setSelected(false);
				EstadoDistribucion estado = EstadoDistribucion.EN_CAMINO;
				DefaultComboBoxModel<Integer> listaDistEnCamino = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado)); 
				comboBoxDistribuciones.setModel(listaDistEnCamino);
				
			}
		});
		rdbtnEnCamino.setBounds(212, 39, 108, 23);
		getContentPane().add(rdbtnEnCamino);
		
		//Entregadas
		rdbtnEntregadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTodas.setSelected(false);
				rdbtnPendientes.setSelected(false);
				rdbtnEnCamino.setSelected(false);
				EstadoDistribucion estado = EstadoDistribucion.ENTREGADO;
				DefaultComboBoxModel<Integer> listaDistEntregadas = new DefaultComboBoxModel<Integer>(icon.listarLasDistribucionesFiltradas(estado)); 
				comboBoxDistribuciones.setModel(listaDistEntregadas);
			}
		});
		rdbtnEntregadas.setBounds(316, 39, 149, 23);
		getContentPane().add(rdbtnEntregadas);
		
		comboBoxDistribuciones = new JComboBox<Integer>();
		comboBoxDistribuciones.setBounds(64, 118, 108, 24);
		getContentPane().add(comboBoxDistribuciones);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(21, 123, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblDonacin = new JLabel("Donación");
		lblDonacin.setBounds(21, 160, 70, 15);
		getContentPane().add(lblDonacin);
		
		JLabel lblBeneficiario = new JLabel("Beneficiario");
		lblBeneficiario.setBounds(21, 204, 112, 15);
		getContentPane().add(lblBeneficiario);
		
		JLabel lblFechaDePreparacin = new JLabel("Fecha de preparación");
		lblFechaDePreparacin.setBounds(21, 245, 184, 15);
		getContentPane().add(lblFechaDePreparacin);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega");
		lblFechaDeEntrega.setBounds(31, 283, 174, 15);
		getContentPane().add(lblFechaDeEntrega);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(21, 310, 132, 15);
		getContentPane().add(lblEstado);
		
		txtDonacion = new JTextField();
		txtDonacion.setBounds(109, 158, 114, 19);
		getContentPane().add(txtDonacion);
		txtDonacion.setColumns(10);
		
		txtBeneficiario = new JTextField();
		txtBeneficiario.setBounds(125, 202, 114, 19);
		getContentPane().add(txtBeneficiario);
		txtBeneficiario.setColumns(10);
		
		txtFechaP = new JTextField();
		txtFechaP.setBounds(206, 243, 114, 19);
		getContentPane().add(txtFechaP);
		txtFechaP.setColumns(10);
		
		txtFechaE = new JTextField();
		txtFechaE.setBounds(184, 281, 114, 19);
		getContentPane().add(txtFechaE);
		txtFechaE.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(109, 312, 114, 19);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblListarDistribuciones = new JLabel("Listar Distribuciones");
		lblListarDistribuciones.setBounds(8, 16, 176, 15);
		getContentPane().add(lblListarDistribuciones);
		
		JButton btnVerInformacin = new JButton("Ver información");
		btnVerInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesActionPerformed(e);
			}
		});
		btnVerInformacin.setBounds(329, 101, 167, 25);
		getContentPane().add(btnVerInformacin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(329, 155, 117, 25);
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
			txtFechaE.setText(dist.getFechaEntrega().toString());
			txtFechaP.setText(dist.getFechaPreparacion().toString());
			txtEstado.setText(dist.getEstado().toString());
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
	
}
