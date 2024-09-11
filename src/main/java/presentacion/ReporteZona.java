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

	
	
	public ReporteZona(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		setTitle("Hola");
		//setTitle("Modificar Usuario existente");
		setIconifiable(true);
		//setMaximizable(true);
		//setClosable(true);
		
		cBDiaFechaP = new JComboBox<String>();
		cBDiaFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaP.setBounds(47, 12, 57, 21);
		getContentPane().add(cBDiaFechaP);
		
		cBMesFechaP = new JComboBox<String>();
		cBMesFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaP.setBounds(105, 12, 59, 21);
		getContentPane().add(cBMesFechaP);
		
		cBAnoFechaP = new JComboBox<String>();
		cBAnoFechaP.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaP.setBounds(166, 12, 59, 21);
		getContentPane().add(cBAnoFechaP);
		
		cBDiaFechaE = new JComboBox<String>();
		cBDiaFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDiaFechaE.setBounds(47, 42, 57, 21);
		getContentPane().add(cBDiaFechaE);
		
		cBMesFechaE = new JComboBox<String>();
		cBMesFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cBMesFechaE.setBounds(105, 42, 59, 21);
		getContentPane().add(cBMesFechaE);
		
		cBAnoFechaE = new JComboBox<String>();
		cBAnoFechaE.setModel(new DefaultComboBoxModel<String>(new String[] {"2024", "2025"}));
		cBAnoFechaE.setBounds(166, 40, 59, 21);
		getContentPane().add(cBAnoFechaE);
		
		JButton btnAceptar = new JButton("asd");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteZonaAceptarActionPerformed(e);
			}
		});
		btnAceptar.setBounds(612, 441, 117, 25);
		getContentPane().add(btnAceptar);
		
		listReporte = new JTextPane();
		listReporte.setBounds(66, 84, 646, 332);
		getContentPane().add(listReporte);
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
