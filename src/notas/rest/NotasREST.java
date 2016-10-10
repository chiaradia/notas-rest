/**
 * 
 */
package notas.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import notas.dao.NotaDAO;
import notas.entidade.Nota;

/**
 * @author Luiz Felipe
 *
 */
@Path("/notas")
public class NotasREST {
	
	private NotaDAO notaDAO;
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	@PostConstruct
	private void init(){
		notaDAO = new NotaDAO();
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Nota> listarNotas(){
		List<Nota> notas = new ArrayList<Nota>();
		try {
			notas = notaDAO.getNotas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notas;
		
	}
	
	@PUT
	@Path("/adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarNota(Nota nota){
		
		String msg = "";
		int id = 0;
		
		try {
			id = notaDAO.addNota(nota);
		} catch (Exception e){
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		msg = "Nota adicionada com sucesso! Id: "+id;
		
		return msg;
	}
	
	@GET
	@Path ("/get/{id}")
	@Consumes (MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Nota getById (@PathParam("id") int id){
		Nota nota = null;
		try {
			nota = notaDAO.getNotaByID(id);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return nota;
		
	}
	
	@POST
	@Path ("/editar")
	@Consumes (MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editar (Nota nota){
		
		String msg = "";
		try {
			notaDAO.editNota(nota);
		} catch (Exception e) {
			// TODO: handle exception
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		msg = "Nota de id " + nota.getId() + " editada com sucesso.";
		return msg;
		
	}
	
	@POST
	@Path ("/editar/{id}")
	@Consumes (MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces (MediaType.TEXT_PLAIN)
	public String editar (Nota nota, @PathParam ("id") int id){
		
		String msg = "";
		try {
			notaDAO.editNotaById(nota, id);
		} catch (Exception e) {
			// TODO: handle exception
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		msg = "Nota de id " + nota.getId() + " editada com sucesso.";
		return msg;
		
	}
	
	@DELETE
	@Path ("/remover")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String remover (Nota nota){
		
		String msg = "";
		
		try {
			notaDAO.removeNota(nota);
		} catch (Exception e) {
			// TODO: handle exception
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		msg = "Nota removida com sucesso.";
		return msg;
	}
	
	@DELETE
	@Path ("/remover/{id}")
	@Consumes (MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String remover(@PathParam ("id") int id){
		
		String msg = "";
		
		try {
			notaDAO.removeNotaById(id);;
		} catch (Exception e) {
			// TODO: handle exception
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		msg = "Nota removida com sucesso.";
		return msg;
	}

}
