package model.exercicio1.seletor;

import java.time.LocalDate;

public class ClienteSeletor {

	private String nome;
	private String sobrenome;
	private String cpf;
	private LocalDate dataNascimentoInicial;
	private LocalDate dataNascimentoFinal;

	public boolean temFiltro() {
		boolean temFiltroPreenchido = false;

		temFiltroPreenchido = (nome != null && nome.trim().length() > 0)
				|| (sobrenome != null && sobrenome.trim().length() > 0) 
				|| (cpf != null && cpf.trim().length() > 0) || dataNascimentoInicial != null
				|| dataNascimentoFinal != null;

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

	public LocalDate getDataNascimentoInicial() {
		return dataNascimentoInicial;
	}

	public void setDataNascimentoInicial(LocalDate dataNascimentoInicial) {
		this.dataNascimentoInicial = dataNascimentoInicial;
	}

	public LocalDate getDataNascimentoFinal() {
		return dataNascimentoFinal;
	}

	public void setDataNascimentoFinal(LocalDate dataNascimentoFinal) {
		this.dataNascimentoFinal = dataNascimentoFinal;
	}
}
