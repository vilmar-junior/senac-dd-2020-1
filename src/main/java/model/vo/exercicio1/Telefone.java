package model.vo.exercicio1;

public class Telefone {
	private int id;
	private Cliente dono;
	private String codigoPais;
	private String ddd;
	private String numero;
	private boolean movel;
	private boolean ativo;

	public Telefone(int id, Cliente dono, String codigoPais, String ddd, String numero, boolean movel, boolean ativo) {
		super();
		this.id = id;
		this.dono = dono;
		this.codigoPais = codigoPais;
		this.ddd = ddd;
		this.numero = numero;
		this.movel = movel;
		this.ativo = ativo;
	}

	public Telefone() {

	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}

	public boolean isMovel() {
		return movel;
	}

	public void setMovel(boolean movel) {
		this.movel = movel;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", dono=" + dono + ", codigoPais=" + codigoPais + ", ddd=" + ddd + ", numero="
				+ numero + ", movel=" + movel + ", ativo=" + ativo + "]";
	}
}