package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaDonacion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField txtTipoDeDonacin;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText("ID");
		txtpnId.setBounds(37, 30, 25, 20);
		getContentPane().add(txtpnId);
		
		textField = new JTextField();
		textField.setBounds(62, 30, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnFechaDeIngreso = new JTextPane();
		txtpnFechaDeIngreso.setText("Fecha de ingreso");
		txtpnFechaDeIngreso.setBounds(37, 61, 95, 20);
		getContentPane().add(txtpnFechaDeIngreso);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(142, 61, 95, 20);
		getContentPane().add(formattedTextField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//si el tipo selecciona uno se imprime una cosa y si selecciona el otro se imprime la otra
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Alimento", "Artículo"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(152, 92, 68, 22);
		getContentPane().add(comboBox);
		
		txtTipoDeDonacin = new JTextField();
		txtTipoDeDonacin.setText("Tipo de donación");
		txtTipoDeDonacin.setBounds(37, 93, 95, 20);
		getContentPane().add(txtTipoDeDonacin);
		txtTipoDeDonacin.setColumns(10);

	}
}
