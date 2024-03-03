package victor.practica7_3.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import victor.practica7_3.modelo.ModeloConversion;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ValorConversionController implements Initializable{


    @FXML
    private TextField eur;
    @FXML
    private TextField yen;
    @FXML
    private TextField gbp;
    @FXML
    private TextField usd;
    @FXML
    private TextField rub;

    Map<String, Double> valores;
    ModeloConversion modeloConversion;

    @FXML
    private Button BTN_volver;
    @FXML
    private Button BTN_aceptar;
    @FXML
    private Label L_campos_vacios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      modeloConversion = ModeloConversion.getModeloConversion();
    }


    @FXML
    public void aceptar(ActionEvent actionEvent) {
        L_campos_vacios.setVisible(false);
        valores = modeloConversion.getFactoresMoneda();
        if (eur.getText().isBlank() || yen.getText().isBlank() || gbp.getText().isBlank() || usd.getText().isBlank() || rub.getText().isBlank()) {
            L_campos_vacios.setVisible(true);
        } else {
            valores.replace("EUR",Double.parseDouble(eur.getText()));
            valores.replace("YEN",Double.parseDouble(yen.getText()));
            valores.replace("GBP",Double.parseDouble(gbp.getText()));
            valores.replace("USD",Double.parseDouble(usd.getText()));
            valores.replace("RUB",Double.parseDouble(rub.getText()));

            modeloConversion.setFactorConversionMoneda((HashMap<String, Double>) valores);

            Stage myStage = (Stage) this.BTN_aceptar.getScene().getWindow();
            myStage.close();
        }
    }

    @FXML
    public void volver(ActionEvent actionEvent) {
        Stage myStage = (Stage) this.BTN_volver.getScene().getWindow();
        myStage.close();
    }
}


