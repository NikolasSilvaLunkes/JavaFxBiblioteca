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

import project.nikolas.javaFx.javaFxProj1.Classes.Editora;


public class EditoraDao {
	private Connection connection;

	public EditoraDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	public boolean inserir(Editora editora) {
		String sql = "INSERT INTO  editora (nome) VALUES (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Editora> listar() {
		String sql = "SELECT ideditora, nome FROM  editora";
		List<Editora> listaEditoras = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Editora editora = new Editora();
				editora.setNome(resultado.getString("nome"));
				editora.setId(resultado.getInt("ideditora"));
				listaEditoras.add(editora);
			}
		} catch (SQLException ex) {
			Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaEditoras;
	}

	public boolean alterar(Editora editora) {
		String sql = "UPDATE editora SET nome=?  WHERE ideditora=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.setInt(2, editora.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Editora editora) {
		String sql = "DELETE FROM editora WHERE ideditora=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, editora.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Editora buscar(Integer id) {
		String sql = "SELECT ideditora, nome FROM editora WHERE ideditora=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Editora editora = new Editora();
			if (resultado.next()) {
				editora.setId(resultado.getInt("id"));
				editora.setNome(resultado.getString("nome"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
