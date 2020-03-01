package model.vo;

public class TipoUsuarioVO {
	
	private int idTipoUsuario;
	private String descricao;
	
	public TipoUsuarioVO(int idTipoUsuario, String descricao) {
		super();
		this.idTipoUsuario = idTipoUsuario;
		this.descricao = descricao;
	}

	public TipoUsuarioVO() {
		super();
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
