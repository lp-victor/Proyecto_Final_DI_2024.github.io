package victor.practica7_3.controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import victor.practica7_3.modelo.ModeloConversion;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConversorController implements Initializable {

    @FXML
    private Button BTN_0;
    @FXML
    private Button BTN_2;
    @FXML
    private Button BTN_1;
    @FXML
    private Label L_resultado;
    @FXML
    private Button BTN_4;
    @FXML
    private Button BTN_3;
    @FXML
    private MenuItem MIt_cientifica;
    @FXML
    private Button BTN_6;
    @FXML
    private Button BTN_5;
    @FXML
    private Button BTN_8;
    @FXML
    private Button BTN_7;
    @FXML
    private Button BTN_igual;
    @FXML
    private Button BTN_9;
    @FXML
    private MenuItem MIt_calc_simple;
    @FXML
    private Button BTN_clear;
    @FXML
    private ComboBox CmB_valor_cambio;
    @FXML
    private Label L_valor_base;
    @FXML
    private ComboBox CmB_valor_base;
    @FXML
    private ComboBox CmB_tipo_conversion;

    private double currentNumber = 0;
    private double result = 0;
    private String tipo;
    private String base;
    private String cambio;
    private ModeloConversion modeloConversion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modeloConversion = ModeloConversion.getModeloConversion();
        cargarComboBoxOpciones();
    }

    @FXML
    public void siete(ActionEvent actionEvent) {
        numberButtonPressed(7);
    }

    @FXML
    public void ocho(ActionEvent actionEvent) {
        numberButtonPressed(8);
    }

    @FXML
    public void dos(ActionEvent actionEvent) {
        numberButtonPressed(2);
    }

    @FXML
    public void seis(ActionEvent actionEvent) {
        numberButtonPressed(6);
    }

    @FXML
    public void nueve(ActionEvent actionEvent) {
        numberButtonPressed(9);
    }

    @FXML
    public void cuatro(ActionEvent actionEvent) {
        numberButtonPressed(4);
    }

    @FXML
    public void cinco(ActionEvent actionEvent) {
        numberButtonPressed(5);
    }

    @FXML
    public void cero(ActionEvent actionEvent) {
        numberButtonPressed(0);
    }

    @FXML
    public void uno(ActionEvent actionEvent) {
        numberButtonPressed(1);
    }

    @FXML
    public void tres(ActionEvent actionEvent) {
        numberButtonPressed(3);
    }

    @FXML
    public void igual(ActionEvent actionEvent) {
        L_resultado.setText(String.valueOf(modeloConversion.convertir(tipo,base,cambio,currentNumber)));
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        L_valor_base.setText("");
        L_resultado.setText("");
        currentNumber = 0;
        result = 0;
    }


    @FXML
    public void abrir_calc_simple(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/victor/practica7_3/vista/calculadora.fxml"));

            Parent root = fxmlLoader.load();

            CalculadoraController calculadoraController = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            String path = "/images/icon_calculator.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("Calculadora");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage myStage = (Stage) this.BTN_0.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    @FXML
    public void abrir_cientifica(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/victor/practica7_3/vista/calculadora_cientifica.fxml"));

            Parent root = fxmlLoader.load();

            CalculadoraCientificaController calculadoraCientificaController = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            String path = "/images/icon_calculator.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("Calculadora Cientifica");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage myStage = (Stage) this.BTN_0.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    @FXML
    public void recogerTeclado(Event event){
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            String teclaPresionada = keyEvent.getText();
            if (teclaPresionada.matches("[0-9]")) {
                numberButtonPressed(Integer.parseInt(teclaPresionada));
                L_resultado.setText(L_resultado.getText() + teclaPresionada);
            }
        }
    }

    private void numberButtonPressed(int number) {
            currentNumber = currentNumber * 10 + number;
            L_valor_base.setText(L_valor_base.getText() + number);
    }

    private void cargarComboBoxOpciones() {
        List<String> tiposConversion = List.of(new String [] {"Moneda", "Longitud", "Tiempo", "Masa"});
        CmB_tipo_conversion.getItems().addAll(tiposConversion);

        CmB_tipo_conversion.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String opcionSeleccionada = newValue.toString();

                switch (opcionSeleccionada){
                    case "Moneda":
                        tipo = "MONEDA";
                        cargarComboBoxSegunOpcion("Moneda");
                        break;
                    case "Longitud":
                        tipo = "Longitud";
                        cargarComboBoxSegunOpcion("Longitud");
                        break;
                    case "Tiempo":
                        tipo = "Tiempo";
                        cargarComboBoxSegunOpcion("Tiempo");
                        break;
                    case "Masa":
                        tipo = "Masa";
                        cargarComboBoxSegunOpcion("Masa");
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void cargarComboBoxSegunOpcion(String opcion) {
        CmB_valor_cambio.getItems().clear();
        CmB_valor_base.getItems().clear();

        switch (opcion){
            case "Moneda":
                CmB_valor_base.getItems().addAll(modeloConversion.getMonedas());
                CmB_valor_cambio.getItems().addAll(modeloConversion.getMonedas());
                break;
            case "Longitud":
                CmB_valor_base.getItems().addAll(modeloConversion.getUnidadesLongitud());
                CmB_valor_cambio.getItems().addAll(modeloConversion.getUnidadesLongitud());
                break;
            case "Tiempo":
                CmB_valor_base.getItems().addAll(modeloConversion.getUnidadesTiempo());
                CmB_valor_cambio.getItems().addAll(modeloConversion.getUnidadesTiempo());
                break;
            case "Masa":
                CmB_valor_base.getItems().addAll(modeloConversion.getUnidadesMasa());
                CmB_valor_cambio.getItems().addAll(modeloConversion.getUnidadesMasa());
                break;
            default:
                break;
        }

        CmB_valor_base.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (tipo.equalsIgnoreCase("MONEDA")){
                    base = newValue.toString().toUpperCase();
                } else {
                    base = newValue.toString();
                }
            }
        });

        CmB_valor_cambio.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (tipo.equalsIgnoreCase("MONEDA")){
                cambio = newValue.toString().toUpperCase();
            } else {
                cambio = newValue.toString();
            }
        });

    }

    @FXML
    public void abrirValorConversor(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/victor/practica7_3/vista/valor_conversion.fxml"));
            Parent root = fxmlLoader.load();

            ValorConversionController valorConversionController = new ValorConversionController();

            Stage stage = new Stage();

            String path = "/images/icon_calculator.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("Valores de conversión");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }
}