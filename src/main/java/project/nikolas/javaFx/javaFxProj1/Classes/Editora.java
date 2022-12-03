package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import project.nikolas.javaFx.javaFxProj1.DaoClasses.EditoraDao;

public class Editora implements Serializable{
	
	
	
	private int id;
	private String nome;
	
	public boolean salvar() {
		EditoraDao dao = new EditoraDao();
		dao.alterar(this);
		return true;
	}
	
	public boolean incluir() {
		EditoraDao dao = new EditoraDao();
		dao.inserir(this);
		return true;
	}

	public boolean buscarPorId(int id) {
		EditoraDao dao = new EditoraDao();
		Editora newEditora = dao.buscar(id);
		this.nome = newEditora.getNome();
		this.id = newEditora.getId();
		return true;
	}
	
	public boolean excluirRegistro() {
		EditoraDao dao = new EditoraDao();
		dao.remover(this);
		return true;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
