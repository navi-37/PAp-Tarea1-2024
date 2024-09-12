package presentacion;


import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import datatypes.Barrio;
import datatypes.DtBeneficiario;
import datatypes.EstadoBeneficiario;
import interfaces.IControlador;

import java.awt.Font;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ListarBeneficiarios extends JInternalFrame {
	
	private IControlador icon; 
		private JComboBox<String> estadoComboBox;
	private JComboBox<String> barrioComboBox;
	private JList<DtBeneficiario> listBeneficiarios;
	private JScrollPane scrollPane;
	private JLabel lblBeneficiarios;
	private JButton btnCerrar;

	private static final long serialVersionUID = 1L;

	public ListarBeneficiarios(IControlador icont) {
		icon = icont;
		
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		setTitle("LISTAR BENEFICIARIOS");
		setClosable(true);
		
		btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(44, 331, 89, 23);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario(); 
                setVisible(false);
            }
        });
        getContentPane().add(btnCerrar);
		
		scrollPane = new JScrollPane();
	    scrollPane.setBounds(34, 129, 500, 200);
	    getContentPane().add(scrollPane);
	    
	    listBeneficiarios = new JList<>();
	    scrollPane.setViewportView(listBeneficiarios);
		
	    lblBeneficiarios = new JLabel("Beneficiarios");
		lblBeneficiarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBeneficiarios.setBounds(34, 12, 167, 14);
		getContentPane().add(lblBeneficiarios);
		
		estadoComboBox = new JComboBox<>(new String[] {"Seleccione un estado", EstadoBeneficiario.ACTIVO.name(), EstadoBeneficiario.SUSPENDIDO.name()});
        estadoComboBox.setBounds(32, 40, 216, 27);
        getContentPane().add(estadoComboBox);

        estadoComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros(); 
            }
        });

        barrioComboBox = new JComboBox<>(new String[] {"Seleccione un barrio", Barrio.CIUDAD_VIEJA.name(), Barrio.CORDON.name(), Barrio.PARQUE_RODO.name(), Barrio.CENTRO.name(), Barrio.PALERMO.name()});
        barrioComboBox.setBounds(34, 90, 214, 27);
        getContentPane().add(barrioComboBox);

        barrioComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });
        
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario(); 
                }
        	public void internalFrameClosed(InternalFrameEvent e) {
        		limpiarFormulario();
        	}
        });
    }
  
	
	public void cargarBeneficiarios() {
		DefaultListModel<DtBeneficiario> model = new DefaultListModel<>();
	    listBeneficiarios.setModel(model);
	    
	    for (DtBeneficiario beneficiario : icon.ListaBeneficiarios()) {
	        model.addElement(beneficiario);
	    }
	    listBeneficiarios.setModel(model);
	}
	
	private void aplicarFiltros() {
	    String estadoSeleccionado = (String) estadoComboBox.getSelectedItem();
	    String barrioSeleccionado = (String) barrioComboBox.getSelectedItem();

	    DefaultListModel<DtBeneficiario> model = new DefaultListModel<>();
	    listBeneficiarios.setModel(model);

	    for (DtBeneficiario beneficiario : icon.ListaBeneficiarios()) {
	        boolean coincideEstado = estadoSeleccionado.equals("Seleccione un estado") || beneficiario.getEstado().name().equals(estadoSeleccionado);
	        boolean coincideBarrio = barrioSeleccionado.equals("Seleccione un barrio") || beneficiario.getBarrio().name().equals(barrioSeleccionado);

	        if (coincideEstado && coincideBarrio) {
	            model.addElement(beneficiario);
	        }
	    }
	    
	    listBeneficiarios.setModel(model);
	}
	
	private void limpiarFormulario() {
        estadoComboBox.setSelectedIndex(0); 
        barrioComboBox.setSelectedIndex(0);     
	}
}
