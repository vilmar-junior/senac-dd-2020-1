package view.exercicio1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import controller.exercicio1.ClienteController;
import model.exercicio1.seletor.ClienteSeletor;
import model.vo.exercicio1.Cliente;

public class TelaListagemClientes {

	private JFrame frmListagemDeClientes;
	private JTable tblClientes;
	private ArrayList<Cliente> clientes;
	private String[] nomesColunas = { "Nome completo", "Data de nascimento", "CPF", "Qtde. Telefones" };
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JFormattedTextField txtCPF;
	private DatePicker dtNascimentoInicial;
	private DatePicker dtNascimentoFinal;

	private void limparTabelaClientes() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaClientes() {
		limparTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

		for (Cliente c : clientes) {

			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = c.getNomeCompleto();
			novaLinhaDaTabela[1] = c.getDataNascimento().format(formatador);
			novaLinhaDaTabela[2] = c.getCpf();
			novaLinhaDaTabela[3] = c.getTelefones().size();

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
		frmListagemDeClientes.setBounds(100, 100, 700, 600);
		frmListagemDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeClientes.getContentPane().setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteSeletor seletor = new ClienteSeletor();
				seletor.setNome(txtNome.getText());
				seletor.setSobrenome(txtSobrenome.getText());
				seletor.setDataNascimentoInicial(dtNascimentoInicial.getDate());
				seletor.setDataNascimentoFinal(dtNascimentoFinal.getDate());

				ClienteController controller = new ClienteController();
				clientes = controller.listarClientes(seletor);

				atualizarTabelaClientes();
			}
		});
		btnBuscar.setBounds(285, 120, 120, 45);
		frmListagemDeClientes.getContentPane().add(btnBuscar);

		tblClientes = new JTable();
		tblClientes.setBounds(25, 164, 650, 328);
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

		JLabel lblDataNascimentoDe = new JLabel("Data de dascimento. De:");
		lblDataNascimentoDe.setBounds(25, 60, 175, 10);
		frmListagemDeClientes.getContentPane().add(lblDataNascimentoDe);

		dtNascimentoInicial = new DatePicker();
		dtNascimentoInicial.setBounds(200, 55, 400, 30);
		frmListagemDeClientes.getContentPane().add(dtNascimentoInicial);

		JLabel lblAte = new JLabel("Até:");
		lblAte.setBounds(25, 90, 175, 10);
		frmListagemDeClientes.getContentPane().add(lblAte);

		dtNascimentoFinal = new DatePicker();
		dtNascimentoFinal.setBounds(200, 90, 400, 30);
		frmListagemDeClientes.getContentPane().add(dtNascimentoFinal);

		JButton btnGerarPlanilha = new JButton("Gerar Planilha");
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");

				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();

					ClienteController controller = new ClienteController();
					controller.gerarRelatorio(clientes, caminhoEscolhido);
				}
			}
		});
		btnGerarPlanilha.setBounds(285, 520, 120, 45);
		frmListagemDeClientes.getContentPane().add(btnGerarPlanilha);
	}
}
