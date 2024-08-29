package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

import datatypes.Barrio;
import datatypes.DtBeneficiario;
import interfaces.IControlador;

public class ListarBenZona extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControlador icu;
	private JComboBox<String> comboBoxBarrios;
	private JLabel lblSelectBarrio;
	private JList<DtBeneficiario> listBenefs; 
	private ArrayList<DtBeneficiario> beneficiarios = icu.ListaBeneficiarios();


	public ListarBenZona() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 684, 449);
		getContentPane().setLayout(null);
		
		JLabel lblSelectBarrio = new JLabel("Seleccione Barrio:");
		lblSelectBarrio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectBarrio.setBounds(10, 9, 106, 30);
		getContentPane().add(lblSelectBarrio);
		
		JComboBox<String> comboBoxBarrios = new JComboBox<String>();
		comboBoxBarrios.setModel(new DefaultComboBoxModel<String>(new String[] {"Centro", "Ciudad Vieja", "Cordón", "Palermo", "Parque Rodó"})); 
		comboBoxBarrios.setBounds(143, 11, 182, 29);
		getContentPane().add(comboBoxBarrios);
		
		JList<DtBeneficiario> listBenefs = new JList<DtBeneficiario>();
		listBenefs.setBounds(47, 63, 562, 320);
		getContentPane().add(listBenefs);

	}

	
	private Barrio seleccionarBarrio(JComboBox<String> comboBoxBarrios) {
		String seleccion = (String) comboBoxBarrios.getSelectedItem();
		switch (seleccion) {
        case "Ciudad Vieja":
            return Barrio.CIUDAD_VIEJA;
        case "Cordón":
            return Barrio.CORDON;
        case "Parque Rodó":
            return Barrio.PARQUE_RODO;
        case "Centro":
            return Barrio.CENTRO;
        case "Palermo":
            return Barrio.PALERMO;
        default:
            return null;
		}
	}
	
	private void actualizarListaBeneficiacios() {
		ArrayList<DtBeneficiario> beneficiariosFiltrados = new ArrayList<DtBeneficiario>(); 
		Barrio barrioSeleccionado = seleccionarBarrio(comboBoxBarrios);
		
		beneficiariosFiltrados = (ArrayList<DtBeneficiario>) beneficiarios.stream()
				.filter(b -> b.getBarrio() == barrioSeleccionado).collect(Collectors.toList());
		
		
		
		
		
	}
}
