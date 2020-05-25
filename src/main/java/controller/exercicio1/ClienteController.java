package controller.exercicio1;

import java.util.ArrayList;

import model.bo.ClienteBO;
import model.dao.exercicio1.ClienteDAO;
import model.exercicio1.seletor.ClienteSeletor;
import model.vo.exercicio1.Cliente;

public class ClienteController {

	private ClienteDAO dao = new ClienteDAO();

	public ArrayList<Cliente> listarTodosOsClientes() {
		return dao.consultarTodos();
	}

	public ArrayList<Cliente> listarClientes(ClienteSeletor seletor) {
		return dao.consultarPorSeletor(seletor);
	}

	public void gerarRelatorio(ArrayList<Cliente> clientes, String caminhoEscolhido) {
		ClienteBO bo = new ClienteBO();
		bo.gerarRelatorio(clientes, caminhoEscolhido);
	}
}
