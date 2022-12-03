package project.nikolas.javaFx.javaFxProj1;

import project.nikolas.javaFx.javaFxProj1.DaoClasses.*;
import project.nikolas.javaFx.javaFxProj1.Classes.*;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
public class ControllerTelaEmprestimo implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.popularTabelaEmprestimo();
		tableViewEmprestimo.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarEmprestimo(newValue));
		tableViewEmprestimoExemplar.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarEmprestimo_Exemplar(newValue));
	}
//

    @FXML
    private TextField textFieldIdInquilino;

    @FXML
    private DatePicker dtpDataEmprestimo;

    @FXML
    private DatePicker dtpDataDevolucao;
    
    @FXML
    private TextField textFieldIdExemplar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TableView<Emprestimo> tableViewEmprestimo;

    @FXML
    private TableColumn<Emprestimo, Integer> tableColumnId;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnNome;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnDataRetirada;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnDataDevolucao;

    @FXML
    private TableView<Emprestimo_Exemplar> tableViewEmprestimoExemplar;
    
    @FXML
    private TableColumn<Emprestimo_Exemplar, Integer> tableColumnIdEE;
    
    @FXML
    private TableColumn<Emprestimo_Exemplar, String> tableColumnNomeEE;
    
    @FXML
    private Button buttonExcluir;

    private ObservableList<Emprestimo> olEmprestimo;
    private ObservableList<Emprestimo_Exemplar> olEmprestimoExemplar;
    private Emprestimo emprestimoSelecionado;
    private Emprestimo_Exemplar emprestimoExemplarSelecionado;
    @FXML
    private Button buttonNovo;
//    
//    
    private EmprestimoDao EmprestimoDao = new EmprestimoDao();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    	EmprestimoDao = new  EmprestimoDao();
//        popularTabelaEmprestimo();
//        tableViewEmprestimo.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarEmprestimo(newValue));
//    }
//
    @FXML
    private void handlerSalvar(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
            String idInquilino = textFieldIdInquilino.getText();
            Date dataEmprestimo = Date.valueOf(dtpDataEmprestimo.getValue());
            Date dataDevolucao = Date.valueOf(dtpDataDevolucao.getValue());
            
            
 
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setIdInquilino(Integer.parseInt(idInquilino));
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataDevolucao(dataDevolucao);
            
//	 
// 
//            if ( id != 0) {
//            	EmprestimoDao.alterar(Emprestimo);
//            }else {
//            EmprestimoDao.inserir(Emprestimo);
//            }
            	
            
            EmprestimoDao dao = new EmprestimoDao();
            dao.inserir(emprestimo);
            limpar();
            popularTabelaEmprestimo();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
//
    public void handlerAdicionar(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
        	System.out.println(textFieldIdExemplar);
            String idExemplar = textFieldIdExemplar.getText();
            
            
 
            Emprestimo_Exemplar emprestimo = new Emprestimo_Exemplar();
            emprestimo.setIdExemplar(Integer.parseInt(idExemplar));
            emprestimo.setIdEmprestimo(emprestimoSelecionado.getId());
            
            Emprestimo_ExemplarDao dao = new Emprestimo_ExemplarDao();
            dao.inserir(emprestimo);
            popularTabelaEmprestimoExemplar();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
        }
    }
    
    public void handlerRemover(ActionEvent event) {
        try {
//            int id = Integer.valueOf(textFieldId.getText());
            
            
 
            Emprestimo_Exemplar emprestimoExemplar = new Emprestimo_Exemplar();
            emprestimoExemplar.setId(emprestimoExemplarSelecionado.getId());
            //emprestimo.setIdEmprestimo(emprestimoSelecionado.getId());
            
            Emprestimo_ExemplarDao dao = new Emprestimo_ExemplarDao();
            dao.remover(emprestimoExemplar);
            popularTabelaEmprestimoExemplar();
        } catch (NumberFormatException e) {
            System.out.println("Alguma coisa errada!");
            System.out.println(e);
        }
    }
    
    public void popularTabelaEmprestimo() {
//    	
    	
    	
        List<Emprestimo> lst = EmprestimoDao.listar();
        
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeInquilino"));
        tableColumnDataRetirada.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        tableColumnDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        

        olEmprestimo = FXCollections.observableArrayList(lst);
        tableViewEmprestimo.setItems(olEmprestimo);
    }
    
    public void popularTabelaEmprestimoExemplar() {
//    	
    	int integ = emprestimoSelecionado.getId();
    	Emprestimo_ExemplarDao dao = new Emprestimo_ExemplarDao();
        List<Emprestimo_Exemplar> lst;
        lst = dao.listar(integ);
        System.out.println(lst.size());
        
        tableColumnIdEE.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNomeEE.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        

        olEmprestimoExemplar = FXCollections.observableArrayList(lst);
        tableViewEmprestimoExemplar.setItems(olEmprestimoExemplar);
    }
//
    private void selecionarEmprestimo(Emprestimo cliente) {
        emprestimoSelecionado = cliente;
        popularTabelaEmprestimoExemplar();
    }
    
    private void selecionarEmprestimo_Exemplar(Emprestimo_Exemplar cliente) {
        emprestimoExemplarSelecionado = cliente;
    }

    @FXML
    private void handlerExcluirEmprestimo(ActionEvent event) {
//        
    	if (emprestimoSelecionado != null){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(emprestimoSelecionado.getId());
            System.out.println(emprestimoSelecionado.getId());
            System.out.println(emprestimo.getId());
            EmprestimoDao pdao = new EmprestimoDao();
            pdao.remover(emprestimo);
            popularTabelaEmprestimo();
        }
//        
    }
//
    @FXML
    private void handlerNovoEmprestimo(ActionEvent event) {
    	if (emprestimoSelecionado != null){
    		 
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(emprestimoSelecionado.getId());
            String idInquilino = textFieldIdInquilino.getText();
            Date dataEmprestimo = Date.valueOf(dtpDataEmprestimo.getValue());
            Date dataDevolucao = Date.valueOf(dtpDataDevolucao.getValue());
            emprestimo.setIdInquilino(Integer.parseInt(idInquilino));
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataDevolucao(dataDevolucao);
        
            
            EmprestimoDao pdao = new EmprestimoDao();
            pdao.alterar(emprestimo);
            popularTabelaEmprestimo();
        }
 
    }
//
	private void limpar() {
        textFieldIdInquilino.setText(null);
        dtpDataEmprestimo.setValue(null);
        dtpDataDevolucao.setValue(null);
	}

}
