package presentacion;

import javax.swing.*;

import datatypes.DtUsuario;
import datatypes.DtRepartidor;
import datatypes.DtBeneficiario;
import datatypes.Barrio;
import datatypes.EstadoBeneficiario;
import excepciones.UsuarioRepetidoExc;
import interfaces.IControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AltaUsuario extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private IControlador icon;
    private JTextField textNombre;
    private JTextField textEmail;
    private JTextField textNumeroLicencia;
    private JTextField textDireccion;
    private JTextField textFechaNacimiento;
    private JComboBox<EstadoBeneficiario> comboBoxEstadoBeneficiario;
    private JComboBox<Barrio> comboBoxBarrio;
    private JComboBox<String> comboBoxTipoUsuario;
    private JLabel lblNumeroLicencia;
    private JLabel lblDireccion;
    private JLabel lblFechaNacimiento;

    public AltaUsuario(IControlador icon) {
        this.icon = icon;
        setBounds(100, 100, 547, 402);
        getContentPane().setLayout(null);

        textNombre = new JTextField();
        textNombre.setBounds(103, 32, 200, 20);
        getContentPane().add(textNombre);
        textNombre.setColumns(10);

        textEmail = new JTextField();
        textEmail.setBounds(103, 64, 200, 20);
        getContentPane().add(textEmail);
        textEmail.setColumns(10);

        comboBoxTipoUsuario = new JComboBox<String>();
        comboBoxTipoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBoxTipoUsuario.getSelectedItem();
                if (selectedItem.equals("Beneficiario")) {
                    textNumeroLicencia.setEnabled(false);
                    textDireccion.setEnabled(true);
                    textFechaNacimiento.setEnabled(true);
                    comboBoxEstadoBeneficiario.setEnabled(true);
                    comboBoxBarrio.setEnabled(true);
                } else if (selectedItem.equals("Repartidor")) {
                    textNumeroLicencia.setEnabled(true);
                    textDireccion.setEnabled(false);
                    textFechaNacimiento.setEnabled(false);
                    comboBoxEstadoBeneficiario.setEnabled(false);
                    comboBoxBarrio.setEnabled(false);
                }
            }
        });

        comboBoxTipoUsuario.setModel(new DefaultComboBoxModel<String>(new String[]{"Beneficiario", "Repartidor"}));
        comboBoxTipoUsuario.setBounds(188, 111, 200, 22);
        getContentPane().add(comboBoxTipoUsuario);

        JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario");
        lblTipoDeUsuario.setBounds(34, 110, 134, 22);
        getContentPane().add(lblTipoDeUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(34, 35, 70, 15);
        getContentPane().add(lblNombre);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(34, 67, 70, 15);
        getContentPane().add(lblEmail);

        textNumeroLicencia = new JTextField();
        textNumeroLicencia.setBounds(188, 158, 200, 20);
        getContentPane().add(textNumeroLicencia);
        textNumeroLicencia.setColumns(10);

        textDireccion = new JTextField();
        textDireccion.setBounds(188, 190, 200, 20);
        getContentPane().add(textDireccion);
        textDireccion.setColumns(10);

        textFechaNacimiento = new JTextField();
        textFechaNacimiento.setBounds(188, 223, 200, 20);
        getContentPane().add(textFechaNacimiento);
        textFechaNacimiento.setColumns(10);
        
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(0, 0, 11, 26);
        getContentPane().add(formattedTextField);

        comboBoxEstadoBeneficiario = new JComboBox<EstadoBeneficiario>(EstadoBeneficiario.values());
        comboBoxEstadoBeneficiario.setBounds(56, 262, 200, 22);
        getContentPane().add(comboBoxEstadoBeneficiario);

        comboBoxBarrio = new JComboBox<Barrio>(Barrio.values());
        comboBoxBarrio.setBounds(56, 296, 200, 22);
        getContentPane().add(comboBoxBarrio);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuarioAceptarActionPerformed(e);
            }
        });
        btnAceptar.setBounds(124, 331, 117, 25);
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuarioCancelarActionPerformed(e);
            }
        });
        btnCancelar.setBounds(298, 331, 117, 25);
        getContentPane().add(btnCancelar);
        
        lblNumeroLicencia = new JLabel("Nro de Licencia");
        lblNumeroLicencia.setBounds(34, 158, 117, 20);
        getContentPane().add(lblNumeroLicencia);
        
        lblDireccion = new JLabel("Direccion");
        lblDireccion.setBounds(34, 189, 102, 22);
        getContentPane().add(lblDireccion);
        
        lblFechaNacimiento = new JLabel("Fecha de nacimiento");
        lblFechaNacimiento.setBounds(34, 222, 134, 22);
        getContentPane().add(lblFechaNacimiento);
    }

    protected void agregarUsuarioAceptarActionPerformed(ActionEvent arg0) {
        String nombre = this.textNombre.getText();
        String email = this.textEmail.getText();
        DtUsuario usuario = null;
        String selectedItem = (String) comboBoxTipoUsuario.getSelectedItem();

        if (selectedItem.equals("Beneficiario")) {
            String direccion = this.textDireccion.getText();
            LocalDateTime fechaNacimiento = LocalDateTime.parse(this.textFechaNacimiento.getText());
            EstadoBeneficiario estado = (EstadoBeneficiario) comboBoxEstadoBeneficiario.getSelectedItem();
            Barrio barrio = (Barrio) comboBoxBarrio.getSelectedItem();
            usuario = new DtBeneficiario(nombre, email, direccion, fechaNacimiento, estado, barrio);
        } else if (selectedItem.equals("Repartidor")) {
            String numeroLicencia = this.textNumeroLicencia.getText();
            usuario = new DtRepartidor(nombre, email, numeroLicencia);
        }

        try {
            this.icon.altaUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario dado de alta con éxito", "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
        } catch (UsuarioRepetidoExc e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Usuario", JOptionPane.ERROR_MESSAGE);
        }

        limpiarFormulario();
        setVisible(false);
    }

    protected void agregarUsuarioCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
    }

    private void limpiarFormulario() {
        textNombre.setText("");
        textEmail.setText("");
        textNumeroLicencia.setText("");
        textDireccion.setText("");
        textFechaNacimiento.setText("");
        comboBoxEstadoBeneficiario.setSelectedIndex(0);
        comboBoxBarrio.setSelectedIndex(0);
    }
}