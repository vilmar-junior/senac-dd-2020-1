package model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.vo.exercicio1.Endereco;

public class EnderecoDAO {

	// TODO padronizar os nomes!!
	public Endereco inserir(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, CEP, ESTADO, CIDADE, BAIRRO, NUMERO) " + " VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;
		try {
			stmt.setString(1, novoEndereco.getRua());
			stmt.setString(2, novoEndereco.getCep());
			stmt.setString(3, novoEndereco.getEstado());
			stmt.setString(4, novoEndereco.getCidade());
			stmt.setString(5, novoEndereco.getBairro());
			stmt.setString(6, novoEndereco.getNumero());

			stmt.execute();

			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				novoEndereco.setId(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return novoEndereco;
	}

	public boolean apagar(int id) {
		Connection conexao = Banco.getConnection();
		Statement statement = Banco.getStatement(conexao);
		String sql = " DELETE FROM ENDERECO WHERE ID = " + id;

		int quantidadeRegistrosExcluidos = 0;
		try {
			quantidadeRegistrosExcluidos = statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço.");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(statement);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosExcluidos > 0;
	}

	public boolean alterar(Endereco endereco) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO SET RUA=?, CEP=?, ESTADO=?, CIDADE=?, BAIRRO=?, NUMERO=? "
		+ " WHERE ID=?";
		
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int quantidadeRegistrosAtualizados = 0;
		try {
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getEstado());
			stmt.setString(4, endereco.getCidade());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getNumero());
			stmt.setInt(7, endereco.getId());

			quantidadeRegistrosAtualizados = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosAtualizados == 1;
	}

	public Endereco consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO WHERE ID=?";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		Endereco enderecoBuscado = null;
		try {
			stmt.setInt(1, id);
			resultadoDaConsulta = stmt.executeQuery();
			
			if(resultadoDaConsulta.next()) {
				enderecoBuscado = construirEnderecoDoResultSet(resultadoDaConsulta);
			}
		}catch(SQLException ex) {
			System.out.println("Erro ao consultar endereço por id: " + id);
			System.out.println("Erro: " + ex.getMessage());
		}finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return enderecoBuscado;
	}

	public ArrayList<Endereco> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			resultadoDaConsulta = stmt.executeQuery();
			
			while(resultadoDaConsulta.next()) {
				Endereco enderecoBuscado = construirEnderecoDoResultSet(resultadoDaConsulta);
				enderecos.add(enderecoBuscado);
			}
		}catch(SQLException ex) {
			System.out.println("Erro ao consultar endereços cadastrados ");
			System.out.println("Erro: " + ex.getMessage());
		}finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return enderecos;
	}
	
	/**
	 * 
	 * Constrói um objeto do tipo Endereco a partir de um registro do resultSet
	 * 
	 * @param resultadoDaConsulta o item do resultSet (isto é, um registro da tabela
	 *                            Endereco)
	 * @return um objeto do tipo Endereco
	 * 
	 */
	private Endereco construirEnderecoDoResultSet(ResultSet rs) {
		Endereco enderecoBuscado = new Endereco();
		try {
			enderecoBuscado.setId(rs.getInt("id"));
			enderecoBuscado.setBairro(rs.getString("bairro"));
			enderecoBuscado.setCidade(rs.getString("cidade"));
			enderecoBuscado.setEstado(rs.getString("estado"));
			enderecoBuscado.setCep(rs.getString("cep"));
			enderecoBuscado.setNumero(rs.getString("numero"));
			enderecoBuscado.setRua(rs.getString("rua"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir endereços a partir do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return enderecoBuscado;
	}
}