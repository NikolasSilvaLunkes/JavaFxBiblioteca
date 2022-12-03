package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class Emprestimo{
	
	private int id;
	private String nomeInquilino;
	public String getNomeInquilino() {
		return nomeInquilino;
	}
	public void setNomeInquilino(String nomeInquilino) {
		this.nomeInquilino = nomeInquilino;
	}
	private Date dataEmprestimo;
	private Date dataDevolucao;
	private int idInquilino;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public int getIdInquilino() {
		return idInquilino;
	}
	public void setIdInquilino(int idInquilino) {
		this.idInquilino = idInquilino;
	}
	
}
