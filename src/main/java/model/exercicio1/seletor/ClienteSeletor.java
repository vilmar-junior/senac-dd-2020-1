package model.exercicio1.seletor;

public class ClienteSeletor {

	private String nome;
	private String sobrenome;
	private String cpf;

	public boolean temFiltro() {
		boolean temFiltroPreenchido = false;

		temFiltroPreenchido = (nome != null && nome.trim().length() > 0)
				|| (sobrenome != null && sobrenome.trim().length() > 0) 
				|| (cpf != null && cpf.trim().length() > 0);

		return temFiltroPreenchido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
