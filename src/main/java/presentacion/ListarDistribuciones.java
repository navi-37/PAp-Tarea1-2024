package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
	private JComboBox comboBoxDistribuciones;

	
	public ListarDistribuciones(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 532, 389);
		getContentPane().setLayout(null);
		
		JRadioButton rdbtnTodas = new JRadioButton("Todas");
		rdbtnTodas.setBounds(8, 39, 83, 23);
		getContentPane().add(rdbtnTodas);
		
		JRadioButton rdbtnPendientes = new JRadioButton("Pendientes");
		rdbtnPendientes.setBounds(97, 39, 108, 23);
		getContentPane().add(rdbtnPendientes);
		
		JRadioButton rdbtnEnCamino = new JRadioButton("En camino");
		rdbtnEnCamino.setBounds(212, 39, 108, 23);
		getContentPane().add(rdbtnEnCamino);
		
		JRadioButton rdbtnEntregadas = new JRadioButton("Entregadas");
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
		limpiarFormulario();
		String strId = this.comboBoxDistribuciones.getSelectedItem().toString();
		 
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
	
	public void iniciarlizarComboBoxes() {
		DefaultComboBoxModel<Integer> listaDist = new DefaultComboBoxModel<Integer>(icon.listarDistribucionesPorID());
		comboBoxDistribuciones.setModel(listaDist);
	}	
}
