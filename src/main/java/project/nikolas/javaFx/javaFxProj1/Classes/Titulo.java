package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Titulo implements Serializable{
	
	
	private static final long serialVersionUID = 2L;
	
	public static String ARQUIVO = "./livro.csv";
	public final static String ARQUIVOSERIAL = "./livro_serialv1.obj";
	
	
	private int id;
	private String nome;
	private int idAutor;
	private int idEditora;
	private String editora;
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	private String autor;
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getId() {
		return id;
	}

	public void setId(int codigo) {
		this.id = codigo;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String Nome) {
		this.nome = Nome;
	}
	
	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	
	public int getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	
	
	
	
}
