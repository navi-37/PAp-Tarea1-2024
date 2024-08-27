/*package presentacion;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import interfaces.IControlador;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import datatypes.DtBeneficiario;
import excepciones.UsuarioRepetidoExc;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ListarBeneficiarios extends JInternalFrame {
	
	
	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JLabel lblEtiquetaLista;
	private JComboBox<DtBeneficiario> comboBoxBeneficiarios; 
	private JButton btnCerrar;  
	
	public ListarBeneficiarios(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 361, 194);
		getContentPane().setLayout(null);
		
		JLabel lblEtiquetaLista = new JLabel("Desplegue para ver lista de beneficiarios.");
		lblEtiquetaLista.setBounds(21, 10, 257, 26);
		lblEtiquetaLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEtiquetaLista);
		
		JComboBox comboBoxBeneficiarios = new JComboBox<DtBeneficiario>();  
		comboBoxBeneficiarios.setBounds(68, 46, 170, 31);
		getContentPane().add(comboBoxBeneficiarios, BorderLayout.CENTER );
				
		
		btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	comboBoxBeneficiarios.removeAllItems();
                setVisible(false);
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH); 
        }
	
		
		public void cargarBeneficiarios() {
			DefaultComboBoxModel<DtBeneficiario> model;
			
				model = new DefaultComboBoxModel<DtBeneficiario>();
				comboBoxBeneficiarios.setModel(model);				
		}
	}
	
		*/
		
/*		DefaultListModel<String> listModel = new DefaultListModel<>();
		List<DtBeneficiario> beneficiarios = icon.ListaBeneficiarios();
		
		
		
		for (DtBeneficiario beneficiario : beneficiarios) {
		    String item = beneficiario.getNombre() + " - " + beneficiario.getDireccion() + " - " + beneficiario.getEstado() + " - " + beneficiario.getBarrio();
		    listModel.addElement(item);
		}*/
		
		

	



/*
public class ListarBeneficiarios extends JInternalFrame { 
    private JList<String> beneficiariosList; 
    private DefaultListModel<String> listModel; 

    public ListarBeneficiarios(IControlador icon) {
    	getContentPane().setLayout(null);
    	
    	JLabel lblNewLabel = new JLabel("Desplegar para ver beneficiarios");
    	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	lblNewLabel.setBounds(25, 10, 459, 23);
    	getContentPane().add(lblNewLabel);
    	
    	JComboBox comboBoxBeneficiarios = new JComboBox();
    	comboBoxBeneficiarios.setBounds(10, 43, 391, 33);
    	getContentPane().add(comboBoxBeneficiarios);
    	
    	JButton btnCerrar = new JButton("Cerrar");
    	btnCerrar.setBounds(282, 124, 119, 33);
    	getContentPane().add(btnCerrar);
        setTitle("Lista de Beneficiarios");
        setBounds(100, 100, 600, 400); /
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Crear el modelo de la lista
        listModel = new DefaultListModel<>();

        // Poblar el modelo con la lista de beneficiarios
        for (DtBeneficiario beneficiario : icon.ListaBeneficiarios()) {
            String displayText = beneficiario.getNombre() + " - " + beneficiario.getDireccion() + " - " + beneficiario.getEstado();
            listModel.addElement(displayText);
        }

        // Crear el JList y asignar el modelo
        beneficiariosList = new JList<>(listModel);
        beneficiariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Agregar el JList a un JScrollPane para que sea desplazable
        JScrollPane scrollPane = new JScrollPane(beneficiariosList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}*/
package presentacion;



