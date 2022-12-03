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

import project.nikolas.javaFx.javaFxProj1.Classes.Autor;


public class AutorDao {
	private Connection connection;

	public AutorDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	public boolean inserir(Autor autor) {
		String sql = "INSERT INTO  autor (nome) VALUES (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Autor> listar() {
		String sql = "SELECT idautor, nome FROM  autor";
		List<Autor> listaAutores = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Autor autor = new Autor();
				autor.setNome(resultado.getString("nome"));
				autor.setId(resultado.getInt("idautor"));
				listaAutores.add(autor);
			}
		} catch (SQLException ex) {
			Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaAutores;
	}

	public boolean alterar(Autor autor) {
		String sql = "UPDATE autor SET nome=?  WHERE idautor=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.setInt(2, autor.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Autor autor) {
		String sql = "DELETE FROM autor WHERE idautor=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, autor.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Autor buscar(Integer id) {
		String sql = "SELECT idautor, nome FROM autor WHERE idautor=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Autor autor = new Autor();
			if (resultado.next()) {
				autor.setId(resultado.getInt("idautor"));
				autor.setNome(resultado.getString("nome"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
