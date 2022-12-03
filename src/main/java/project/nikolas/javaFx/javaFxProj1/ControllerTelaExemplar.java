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
public class ControllerTelaExemplar implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaExemplar();
		tableViewExemplar.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarExemplar(newValue));
	}
//

    @FXML
    private TextField textFieldEdicao;

    @FXML
    private TextField textFieldLocalizacao;

    @FXML
    private TextField textFieldIdTitulo;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Exemplar> tableViewExemplar;

    @FXML
    private TableColumn<Exemplar, Integer> tableColumnId;

    @FXML
    private TableColumn<Exemplar, String> tableColumnNome;

    @FXML
    private TableColumn<Exemplar, String> tableColumnAutor;

    @FXML
    private TableColumn<Exemplar, String> tableColumnEditora;
    
    @FXML
    private TableColumn<Exemplar, String> tableColumnEdicao;
    
    @FXML
    private TableColumn<Exemplar, String> tableColumnLocalizacao;

    @FXML
    private Button buttonExcluir;

    private ObservableList<Exemplar> olInquilino;
    private Exemplar exemplarSelecionado;
    @FXML
    private Button buttonNovo;
//    
//    
    private ExemplarDao ExemplarDao = new ExemplarDao();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    	ExemplarDao = new  ExemplarDao();
//        popularTabelaExemplar();
//        tableViewExemplar.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarExemplar(newValue));
//    }
//
    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
            
            
 
            Exemplar exemplar = new Exemplar();
            exemplar.setIdTitulo(Integer.parseInt(textFieldIdTitulo.getText()));
            exemplar.setLocalizacao(textFieldLocalizacao.getText());
            exemplar.setEdicao(textFieldEdicao.getText());
            
//	 
// 
//            if ( id != 0) {
//            	ExemplarDao.alterar(Exemplar);
//            }else {
//            ExemplarDao.inserir(Exemplar);
//            }
            	
            
            ExemplarDao dao = new ExemplarDao();
            dao.inserir(exemplar);
            limpar();
            popularTabelaExemplar();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void popularTabelaExemplar() {
//    	
    	
    	
        List<Exemplar> lst = ExemplarDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        tableColumnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        tableColumnLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
       
        olInquilino = FXCollections.observableArrayList(lst);
        tableViewExemplar.setItems(olInquilino);
    }
//
    private void selecionarExemplar(Exemplar cliente) {
        exemplarSelecionado = cliente;
    }

    @FXML
    private void handlerExcluirExemplar(ActionEvent event) {
    	if (exemplarSelecionado != null){
            Exemplar exemplar = new Exemplar();
            exemplar.setId(exemplarSelecionado.getId());
            System.out.println(exemplarSelecionado.getId());
            System.out.println(exemplar.getId());
            ExemplarDao pdao = new ExemplarDao();
            pdao.remover(exemplar);
            popularTabelaExemplar();
        }
//        
    }
//
    @FXML
    private void handlerAlterarExemplar(ActionEvent event) {
    	if (exemplarSelecionado != null){
   		 
            Exemplar exemplar = new Exemplar();
            exemplar.setId(exemplarSelecionado.getId());
            exemplar.setEdicao(textFieldEdicao.getText());
            exemplar.setLocalizacao(textFieldLocalizacao.getText());
            String idTitulo = textFieldIdTitulo.getText();
            Integer idTituloInt = Integer.parseInt(idTitulo);
            exemplar.setIdTitulo(idTituloInt);
        
            
            ExemplarDao pdao = new ExemplarDao();
            pdao.alterar(exemplar);
            popularTabelaExemplar();
        }
 
    }
//
	private void limpar() {
        textFieldIdTitulo.setText(null);
        textFieldEdicao.setText(null);
        textFieldLocalizacao.setText(null);
	}

}
