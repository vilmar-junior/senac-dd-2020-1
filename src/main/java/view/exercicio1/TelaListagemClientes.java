package view.exercicio1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.exercicio1.ClienteController;
import model.exercicio1.seletor.ClienteSeletor;
import model.vo.exercicio1.Cliente;

public class TelaListagemClientes {

	private JFrame frmListagemDeClientes;
	private JTable tblClientes;
	private ArrayList<Cliente> clientes;
	private String[] nomesColunas = { "Nome completo", "CPF", "Qtde. Telefones" };
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JFormattedTextField txtCPF;

	private void limparTabelaClientes() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaClientes() {
		limparTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

		for (Cliente c : clientes) {

			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = c.getNomeCompleto();
			novaLinhaDaTabela[1] = c.getCpf();
			novaLinhaDaTabela[2] = c.getTelefones().size();

			model.addRow(novaLinhaDaTabela);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemClientes window = new TelaListagemClientes();
					window.frmListagemDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemClientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeClientes = new JFrame();
		frmListagemDeClientes.setTitle("Listagem de Clientes");
		frmListagemDeClientes.setBounds(100, 100, 700, 520);
		frmListagemDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeClientes.getContentPane().setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteSeletor seletor = new ClienteSeletor();
				seletor.setNome(txtNome.getText());
				seletor.setSobrenome(txtSobrenome.getText());
//				seletor.setCpf(txtCPF.getText());

				ClienteController controller = new ClienteController();
				clientes = controller.listarClientes(seletor);

				atualizarTabelaClientes();
			}
		});
		btnBuscar.setBounds(280, 100, 120, 30);
		frmListagemDeClientes.getContentPane().add(btnBuscar);

		tblClientes = new JTable();
		tblClientes.setBounds(25, 142, 650, 328);
		frmListagemDeClientes.getContentPane().add(tblClientes);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(25, 25, 61, 16);
		frmListagemDeClientes.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(90, 19, 134, 28);
		frmListagemDeClientes.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(339, 25, 108, 16);
		frmListagemDeClientes.getContentPane().add(lblSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(459, 19, 134, 28);
		frmListagemDeClientes.getContentPane().add(txtSobrenome);
		txtSobrenome.setColumns(10);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(25, 65, 61, 16);
		frmListagemDeClientes.getContentPane().add(lblCPF);

//		txtCPF = new JFormattedTextField("###.###.###-##");
//		txtCPF.setBounds(90, 59, 134, 28);
//		frmListagemDeClientes.getContentPane().add(txtCPF);
	}
}
