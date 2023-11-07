package semana12;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

public class Libreria extends JFrame implements ActionListener {

	// Declaraci�n de variables
	private static final long serialVersionUID = 9206324162700448001L;
	private JPanel contentPane;
	private JLabel lblMarca;
	private JLabel lblCantidad;
	private JComboBox<String> cboMarca;
	private JTextField txtCantidad;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Lanza la aplicaci�n
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Libreria frame = new Libreria();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Libreria() {
		setTitle("Librer\u00EDa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 233);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 11, 47, 14);
		contentPane.add(lblMarca);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 36, 47, 14);
		contentPane.add(lblCantidad);

		cboMarca = new JComboBox<String>();
		cboMarca.setModel(new DefaultComboBoxModel<String>(new String[] { "Standford", "Alpha", "Justus", "Loro" }));
		cboMarca.setBounds(67, 8, 100, 20);
		contentPane.add(cboMarca);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(67, 33, 100, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 7, 89, 23);
		contentPane.add(btnProcesar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(335, 32, 89, 23);
		contentPane.add(btnBorrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 61, 414, 123);
		contentPane.add(scpScroll);

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}

	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}

	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		txtCantidad.setText("");
		txtS.setText("");
		cboMarca.setSelectedIndex(0);
		txtCantidad.requestFocus();
	}

	void imprimir(String cad) {
		txtS.append(cad + "\n");
	}

	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		String marca = LeerMarca();
		int cant = LeerCantidad();
		double pre= PrecioMarca(marca);
		double impP = calcularImpPagar(cant,pre);
		int libro = GifBook(cant);
		int borrador = GifEraser(marca,cant);
		mostrarResultados(impP,libro, borrador);
	}
	String LeerMarca() {
		return cboMarca.getSelectedItem().toString();
	}
	int LeerCantidad() {
		return Integer.parseInt(txtCantidad.getText());
	}
	double PrecioMarca(String marca) {
		switch (marca) {
		case "Standford" : return 4.85;
		case "Alpha" : return 4.35;
		case "Justus" : return 3.50;
		default : return 4.55;
		}
	}
	double calcularImpPagar(int cant,double pre) {
		return pre*cant;
	}
	int GifBook(int cant) {
		if (cant>=36) { return 2*cant;}
		else if (cant>=24 && cant<36) {return 6;}
		else if (cant>=12 && cant<24) {return 3;}
		else {return 1;}
	}
	int GifEraser(String marca,int cant) {
		switch(marca) {
		case "Standford" : return 2;
		case "Alpha" : return 3;
		case "Justus" : return 2;
		default : return 1;
		}
	}
	void mostrarResultados(double impP,int libro,int borrador) {
		txtS.setText("");
		imprimir("Importe a pagar : " + impP);
		imprimir("Cuadernos        : " + libro);
		imprimir("Borradores      : "+ borrador);
	}
}