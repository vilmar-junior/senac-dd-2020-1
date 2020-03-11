package executavel.exercicio1;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.exercicio1.ClienteController;
import controller.exercicio1.TelefoneController;
import model.bo.ClienteBO;
import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ExecutavelExercicio1 {

	public static void main(String[] argumentosLinhaDeComando) {

		// executarExercicio2();

		executarExercicio3();
	}

	/**
	 * Exercício 2 - Cadastro de cliente com JOptionPane
	 * 
	 * Obtém um cliente da tela e salva no banco
	 * 
	 * Obs.: ainda está violando o MVC, pois chama um BO (da camada model), o
	 * correto é chamar um controller.
	 */
	private static void executarExercicio2() {
		Cliente cliente1 = obterClienteDaTela();

		// - Salvar no banco (APENAS TESTES, AINDA VIOLANDO O MVC)
		ClienteBO clienteBO = new ClienteBO();
		String mensagem = clienteBO.salvar(cliente1);

		JOptionPane.showMessageDialog(null, mensagem);
	}

	/**
	 * Exercício 3 - Cadastro de telefones com JOptionPane
	 * 
	 * Obtém um telefone da tela e salva no banco
	 * 
	 * Salvar telefones com e sem dono.
	 */
	private static void executarExercicio3() {
		Telefone novoTelefone = obterTelefoneDaTela();

		TelefoneController controlador = new TelefoneController();
		String mensagem = controlador.salvar(novoTelefone);

		JOptionPane.showMessageDialog(null, mensagem);
	}

	private static Telefone obterTelefoneDaTela() {
		Telefone novoTelefone = new Telefone();

		String codigoPais = JOptionPane.showInputDialog("Informe o código do país");
		String ddd = JOptionPane.showInputDialog("Informe o DDD (2 dígitos)");
		String numero = JOptionPane.showInputDialog("Informe o número");
		int opcaoMovel = JOptionPane.showConfirmDialog(null, "O telefone é móvel?", "Selecione",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		int opcaoAtivo = JOptionPane.showConfirmDialog(null, "O telefone está ativo?", "Selecione",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

		novoTelefone.setCodigoPais(codigoPais);
		novoTelefone.setDdd(ddd);
		novoTelefone.setNumero(numero);
		novoTelefone.setMovel(opcaoMovel == JOptionPane.YES_OPTION);
		novoTelefone.setAtivo(opcaoAtivo == JOptionPane.YES_OPTION);

		int opcaoDono = JOptionPane.showConfirmDialog(null, "O telefone possui dono?", "Selecione",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

		if (opcaoDono == JOptionPane.YES_OPTION) {
			ClienteController controlador = new ClienteController();
			ArrayList<Cliente> clientes = controlador.listarTodosOsClientes();

			Cliente clienteSelecionado = (Cliente) JOptionPane.showInputDialog(null, "Selecione um cliente", "Clientes",
					JOptionPane.QUESTION_MESSAGE, null, clientes.toArray(), null);

			novoTelefone.setDono(clienteSelecionado);
		}

		return novoTelefone;
	}

	private static Cliente obterClienteDaTela() {
		String nome = JOptionPane.showInputDialog("Informe o nome");
		String sobrenome = JOptionPane.showInputDialog("Informe o sobrenome");
		String cpf = JOptionPane.showInputDialog("Informe o CPF");

		EnderecoDAO endDAO = new EnderecoDAO();
		ArrayList<Endereco> listaEnderecos = endDAO.consultarTodos();

		Object[] enderecos = listaEnderecos.toArray();
		Endereco enderecoSelecionado = (Endereco) JOptionPane.showInputDialog(null, "Selecione um endereço", "Endereço",
				JOptionPane.QUESTION_MESSAGE, null, enderecos, null);

		Cliente novoCliente = new Cliente(nome, sobrenome, cpf, new ArrayList<Telefone>(), enderecoSelecionado);

		return novoCliente;
	}
}
