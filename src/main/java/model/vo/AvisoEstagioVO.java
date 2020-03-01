package model.vo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvisoEstagioVO extends AvisoVO{
	
	DateTimeFormatter formataDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("0.00");

	private int idAvisoEstagio;
	private String empresa;
	private String telefone;
	private String email;
	private String cargo;
	private int jornada;
	private double remuneracao;
	
	public AvisoEstagioVO(int idAviso, int idUsuario, LocalDate dataCadastro, LocalDate dataExpiracao, int idAvisoEstagio,
			String empresa, String telefone, String email, String cargo, int jornada, double remuneracao) {
		super(idAviso, idUsuario, dataCadastro, dataExpiracao);
		this.idAvisoEstagio = idAvisoEstagio;
		this.empresa = empresa;
		this.telefone = telefone;
		this.email = email;
		this.cargo = cargo;
		this.jornada = jornada;
		this.remuneracao = remuneracao;
	}

	public AvisoEstagioVO() {
		super();
	}

	public int getIdAvisoEstagio() {
		return idAvisoEstagio;
	}

	public void setIdAvisoEstagio(int idAvisoEstagio) {
		this.idAvisoEstagio = idAvisoEstagio;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public double getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(double remuneracao) {
		this.remuneracao = remuneracao;
	}
 
	public void imprimir() {
		System.out.printf("\n%8d   %16d   %-30s   %-13s   %-20s   %-30s   %-7d   %-11s   %-15s   %-15s \n", 
				this.getIdAviso(), 
				this.getIdAvisoEstagio(), 
				this.getEmpresa(), 
				this.getTelefone(), 
				this.getEmail(), 
				this.getCargo(), 
				this.getJornada(), 
				df.format(this.getRemuneracao()), 
				this.getDataCadastro().format(formataDate), 
				this.getDataExpiracao().format(formataDate));
		
	}
	
}
