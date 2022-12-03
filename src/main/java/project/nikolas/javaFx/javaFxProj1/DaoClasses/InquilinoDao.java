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

import project.nikolas.javaFx.javaFxProj1.Classes.Inquilino;


public class InquilinoDao {
	private Connection connection;

	public InquilinoDao() {
		this.connection = MysqlSingleton.getConnection();
	}

	public boolean inserir(Inquilino inquilino) {
		String sql = "INSERT INTO  inquilino (nome, cpf, rg, telefone) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, inquilino.getNome());
			stmt.setString(2, inquilino.getCpf());
			stmt.setString(3, inquilino.getRg());
			stmt.setString(4, inquilino.getTelefone());
			stmt.execute();
			return true;
		} catch (Exception e) {
			Logger.getLogger(InquilinoDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Inquilino> listar() {
		String sql = "SELECT idinquilino, nome, cpf, rg FROM  inquilino";
		List<Inquilino> listaClientes = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Inquilino inquilino = new Inquilino();
				inquilino.setNome(resultado.getString("nome"));
				inquilino.setRg(resultado.getString("rg"));
				inquilino.setCpf(resultado.getString("cpf"));
				inquilino.setId(resultado.getInt("idinquilino"));
				listaClientes.add(inquilino);
			}
		} catch (SQLException ex) {
			Logger.getLogger(InquilinoDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaClientes;
	}

	public boolean alterar(Inquilino cliente) {
		String sql = "UPDATE inquilino SET nome=?, rg=?, cpf=?  WHERE idinquilino=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRg());
			stmt.setString(3, cliente.getCpf());
			stmt.setInt(4, cliente.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(InquilinoDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean remover(Inquilino inquilino) {
		String sql = "DELETE FROM inquilino WHERE idinquilino=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, inquilino.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(InquilinoDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Inquilino buscar(Integer idinquilino) {
		String sql = "SELECT idinquilino, nome, cpf, rg FROM inquilino WHERE idinquilino=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idinquilino);
			ResultSet resultado = stmt.executeQuery();
			Inquilino inquilino = new Inquilino();
			if (resultado.next()) {
				inquilino.setId(resultado.getInt("idinquilino"));
				inquilino.setNome(resultado.getString("nome"));
				inquilino.setRg(resultado.getString("cpf"));
				inquilino.setCpf(resultado.getString("rg"));
				return inquilino;
			}
		} catch (SQLException ex) {
			Logger.getLogger(InquilinoDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
