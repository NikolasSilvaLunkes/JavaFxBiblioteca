package project.nikolas.javaFx.javaFxProj1;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class ControllerTelaPrincipal implements Initializable {

    
    
    @FXML
    private MenuItem menuTeste;
    

    @FXML
    private AnchorPane anchorPaneFundo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handlerTelaAdicionarInquilino(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaAdicionarInquilino.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaAutor(javafx.event.ActionEvent event) throws IOException {
    	System.out.println("Autor");
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaAutor.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaEditora(javafx.event.ActionEvent event) throws IOException {
    	System.out.println("Autor");
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaEditora.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    
    @FXML
    private void handlerListaInquilino(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaInquilino.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaTitulo(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaTitulo.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerRealizarEmprestimo(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaEmprestimo.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaExemplar(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaExemplar.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    
    
    @FXML
    private void handlerTelaMedico(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaMedico.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerRealizarAtendimento(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaAtendimento.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerTelaAgenda(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaAgenda.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    @FXML
    private void handlerRelatorioPaciente(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/Relatorios.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerRelatorioMedico(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/project/nikolas/javaFx/javaFXProj1/TelaRelatorioMedico.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }

}
