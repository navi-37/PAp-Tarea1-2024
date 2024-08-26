package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtDonacion;
import excepciones.DonacionRepetidaExc;
import interfaces.IControlador;

//import excepciones.SocioRepetidoExcepcion;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AltaDonacion extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControlador icon;
	private JTextField textid;
	private JTextField textDimensiones;
	private JTextField textPeso;
	private JTextField textCantElem;
	private JTextField textDescripcion;
	private JComboBox<String> comboBoxTipoDonacion;

	public AltaDonacion(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 547, 402);
		getContentPane().setLayout(null);
		
		textid = new JTextField();
		textid.setBounds(56, 33, 60, 20);
		getContentPane().add(textid);
		textid.setColumns(10);
		
		//JComboBox<String> 
		comboBoxTipoDonacion = new JComboBox<String>();
		comboBoxTipoDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBoxTipoDonacion.getSelectedItem();
				if (selectedItem.equals("Alimento")) {
					// despliego las que corresponden (setenabled(false) a las que no van
					// y setenabled(true) a las que si
					textCantElem.setEnabled(true);
					textPeso.setEnabled(false);
					textDimensiones.setEnabled(false);
				} else if (selectedItem.equals("Artículo")) {
					//al revé
					textCantElem.setEnabled(false);
					textPeso.setEnabled(true);
					textDimensiones.setEnabled(true);
				}
				
			}
		});
		
		comboBoxTipoDonacion.setModel(new DefaultComboBoxModel<String>(new String[] {"Alimento", "Artículo"}));
		comboBoxTipoDonacion.setToolTipText("");
		comboBoxTipoDonacion.setBounds(160, 80, 95, 22);
		getContentPane().add(comboBoxTipoDonacion);
		
		JLabel lblTipoDeDonacin = new JLabel("Tipo de donación");
		lblTipoDeDonacin.setBounds(34, 80, 134, 22);
		getContentPane().add(lblTipoDeDonacin);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(34, 35, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(34, 129, 111, 20);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(34, 178, 176, 15);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(34, 227, 70, 15);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(34, 270, 134, 15);
		getContentPane().add(lblDimensiones);
		
		textDimensiones = new JTextField();
		textDimensiones.setBounds(131, 267, 79, 19);
		getContentPane().add(textDimensiones);
		textDimensiones.setColumns(10);
		
		textPeso = new JTextField();
		textPeso.setBounds(71, 224, 60, 19);
		getContentPane().add(textPeso);
		textPeso.setColumns(10);
		
		textCantElem = new JTextField();
		textCantElem.setBounds(184, 175, 60, 19);
		getContentPane().add(textCantElem);
		textCantElem.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(128, 129, 228, 19);
		getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionAceptarActionPerformed(e);		
			}
		});
		btnAceptar.setBounds(128, 319, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionCancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(298, 319, 117, 25);
		getContentPane().add(btnCancelar);

	}
	
	protected void agregarDonacionAceptarActionPerformed(ActionEvent arg0) {
		Integer id = Integer.valueOf(this.textid.getText());
        //String fecha = this.textFechaIngreso.getText();
		LocalDateTime fecha = LocalDateTime.now();
        String selectedItem = (String) comboBoxTipoDonacion.getSelectedItem();
        DtDonacion dt = null;
		if (selectedItem.equals("Alimento")) {
			String descripcionProductos = this.textDescripcion.getText();
			Integer cantElementos = Integer.valueOf(this.textCantElem.getText());
			dt = new DtAlimento(id, fecha, descripcionProductos, cantElementos);
		} else if (selectedItem.equals("Artículo")) {
			String descripcion = this.textDescripcion.getText();
			float peso = Float.parseFloat(this.textPeso.getText());
			String dimensiones = this.textDimensiones.getText();
			dt = new DtArticulo(id, fecha, descripcion, peso, dimensiones);
		}
        //hay que usar la instancia de icon para que agregue el dt
        try {
        	this.icon.altaDonacion(dt);
        	JOptionPane.showMessageDialog(this, "Donación dada de alta con éxito", "Alta donación", JOptionPane.INFORMATION_MESSAGE);
        	} catch (DonacionRepetidaExc e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Alta donación", JOptionPane.ERROR_MESSAGE);
        	}
   
        limpiarFormulario();
        setVisible(false);
	} 
	protected void agregarDonacionCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
		
	} 
	
	private void limpiarFormulario() {
        textid.setText("");
        textDimensiones.setText("");
        textPeso.setText("");
        textDescripcion.setText("");
        textCantElem.setText("");
	}
}
