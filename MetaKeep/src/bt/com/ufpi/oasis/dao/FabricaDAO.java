package bt.com.ufpi.oasis.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDAO {

	private static String urlMYSQL = "jdbc:mysql://127.0.0.1/metakeep";
	private static String nomeUsuarioMySQL = "root";
	private static String senhaUsuarioMySQL = "";
	
	public static Connection criarConexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(urlMYSQL, nomeUsuarioMySQL, senhaUsuarioMySQL);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}