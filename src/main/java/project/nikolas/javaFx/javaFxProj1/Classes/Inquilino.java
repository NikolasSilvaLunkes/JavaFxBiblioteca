package project.nikolas.javaFx.javaFxProj1.Classes;


import java.io.Serializable;
import java.util.ArrayList;

public class Inquilino extends Pessoa implements Serializable{
	
	
	private static final long serialVersionUID = 2L;
	
	public static String ARQUIVO = "./cliente.csv";
	public static String ARQUIVOSERIAL = "./cliente_serial.obj";
	
	public String telefone;
	
	
	@Override
	public String toString(){
		return super.toString()+ String.format(";%s",telefone);
	}
	
	public void salvar() {
		Utils utils = new Utils();
		utils.salvar(this, ARQUIVOSERIAL);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	
}