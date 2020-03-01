package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.AvisoCursoLivreVO;

public class AvisoCursoLivreDAO extends AvisoDAO{
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public boolean existeRegistroAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT avcl.nome, av.idaviso FROM avisocursolivre avcl, aviso av "
				+ "WHERE avcl.idaviso = av.idaviso "
				+ "and avcl.nome like '" + avisoCursoLivreVO.getNome().toUpperCase() + "' "
				+ "and av.dataexpiracao >= " + avisoCursoLivreVO.getDataExpiracao();
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Aviso de Curso Livre.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public boolean existeRegistroPorIdAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT avcl.idavisocoordenacao, av.idaviso FROM avisocoordenacao avcl, aviso av "
				+ "WHERE avcl.idaviso = av.idaviso "
				+ " and av.idaviso = " + avisoCursoLivreVO.getIdAviso()
				+ " and avcl.idavisocoordenacao = " + avisoCursoLivreVO.getIdAvisoCursoLivre();
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Aviso de Curso Livre por ID.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
	
	public int cadastrarAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		int idAviso = this.cadastrarAvisoDAO(avisoCursoLivreVO);
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO avisocursolivre (idaviso, nome, publicoalvo, requisito, ambiente, datacurso, valor) VALUES (" 
				+ idAviso + ", '" 
				+ avisoCursoLivreVO.getNome() + "', '"
				+ avisoCursoLivreVO.getPublicoAlvo() + "', '" 
				+ avisoCursoLivreVO.getRequisito() + "', '" 
				+ avisoCursoLivreVO.getAmbiente() + "', '" 
				+ avisoCursoLivreVO.getDataCurso() + "', " 
				+ avisoCursoLivreVO.getValor() + ") ";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Aviso de Curso Livre.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public int atualizarAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		boolean atualizado = this.atualizarAvisoDAO(avisoCursoLivreVO);
		int resultado = 0;
		if(atualizado) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			String query = "UPDATE avisocursolivre SET idaviso = " + avisoCursoLivreVO.getIdAviso() 
						+ ", nome = '" + avisoCursoLivreVO.getNome()
						+ "', publicoalvo = '" + avisoCursoLivreVO.getPublicoAlvo()
						+ "', requisito = '" + avisoCursoLivreVO.getRequisito()
						+ "', ambiente = '" + avisoCursoLivreVO.getAmbiente()
						+ "', datacurso = '" + avisoCursoLivreVO.getDataCurso()
						+ "', valor = " + avisoCursoLivreVO.getValor()
						+ " WHERE idavisocursolivre = " + avisoCursoLivreVO.getIdAvisoCursoLivre();
			try {
				resultado = stmt.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println("Erro ao executar a Query de Atualização do Aviso Curso Livre.");
				System.out.println("Erro: " + e.getMessage());
			} finally {
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
		} 
		return resultado;
	}

	public int excluirAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM avisocursolivre WHERE idavisocursolivre = " + avisoCursoLivreVO.getIdAvisoCursoLivre();
		try{
			resultado = stmt.executeUpdate(query);
			if(resultado == 1) {
				query = "DELETE FROM aviso WHERE idaviso = " + avisoCursoLivreVO.getIdAviso();
				resultado = 0;
				resultado = stmt.executeUpdate(query);
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Exclusão do Aviso de Curso Livre.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public ArrayList<AvisoCursoLivreVO> consultarTodosAvisoCursoLivreDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<AvisoCursoLivreVO> avisosCursoLivreVO = new ArrayList<AvisoCursoLivreVO>();
		String query = "SELECT av.idaviso, avcl.idavisocursolivre, av.idusuario, avcl.nome, "
				+ " avcl.publicoalvo, avcl.requisito, avcl.ambiente, avcl.datacurso, "
				+ " avcl.valor, av.datacadastro, av.dataexpiracao "
				+ " FROM aviso av, avisocursolivre avcl "
				+ " WHERE av.idaviso = avcl.idaviso";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				AvisoCursoLivreVO avisoCursoLivreVO = new AvisoCursoLivreVO();
				avisoCursoLivreVO.setIdAviso(Integer.parseInt(resultado.getString(1)));
				avisoCursoLivreVO.setIdAvisoCursoLivre(Integer.parseInt(resultado.getString(2)));
				avisoCursoLivreVO.setIdUsuario(Integer.parseInt(resultado.getString(3)));
				avisoCursoLivreVO.setNome(resultado.getString(4));
				avisoCursoLivreVO.setPublicoAlvo(resultado.getString(5));
				avisoCursoLivreVO.setRequisito(resultado.getString(6));
				avisoCursoLivreVO.setAmbiente(resultado.getString(7));
				avisoCursoLivreVO.setDataCurso(LocalDate.parse(resultado.getString(8), dataFormatter));
				avisoCursoLivreVO.setValor(Double.parseDouble(resultado.getString(9)));
				avisoCursoLivreVO.setDataCadastro(LocalDate.parse(resultado.getString(10), dataFormatter));
				avisoCursoLivreVO.setDataExpiracao(LocalDate.parse(resultado.getString(11), dataFormatter));
				avisosCursoLivreVO.add(avisoCursoLivreVO);
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta dos Avisos de Curso Livre.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return avisosCursoLivreVO;
	}

	public AvisoCursoLivreVO consultarAvisoCursoLivreDAO(AvisoCursoLivreVO avisoCursoLivreVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		AvisoCursoLivreVO avisoCursoLivre = new AvisoCursoLivreVO();
		String query = "SELECT av.idaviso, avcl.idavisocursolivre, av.idusuario, avcl.nome, "
				+ " avcl.publicoalvo, avcl.requisito, avcl.ambiente, avcl.datacurso, "
				+ " avcl.valor, av.datacadastro, av.dataexpiracao "
				+ " FROM aviso av, avisocoordenacao avcl "
				+ " WHERE av.idaviso = avc.idaviso "
				+ " and av.idaviso = " + avisoCursoLivreVO.getIdAviso()
				+ " and avc.idavisocursolivre = " + avisoCursoLivreVO.getIdAvisoCursoLivre();
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				avisoCursoLivre.setIdAviso(Integer.parseInt(resultado.getString(1)));
				avisoCursoLivre.setIdAvisoCursoLivre(Integer.parseInt(resultado.getString(2)));
				avisoCursoLivre.setIdUsuario(Integer.parseInt(resultado.getString(3)));
				avisoCursoLivre.setNome(resultado.getString(4));
				avisoCursoLivre.setPublicoAlvo(resultado.getString(5));
				avisoCursoLivre.setRequisito(resultado.getString(6));
				avisoCursoLivre.setAmbiente(resultado.getString(7));
				avisoCursoLivre.setDataCurso(LocalDate.parse(resultado.getString(8), dataFormatter));
				avisoCursoLivre.setValor(Double.parseDouble(resultado.getString(9)));
				avisoCursoLivre.setDataCadastro(LocalDate.parse(resultado.getString(10), dataFormatter));
				avisoCursoLivre.setDataExpiracao(LocalDate.parse(resultado.getString(11), dataFormatter));
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Consulta do Aviso de Curso Livre.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return avisoCursoLivre;
	}

}
