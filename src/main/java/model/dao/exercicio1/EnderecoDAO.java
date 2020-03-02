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

public class EnderecoDAO implements BaseDAO<Endereco>{

	public Endereco salvar(Endereco novaEntidade) {
		//Conectar no banco
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO ENDERECO (CEP, ESTADO, CIDADE, RUA, BAIRRO, NUMERO) "
				+ " VALUES ( " 
					+ novaEntidade.getCep() + ", " + novaEntidade.getEstado() 
					+ "," + novaEntidade.getCidade() + ", " + novaEntidade.getRua()
					+ "," + novaEntidade.getBairro() + "," + novaEntidade.getNumero()
				+ ")";
		
		//Obter um statement
		PreparedStatement statement = Banco.getPreparedStatement(conexao, sql );
		try {
			//Fazer o INSERT
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			//Executar
			ResultSet resultado = statement.getGeneratedKeys();
			
			if(resultado.next()) {
				//Incluir a chave gerada na novaEntidade (coluna de posição 1)
				novaEntidade.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(" Erro ao salvar novo endereço. Causa: " + e.getMessage());
		}
		 
		return novaEntidade;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Endereco consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Endereco> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}