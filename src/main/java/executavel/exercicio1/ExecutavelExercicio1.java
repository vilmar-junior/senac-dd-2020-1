package executavel.exercicio1;

import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Endereco;

public class ExecutavelExercicio1 {

	public static void main(String[] argumentosLinhaDeComando) {
		
		//Buscar e mostrar o endereco 1
		EnderecoDAO endDAO = new EnderecoDAO();
		Endereco enderecoConsultado = endDAO.consultarPorId(3);
		System.out.println("Endereço 3: " + enderecoConsultado.toString());
		
		//Já com as tabelas criadas...
		
		//TODO exercício 2
		//- Criar 5 clientes
		
		//- Salvar no banco
		
		//- Consultar no banco
		
	}
}
