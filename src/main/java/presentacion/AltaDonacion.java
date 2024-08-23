package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

//import excepciones.SocioRepetidoExcepcion;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AltaDonacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textid;
	private JTextField textFechaIngreso;
	private JTextField textDimensiones;
	private JTextField textPeso;
	private JTextField textCantElem;
	private JTextField textDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDonacion frame = new AltaDonacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaDonacion() {
		setBounds(100, 100, 547, 402);
		getContentPane().setLayout(null);
		
		textid = new JTextField();
		textid.setBounds(56, 39, 86, 20);
		getContentPane().add(textid);
		textid.setColumns(10);
		
		textFechaIngreso = new JTextField();
		textFechaIngreso.setBounds(162, 76, 95, 20);
		getContentPane().add(textFechaIngreso);
		
		JComboBox<String> comboBoxTipoDonacion = new JComboBox<String>();
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
		comboBoxTipoDonacion.setBounds(162, 125, 95, 22);
		getContentPane().add(comboBoxTipoDonacion);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso");
		lblFechaDeIngreso.setBounds(34, 71, 131, 28);
		getContentPane().add(lblFechaDeIngreso);
		
		JLabel lblTipoDeDonacin = new JLabel("Tipo de donación");
		lblTipoDeDonacin.setBounds(34, 126, 134, 22);
		getContentPane().add(lblTipoDeDonacin);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(34, 41, 70, 15);
		getContentPane().add(lblId);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(34, 169, 111, 20);
		getContentPane().add(lblDescripcin);
		
		JLabel lblCantidadDeElementos = new JLabel("Cantidad de elementos");
		lblCantidadDeElementos.setBounds(34, 216, 189, 15);
		getContentPane().add(lblCantidadDeElementos);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(34, 243, 70, 15);
		getContentPane().add(lblPeso);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(34, 270, 134, 15);
		getContentPane().add(lblDimensiones);
		
		textDimensiones = new JTextField();
		textDimensiones.setBounds(143, 268, 114, 19);
		getContentPane().add(textDimensiones);
		textDimensiones.setColumns(10);
		
		textPeso = new JTextField();
		textPeso.setBounds(81, 241, 114, 19);
		getContentPane().add(textPeso);
		textPeso.setColumns(10);
		
		textCantElem = new JTextField();
		textCantElem.setBounds(214, 214, 114, 19);
		getContentPane().add(textCantElem);
		textCantElem.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(132, 170, 114, 19);
		getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionAceptarActionPerformed(e);		
			}
		});
		btnAceptar.setBounds(132, 311, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDonacionCancelarActionPerformed(e);
			}
		});
		//agregarDonacionInternalFrame.setVisible(false);
		btnCancelar.setBounds(302, 311, 117, 25);
		getContentPane().add(btnCancelar);

	}
	
	protected void agregarDonacionAceptarActionPerformed(ActionEvent arg0) {
		String id = this.textid.getText();
        String fecha = this.textDescripcion.getText();
        /*if (checkFormulario()) {
            try {
                this.icon.agregarSocio(ci,nombre);
                JOptionPane.showMessageDialog(this, "El Socio se ha creado con éxito", "Agregar Socio",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SocioRepetidoExcepcion e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Socio", JOptionPane.ERROR_MESSAGE);
            }
            limpiarFormulario();
            setVisible(false);
        }*/
        limpiarFormulario();
        setVisible(false);
	} 
	protected void agregarDonacionCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
		
	} 
	
	private void limpiarFormulario() {
        textid.setText("");
        textFechaIngreso.setText("");
        textDimensiones.setText("");
        textPeso.setText("");
        textDescripcion.setText("");
        textCantElem.setText("");
	}
}
