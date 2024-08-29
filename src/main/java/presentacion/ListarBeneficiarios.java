package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import datatypes.DtBeneficiario;
import excepciones.UsuarioNOBeneficiarioExc;
import interfaces.IControlador;

import java.awt.Font;

public class ListarBeneficiarios extends JInternalFrame {
	
	private IControlador icon; 
	// componentes gráficos
	private JComboBox<DtBeneficiario> comboBoxBeneficiarios;
	private JLabel lblBeneficiarios;
	private JButton btnCerrar;

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarBeneficiarios frame = new ListarBeneficiarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ListarBeneficiarios(IControlador icont) {
		icon = icont;
		
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 436, 150);
		setTitle("Lista de Beneficiarios");
		getContentPane().setLayout(null);  // QUITAR?
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			comboBoxBeneficiarios.removeAllItems();
			setVisible(false);} // REMOVER    comboBoxBeneficiarios.removeAllItems();  setVisible(false);
		});
		btnCerrar.setBounds(161, 86, 89, 23);
		getContentPane().add(btnCerrar);
		
		comboBoxBeneficiarios = new JComboBox<DtBeneficiario>();
		comboBoxBeneficiarios.setBounds(32, 38, 343, 37);
		getContentPane().add(comboBoxBeneficiarios); 
		
		JLabel lblBeneficiarios = new JLabel("Beneficiarios");
		lblBeneficiarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBeneficiarios.setBounds(172, 11, 167, 14);
		getContentPane().add(lblBeneficiarios);

	}
	
	public void cargarBeneficiarios() {
		DefaultComboBoxModel<DtBeneficiario> model = new DefaultComboBoxModel<>();
		comboBoxBeneficiarios.setModel(model);
		
		for (DtBeneficiario beneificiario : icon.ListaBeneficiarios()) {	// necesario para operar con setModel con arrayList
			model.addElement(beneificiario);
		}
		comboBoxBeneficiarios.setModel(model);
	}
	
	}

