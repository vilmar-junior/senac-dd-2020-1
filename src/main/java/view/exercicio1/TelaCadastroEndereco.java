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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

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
		setBounds(100, 100, 623, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Força a inicialização da tela MAXIMIZADA
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("80px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("200px:grow"),
						FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("15px"), FormSpecs.UNRELATED_GAP_COLSPEC,
						ColumnSpec.decode("80px"), ColumnSpec.decode("200px:grow"), },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("28px"),
						FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("28px"),
						FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("28px"), RowSpec.decode("25px"),
						RowSpec.decode("29px"), FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("16px"), }));

		JLabel lblRua = new JLabel("Rua (*): ");
		contentPane.add(lblRua, "2, 2, fill, center");

		JLabel lblBairro = new JLabel("Bairro (*):");
		contentPane.add(lblBairro, "2, 4, left, center");

		JLabel lblCidade = new JLabel("Cidade (*):");
		contentPane.add(lblCidade, "2, 6, right, center");

		JLabel lblEstado = new JLabel("Estado (*):");
		contentPane.add(lblEstado, "8, 6, fill, bottom");

		JLabel lblNumero = new JLabel("Número (*):");
		contentPane.add(lblNumero, "8, 2, fill, center");

		JLabel lblCep = new JLabel("CEP (*):");
		contentPane.add(lblCep, "8, 4, fill, center");

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController controller = new EnderecoController();
				String mensagem = controller.salvar(txtRua.getText(), txtBairro.getText(), txtNumero.getText(),
						txtCep.getText(),
						txtCidade.getText(), (String) cbSiglaEstado.getSelectedItem());

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		contentPane.add(btnSalvar, "2, 8, 3, 1, fill, fill");

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}

		});
		contentPane.add(btnLimpar, "6, 8, 4, 1, fill, fill");

		txtRua = new JTextField();
		contentPane.add(txtRua, "4, 2, fill, top");
		txtRua.setColumns(10);

		txtBairro = new JTextField();
		contentPane.add(txtBairro, "4, 4, fill, top");
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		contentPane.add(txtCidade, "4, 6, fill, top");
		txtCidade.setColumns(10);

		txtNumero = new JTextField();
		contentPane.add(txtNumero, "9, 2, center, top");
		txtNumero.setColumns(10);

		// O campo está sem máscara
		try {
			MaskFormatter mascaraCep = new MaskFormatter("#####-###");

			txtCep = new JFormattedTextField(mascaraCep);
			contentPane.add(txtCep, "9, 4, fill, center");

		} catch (ParseException e) {
			// TODO por enquanto não vamos tratar
			e.printStackTrace();
		}

		ArrayList<String> siglasEstados = consultarEstados();
		cbSiglaEstado = new JComboBox(siglasEstados.toArray());
		contentPane.add(cbSiglaEstado, "9, 6, fill, fill");

		JLabel lblCamposObrigatorios = new JLabel("(*) campos obrigatórios");
		lblCamposObrigatorios.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		contentPane.add(lblCamposObrigatorios, "2, 10, 3, 1, fill, fill");
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
