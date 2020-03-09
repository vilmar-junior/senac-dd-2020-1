package executavel.exercicio1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.bo.ClienteBO;
import model.dao.exercicio1.ClienteDAO;
import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ExecutavelExercicio1 {

	public static void main(String[] argumentosLinhaDeComando) {

		// TODO criar e SALVAR telefones
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();

		// Exercício 2
		Cliente cliente1 = obterClienteDaTela();

		// - Salvar no banco (APENAS TESTES, AINDA VIOLANDO O MVC)
		ClienteBO clienteBO = new ClienteBO();
		String mensagem = clienteBO.salvar(cliente1);

		JOptionPane.showMessageDialog(null, mensagem);

	}

	private static Cliente obterClienteDaTela() {
		String nome = JOptionPane.showInputDialog("Informe o nome");
		String sobrenome = JOptionPane.showInputDialog("Informe o sobrenome");
		String cpf = JOptionPane.showInputDialog("Informe o CPF");

		EnderecoDAO endDAO = new EnderecoDAO();
		ArrayList<Endereco> listaEnderecos = endDAO.consultarTodos();
		
		Object[] enderecos = listaEnderecos.toArray();
		Endereco enderecoSelecionado = (Endereco) JOptionPane.showInputDialog(null, 
				"Selecione um endereço", "Endereço", 
				JOptionPane.QUESTION_MESSAGE, 
				null, enderecos, null);

		Cliente novoCliente = new Cliente(nome, sobrenome, cpf, 
				new ArrayList<Telefone>(), enderecoSelecionado);

		return novoCliente;
	}
}
