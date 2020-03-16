package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe criada na disciplina de POO (2019/1).
 * 
 * Representa um banco de dados que se conecta à aplicação
 * 
 * Encapsula métodos da API JDBC, com a definição, criação e fechamento de
 * conexões à bancos de dados relacionais
 * 
 * @author Adriano de Melo
 * 
 *         Vilmar César Pereira Júnior (continuação em Desenvolvimento Desktop
 *         2018/2) -- ALTERADO remotamente (no github)
 * 
 *         Diferenças entre Statement e PreparedStatement:
 * 
 *         A maioria dos bancos de dados relacionais lida com uma consulta
 *         (query) JDBC / SQL em quatro passos:
 *
 *         1- Interpretar (parse) a consulta SQL;
 * 
 *         2- Compilar a consulta SQL;
 * 
 *         3- Planejar e otimizar o caminho de busca dos dados;
 * 
 *         4- Executar a consulta otimizada, buscando e retornando os dados.
 * 
 *         Um Statement irá sempre passar pelos quatro passos acima para cada
 *         consulta SQL enviada para o banco. Já um Prepared Statement
 *         pré-executa os passos (1) a (3).
 * 
 *         Então, ao criar um Prepared Statement alguma pré-otimização é feita
 *         de imediato. O efeito disso é que, se você pretende executar a mesma
 *         consulta repetidas vezes mudando apenas os parâmetros de cada uma, a
 *         execução usando Prepared Statements será mais rápida e com menos
 *         carga sobre o banco.
 * 
 *         Outra vantagem dos Prepared Statements é que, se utilizados
 *         corretamente, ajudam a evitar <b>ataques de Injeção de SQL</b>.
 * 
 *         Note que para isso é preciso que os parâmetros da consulta sejam
 *         atribuídos através dos métodos setInt(), setString(), etc. presentes
 *         na interface PreparedStatement e não por concatenação de strings.
 * 
 *         Para uma consulta que vai ser executada poucas vezes e não requer
 *         nenhum parâmetro, Statement basta. Para os demais casos, prefira
 *         PreparedStatement.
 * 
 *         FONTE:
 *         {@link https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement}
 *         ======= Classe responsável pela conexão JDBC com o banco de dados
 *         escolhido.
 * 
 * @author Adriano de Melo
 *
 */
public class Banco {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String BANCODADOS = "exemplos";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS
			+ "?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";

	public static final int CODIGO_RETORNO_ERRO_EXCLUSAO = 0;
	public static final int CODIGO_RETORNO_SUCESSO_EXCLUSAO = 1;

	/**
	 * Estabelece a conexão JBDC considerando as configurações da classe Banco.
	 * 
	 * @return Connection um objeto de conexão JDBC.
	 * 
	 * @throws ClassNotFoundException caso o nome completo de DRIVER_MYSQL esteja
	 *                                incorreto ou o driver JDBC do banco
	 *                                selecionado não foi adicionado ao projeto (via
	 *                                .jar ou dependência no pom.xml).
	 * 
	 * @throws SQLException           caso a URL_CONEXAO, USUARIO e/ou SENHA estejam
	 *                                incorretos.
	 */
	public static Connection getConnection() {

		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver não foi encontrada. Causa: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection. Causa: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conexão. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Solicita um objeto Statement para uma conexão. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * Este método deve ser sempre chamado nos DAOs após a criação da expressão SQL,
	 * geralmente com os métodos execute(sql), executeUpdate(sql) ou
	 * executeQuery(sql), onde "sql" é do tipo String.
	 * 
	 * @param conn uma conexão anteriormente criada.
	 * @return stmt um objeto do tipo Statement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static Statement getStatement(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement. Causa: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * Fecha um objeto Statement anteriormente criado.
	 * 
	 * Este método deve ser sempre chamado nos DAOs após a execução da expressão
	 * SQL.
	 * 
	 * @param stmt um objeto do tipo Statement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Solicita um objeto PreparedStatement para uma conexão. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * @param conn uma conexão anteriormente criada.
	 * @return stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static PreparedStatement getPreparedStatement(Connection conn) {
		try {
			PreparedStatement stmt = null;
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement. Causa: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * Solicita um objeto PreparedStatement para uma conexão. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * @param conn uma conexão anteriormente criada.
	 * @return stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement. Causa: " + e.getCause());
			return null;
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql, int tipoRetorno) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, tipoRetorno);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			return null;
		}
	}

	/**
	 * 
	 * Fecha um objeto PreparedStatement anteriormente criado.
	 * 
	 * Este método deve ser sempre chamado nos DAOs após a execução da expressão
	 * SQL.
	 * 
	 * @param stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closePreparedStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Fecha um objeto ResultSet anteriormente criado.
	 * 
	 * Este método deve ser sempre chamado nos DAOs após a consulta de todos os
	 * resultados e conversão para objetos.
	 * 
	 * @param result um objeto do tipo ResultSet
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet. Causa: " + e.getMessage());
		}
	}
}
