package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvisoCursoLivreVO extends AvisoVO {
	
	DateTimeFormatter formataDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private int idAvisoCursoLivre;
	private String nome;
	private String publicoAlvo;
	private String requisito;
	private String ambiente;
	private LocalDate dataCurso;
	private double valor;
	
	public AvisoCursoLivreVO(int idAviso, int idUsuario, LocalDate dataCadastro, LocalDate dataExpiracao,
			int idAvisoCursoLivre, String nome, String publicoAlvo, String requisito, String ambiente,
			LocalDate dataCurso, double valor) {
		super(idAviso, idUsuario, dataCadastro, dataExpiracao);
		this.idAvisoCursoLivre = idAvisoCursoLivre;
		this.nome = nome;
		this.publicoAlvo = publicoAlvo;
		this.requisito = requisito;
		this.ambiente = ambiente;
		this.dataCurso = dataCurso;
		this.valor = valor;
	}

	public AvisoCursoLivreVO() {
		super();
	}

	public int getIdAvisoCursoLivre() {
		return idAvisoCursoLivre;
	}

	public void setIdAvisoCursoLivre(int idAvisoCursoLivre) {
		this.idAvisoCursoLivre = idAvisoCursoLivre;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getRequisito() {
		return requisito;
	}

	public void setRequisito(String requisito) {
		this.requisito = requisito;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public LocalDate getDataCurso() {
		return dataCurso;
	}

	public void setDataCurso(LocalDate dataCurso) {
		this.dataCurso = dataCurso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void imprimir() {
		// TODO Auto-generated method stub
		
	}
	
}
