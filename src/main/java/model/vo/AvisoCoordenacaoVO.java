package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvisoCoordenacaoVO extends AvisoVO {
	
	DateTimeFormatter formataDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private int idAvisoCoordenacao;
	private String descricao;
	
	public AvisoCoordenacaoVO(int idAviso, int idUsuario, LocalDate dataCadastro, LocalDate dataExpiracao,
			int idAvisoCoordenacao, String descricao) {
		super(idAviso, idUsuario, dataCadastro, dataExpiracao);
		this.idAvisoCoordenacao = idAvisoCoordenacao;
		this.descricao = descricao;
	}

	public AvisoCoordenacaoVO() {
		super();
	}

	public int getIdAvisoCoordenacao() {
		return idAvisoCoordenacao;
	}

	public void setIdAvisoCoordenacao(int idAvisoCoordenacao) {
		this.idAvisoCoordenacao = idAvisoCoordenacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void imprimir() {
		System.out.printf("\n%8d   %15d   %-50s   %-15s   %-15s  \n", 
				this.getIdAviso(), 
				this.getIdAvisoCoordenacao(), 
				this.getDescricao(), 
				this.getDataCadastro().format(formataDate), 
				this.getDataExpiracao().format(formataDate));
	}
	
}
