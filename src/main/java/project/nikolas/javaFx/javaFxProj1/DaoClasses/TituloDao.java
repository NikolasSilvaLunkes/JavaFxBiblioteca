package project.nikolas.javaFx.javaFxProj1.DaoClasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import project.nikolas.javaFx.javaFxProj1.Classes.Titulo;


public class TituloDao {
	private Connection connection;

	public TituloDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	public boolean inserir(Titulo titulo) {
		String sql = "INSERT INTO  titulo (nometitulo, idautor, ideditora) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, titulo.getNome());
			stmt.setInt(2, titulo.getIdAutor());
			stmt.setInt(3, titulo.getIdEditora());
			System.out.println(titulo.getId());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(TituloDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Titulo> listar() {
		String sql = "SELECT ti.*, ed.nome AS nomeeditora, au.nome AS nomeautor FROM titulo ti INNER JOIN editora ed ON ed.ideditora = ti.ideditora INNER JOIN autor au ON au.idautor = ti.idautor";
		List<Titulo> listaAutores = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Titulo titulo = new Titulo();
				titulo.setNome(resultado.getString("nometitulo"));
				titulo.setId(resultado.getInt("idtitulo"));
				titulo.setIdAutor(resultado.getInt("idautor"));
				titulo.setIdEditora(resultado.getInt("ideditora"));
				titulo.setEditora(resultado.getString("nomeeditora"));
				titulo.setAutor(resultado.getString("nomeautor"));
				listaAutores.add(titulo);
			}
		} catch (SQLException ex) {
			Logger.getLogger(TituloDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaAutores;
	}

	public boolean alterar(Titulo autor) {
		String sql = "UPDATE titulo SET nometitulo=?, idautor=?,ideditora=?  WHERE idtitulo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.setInt(2, autor.getIdAutor());
			stmt.setInt(3, autor.getIdEditora());
			stmt.setInt(4, autor.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TituloDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Titulo titulo) {
		String sql = "DELETE FROM titulo WHERE idtitulo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, titulo.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TituloDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Titulo buscar(Integer id) {
		String sql = "SELECT ti.*, ed.nome AS nomeeditora, au.nome AS nomeautor  FROM titulo ti INNER JOIN editora ed ON ed.ideditora = ti.ideditora INNER JOIN autor au ON au.idautor = ti.idautor WHERE idtitulo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Titulo titulo = new Titulo();
			if (resultado.next()) {
				titulo.setNome(resultado.getString("nometitulo"));
				titulo.setId(resultado.getInt("idtitulo"));
				titulo.setIdAutor(resultado.getInt("idautor"));
				titulo.setIdEditora(resultado.getInt("ideditora"));
				titulo.setEditora(resultado.getString("nomeeditora"));
				titulo.setAutor(resultado.getString("nomeautor"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(TituloDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
