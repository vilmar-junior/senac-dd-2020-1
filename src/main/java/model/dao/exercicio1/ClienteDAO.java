package model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.exercicio1.seletor.ClienteSeletor;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ClienteDAO {

	public Cliente salvar(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, SOBRENOME, CPF, IDENDERECO, DATANASCIMENTO) "
				+ " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getSobrenome());
			stmt.setString(3, novoCliente.getCpf());
			stmt.setInt(4, novoCliente.getEndereco().getId());
			stmt.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novoCliente.setId(idGerado);

				if (!novoCliente.getTelefones().isEmpty()) {
					TelefoneDAO telefoneDAO = new TelefoneDAO();
					telefoneDAO.ativarTelefones(novoCliente, novoCliente.getTelefones());
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoCliente;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		boolean excluiu = quantidadeLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.desativarTelefones(id);
		}

		return excluiu;
	}

	public boolean alterar(Cliente cliente) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE SET NOME=?, SOBRENOME=?, CPF=?, IDENDERECO=?, DATANASCIMENTO=? "
				+ " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobrenome());
			stmt.setString(3, cliente.getCpf());
			stmt.setInt(4, cliente.getEndereco().getId());
			stmt.setDate(5, java.sql.Date.valueOf(cliente.getDataNascimento()));
			stmt.setInt(6, cliente.getId());
			registrosAlterados = stmt.executeUpdate();
			 
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.ativarTelefones(cliente, cliente.getTelefones());

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return registrosAlterados > 0;
	}

	public Cliente consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE WHERE id = " + id;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		Cliente cliente = null;
		try {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cliente = construirClienteDoResultSet(rs);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar cliente com id: " + id);
			System.out.println("Erro: " + e.getMessage());
		}

		return cliente;
	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Cliente c = construirClienteDoResultSet(rs);
				clientes.add(c);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return clientes;
	}

	public ArrayList<Cliente> consultarPorSeletor(ClienteSeletor seletor) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE C ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(sql, seletor);
		}

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente c = construirClienteDoResultSet(rs);
				clientes.add(c);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		}

		return clientes;
	}

	/**
	 * Recebe uma query de base (somente o SELECT) e inclui os filtros conforme os
	 * atributos do seletor
	 * 
	 * @param sql     a consulta básica
	 * @param seletor objeto que encapsula todos os possíveis valores selecionados
	 *                para a filtragem.
	 * 
	 * @return a query completa (base + filtros)
	 */
	private String criarFiltros(String sql, ClienteSeletor seletor) {

		boolean primeiro = true;
		sql += " WHERE ";

		if (seletor.getNome() != null && seletor.getNome().trim().length() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			sql += " C.NOME LIKE " + "'%" + seletor.getNome() + "%' ";
			primeiro = false;
		}

		if (seletor.getSobrenome() != null && seletor.getSobrenome().trim().length() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			sql += " C.SOBRENOME LIKE " + "'%" + seletor.getSobrenome() + "%' ";
		}

		if (seletor.getCpf() != null && seletor.getCpf().trim().length() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			sql += " C.CPF LIKE " + "'%" + seletor.getCpf() + "%' ";
		}

		if (seletor.getDataNascimentoInicial() != null || seletor.getDataNascimentoFinal() != null) {
			if (!primeiro) {
				sql += " AND ";
			}

			if (seletor.getDataNascimentoInicial() != null && seletor.getDataNascimentoFinal() != null) {
				sql += "C.DATANASCIMENTO BETWEEN '" + seletor.getDataNascimentoInicial() + "' AND '"
						+ seletor.getDataNascimentoFinal() + "'";
			} else {
				if (seletor.getDataNascimentoInicial() == null) {
					sql += "C.DATANASCIMENTO <= '" + seletor.getDataNascimentoFinal() + "'";
				}

				if (seletor.getDataNascimentoFinal() == null) {
					sql += "C.DATANASCIMENTO >= '" + seletor.getDataNascimentoInicial() + "'";
				}
			}
		}

		return sql;
	}

	/**
	 * 
	 * Constrói um objeto do tipo Cliente a partir de um registro do resultSet
	 * 
	 * @param resultadoDaConsulta o item do resultSet (isto é, um registro da tabela
	 *                            Cliente)
	 * @return um objeto do tipo Cliente
	 * 
	 */
	private Cliente construirClienteDoResultSet(ResultSet rs) {
		Cliente c = new Cliente();
		try {
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setSobrenome(rs.getString("sobrenome"));
			c.setCpf(rs.getString("cpf"));
			c.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco end = enderecoDAO.consultarPorId(rs.getInt("idendereco"));
			c.setEndereco(end);
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			ArrayList<Telefone> telefones = telefoneDAO.consultarTodosPorIdCliente(rs.getInt("id"));
			c.setTelefones(telefones);
		} catch (SQLException e) {
			System.out.println("Erro ao construir cliente a partir do ResultSet. Causa: " + e.getMessage());
		}
		
		return c;
	}

	public boolean cpfJaUtilizado(String cpf) {
		Connection conexao = Banco.getConnection();
		String sql = " select id from cliente c " + 
				"where c.cpf = '" + cpf + "'";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		boolean cpfUsado = false;
		
		try {
			ResultSet rs = stmt.executeQuery();
			cpfUsado = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se CPF já foi usado. Causa: " + e.getMessage());
		}
		
		return cpfUsado;
	}

	public boolean temClienteMorandoNoEndereco(int idEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT id FROM CLIENTE c " + " WHERE c.idEndereco = " + idEndereco;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		boolean enderecoJaUsado = false;

		try {
			ResultSet rs = stmt.executeQuery();
			enderecoJaUsado = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se endereço já foi usado. Causa: " + e.getMessage());
		}

		return enderecoJaUsado;
	}
}
