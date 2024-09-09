package presentacion;



import javax.swing.JInternalFrame;

import interfaces.IControlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;
import logica.Beneficiario;
import logica.Donacion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AltaDistribucion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private IControlador icon;
	private JTextField textDonacion;
	private JTextField textBeneficiario;
	private JTextField textId;
	private JComboBox<String> cBDiaFechaP;
	private JComboBox<String> cBDiaFechaE;
	private JComboBox<String> cBMesFechaP;
	private JComboBox<String> cBMesFechaE;
	private JComboBox<String> cBAnoFechaP;
	private JComboBox<String> cBAnoFechaE;
	
	

	public AltaDistribucion(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblDonacion = new JLabel("Donación (ID)");
		lblDonacion.setBounds(33, 74, 108, 13);
		getContentPane().add(lblDonacion);
		
		JLabel lblBeneficiario = new JLabel("Beneficiario (e-mail)");
		lblBeneficiario.setBounds(33, 108, 135, 13);
		getContentPane().add(lblBeneficiario);
		
		JLabel lblFechaPrep = new JLabel("Fecha de preparación");
		lblFechaPrep.setBounds(33, 143, 149, 13);
		getContentPane().add(lblFechaPrep);
		
		JLabel lblFechaEntrega = new JLabel("Fecha de entrega");
		lblFechaEntrega.setBounds(33, 173, 149, 13);
		getContentPane().add(lblFechaEntrega);
		
		textDonacion = new JTextField();
		textDonacion.setBounds(142, 71, 96, 19);
		getContentPane().add(textDonacion);
		textDonacion.setColumns(10);
		
		textBeneficiario = new JTextField();
		textBeneficiario.setBounds(172, 105, 96, 19);
		getContentPane().add(textBeneficiario);
		textBeneficiario.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDistribucionAceptarActionPerformed(e);
			}
		});
		btnAceptar.setBounds(105, 217, 85, 21);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDistribucionCancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(237, 217, 85, 21);
		getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Alta de Distribución");
		lblNewLabel.setBounds(33, 10, 205, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(33, 33, 57, 13);
		getContentPane().add(lblid);
		
		textId = new JTextField();
		textId.setBounds(94, 33, 96, 19);
		getContentPane().add(textId);
		textId.setColumns(10);
		
		cBDiaFechaP = new JComboBox<String>();
		cBDiaFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaP.setBounds(193, 135, 57, 21);
		getContentPane().add(cBDiaFechaP);
		
		cBMesFechaP = new JComboBox<String>();
		cBMesFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaP.setBounds(251, 135, 59, 21);
		getContentPane().add(cBMesFechaP);
		
		cBAnoFechaP = new JComboBox<String>();
		cBAnoFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaP.setBounds(312, 135, 59, 21);
		getContentPane().add(cBAnoFechaP);
		
		cBDiaFechaE = new JComboBox<String>();
		cBDiaFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaE.setBounds(193, 165, 57, 21);
		getContentPane().add(cBDiaFechaE);
		
		cBMesFechaE = new JComboBox<String>();
		cBMesFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaE.setBounds(251, 165, 59, 21);
		getContentPane().add(cBMesFechaE);
		
		cBAnoFechaE = new JComboBox<String>();
		cBAnoFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaE.setBounds(312, 165, 59, 21);
		getContentPane().add(cBAnoFechaE);
		
		limpiarFormulario();
	}
	protected void agregarDistribucionAceptarActionPerformed(ActionEvent arg0) {
		int id_distribucion = Integer.parseInt(this.textId.getText());
		Integer id_donacion = Integer.valueOf(this.textDonacion.getText());
		String email_beneficiario = this.textBeneficiario.getText();
		
		Donacion donacion = new Donacion(id_donacion, null);
		Beneficiario beneficiario = new Beneficiario(null, email_beneficiario, null, null, null, null);

		// transformar en funcion esto pa reusar en altausuario
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String anoFechaP = (String) cBAnoFechaP.getSelectedItem();
		String mesFechaP = (String) cBMesFechaP.getSelectedItem();
		String diaFechaP = (String) cBDiaFechaP.getSelectedItem();
		String anoFechaE = (String) cBAnoFechaE.getSelectedItem();
		String mesFechaE = (String) cBMesFechaE.getSelectedItem();
		String diaFechaE = (String) cBDiaFechaE.getSelectedItem();
		String string_fechaP = anoFechaP+"-"+mesFechaP+"-"+diaFechaP;
		String string_fechaE = anoFechaE+"-"+mesFechaE+"-"+diaFechaE;
		Date fechaP = null;
		Date fechaE = null;
		try {
			fechaP = formatter.parse(string_fechaP);
			fechaE = formatter.parse(string_fechaE);
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		EstadoDistribucion estado = EstadoDistribucion.PENDIENTE;
		DtDistribucion dt = null;
		dt = new DtDistribucion(id_distribucion, fechaP, fechaE, estado, beneficiario, donacion);
		try {
        	this.icon.altaDistribucion(dt);
        		JOptionPane.showMessageDialog(this, "Distribución dada de alta con éxito", "Alta distribución", JOptionPane.INFORMATION_MESSAGE);
        	} catch (DistribucionRepetidaExc e) {
        		JOptionPane.showMessageDialog(this, e.getMessage(), "Alta distribución", JOptionPane.ERROR_MESSAGE);
        	} catch (UsuarioNOBeneficiarioExc a) {
        		JOptionPane.showMessageDialog(this, a.getMessage(), "Alta distribución", JOptionPane.ERROR_MESSAGE);
        	} catch (DonacionNoExisteExc b) {
        		JOptionPane.showMessageDialog(this, b.getMessage(), "Alta distribución", JOptionPane.ERROR_MESSAGE);
        	}
        limpiarFormulario();
        setVisible(false);
	}
	
	protected void agregarDistribucionCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
	} 
	
	private void limpiarFormulario() {
		textDonacion.setText("");
		textBeneficiario.setText("");
		textId.setText("");
		cBDiaFechaE.setSelectedItem(null);
		cBMesFechaE.setSelectedItem(null);
		cBAnoFechaE.setSelectedItem(null);
		cBDiaFechaP.setSelectedItem(null);
		cBMesFechaP.setSelectedItem(null);
		cBAnoFechaP.setSelectedItem(null);

	}
}
