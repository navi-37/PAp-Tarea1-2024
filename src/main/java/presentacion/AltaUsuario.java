package presentacion;

import javax.swing.*;

import datatypes.DtUsuario;
import datatypes.DtRepartidor;
import datatypes.DtBeneficiario;
import datatypes.Barrio;
import datatypes.EstadoBeneficiario;
import excepciones.FechaInvalidaExc;
import excepciones.UsuarioRepetidoExc;
import interfaces.IControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;



public class AltaUsuario extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private IControlador icon;
    private JTextField textNombre;
    private JTextField textEmail;
    private JTextField textNumeroLicencia;
    private JTextField textDireccion;
    private JComboBox<EstadoBeneficiario> comboBoxEstadoBeneficiario;
    private JComboBox<Barrio> comboBoxBarrio;
    private JComboBox<String> comboBoxTipoUsuario;
    private JLabel lblNumeroLicencia;
    private JLabel lblDireccion;
    private JLabel lblFechaNacimiento;
    
    private JFormattedTextField texFechaNacimiento;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public AltaUsuario(IControlador icon) {
        this.icon = icon;
        setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		setTitle("ALTA USUARIO");
		setClosable(true);

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
                if (selectedItem != null) {
                	if (selectedItem.equals("Beneficiario")) {
                        textNumeroLicencia.setEnabled(false);
                        textDireccion.setEnabled(true);
                        texFechaNacimiento.setEnabled(true);
                        comboBoxEstadoBeneficiario.setEnabled(true);
                        comboBoxBarrio.setEnabled(true);
                    } else if (selectedItem.equals("Repartidor")) {
                        textNumeroLicencia.setEnabled(true);
                        textDireccion.setEnabled(false);
                        texFechaNacimiento.setEnabled(false);
                        comboBoxEstadoBeneficiario.setEnabled(false);
                        comboBoxBarrio.setEnabled(false);
                    }
                }  
            }
        });
        
        //
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(format);
        texFechaNacimiento = new JFormattedTextField(dateFormatter);
        texFechaNacimiento.setBounds(188, 221, 200, 20);
        getContentPane().add(texFechaNacimiento);
        
        comboBoxTipoUsuario.setModel(new DefaultComboBoxModel<String>(new String[]{"Beneficiario", "Repartidor"}));
        comboBoxTipoUsuario.setBounds(188, 125, 200, 22);
        getContentPane().add(comboBoxTipoUsuario);

        JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario");
        lblTipoDeUsuario.setBounds(34, 125, 134, 22);
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

        comboBoxEstadoBeneficiario = new JComboBox<EstadoBeneficiario>(EstadoBeneficiario.values());
        comboBoxEstadoBeneficiario.setBounds(188, 285, 200, 22);
        getContentPane().add(comboBoxEstadoBeneficiario);

        comboBoxBarrio = new JComboBox<Barrio>(Barrio.values());
        comboBoxBarrio.setBounds(188, 252, 200, 22);
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
        lblFechaNacimiento.setBounds(34, 222, 134, 20);
        getContentPane().add(lblFechaNacimiento);
        
        JLabel lblBarrio = new JLabel("Barrio");
        lblBarrio.setBounds(34, 256, 46, 14);
        getContentPane().add(lblBarrio);
        
        JLabel lblEstadoBenef = new JLabel("Estado");
        lblEstadoBenef.setBounds(34, 289, 46, 14);
        getContentPane().add(lblEstadoBenef);
        
        limpiarFormulario();
    }

    protected void agregarUsuarioAceptarActionPerformed(ActionEvent arg0) {
        String nombre = this.textNombre.getText();
        String email = this.textEmail.getText();
        DtUsuario usuario = null;
        String selectedItem = (String) comboBoxTipoUsuario.getSelectedItem();
        String emailRegex = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)+$";
        
        if (checkFormulario()) {
        
	        if (!email.matches(emailRegex)) {
	            JOptionPane.showMessageDialog(this, "Por favor ingrese un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        if (selectedItem.equals("Beneficiario")) {
	            String direccion = this.textDireccion.getText();
	            LocalDateTime fechaNacimiento = null;
	            try {
	            	fechaNacimiento = LocalDate.parse(this.texFechaNacimiento.getText(), dateTimeFormatter).atStartOfDay();
	                validarFechaNacimiento(fechaNacimiento.toLocalDate());
	            } catch (FechaInvalidaExc e) {
	                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Fecha", JOptionPane.ERROR_MESSAGE);
	                return;
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
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

        
    }
    
    public void validarFechaNacimiento(LocalDate fechaNacimiento) throws FechaInvalidaExc {
        LocalDate hoy = LocalDate.now();
        int anioActual = hoy.getYear();
        
        if (fechaNacimiento.getYear() > anioActual) {
            throw new FechaInvalidaExc("El año no puede ser mayor al actual.");
        }
        
        if (fechaNacimiento.getMonthValue() < 1 || fechaNacimiento.getMonthValue() > 12) {
            throw new FechaInvalidaExc("El mes debe estar entre 1 y 12.");
        }

        int diasEnMes = fechaNacimiento.getMonth().length(fechaNacimiento.isLeapYear());
        if (fechaNacimiento.getDayOfMonth() < 1 || fechaNacimiento.getDayOfMonth() > diasEnMes) {
            throw new FechaInvalidaExc("Día inválido para el mes seleccionado.");
        }
    }
    
    protected void agregarUsuarioCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
    }
    
    private boolean checkFormulario() {
        String nombre = this.textNombre.getText();
        String email = this.textEmail.getText();
        String nLicencia = this.textNumeroLicencia.getText();
        String direccion = this.textDireccion.getText();
        String fechaNac = this.texFechaNacimiento.getText();
        String tipoUsr = (String) this.comboBoxTipoUsuario.getSelectedItem();
        String barrio = (String) this.comboBoxBarrio.getSelectedItem();
        String estado = (String) this.comboBoxEstadoBeneficiario.getSelectedItem();
        
        if (nombre.isEmpty() || email.isEmpty() || fechaNac.isEmpty() || tipoUsr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tipoUsr == "Beneficiario") {
        	if (direccion.isEmpty() || barrio.isEmpty() || estado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (tipoUsr == "Repartidor") {
        	if (nLicencia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Faltan datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void limpiarFormulario() {
        textNombre.setText("");
        textEmail.setText("");
        textNumeroLicencia.setText("");
        textDireccion.setText("");
        texFechaNacimiento.setValue(null);
        comboBoxTipoUsuario.setSelectedItem(null);
        comboBoxEstadoBeneficiario.setSelectedItem(null);
        comboBoxBarrio.setSelectedItem(null);
    }
}