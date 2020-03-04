package executavel.exercicio1;

import java.util.ArrayList;
import java.util.List;

import model.bo.ClienteBO;
import model.dao.exercicio1.ClienteDAO;
import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ExecutavelExercicio1 {

	public static void main(String[] argumentosLinhaDeComando) {
		
		//Buscar e mostrar o endereco 1
		EnderecoDAO endDAO = new EnderecoDAO();
		Endereco enderecoConsultado = endDAO.consultarPorId(3);
		//System.out.println("Endereço 3: " + enderecoConsultado.toString());
	
		//TODO criar e SALVAR telefones
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		
		//Exercício 2
		//- Criar 5 clientes
		Cliente cliente1 = new Cliente("Edson", "Arantes", "11100011100", telefones , enderecoConsultado);
		Cliente cliente2 = new Cliente("Sócrates", "Brasileiro", "22211122211", telefones, enderecoConsultado);
		Cliente cliente3 = new Cliente("Roberto", "Rivellino", "33322233322", telefones, enderecoConsultado);
		Cliente cliente4 = new Cliente("Romário", "de Souza", "55544455544", telefones, enderecoConsultado);
		Cliente cliente5 = new Cliente("Ronaldo", "Nazário", "66655566655", telefones, enderecoConsultado);
		
		//- Salvar no banco (APENAS TESTES, AINDA VIOLANDO O MVC)
		ClienteBO clienteBO = new ClienteBO();
		String mensagem = clienteBO.salvar(cliente1);
		
		System.out.println(mensagem);
		
		//- Consultar no banco
		
	}
}
