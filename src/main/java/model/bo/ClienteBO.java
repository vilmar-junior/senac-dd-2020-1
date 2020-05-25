package model.bo;

import java.util.ArrayList;

import model.dao.exercicio1.ClienteDAO;
import model.util.GeradorPlanilha;
import model.vo.exercicio1.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();
	/**
	 * Tenta salvar um cliente novo, validando o CPF
	 * @param cliente o Cliente a ser salvo no banco
	 * @return
	 */
	public String salvar(Cliente cliente) {
		String mensagem = "";
		
		if(dao.cpfJaUtilizado(cliente.getCpf())) {
			mensagem = "CPF informado (" + cliente.getCpf() + ") já foi utilizado";
		}else {
			cliente = dao.salvar(cliente);
			
			if(cliente.getId() > 0) {
				mensagem = "Cliente salvo com sucesso";
			}else {
				mensagem = "Erro ao salvar cliente";
			}
		}
		
		return mensagem;
	}

	public void gerarRelatorio(ArrayList<Cliente> clientes, String caminhoEscolhido) {
		GeradorPlanilha gerador = new GeradorPlanilha();
		gerador.gerarPlanilhaClientes(clientes, caminhoEscolhido);
	}
}
