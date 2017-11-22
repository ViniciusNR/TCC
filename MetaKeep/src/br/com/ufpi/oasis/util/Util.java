package br.com.ufpi.oasis.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	public static String montarJSON(String[] nomes, String[] valores) throws JSONException{
		JSONObject metadados = new JSONObject();
		ArrayList<JSONObject> params = new ArrayList<>();
		for (int i = 0; i < nomes.length; i++) {
			if(nomes[i] != null && valores[i] != null){
				params.add(new JSONObject().put("nome",nomes[i]).accumulate("valor",valores[i]));
			}
		}
		metadados.put("metadados", new JSONArray(params));
		return metadados.toString();
	}

	public static String montarJSON(String[] nomes, String[] valores, String nomeSoftware, String descricao, String versao, String autores, String categoria) throws JSONException{
		JSONObject metadados = new JSONObject();
		JSONObject infoBasicas = new JSONObject();

		ArrayList<JSONObject> params = new ArrayList<>();
		for (int i = 0; i < nomes.length; i++) {
			if(nomes[i] != null && valores[i] != null){
				params.add(new JSONObject().put("nome",nomes[i]).accumulate("valor",valores[i]));
			}
		}

		infoBasicas.put("nomeSoftware", nomeSoftware);
		infoBasicas.put("descricao", descricao);
		infoBasicas.put("versao", versao);
		infoBasicas.put("autores", autores);
		infoBasicas.put("categoria", categoria);

		metadados.put("metadados", new JSONArray(params));
		metadados.put("infoBasicas", infoBasicas);

		return metadados.toString();
	}

	public static void criarArquivo(String caminho, String contetudo) throws IOException{
		FileWriter arq = new FileWriter(caminho+"/metadados.princ");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.print(contetudo);
		arq.close();
	}

	public static String encode(String s) {
		return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
	}

	public static String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}

	public static void downloadFile(HttpServletResponse response) {
		try{
			String sep = System.getProperty("file.separator");
			String filepath = System.getProperty("user.home")+sep+Constantes.REPO_OWNER+sep+Constantes.REPO_NAME+sep+"metadados.princ";   //change your directory path

			File file = new File(filepath);
			if(!file.exists())
			{
				throw new ServletException("File doesn't exists on server.");
			}

			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition","attachment; filename=\"metadados.princ\""); 

			java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath);

			int i; 
			while ((i=fileInputStream.read()) != -1) 
			{
				response.getWriter().write(i); 
			} 
			fileInputStream.close();
		} catch(Exception e) {
			System.err.println("Error while downloading file[metadados.princ] "+e);
		}
	}
 
	//	public static String encode(String s) {
	//		return Base64.getEncoder().encodeToString(s.getBytes());
	//	}
	//	
	//	public static String decode(String s) {
	//	    return Base64.getDecoder().decode(s).toString();
	//	}

}
