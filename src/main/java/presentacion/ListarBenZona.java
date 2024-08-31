package presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import datatypes.Barrio;
import datatypes.DtBeneficiario;
import interfaces.IControlador;

public class ListarBenZona extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private IControlador icon;
    private JComboBox<String> comboBoxBarrios;
    private JLabel lblSelectBarrio;
    private JList<DtBeneficiario> listBenefs;  // comprobar si acá no me da problema

    public ListarBenZona(IControlador icon) {
        setTitle("Listar Beneficiarios por Zona");
        
        this.icon = icon;        
        
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 684, 449);
        getContentPane().setLayout(null);
        
        lblSelectBarrio = new JLabel("Seleccione Barrio:");
        lblSelectBarrio.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSelectBarrio.setBounds(10, 9, 106, 30);
        getContentPane().add(lblSelectBarrio);
        
        comboBoxBarrios = new JComboBox<String>();
        comboBoxBarrios.setModel(new DefaultComboBoxModel<String>(new String[] {"Centro", "Ciudad Vieja", "Cordón", "Palermo", "Parque Rodó"})); 
        comboBoxBarrios.setBounds(143, 11, 182, 29);
        
        comboBoxBarrios.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaBeneficiarios();  
            }
        });
        getContentPane().add(comboBoxBarrios); 
        
        listBenefs = new JList<DtBeneficiario>();
        listBenefs.setBounds(47, 63, 562, 320);
        getContentPane().add(listBenefs);

        // a ver si acá funca
        actualizarListaBeneficiarios(); 
    }

    public Barrio seleccionarBarrio(JComboBox<String> comboBoxBarrios) {
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

    public void actualizarListaBeneficiarios() {
        ArrayList<DtBeneficiario> beneficiarios = icon.ListaBeneficiarios();
        ArrayList<DtBeneficiario> beneficiariosFiltrados = new ArrayList<>(); 
        Barrio barrioSeleccionado = seleccionarBarrio(comboBoxBarrios);
        
        if (barrioSeleccionado != null) {  
            beneficiariosFiltrados = (ArrayList<DtBeneficiario>) beneficiarios.stream()
                .filter(b -> b.getBarrio() == barrioSeleccionado)
                .collect(Collectors.toList());
        }
        
        DefaultListModel<DtBeneficiario> listModel = new DefaultListModel<>();
        for (DtBeneficiario beneficiario : beneficiariosFiltrados) {
            listModel.addElement(beneficiario);
        }

        
        listBenefs.setModel(listModel);
    }
}
