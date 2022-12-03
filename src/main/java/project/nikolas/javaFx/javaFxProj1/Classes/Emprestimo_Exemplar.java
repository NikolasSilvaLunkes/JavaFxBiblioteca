package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class Emprestimo_Exemplar{
	
	private int id;
	private int idExmprestimo;
	
	private String titulo;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEmprestimo() {
		return idExmprestimo;
	}
	public void setIdEmprestimo(int idExmprestimo) {
		this.idExmprestimo = idExmprestimo;
	}
	public int getIdExemplar() {
		return idExemplar;
	}
	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}
	private int idExemplar;
	
	
}
