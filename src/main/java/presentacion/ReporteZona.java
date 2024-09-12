package presentacion;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;

import datatypes.DtReporte;
import interfaces.IControlador;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;


public class ReporteZona extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JComboBox<String> cBDiaFechaP;
	private JComboBox<String> cBDiaFechaE;
	private JComboBox<String> cBMesFechaP;
	private JComboBox<String> cBMesFechaE;
	private JComboBox<String> cBAnoFechaP;
	private JComboBox<String> cBAnoFechaE;
	private JTextPane listReporte;
	private JScrollPane scrollPane;

	
	
	public ReporteZona(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		setTitle("ZONAS CON MÁS DISTRIBUCIONES");
		setClosable(true);
		
		cBDiaFechaP = new JComboBox<String>();
		cBDiaFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaP.setBounds(185, 23, 57, 21);
		getContentPane().add(cBDiaFechaP);
		
		cBMesFechaP = new JComboBox<String>();
		cBMesFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaP.setBounds(243, 23, 59, 21);
		getContentPane().add(cBMesFechaP);
		
		cBAnoFechaP = new JComboBox<String>();
		cBAnoFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaP.setBounds(304, 23, 59, 21);
		getContentPane().add(cBAnoFechaP);
		
		cBDiaFechaE = new JComboBox<String>();
		cBDiaFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaE.setBounds(185, 53, 57, 21);
		getContentPane().add(cBDiaFechaE);
		
		cBMesFechaE = new JComboBox<String>();
		cBMesFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaE.setBounds(243, 53, 59, 21);
		getContentPane().add(cBMesFechaE);
		
		cBAnoFechaE = new JComboBox<String>();
		cBAnoFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaE.setBounds(304, 53, 59, 21);
		getContentPane().add(cBAnoFechaE);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteZonaAceptarActionPerformed(e);
			}
		});
		btnAceptar.setBounds(612, 441, 117, 25);
		getContentPane().add(btnAceptar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 84, 646, 332);
		getContentPane().add(scrollPane);
		
		listReporte = new JTextPane();
		listReporte.setBounds(66, 84, 646, 332);
		//getContentPane().add(listReporte);
		scrollPane.setViewportView(listReporte);
		
		JLabel lblNewLabel = new JLabel("Fecha inicial");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(66, 27, 109, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha final");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(66, 57, 134, 13);
		getContentPane().add(lblNewLabel_1);
		
		
	}
	
	protected void reporteZonaAceptarActionPerformed(ActionEvent arg0) {
	
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
		
		ArrayList<DtReporte> reportez = new ArrayList<DtReporte>();
		reportez = icon.reporte(fechaP, fechaE);
		listReporte.setText("");
	    String datos = "REPORTE DE ZONAS CON MÁS DISTRIBUCIONES";
	    for (DtReporte reporte : reportez) {
	    	datos = datos + "\n\n" + reporte.toString();
	    }
	    listReporte.setText(datos);
	    
	    
		
	}	
}
