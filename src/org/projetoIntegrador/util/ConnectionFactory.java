package org.projetoIntegrador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/bd_projetoIntegrador";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123456";

	public static Connection getConexao() {
		Connection conexao = null;
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.err.println("Erro na conexão: " + e.getMessage());
		}
		return conexao;
	}

	public static void fecharConexao(Connection connection,
			Statement statement, ResultSet result) {
		fechar(connection, statement, result);
	}

	public static void fecharConexao(Connection connection, Statement statement) {
		fechar(connection, statement, null);
	}

	private static void fechar(Connection connection, Statement statement,
			ResultSet result) {
		try {
			if (result != null) result.close();
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}catch (Exception e){
			System.err.println("Erro ao fechar a conexão."  + e.getMessage());
		}
	}
}
