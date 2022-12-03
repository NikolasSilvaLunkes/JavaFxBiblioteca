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

import project.nikolas.javaFx.javaFxProj1.Classes.Emprestimo;


public class EmprestimoDao {
	private Connection connection;

	public EmprestimoDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	
	public boolean inserir(Emprestimo emprestimo) {
		String sql = "INSERT INTO  emprestimo ( dataemprestimo, datadevolucao, idinquilino) VALUES ( ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, (Date) emprestimo.getDataEmprestimo());
			stmt.setDate(2, (Date) emprestimo.getDataDevolucao());
			stmt.setInt(3, emprestimo.getIdInquilino());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Emprestimo> listar() {
		String sql = "SELECT * FROM emprestimo emp INNER JOIN inquilino inq ON inq.idinquilino = emp.idinquilino";
		List<Emprestimo> listaClientes = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setId(resultado.getInt("idemprestimo"));
				emprestimo.setNomeInquilino(resultado.getString("nome"));
				emprestimo.setIdInquilino(resultado.getInt("idinquilino"));
				emprestimo.setDataEmprestimo(resultado.getDate("dataemprestimo"));
				emprestimo.setDataDevolucao(resultado.getDate("datadevolucao"));
				listaClientes.add(emprestimo);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaClientes;
	}

	public boolean alterar(Emprestimo emprestimo) {
		String sql = "UPDATE emprestimo SET dataemprestimo=?, datadevolucao=?, idinquilino=?  WHERE idemprestimo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, (Date) emprestimo.getDataEmprestimo());
			stmt.setDate(2, (Date) emprestimo.getDataDevolucao());
			stmt.setInt(3, emprestimo.getIdInquilino());
			stmt.setInt(4, emprestimo.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Emprestimo emprestimo) {
		String sql = "DELETE FROM emprestimo WHERE idemprestimo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, emprestimo.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Emprestimo buscar(Integer id) {
		String sql = "SELECT * FROM emprestimo emp INNER JOIN inquilino inq ON inq.idinquilino = emp.idinquilino WHERE idemprestimo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			Emprestimo emprestimo = new Emprestimo();
			if (resultado.next()) {
				emprestimo.setId(resultado.getInt("idemprestimo"));
				emprestimo.setNomeInquilino(resultado.getString("nome"));
				emprestimo.setIdInquilino(resultado.getInt("idinquilino"));
				emprestimo.setDataEmprestimo(resultado.getDate("dataemprestimo"));
				emprestimo.setDataDevolucao(resultado.getDate("datadevolucao"));
				return emprestimo;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ExemplarDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
