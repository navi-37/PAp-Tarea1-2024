package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtDonacion;
import excepciones.DonacionRepetidaExc;
import interfaces.IControlador;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
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
		setBounds(100, 100, 650, 450);
		getContentPane().setLayout(null);
		setTitle("ALTA DONACIÓN");
		setClosable(true);
		
		textid = new JTextField();
		textid.setBounds(148, 29, 338, 19);
		getContentPane().add(textid);
		textid.setColumns(10);
		
		comboBoxTipoDonacion = new JComboBox<String>();
		comboBoxTipoDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBoxTipoDonacion.getSelectedItem();
				if (selectedItem != null) { // nos fijamos que la combobox no esté vacía
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
			}
		});
		
		comboBoxTipoDonacion.setModel(new DefaultComboBoxModel<String>(new String[] {"Alimento", "Artículo"}));
		comboBoxTipoDonacion.setToolTipText("");
		comboBoxTipoDonacion.setBounds(148, 88, 338, 19);
		getContentPane().add(comboBoxTipoDonacion);
		
		JLabel lblTipoDeDonacin = new JLabel("Tipo de donación");
		lblTipoDeDonacin.setBounds(148, 69, 134, 13);
		getContentPane().add(lblTipoDeDonacin);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(148, 10, 70, 13);
		getContentPane().add(lblId);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(148, 128, 111, 13);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(148, 187, 176, 13);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(148, 246, 70, 13);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(148, 305, 134, 13);
		getContentPane().add(lblDimensiones);
		
		textDimensiones = new JTextField();
		textDimensiones.setBounds(148, 324, 338, 19);
		getContentPane().add(textDimensiones);
		textDimensiones.setColumns(10);
		
		textPeso = new JTextField();
		textPeso.setBounds(148, 265, 338, 19);
		getContentPane().add(textPeso);
		textPeso.setColumns(10);
		
		textCantElem = new JTextField();
		textCantElem.setBounds(148, 206, 338, 19);
		getContentPane().add(textCantElem);
		textCantElem.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(148, 147, 338, 19);
		getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JButton btnAceptar = new JButton("✔ Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionAceptarActionPerformed(e);		
			}
		});
		btnAceptar.setBounds(188, 373, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("✘ Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionCancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(329, 373, 117, 25);
		getContentPane().add(btnCancelar);
		
		limpiarFormulario();
	}
	
	protected void agregarDonacionAceptarActionPerformed(ActionEvent arg0) {
		
		if (checkFormulario()) {
			Integer id = Integer.valueOf(this.textid.getText());
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
	        	limpiarFormulario();
	            setVisible(false);
	        	} catch (DonacionRepetidaExc e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Alta donación", JOptionPane.ERROR_MESSAGE);
	        	}
		}
   
        
	} 
	protected void agregarDonacionCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);	
	} 
	
	private boolean checkFormulario() {
        String id = this.textid.getText();
        String dimensiones = this.textDimensiones.getText();
        String peso = this.textPeso.getText();
        String descripcion = this.textDescripcion.getText();
        String cantElem = this.textCantElem.getText();
        String tipoDon = (String) this.comboBoxTipoDonacion.getSelectedItem();
        
        if (id.isEmpty() || descripcion.isEmpty() || (this.comboBoxTipoDonacion.getSelectedItem() == null)) {
            JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (tipoDon == "Artículo") {
        	if (peso.isEmpty() || dimensiones.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (tipoDon == "Alimento") {
        	if (cantElem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

	        try {
	        	Integer.parseInt(cantElem);
	        	 
	        } catch (NumberFormatException e){
	        	JOptionPane.showMessageDialog(this, "La cantidad de elementos tiene que ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
		}
        return true;
    }
	
	private void limpiarFormulario() {
        textid.setText("");
        textDimensiones.setText("");
        textPeso.setText("");
        textDescripcion.setText("");
        textCantElem.setText("");
        comboBoxTipoDonacion.setSelectedItem(null);
	}
}
