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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		
		Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();
		
		Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;
		
		agregarDonacionInternalFrame = new AltaDonacion();
		agregarDonacionInternalFrame.setClosable(true);
		jInternalFrameSize = agregarDonacionInternalFrame.getSize();
		agregarDonacionInternalFrame.setLocation(24,
		34);
		agregarDonacionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarDonacionInternalFrame);
	}

		
	/**
	 * 
	 * 
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 576, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu menuAltas = new JMenu("Altas");
		menuBar.add(menuAltas);
		
		JMenuItem menuItemUsuario = new JMenuItem("Usuario");
		menuAltas.add(menuItemUsuario);
		
		JMenuItem menuItemDonacion = new JMenuItem("Donación");
		menuItemDonacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				agregarDonacionInternalFrame.setVisible(true);
			}
		});
		
		menuAltas.add(menuItemDonacion);
		
		JMenuItem menuItemDist = new JMenuItem("Distribución");
		menuAltas.add(menuItemDist);
		
		JMenu menuModificar = new JMenu("Modificaciones");
		menuBar.add(menuModificar);
		
		JMenu menuListar = new JMenu("Listas");
		menuBar.add(menuListar);
		
		JMenu menuReporte = new JMenu("Reporte");
		menuBar.add(menuReporte);
		
		//agregarDonacionInternalFrame.setVisible(false);

	}
}