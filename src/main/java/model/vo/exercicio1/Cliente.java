package model.vo.exercicio1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {
	private int id;
	private String nome;
	private String sobrenome;
	private String cpf;
	private LocalDate dataNascimento;
	private ArrayList<Telefone> telefones;
	private Endereco endereco;

	public Cliente() {
		
	}
	
	public Cliente(String nome, String sobrenome, String cpf, ArrayList<Telefone> telefones, Endereco endereco,
			LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}

	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
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

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		String mensagem = "Cliente: " + this.getNomeCompleto() + " (" + cpf + ")";

//		for (Telefone t : telefones) {
//			mensagem += t.toString() + "\n";
//		}

		return mensagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}