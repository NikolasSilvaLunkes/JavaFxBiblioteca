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
public class ControllerTelaEditora implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaEditora();
		tableViewEditora.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarEditora(newValue));	
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
    private TableView<Editora> tableViewEditora;

    @FXML
    private TableColumn<Editora, Integer> tableColumnId;

    @FXML
    private TableColumn<Editora, String> tableColumnNome;


    @FXML
    private Button buttonExcluir;

    private ObservableList<Editora> olEditora;
    private Editora editoraSelecionada;
    @FXML
    private Button buttonNovo;
//    
//    
    private EditoraDao editoraDao = new EditoraDao();
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
           
 
            Editora editora = new Editora();
            editora.setNome(nome);
// 
//            if ( id != 0) {
//            	InquilinoDao.alterar(Inquilino);
//            }else {
//            InquilinoDao.inserir(Inquilino);
//            }
            	
            
            
            editoraDao.inserir(editora);
            limpar();
            popularTabelaEditora();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void popularTabelaEditora() {
//    	
    	
    	
        List<Editora> lst = editoraDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
       

        olEditora = FXCollections.observableArrayList(lst);
        tableViewEditora.setItems(olEditora);
    }
//
    private void selecionarEditora(Editora editora) {
        editoraSelecionada = editora;
    }

    @FXML
    private void handlerExcluirEditora(ActionEvent event) {
    	if (editoraSelecionada != null){
            EditoraDao pdao = new EditoraDao();
            pdao.remover(editoraSelecionada);
            popularTabelaEditora();
        }
//        
    }
//
    @FXML
    private void handlerAlterarEditora(ActionEvent event) {
    	if (editoraSelecionada != null){
            String nome = textFieldNome.getText();
           
 
            Editora editora = new Editora();
            editora.setId(editoraSelecionada.getId());
            editora.setNome(nome);
            
            EditoraDao pdao = new EditoraDao();
            pdao.alterar(editora);
            popularTabelaEditora();
        }
    }
//
	private void limpar() {
        textFieldNome.setText(null);
	}

}
