package bt.com.ufpi.oasis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class BaseDAO {

	protected Connection conexao;

	protected PreparedStatement st;

	private int linhasAfetadas;
	
	protected void iniciarTransacao() throws SQLException {
		if (conexao == null) {
			conexao = FabricaDAO.criarConexao();
		}
		conexao.setAutoCommit(false);
	}

	protected void finalizarTransacao() throws SQLException {
		conexao.commit();
		conexao.setAutoCommit(true);
	}
	
	protected void desfazerTransacao() throws SQLException {
		conexao.rollback();
		conexao.setAutoCommit(true);
	}
	
	protected void prepararDAO(String instrucaoSQL) throws SQLException {
		if (conexao == null) {
			conexao = FabricaDAO.criarConexao();
		}
		st = conexao.prepareStatement(instrucaoSQL, Statement.RETURN_GENERATED_KEYS);
	}

	protected int atualizar() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		return linhasAfetadas;
	}
	
	protected int atualizarERetornarID() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		
		int valorChave = 0;
		
		while (rs.next()) {
			valorChave = rs.getInt(1);
		}
		
		rs.close();
		rs = null;
		
		return valorChave;
	}

	protected void inserirComandoNoLote() throws SQLException {
		st.addBatch();
	}
	
	protected void atualizarLote() throws SQLException {
		st.executeBatch();
	}

	protected ResultSet listar() throws SQLException {
		return st.executeQuery();
	}

	protected Connection getConexao() {
		return conexao;
	}

	protected void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	protected PreparedStatement getSt() {
		return st;
	}

	protected void setSt(PreparedStatement st) {
		this.st = st;
	}

	protected int getLinhasAfetadas() {
		return linhasAfetadas;
	}

	protected void setLinhasAfetadas(int linhasAfetadas) {
		this.linhasAfetadas = linhasAfetadas;
	}
	
	protected void fecharConexao() throws SQLException {
		if(conexao != null && conexao.getAutoCommit()) {
			try {
				if (st != null) {
					st.close();
				}
				if (conexao != null && !conexao.isClosed()) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				st = null;
				conexao = null;
			}	
		}
	}

}
