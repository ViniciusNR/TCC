package bt.com.ufpi.oasis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufpi.oasis.entidade.RepositorioVO;
import br.com.ufpi.oasis.entidade.UsuarioVO;

public class UsuariosDAO extends BaseDAO {

	private static final String SELECT = " SELECT usuarios.id "
			+ ", usuarios.username "
			+ ", usuarios.password "
			+ ", usuarios.gituser "
			+ ", repositorios.name "
			+ ", repositorios.metadata "
			+ " FROM usuarios "
			+ " JOIN usuarios_repositorios ON usuarios_repositorios.idusuario = usuarios.id "
			+ " JOIN repositorios ON repositorios.id = usuarios_repositorios.idrepositorio ;";
	
	private static final String SELECT_BY_NAME = " SELECT usuarios.id "
			+ ", usuarios.username "
			+ ", usuarios.password "
			+ ", usuarios.gituser "
			+ ", repositorios.name "
			+ ", repositorios.metadata "
			+ " FROM usuarios "
			+ " WHERE usuarios.username like ?";

	private static final String INSERT = " INSERT INTO usuarios (username, password, gituser) values (user, pass, ?)";
	
	public ArrayList<UsuarioVO> recuperarUsuarios() throws SQLException {
		try {
			super.prepararDAO(SELECT);

			ResultSet rs = super.listar();
			ArrayList<UsuarioVO> lista = new ArrayList<UsuarioVO>(); 
			UsuarioVO item = new UsuarioVO();

			while(rs.next()) {
				item = new UsuarioVO();
				item.setId(rs.getInt("id"));
				item.setUsername(rs.getString("username"));
				item.setPassword(rs.getString("password"));
				item.setGituser(rs.getString("gituser"));

				lista.add(item);
			}
			
			rs.close();
			rs = null;
			
			return lista;
		} finally {
			super.fecharConexao();
		}
	}
	
	public UsuarioVO recuperarUsuario() throws SQLException {
		try {
			super.prepararDAO(SELECT);

			ResultSet rs = super.listar();
			UsuarioVO item = new UsuarioVO();
			RepositorioVO repositorio = null;
			ArrayList<RepositorioVO> repositorios = new ArrayList<>();
			
			
			if(rs.next()) {
				item = new UsuarioVO();
				item.setId(rs.getInt("id"));
				item.setUsername(rs.getString("username"));
				item.setPassword(rs.getString("password"));
				item.setGituser(rs.getString("gituser"));
			
				repositorio = new RepositorioVO();
				repositorio.setName(rs.getString("name"));
				repositorio.setMetadata(rs.getString("metadata"));
				repositorios.add(repositorio);
			}
			
			item.setRepositorios(repositorios);
			
			rs.close();
			rs = null;
			
			return item;
		} finally {
			super.fecharConexao();
		}
	}
	
	public UsuarioVO recuperarUsuarioPorNome(String username) throws SQLException {
		try {
			super.prepararDAO(SELECT_BY_NAME);
			ResultSet rs = super.listar();
			UsuarioVO item = new UsuarioVO();
			RepositorioVO repositorio = null;
			ArrayList<RepositorioVO> repositorios = new ArrayList<>();
			
			st.setString(1,username);
			
			if(rs.next()) {
				item = new UsuarioVO();
				item.setId(rs.getInt("id"));
				item.setUsername(rs.getString("username"));
				item.setPassword(rs.getString("password"));
				item.setGituser(rs.getString("gituser"));
			
				repositorio = new RepositorioVO();
				repositorio.setName(rs.getString("name"));
				repositorio.setMetadata(rs.getString("metadata"));
				repositorios.add(repositorio);
			}
			
			item.setRepositorios(repositorios);
			
			rs.close();
			rs = null;
			
			return item;
		} finally {
			super.fecharConexao();
		}
	}
	
	public void inserirusuario(UsuarioVO usuario) throws SQLException {
		try {
			super.prepararDAO(INSERT);
			st.setString(1, usuario.getUsername());
			st.setString(2, usuario.getPassword());
			st.setString(3, usuario.getGituser());
			super.atualizar();
		}catch (Exception e) {
			System.out.println("Falha ao inserir usuario");
			e.printStackTrace();
		} finally {
			super.fecharConexao();
		}
		
	}
	
}
