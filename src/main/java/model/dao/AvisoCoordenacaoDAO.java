package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.vo.AvisoCoordenacaoVO;

public class AvisoCoordenacaoDAO extends AvisoDAO{
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public boolean existeRegistroAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT avc.descricao FROM avisocoordenacao avc, aviso av "
				+ "WHERE avc.idaviso = av.idaviso "
				+ "and avc.descricao like '" + avisoCoordenacaoVO.getDescricao().toUpperCase() + "' "
				+ "and av.dataexpiracao >= " + avisoCoordenacaoVO.getDataExpiracao();
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Aviso da Coordenação.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
	
	
	public boolean existeRegistroPorIdAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT avc.idavisocoordenacao, av.idaviso FROM avisocoordenacao avc, aviso av "
				+ "WHERE avc.idaviso = av.idaviso "
				+ " and av.idaviso = " + avisoCoordenacaoVO.getIdAviso()
				+ " and avc.idavisocoordenacao = " + avisoCoordenacaoVO.getIdAvisoCoordenacao();
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Aviso da Coordenação por ID.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	
	public int cadastrarAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		int idAviso = this.cadastrarAvisoDAO(avisoCoordenacaoVO);
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO avisocoordenacao (idaviso, descricao) VALUES (" 
				+ idAviso + ", '" 
				+ avisoCoordenacaoVO.getDescricao() + "')";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Aviso da Coordenação.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}


	public int atualizarAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		boolean atualizado = this.atualizarAvisoDAO(avisoCoordenacaoVO);
		int resultado = 0;
		if(atualizado) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			String query = "UPDATE avisocoordenacao SET idaviso = " + avisoCoordenacaoVO.getIdAviso() 
						+ ", descricao = '" + avisoCoordenacaoVO.getDescricao()
						+ "' WHERE idavisocoordenacao = " + avisoCoordenacaoVO.getIdAvisoCoordenacao();
			try {
				resultado = stmt.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println("Erro ao executar a Query de Atualização do Aviso da Coordenação.");
				System.out.println("Erro: " + e.getMessage());
			} finally {
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
		} 
		return resultado;
	}


	public int excluirAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM avisocoordenacao WHERE idavisocoordenacao = " + avisoCoordenacaoVO.getIdAvisoCoordenacao();
		try{
			resultado = stmt.executeUpdate(query);
			if(resultado == 1) {
				query = "DELETE FROM aviso WHERE idaviso = " + avisoCoordenacaoVO.getIdAviso();
				resultado = 0;
				resultado = stmt.executeUpdate(query);
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Exclusão do Aviso da Coordenação.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}


	public ArrayList<AvisoCoordenacaoVO> consultarTodosAvisoCoordenacaoDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<AvisoCoordenacaoVO> avisosCoordenacaoVO = new ArrayList<AvisoCoordenacaoVO>();
		String query = "SELECT av.idaviso, avc.idavisocoordenacao, av.idusuario, avc.descricao, av.datacadastro, av.dataexpiracao "
				+ " FROM aviso av, avisocoordenacao avc "
				+ " WHERE av.idaviso = avc.idaviso";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				AvisoCoordenacaoVO avisoCoordenacaoVO = new AvisoCoordenacaoVO();
				avisoCoordenacaoVO.setIdAviso(Integer.parseInt(resultado.getString(1)));
				avisoCoordenacaoVO.setIdAvisoCoordenacao(Integer.parseInt(resultado.getString(2)));
				avisoCoordenacaoVO.setIdUsuario(Integer.parseInt(resultado.getString(3)));
				avisoCoordenacaoVO.setDescricao(resultado.getString(4));
				avisoCoordenacaoVO.setDataCadastro(LocalDate.parse(resultado.getString(5), dataFormatter));
				avisoCoordenacaoVO.setDataExpiracao(LocalDate.parse(resultado.getString(6), dataFormatter));
				avisosCoordenacaoVO.add(avisoCoordenacaoVO);
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta dos Avisos da Coordenação.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return avisosCoordenacaoVO;
	}


	public AvisoCoordenacaoVO consultarAvisoCoordenacaoDAO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		AvisoCoordenacaoVO avisoCoordenacao = new AvisoCoordenacaoVO();
		String query = "SELECT av.idaviso, avc.idavisocoordenacao, av.idusuario, avc.descricao, av.datacadastro, av.dataexpiracao "
				+ " FROM aviso av, avisocoordenacao avc "
				+ " WHERE av.idaviso = avc.idaviso "
				+ " and av.idaviso = " + avisoCoordenacaoVO.getIdAviso()
				+ " and avc.idavisocoordenacao = " + avisoCoordenacaoVO.getIdAvisoCoordenacao();
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				avisoCoordenacao.setIdAviso(Integer.parseInt(resultado.getString(1)));
				avisoCoordenacao.setIdAvisoCoordenacao(Integer.parseInt(resultado.getString(2)));
				avisoCoordenacao.setIdUsuario(Integer.parseInt(resultado.getString(3)));
				avisoCoordenacao.setDescricao(resultado.getString(4));
				avisoCoordenacao.setDataCadastro(LocalDate.parse(resultado.getString(5), dataFormatter));
				avisoCoordenacao.setDataExpiracao(LocalDate.parse(resultado.getString(6), dataFormatter));
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta do Aviso da Coordenação.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return avisoCoordenacao;
	}
	

}
