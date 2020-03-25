package view.exercicio1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.exercicio1.EnderecoController;

public class TelaCadastroEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JFormattedTextField txtCep;
	private JComboBox cbSiglaEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEndereco frame = new TelaCadastroEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroEndereco() {
		setTitle("Cadastro de endereço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRua = new JLabel("Rua (*): ");
		lblRua.setBounds(10, 15, 50, 16);
		contentPane.add(lblRua);

		JLabel lblBairro = new JLabel("Bairro (*):");
		lblBairro.setBounds(10, 45, 50, 16);
		contentPane.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade (*):");
		lblCidade.setBounds(10, 75, 50, 16);
		contentPane.add(lblCidade);

		JLabel lblEstado = new JLabel("Estado (*):");
		lblEstado.setBounds(270, 80, 60, 16);
		contentPane.add(lblEstado);

		JLabel lblNumero = new JLabel("Número (*):");
		lblNumero.setBounds(270, 15, 60, 16);
		contentPane.add(lblNumero);

		JLabel lblCep = new JLabel("CEP (*):");
		lblCep.setBounds(270, 45, 60, 16);
		contentPane.add(lblCep);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController controller = new EnderecoController();
				controller.salvar(txtRua.getText(), txtBairro.getText(), txtNumero.getText(), txtCep.getText(),
						txtCidade.getText(), (String) cbSiglaEstado.getSelectedItem());

			}
		});
		btnSalvar.setBounds(105, 123, 117, 29);
		contentPane.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}

		});
		btnLimpar.setBounds(234, 123, 117, 29);
		contentPane.add(btnLimpar);

		txtRua = new JTextField();
		txtRua.setBounds(62, 10, 200, 28);
		contentPane.add(txtRua);
		txtRua.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(62, 40, 200, 28);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(62, 70, 200, 28);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(330, 10, 110, 28);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		// O campo está sem máscara
		try {
			MaskFormatter mascaraCep = new MaskFormatter("#####-###");

			txtCep = new JFormattedTextField(mascaraCep);
			txtCep.setBounds(330, 40, 110, 28);
			contentPane.add(txtCep);

		} catch (ParseException e) {
			// TODO por enquanto não vamos tratar
			e.printStackTrace();
		}

		ArrayList<String> siglasEstados = consultarEstados();
		cbSiglaEstado = new JComboBox(siglasEstados.toArray());
		cbSiglaEstado.setBounds(330, 70, 110, 27);
		contentPane.add(cbSiglaEstado);

		JLabel lblCamposObrigatorios = new JLabel("(*) campos obrigatórios");
		lblCamposObrigatorios.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		lblCamposObrigatorios.setBounds(20, 164, 200, 16);
		contentPane.add(lblCamposObrigatorios);
	}

	protected void limparCampos() {
		// Campos de texto: "setar" o texto para ""
		this.txtRua.setText("");
		this.txtBairro.setText("");
		this.txtCidade.setText("");
		this.txtNumero.setText("");
		this.txtCep.setText("");

		// Campos com combobox: "setar" o índice selecionado para -1 (sem item
		// selecionado no combo)
		this.cbSiglaEstado.setSelectedIndex(0);
	}

	private ArrayList<String> consultarEstados() {
		// TODO o ideal é consultar numa tabela estes dados...

		ArrayList<String> siglasEstados = new ArrayList<String>();

		siglasEstados.add("AC");
		siglasEstados.add("BA");
		siglasEstados.add("CE");
		siglasEstados.add("DF");
		siglasEstados.add("GO");
		siglasEstados.add("PR");
		siglasEstados.add("SC");
		siglasEstados.add("RS");

		// e assim por diante...

		return siglasEstados;
	}
}
