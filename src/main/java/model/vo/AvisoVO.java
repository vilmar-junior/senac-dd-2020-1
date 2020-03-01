package model.vo;

import java.time.LocalDate;

public abstract class AvisoVO {
	
	private int idAviso;
	private int idUsuario;
	private LocalDate dataCadastro;
	private LocalDate dataExpiracao;
	
	public AvisoVO(int idAviso, int idUsuario, LocalDate dataCadastro, LocalDate dataExpiracao) {
		super();
		this.idAviso = idAviso;
		this.idUsuario = idUsuario;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
	}
	
	public AvisoVO() {
		super();
	}

	public int getIdAviso() {
		return idAviso;
	}
	
	public void setIdAviso(int idAviso) {
		this.idAviso = idAviso;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}
	
	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
}
