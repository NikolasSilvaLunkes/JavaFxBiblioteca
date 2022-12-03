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

import project.nikolas.javaFx.javaFxProj1.Classes.Exemplar;


public class ExemplarDao {
	private Connection connection;

	public ExemplarDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	
	public boolean inserir(Exemplar exemplar) {
		String sql = "INSERT INTO  exemplar ( edicao, localizacao, idtitulo) VALUES ( ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, exemplar.getEdicao());
			stmt.setString(2, exemplar.getLocalizacao());
			stmt.setInt(3, exemplar.getIdTitulo());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(EmprestimoDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Exemplar> listar() {
		String sql = "SELECT *, ed.nome AS nomeeditora, au.nome AS nomeautor FROM exemplar ex INNER JOIN titulo ti ON ti.idtitulo = ex.idtitulo INNER JOIN editora ed ON ed.ideditora = ti.ideditora INNER JOIN autor au ON au.idautor = ti.idautor";
		List<Exemplar> listaClientes = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setId(resultado.getInt("idexemplar"));
				exemplar.setNome(resultado.getString("nometitulo"));
				exemplar.setLocalizacao(resultado.getString("localizacao"));
				exemplar.setEdicao(resultado.getString("edicao"));
				exemplar.setIdTitulo(resultado.getInt("idtitulo"));
				exemplar.setIdEditora(resultado.getInt("ideditora"));
				exemplar.setEditora(resultado.getString("nomeeditora"));
				exemplar.setAutor(resultado.getString("nomeautor"));
				listaClientes.add(exemplar);
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmprestimoDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaClientes;
	}

	public boolean alterar(Exemplar exemplar) {
		String sql = "UPDATE exemplar SET edicao=?, localizacao=?, idtitulo=?  WHERE idexemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, exemplar.getEdicao());
			stmt.setString(2, exemplar.getLocalizacao());
			stmt.setInt(3, exemplar.getIdTitulo());
			stmt.setInt(4, exemplar.getId());
			stmt.execute();
			System.out.println(exemplar.getId());
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EmprestimoDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Exemplar inquilino) {
		String sql = "DELETE FROM exemplar WHERE idexemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, inquilino.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EmprestimoDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Exemplar buscar(Integer id) {
		String sql = "SELECT *, ed.nome AS nomeeditora, au.nome AS nomeautor FROM exemplar ex INNER JOIN titulo ti ON ti.idtitulo = ex.idtitulo INNER JOIN editora ed ON ed.ideditora = ti.ideditora INNER JOIN autor au ON au.idautor = ti.idautor WHERE idexemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Exemplar exemplar = new Exemplar();
			if (resultado.next()) {
				exemplar.setId(resultado.getInt("idexemplar"));
				exemplar.setNome(resultado.getString("nometitulo"));
				exemplar.setLocalizacao(resultado.getString("localizacao"));
				exemplar.setEdicao(resultado.getString("edicao"));
				exemplar.setIdTitulo(resultado.getInt("idtitulo"));
				exemplar.setIdEditora(resultado.getInt("ideditora"));
				exemplar.setEditora(resultado.getString("nomeeditora"));
				exemplar.setAutor(resultado.getString("nomeautor"));
				return exemplar;
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmprestimoDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
