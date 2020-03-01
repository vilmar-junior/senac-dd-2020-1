package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.vo.AvisoVO;

public abstract class AvisoDAO {
	
	public int cadastrarAvisoDAO(AvisoVO avisoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet chave = null;
		int resultado = 0;
		String query = "INSERT INTO aviso (idusuario, datacadastro, dataexpiracao) VALUES (" 
				+ avisoVO.getIdUsuario() + ", '" 
				+ avisoVO.getDataCadastro() + "', '" 
				+ avisoVO.getDataExpiracao() + "')";
		try {
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			chave = stmt.getGeneratedKeys();
			if(chave.next()) {
				resultado = chave.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Aviso.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}
	
	public boolean atualizarAvisoDAO(AvisoVO avisoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean atualizado = false;
		int resultado = 0;
		String query = "UPDATE aviso SET idusuario = " + avisoVO.getIdUsuario() + ", " 
					+ " datacadastro = '" + avisoVO.getDataCadastro() + "', " 
					+ " dataexpiracao = '" + avisoVO.getDataExpiracao() + "' " 
					+ " WHERE idaviso = " + avisoVO.getIdAviso();
		try {
			resultado = stmt.executeUpdate(query);
			if(resultado == 1) {
				atualizado = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Atualização do Aviso.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return atualizado;
	}

}
