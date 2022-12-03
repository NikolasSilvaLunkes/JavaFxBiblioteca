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

import project.nikolas.javaFx.javaFxProj1.Classes.Emprestimo_Exemplar;


public class Emprestimo_ExemplarDao {
	private Connection connection;

	public Emprestimo_ExemplarDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	
	public boolean inserir(Emprestimo_Exemplar emprestimo_exemplar) {
		String sql = "INSERT INTO  emprestimo_exemplar ( idemprestimo, idexemplar) VALUES ( ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, emprestimo_exemplar.getIdEmprestimo());
			stmt.setInt(2, emprestimo_exemplar.getIdExemplar());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Emprestimo_Exemplar> listar(int idemprestimo) {
		String sql = "SELECT ex.*, tt.nometitulo FROM emprestimo_exemplar ex INNER JOIN exemplar ez ON ez.idexemplar = ex.idexemplar INNER JOIN titulo tt ON tt.idtitulo = ez.idtitulo WHERE idemprestimo = ?";
		List<Emprestimo_Exemplar> listaClientes = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idemprestimo);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Emprestimo_Exemplar emprestimo_exemplar = new Emprestimo_Exemplar();
				emprestimo_exemplar.setId(resultado.getInt("idemprestimo_exemplar"));
				emprestimo_exemplar.setIdEmprestimo(resultado.getInt("idemprestimo"));
				emprestimo_exemplar.setIdExemplar(resultado.getInt("idexemplar"));
				emprestimo_exemplar.setTitulo(resultado.getString("nometitulo"));
				
				listaClientes.add(emprestimo_exemplar);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaClientes;
	}

	public boolean alterar(Emprestimo_Exemplar emprestimo) {
		String sql = "UPDATE emprestimo_exemplar SET idemprestimo=?, idexemplar=?  WHERE idemprestimo_exemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, emprestimo.getIdEmprestimo());
			stmt.setInt(2, emprestimo.getIdExemplar());
			stmt.setInt(3, emprestimo.getId());
			
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Emprestimo_Exemplar emprestimo_exemplar) {
		String sql = "DELETE FROM emprestimo_exemplar WHERE idemprestimo_exemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, emprestimo_exemplar.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Emprestimo_Exemplar buscar(Integer idemprestimo, Integer id) {
		String sql = "SELECT * FROM emprestimo_exemplar WHERE idemprestimo = ? AND idemprestimo_exemplar=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idemprestimo);
			stmt.setInt(2, id);
			ResultSet resultado = stmt.executeQuery();
			Emprestimo_Exemplar emprestimo_exemplar = new Emprestimo_Exemplar();
			if (resultado.next()) {
				emprestimo_exemplar.setId(resultado.getInt("idemprestimo_exemplar"));
				emprestimo_exemplar.setIdEmprestimo(resultado.getInt("idemprestimo"));
				emprestimo_exemplar.setIdExemplar(resultado.getInt("idexemplar"));
				return emprestimo_exemplar;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
