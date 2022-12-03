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
public class ControllerTelaInquilino implements Initializable {

	
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
    private TableView<Inquilino> tableViewInquilino;

    @FXML
    private TableColumn<Inquilino, Integer> tableColumnId;

    @FXML
    private TableColumn<Inquilino, String> tableColumnNome;

    @FXML
    private TableColumn<Inquilino, Integer> tableColumnRg;

    @FXML
    private TableColumn<Inquilino, Integer> tableColumnCpf;

    @FXML
    private TableColumn<Inquilino, Integer> tableColumnTelefone;

    @FXML
    private Button buttonExcluir;

    private ObservableList<Inquilino> olInquilino;
    private Inquilino inquilinoSelecionado;
    @FXML
    private Button buttonNovo;
//    
//    
    private InquilinoDao InquilinoDao = new InquilinoDao();
//
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaInquilino();
		tableViewInquilino.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarInquilino(newValue));
	}
    
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
            String rg = textFieldRg.getText();
            String cpf = textFieldCpf.getText();
            String telefone =  textFieldTelefone.getText();
           
 
            Inquilino inquilino = new Inquilino();
            inquilino.setNome(nome);
            inquilino.setCpf(cpf);
            inquilino.setRg(rg);
            inquilino.setTelefone(telefone);
//	 	
// 
//            if ( id != 0) {
//            	InquilinoDao.alterar(Inquilino);
//            }else {
//            InquilinoDao.inserir(Inquilino);
//            }
            	
            
            InquilinoDao dao = new InquilinoDao();
            dao.inserir(inquilino);
            limpar();
            popularTabelaInquilino();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void popularTabelaInquilino() {
//    	
    	
    	
        List<Inquilino> lst = InquilinoDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        

        olInquilino = FXCollections.observableArrayList(lst);
        tableViewInquilino.setItems(olInquilino);
    }
//
    private void selecionarInquilino(Inquilino inquilino) {
//      
    	inquilinoSelecionado = inquilino;
    	//tableViewInquilino.getItems().clear();
//    	List<Inquilino> lst = InquilinoDao.listar();
//		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
//		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
//		tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
//		olInquilino = FXCollections.observableArrayList(lst);
//		tableViewInquilino.setItems(olInquilino);
// 
    }

    @FXML
    private void handlerExcluirInquilino(ActionEvent event) {
        if (inquilinoSelecionado != null){
        	System.out.println(inquilinoSelecionado);
            InquilinoDao pdao = new InquilinoDao();
            pdao.remover(inquilinoSelecionado);
            popularTabelaInquilino();
        }
//        
    }
//
    @FXML
    private void handlerAlterarInquilino(ActionEvent event) {
    	if (inquilinoSelecionado != null){
            String nome = textFieldNome.getText();
            String rg = textFieldRg.getText();
            String cpf = textFieldCpf.getText();
            String telefone =  textFieldTelefone.getText();
           
 
            Inquilino inquilino = new Inquilino();
            inquilino.setId(inquilinoSelecionado.getId());
            inquilino.setNome(nome);
            inquilino.setCpf(cpf);
            inquilino.setRg(rg);
            inquilino.setTelefone(telefone);
            
            InquilinoDao pdao = new InquilinoDao();
            pdao.alterar(inquilino);
            popularTabelaInquilino();
        }
 
    }
//
	private void limpar() {
        textFieldNome.setText(null);
        textFieldRg.setText(null);
        textFieldCpf.setText(null);
        textFieldEndereco.setText(null);
        textFieldTelefone.setText(null);
	}

}
