package project.nikolas.javaFx.javaFxProj1;

import project.nikolas.javaFx.javaFxProj1.DaoClasses.*;
import project.nikolas.javaFx.javaFxProj1.Classes.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Cassio
 */
public class ControllerTelaAutor implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaAutor();
		tableViewAutor.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarAutor(newValue));
	}
//

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldRg;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private DatePicker datePickerNascimento;

    @FXML
    private TextField textFieldTelefone;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Autor> tableViewAutor;

    @FXML
    private TableColumn<Autor, Integer> tableColumnId;

    @FXML
    private TableColumn<Autor, String> tableColumnNome;


    @FXML
    private Button buttonExcluir;

    private ObservableList<Autor> olAutor;
    private Autor autorSelecionado;
    @FXML
    private Button buttonNovo;
//    
//    
    private AutorDao autorDao = new AutorDao();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    	InquilinoDao = new  InquilinoDao();
//        popularTabelaInquilino();
//        tableViewInquilino.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarInquilino(newValue));
//    }
//
    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
            String nome = textFieldNome.getText();
           
 
            Autor autor = new Autor();
            autor.setNome(nome);
// 
//            if ( id != 0) {
//            	InquilinoDao.alterar(Inquilino);
//            }else {
//            InquilinoDao.inserir(Inquilino);
//            }
            	
            
            
            autorDao.inserir(autor);
            limpar();
            popularTabelaAutor();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void popularTabelaAutor() {
//    	
    	
    	
        List<Autor> lst = autorDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
       

        olAutor = FXCollections.observableArrayList(lst);
        tableViewAutor.setItems(olAutor);
    }
//
    private void selecionarAutor(Autor autor) {
        autorSelecionado = autor;
// 
    }

    @FXML
    private void handlerExcluirAutor(ActionEvent event) {
    	System.out.println(autorSelecionado);
    	if (autorSelecionado != null){
            AutorDao pdao = new AutorDao();
            pdao.remover(autorSelecionado);
            popularTabelaAutor();
        }
//        
    }
//
    @FXML
    private void handlerAlterarAutor(ActionEvent event) {
    	if (autorSelecionado != null){
            String nome = textFieldNome.getText();
           
 
            Autor autor = new Autor();
            autor.setId(autorSelecionado.getId());
            autor.setNome(nome);
            
            AutorDao pdao = new AutorDao();
            pdao.alterar(autor);
            popularTabelaAutor();
        }
 
    }
//
	private void limpar() {
        textFieldNome.setText(null);
	}

}
