package model.bo.exercicio1;

import java.util.ArrayList;

import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Cliente;

public class ClienteBO {

	private ClienteDAO dao;

	public ArrayList<Cliente> pesquisarTodos() {
		return dao.consultarTodos();
	}

}
