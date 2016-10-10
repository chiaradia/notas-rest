package notas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import notas.config.BDConfig;
import notas.entidade.Nota;

/**
 * @author Luiz Felipe
 *
 */
public class NotaDAO {
	
	public List<Nota> getNotas() throws SQLException {
		
		List<Nota> notas = new ArrayList<Nota>();
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "SELECT * FROM tb_nota";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()){
			Nota nota = new Nota();
			nota.setId(rs.getInt("id"));
			nota.setDescricao(rs.getString("descricao"));
			nota.setTitulo(rs.getString("titulo"));
			
			notas.add(nota);
		}
		return notas;
	}
	
	
	public Nota getNotaByID(int id) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "SELECT * FROM tb_nota WHERE id=?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Nota nota = null;
		
		while(rs.next()){
			nota = new Nota();
			nota.setId(rs.getInt("id"));
			nota.setDescricao(rs.getString("descricao"));
			nota.setTitulo(rs.getString("titulo"));
		}
		
		return nota;
	}
	
/*	public void addNota (Nota nota) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "INSERT INTO tb_nota(titulo, descricao) VALUES (?, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nota.getTitulo());
		ps.setString(2, nota.getDescricao());
		ps.execute();

	}*/
	
	public int addNota (Nota nota) throws SQLException {
		
		int idDaNota = 0;
		Connection conexao = BDConfig.getConexao();
		
		String sql = "INSERT INTO tb_nota(titulo, descricao) VALUES (?, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, nota.getTitulo());
		ps.setString(2, nota.getDescricao());
		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()){
			idDaNota = rs.getInt(1);
		}
		
		return idDaNota;
	}
	
	public void editNota (Nota nota) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "UPDATE tb_nota SET titulo=?, descricao=? WHERE id=?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nota.getTitulo());
		ps.setString(2, nota.getDescricao());
		ps.setInt(3, nota.getId());
		ps.execute();
	}
	
	public void editNotaById (Nota nota, int id) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "UPDATE tb_nota SET titulo=?, descricao=? WHERE id=?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nota.getTitulo());
		ps.setString(2, nota.getDescricao());
		ps.setInt(3, id);
		ps.execute();
	}
	
	public void removeNota (Nota nota) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "DELETE FROM tb_nota WHERE id=?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, nota.getId());
		ps.execute();
	}
	
	public void removeNotaById (int id) throws SQLException {
		
		Connection conexao = BDConfig.getConexao();
		
		String sql = "DELETE FROM tb_nota WHERE id=?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
	}

}
