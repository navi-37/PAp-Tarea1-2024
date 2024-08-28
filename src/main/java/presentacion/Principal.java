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

public class Principal {

	private JFrame frame;
  
	private AltaDonacion agregarDonacionInternalFrame;
	private AltaUsuario agregarUsuarioInternalFrame;
	private AltaDistribucion agregarDistribucionInternalFrame;
	private ListarDistribuciones listarDistribucionesInternalFrame;
	
	private ListarBeneficiarios lisBeneInternalFrame;

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
		
		Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();
		
		Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;
		
		agregarDonacionInternalFrame = new AltaDonacion(icon);
		agregarDonacionInternalFrame.setClosable(true);
		jInternalFrameSize = agregarDonacionInternalFrame.getSize();
		//agregarDonacionInternalFrame.setLocation(29,
		//27);
		agregarDonacionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height - jInternalFrameSize.height)/2);
		agregarDonacionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarDonacionInternalFrame);
	
		agregarUsuarioInternalFrame = new AltaUsuario(icon);
		agregarUsuarioInternalFrame.setClosable(true);
		jInternalFrameSize = agregarUsuarioInternalFrame.getSize();
		agregarUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height - jInternalFrameSize.height)/2);
		agregarUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarUsuarioInternalFrame);

		agregarDistribucionInternalFrame = new AltaDistribucion(icon);
		agregarDistribucionInternalFrame.setClosable(true);
		jInternalFrameSize = agregarDistribucionInternalFrame.getSize();
		agregarDistribucionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height - jInternalFrameSize.height)/2);
		agregarDistribucionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarDistribucionInternalFrame);

		
		listarDistribucionesInternalFrame = new ListarDistribuciones(icon);
		listarDistribucionesInternalFrame.setClosable(true);
		jInternalFrameSize = listarDistribucionesInternalFrame.getSize();
		listarDistribucionesInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height - jInternalFrameSize.height)/2);
		listarDistribucionesInternalFrame.setVisible(false);
		frame.getContentPane().add(listarDistribucionesInternalFrame);
		
		lisBeneInternalFrame = new ListarBeneficiarios(icon); 
		lisBeneInternalFrame.setVisible(false);
		frame.getContentPane().add(lisBeneInternalFrame);
		
		
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setBounds(0, 0, 576, 22);
		//frame.getContentPane().add(menuBar);
		frame.setJMenuBar(menuBar);
		
		JMenu menuAltas = new JMenu("Dar de alta");
		menuBar.add(menuAltas);
		
		JMenuItem menuItemUsuario = new JMenuItem("Usuario");
		menuItemUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				agregarUsuarioInternalFrame.setVisible(true);
			}
		});
		menuAltas.add(menuItemUsuario);
		
		JMenuItem menuItemDonacion = new JMenuItem("Donación");
		menuItemDonacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				agregarDonacionInternalFrame.setVisible(true);
			}
		});
		
		menuAltas.add(menuItemDonacion);
		
		JMenuItem menuItemDist = new JMenuItem("Distribución");
		menuItemDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarDistribucionInternalFrame.setVisible(true);
			}
		});
		menuAltas.add(menuItemDist);
		
		JMenu menuModificar = new JMenu("Modificar");
		menuBar.add(menuModificar);
		
		JMenu menuListar = new JMenu("Listar");
		menuBar.add(menuListar);
		
		JMenuItem mntmDistribuciones = new JMenuItem("Distribuciones");
		mntmDistribuciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDistribucionesInternalFrame.iniciarlizarComboBoxes();
				listarDistribucionesInternalFrame.setVisible(true);
			}
		});
		menuListar.add(mntmDistribuciones);
		
		JMenu menuReporte = new JMenu("Reporte");
		menuBar.add(menuReporte);
		
		JMenuItem mntmListaBeneficiarios = new JMenuItem("Beneficiarios");
		mntmListaBeneficiarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	lisBeneInternalFrame.cargarBeneficiarios();
            	lisBeneInternalFrame.setVisible(true);
            }
        });
        menuListar.add(mntmListaBeneficiarios);


	}
}