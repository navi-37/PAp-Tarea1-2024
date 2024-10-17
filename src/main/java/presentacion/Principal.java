package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.Fabrica;
import interfaces.IControlador;
import publicadores.ControladorPublish;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;

public class Principal {

	private JFrame frame;
  
	private AltaDonacion agregarDonacionInternalFrame;
	private AltaUsuario agregarUsuarioInternalFrame;
	private AltaDistribucion agregarDistribucionInternalFrame;
	private ListarDistribuciones listarDistribucionesInternalFrame;
	private ListarBeneficiarios lisBeneInternalFrame;
	private ModificarDonacion modificarDonacionInternalFrame;
	private ModificarDistribucion modificarDistribucion;
	private ModificarUsuario modificarUsuarioInternalFrame; 
	private ReporteZona reporteZonasInternalFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		initialize();
		ControladorPublish cp = new ControladorPublish();
		cp.publicar();
		
		Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();
		
		Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;
		
		agregarDonacionInternalFrame = new AltaDonacion(icon);
		agregarDonacionInternalFrame.setClosable(true);
		jInternalFrameSize = agregarDonacionInternalFrame.getSize();

		agregarDonacionInternalFrame.setLocation(118,
				41);
		agregarDonacionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarDonacionInternalFrame);
	
		agregarUsuarioInternalFrame = new AltaUsuario(icon);
		agregarUsuarioInternalFrame.setClosable(true);
		jInternalFrameSize = agregarUsuarioInternalFrame.getSize();
		agregarUsuarioInternalFrame.setLocation(118,
				36);
		agregarUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarUsuarioInternalFrame);

		agregarDistribucionInternalFrame = new AltaDistribucion(icon);
		agregarDistribucionInternalFrame.setClosable(true);
		jInternalFrameSize = agregarDistribucionInternalFrame.getSize();
		agregarDistribucionInternalFrame.setLocation(118,
				41);
		agregarDistribucionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarDistribucionInternalFrame);

		
		listarDistribucionesInternalFrame = new ListarDistribuciones(icon);
		listarDistribucionesInternalFrame.setClosable(true);
		jInternalFrameSize = listarDistribucionesInternalFrame.getSize();
		listarDistribucionesInternalFrame.setLocation(118,
				41);
		listarDistribucionesInternalFrame.setVisible(false);
		frame.getContentPane().add(listarDistribucionesInternalFrame);
		
		
		lisBeneInternalFrame = new ListarBeneficiarios(icon); 
		lisBeneInternalFrame.setLocation(118, 41);
		lisBeneInternalFrame.setVisible(false);
		frame.getContentPane().add(lisBeneInternalFrame);

		
		modificarDistribucion = new ModificarDistribucion(icon);
        modificarDistribucion.setClosable(true);
        jInternalFrameSize = modificarDistribucion.getSize();
        modificarDistribucion.setLocation(118,
                41);
        modificarDistribucion.setVisible(false);
        frame.getContentPane().add(modificarDistribucion);

        
		modificarDonacionInternalFrame = new ModificarDonacion(icon);
		modificarDonacionInternalFrame.setClosable(true);
		jInternalFrameSize = modificarDonacionInternalFrame.getSize();
		modificarDonacionInternalFrame.setLocation(118,
				41);
		modificarDonacionInternalFrame.setVisible(false);
		frame.getContentPane().add(modificarDonacionInternalFrame);
		
		modificarUsuarioInternalFrame = new ModificarUsuario(icon);
		modificarUsuarioInternalFrame.setLocation(118, 41);
		modificarUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(modificarUsuarioInternalFrame);
		
		reporteZonasInternalFrame = new ReporteZona(icon);
		reporteZonasInternalFrame.setClosable(true);
		jInternalFrameSize = reporteZonasInternalFrame.getSize();
		reporteZonasInternalFrame.setLocation(118,
				16);
		reporteZonasInternalFrame.setVisible(false);
		frame.getContentPane().add(reporteZonasInternalFrame);
		
		JLabel lblNewLabel = new JLabel("Ayudemos.uy");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 99));
		lblNewLabel.setBounds(174, 64, 538, 404);
		frame.getContentPane().add(lblNewLabel);

	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuAltas = new JMenu("› Dar de alta  ");
		menuAltas.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuAltas);
		
		JMenuItem menuItemUsuario = new JMenuItem("› Usuario");
		menuItemUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuItemUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				agregarUsuarioInternalFrame.setVisible(true);
			}
		});
		menuAltas.add(menuItemUsuario);
		
		JMenuItem menuItemDonacion = new JMenuItem("› Donación");
		menuItemDonacion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuItemDonacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				agregarDonacionInternalFrame.setVisible(true);
			}
		});
		
		menuAltas.add(menuItemDonacion);
		
		JMenuItem menuItemDist = new JMenuItem("› Distribución");
		menuItemDist.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuItemDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDistribucionInternalFrame.setVisible(true);
			}
		});
		menuAltas.add(menuItemDist);
		
		JMenu menuModificar = new JMenu("› Modificar  ");
		menuModificar.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuModificar);
		

		JMenuItem menuItemModificarDist = new JMenuItem("› Distribución");
		menuItemModificarDist.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuItemModificarDist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	modificarDistribucion.setVisible(true);
    	        modificarDistribucion.toFront();
            }
        });
        menuModificar.add(menuItemModificarDist);
        

		JMenuItem mntmDonacion = new JMenuItem("› Donación");
		mntmDonacion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDonacionInternalFrame.actualizarDonaciones();
				modificarDonacionInternalFrame.setVisible(true);
			}
		});
		menuModificar.add(mntmDonacion);
		
		JMenuItem mntmUsuario = new JMenuItem("› Usuario");
		mntmUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarUsuarioInternalFrame.setVisible(true);
			}
		});
		menuModificar.add(mntmUsuario);
		

		
		JMenu menuListar = new JMenu("› Listar  ");
		menuListar.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuListar);
		
		JMenuItem mntmDistribuciones = new JMenuItem("› Distribuciones");
		mntmDistribuciones.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmDistribuciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesInternalFrame.setVisible(true);
			}
		});
		menuListar.add(mntmDistribuciones);
		
		JMenu menuReporte = new JMenu("› Reporte");
		menuReporte.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuReporte);
		
		JMenuItem mntmZonasConMs = new JMenuItem("› Zonas con más distribuciones");
		mntmZonasConMs.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmZonasConMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteZonasInternalFrame.setVisible(true);
			}
		});
		menuReporte.add(mntmZonasConMs);
		
		JMenuItem mntmListaBeneficiarios = new JMenuItem("› Beneficiarios");
		mntmListaBeneficiarios.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmListaBeneficiarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	lisBeneInternalFrame.cargarBeneficiarios();
            	lisBeneInternalFrame.setVisible(true);
            }
        });
        menuListar.add(mntmListaBeneficiarios);
	}
}