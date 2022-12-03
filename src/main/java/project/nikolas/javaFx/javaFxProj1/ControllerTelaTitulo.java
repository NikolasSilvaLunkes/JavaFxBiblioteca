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
public class ControllerTelaTitulo implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaTitulo();
		tableViewTitulo.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarTitulo(newValue));
	}
//

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldEditora;

    @FXML
    private TextField textFieldAutor;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Titulo> tableViewTitulo;

    @FXML
    private TableColumn<Titulo, Integer> tableColumnId;

    @FXML
    private TableColumn<Titulo, String> tableColumnNome;

    @FXML
    private TableColumn<Titulo, String> tableColumnAutor;

    @FXML
    private TableColumn<Titulo, String> tableColumnEditora;

    @FXML
    private Button buttonExcluir;

    private ObservableList<Titulo> olInquilino;
    private Titulo tituloSelecionado;
    @FXML
    private Button buttonNovo;
//    
//    
    private TituloDao TituloDao = new TituloDao();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    	TituloDao = new  TituloDao();
//        popularTabelaTitulo();
//        tableViewTitulo.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarTitulo(newValue));
//    }
//
    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
            String nome = textFieldNome.getText();
            String idAutor = textFieldAutor.getText();
            String idEditora = textFieldEditora.getText();
            Integer idAutorInt = Integer.parseInt(idAutor);
            Integer idEditoraInt = Integer.parseInt(idEditora);
            
            
 
            Titulo titulo = new Titulo();
            titulo.setNome(nome);
            titulo.setIdEditora(idEditoraInt);
            titulo.setIdAutor(idAutorInt);
            
//	 
// 
//            if ( id != 0) {
//            	TituloDao.alterar(Titulo);
//            }else {
//            TituloDao.inserir(Titulo);
//            }
            	
            
            TituloDao dao = new TituloDao();
            dao.inserir(titulo);
            limpar();
            popularTabelaTitulo();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void popularTabelaTitulo() {
//    	
    	
    	
        List<Titulo> lst = TituloDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        

        olInquilino = FXCollections.observableArrayList(lst);
        tableViewTitulo.setItems(olInquilino);
    }
//
    private void selecionarTitulo(Titulo titulo) {
    	
    	tituloSelecionado = titulo;
// 
    }

    @FXML
    private void handlerExcluirTitulo(ActionEvent event) {

    	if (tituloSelecionado != null){
            Titulo titulo = new Titulo();
            titulo.setId(tituloSelecionado.getId());
            System.out.println(tituloSelecionado.getId());
            System.out.println(titulo.getId());
            TituloDao pdao = new TituloDao();
            pdao.remover(titulo);
            popularTabelaTitulo();
        }
//        
    }
//
    @FXML
    private void handlerAlterarTitulo(ActionEvent event) {
    	System.out.println(tituloSelecionado.toString());
    	if (tituloSelecionado != null){
 
            Titulo titulo = new Titulo();
            titulo.setId(tituloSelecionado.getId());
            titulo.setNome(textFieldNome.getText());
            String idAutor = textFieldAutor.getText();
            String idEditora = textFieldEditora.getText();
            Integer idAutorInt = Integer.parseInt(idAutor);
            Integer idEditoraInt = Integer.parseInt(idEditora);
            titulo.setIdAutor(idAutorInt);
            titulo.setIdEditora(idEditoraInt);
        
            
            TituloDao pdao = new TituloDao();
            pdao.alterar(titulo);
            popularTabelaTitulo();
        }
 
    }
//
	private void limpar() {
        textFieldNome.setText(null);
        textFieldAutor.setText(null);
        textFieldEditora.setText(null);
	}

}
