package model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.vo.exercicio1.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {

	public Endereco salvar(Endereco novaEntidade) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO ENDERECO (CEP, ESTADO, CIDADE, RUA, BAIRRO, NUMERO) " + " VALUES ( "
				+ novaEntidade.getCep() + ", " + novaEntidade.getEstado() + "," + novaEntidade.getCidade() + ", "
				+ novaEntidade.getRua() + "," + novaEntidade.getBairro() + "," + novaEntidade.getNumero() + ")";

		PreparedStatement statement = Banco.getPreparedStatement(conexao, sql);
		try {
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultado = statement.getGeneratedKeys();

			if (resultado.next()) {
				novaEntidade.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(" Erro ao salvar novo endereço. Causa: " + e.getMessage());
		}

		return novaEntidade;
	}

	public boolean excluir(int id) {
		String sql = " DELETE FROM endereco WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		boolean excluiu = false;
		try {
			preparedStatement.setInt(1, id);
			int codigoRetornoUpdate = preparedStatement.executeUpdate();

			excluiu = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao excluir endereço. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return excluiu;
	}

	public boolean alterar(Endereco endereco) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO SET CEP=?, ESTADO=?, CIDADE=?, RUA=?, BAIRRO=?, NUMERO=? WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;

		try {
			stmt.setString(1, endereco.getCep());
			stmt.setString(2, endereco.getEstado());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getRua());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getNumero());
			stmt.setInt(7, endereco.getId());

			registrosAlterados = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public Endereco consultarPorId(int id) {
		String sql = " SELECT * FROM endereco WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		Endereco enderecoConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				enderecoConsultado = construirEnderecoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereço. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return enderecoConsultado;
	}

	private Endereco construirEnderecoDoResultSet(ResultSet conjuntoResultante) {
		Endereco e = new Endereco();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setCep(conjuntoResultante.getString("cep"));
			e.setBairro(conjuntoResultante.getString("bairro"));
			e.setCidade(conjuntoResultante.getString("cidade"));
			e.setEstado(conjuntoResultante.getString("estado"));
			e.setRua(conjuntoResultante.getString("rua"));
			e.setNumero(conjuntoResultante.getString("numero"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir endereço a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

	public ArrayList<Endereco> consultarTodos() {
		String sql = " SELECT * FROM endereco ";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			while (conjuntoResultante.next()) {
				Endereco enderecoConsultado = construirEnderecoDoResultSet(conjuntoResultante);
				enderecos.add(enderecoConsultado);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereços. Causa: " + ex.getMessage());
		}
		return enderecos;
	}

	public boolean temEnderecoCadastradoComId(int idSelecionado) {
		String sql = " SELECT id FROM endereco WHERE id = " + idSelecionado;

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);

		boolean enderecoJaCadastrado = false;
		try {
			ResultSet conjuntoResultante = preparedStatement.executeQuery();
			enderecoJaCadastrado = conjuntoResultante.next();
		} catch (SQLException ex) {
			System.out.println(" Erro ao verificar se endereço consta no banco. Causa: " + ex.getMessage());
		}

		return enderecoJaCadastrado;
	}

}