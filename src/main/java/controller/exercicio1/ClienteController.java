package controller.exercicio1;

import java.util.ArrayList;

import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Cliente;

public class ClienteController {

	private ClienteDAO dao = new ClienteDAO();

	public ArrayList<Cliente> listarTodosOsClientes() {
		return dao.consultarTodos();
	}
}
