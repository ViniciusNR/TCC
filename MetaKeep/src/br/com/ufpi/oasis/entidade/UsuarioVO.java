package br.com.ufpi.oasis.entidade;

import java.util.ArrayList;

public class UsuarioVO {
	private int id;
	private String username;
	private String password;
	private String gituser;
	private ArrayList<RepositorioVO> repositorios; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGituser() {
		return gituser;
	}
	public void setGituser(String gituser) {
		this.gituser = gituser;
	}
	public ArrayList<RepositorioVO> getRepositorios() {
		return repositorios;
	}
	public void setRepositorios(ArrayList<RepositorioVO> repositorios) {
		this.repositorios = repositorios;
	}
}
