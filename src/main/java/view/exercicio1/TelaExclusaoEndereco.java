package view.exercicio1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.exercicio1.EnderecoController;

public class TelaExclusaoEndereco {

	private JFrame frmExclusaoEnderecos;
	private JTextField txtIdEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExclusaoEndereco window = new TelaExclusaoEndereco();
					window.frmExclusaoEnderecos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaExclusaoEndereco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExclusaoEnderecos = new JFrame();
		frmExclusaoEnderecos.setTitle("Exclusão de endereços");
		frmExclusaoEnderecos.setBounds(100, 100, 310, 200);
		frmExclusaoEnderecos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExclusaoEnderecos.getContentPane().setLayout(null);

		JLabel lblIdEndereco = new JLabel("Informe o id:");
		lblIdEndereco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdEndereco.setBounds(30, 30, 80, 15);
		frmExclusaoEnderecos.getContentPane().add(lblIdEndereco);

		txtIdEndereco = new JTextField();
		txtIdEndereco.setBounds(120, 30, 150, 20);
		frmExclusaoEnderecos.getContentPane().add(txtIdEndereco);
		txtIdEndereco.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnderecoController controladora = new EnderecoController();

				String mensagem = controladora.excluir(txtIdEndereco.getText());
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnExcluir.setBounds(30, 80, 240, 70);
		frmExclusaoEnderecos.getContentPane().add(btnExcluir);
	}
}
