package view.aula10;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import view.aula10.paineis.PainelCadastroTelefone;

public class MenuPrincipal extends JFrame {

	private JDesktopPane desktopPane;
	private PainelCadastroTelefone painelCadastroTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setExtendedState(MAXIMIZED_BOTH);
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
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuCliente = new JMenu("Clientes");
		menuCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-gestão-de-cliente.png")));
		menuBar.add(menuCliente);

		JMenuItem miCadastrarCliente = new JMenuItem("Cadastrar");
		miCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInternaCadastroCliente novaTelinha = new TelaInternaCadastroCliente();
				desktopPane.add(novaTelinha);
				novaTelinha.show();
			}
		});
		miCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		miCadastrarCliente.setIcon(
				new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		menuCliente.add(miCadastrarCliente);

		JMenu menuTelefone = new JMenu("Telefones");
		menuTelefone.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-cardápio.png")));

		JMenuItem miCadastrarTelefone = new JMenuItem("Cadastrar");
		miCadastrarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// instanciar o painel de cadastro de telefones
				painelCadastroTelefone = new PainelCadastroTelefone();

				// alterar o contentPane da tela principal, mostrando o painel de cadastro de
				// telefones
				setContentPane(painelCadastroTelefone);
				revalidate();
			}
		});
				
		miCadastrarTelefone.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		miCadastrarTelefone.setIcon(
				new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));

		menuTelefone.add(miCadastrarTelefone);
		menuBar.add(menuTelefone);

		JMenu menuSobre = new JMenu("Sobre");
		menuSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-confiança.png")));
		menuBar.add(menuSobre);

		JMenuItem miAutor = new JMenuItem("Autor");
		miAutor.setIcon(
				new ImageIcon(MenuPrincipal.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		miAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO mostrar um JFrame com informações do autor
				TelaSobreAutor janelaSobre = new TelaSobreAutor();
				janelaSobre.setVisible(true);
			}
		});

		menuSobre.add(miAutor);

		/*
		 * TODO usar apenas na opção 2 da aula 10
		 * 
		 * desktopPane = new JDesktopPane();
		 * 
		 * desktopPane.setBackground(Color.GRAY); desktopPane.setBounds(5, 5, 1000,
		 * 800); getContentPane().add(desktopPane);
		 */
		
	}
}
