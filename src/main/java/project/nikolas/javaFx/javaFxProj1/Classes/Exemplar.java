package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Exemplar extends Titulo{
	
	
	
	
	private int id;
	private String localizacao;
	private String edicao;
	private int idTitulo;
	
	
	public int getId() {
		return id;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getIdTitulo() {
		return idTitulo;
	}

	public void setIdTitulo(int idTitulo) {
		this.idTitulo = idTitulo;
	}

	public void setId(int codigo) {
		this.id = codigo;
	}
	

	
	
	
	
	
}
