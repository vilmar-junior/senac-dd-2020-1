package controller.exercicio1;

import model.bo.exercicio1.EnderecoBO;

public class EnderecoController {

	private EnderecoBO bo = new EnderecoBO();

	public String excluir(String textoIdSelecionado) {

		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = bo.excluir(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um número inteiro";
		}
		return mensagem;
	}

}
