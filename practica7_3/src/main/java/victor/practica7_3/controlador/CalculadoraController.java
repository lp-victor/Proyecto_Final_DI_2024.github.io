package victor.practica7_3.controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import victor.practica7_3.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {


    @FXML
    private Button BTN_div;
    @FXML
    private Button BTN_0;
    @FXML
    private Button BTN_2;
    @FXML
    private Button BTN_1;
    @FXML
    private Button BTN_C;
    @FXML
    private Button BTN_mult;
    @FXML
    private Button BTN_mas;
    @FXML
    private Button BTN_4;
    @FXML
    private Button BTN_3;
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
    private Button BTN_punto;
    @FXML
    private Button BTN_mMenos;
    @FXML
    private Label L_resultado;
    @FXML
    private Button BTN_menos;
    @FXML
    private Button BTN_mMas;
    @FXML
    private Label L_operacion;
    @FXML
    private Button BTN_masMenos;
    @FXML
    private MenuItem MIt_cientifica;
    @FXML
    private MenuItem MIt_conv;

    private double currentNumber = 0;
    private double result = 0;
    private String memory = "";
    private String operator = "";
    private boolean startNewNumber = true;
    private boolean decimalUsed = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        L_resultado.setText("");
        L_resultado.requestFocus();
    }

    @FXML
    public void sumar(ActionEvent actionEvent) {
        operate("+");
    }

    @FXML
    public void multiplicacion(ActionEvent actionEvent) {
        operate("*");
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
    public void clear(ActionEvent actionEvent) {
        resetCalculator();
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
    public void division(ActionEvent actionEvent) {
        operate("/");
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
    public void igual(ActionEvent actionEvent) {
        operate("=");
    }

    @FXML
    public void tres(ActionEvent actionEvent) {
        numberButtonPressed(3);
    }

    @FXML
    public void resta(ActionEvent actionEvent) {
        operate("-");
    }

    // Método genérico para cuando se presiona un botón de número
    private void numberButtonPressed(int number) {
        if (startNewNumber) {
            currentNumber = number;
            L_resultado.setText(String.valueOf(number));
            L_operacion.setText(L_operacion.getText() + number);
            startNewNumber = false;
        } else {
            currentNumber = currentNumber * 10 + number;
            L_resultado.setText(String.valueOf(currentNumber));
            L_operacion.setText(L_operacion.getText() + number);
        }
    }

    // Método para manejar las operaciones
    private void operate(String newOperator) {
        if (!startNewNumber) {
            calculate();
            operator = newOperator;
        } else {
            // Especialmente para el caso cuando se presiona el botón igual sin una operación previa
            if (newOperator.equals("=") && !operator.isEmpty()) {
                calculate();
            }
        }

        // Aquí cambiamos la lógica para cuando se presiona el botón igual
        if (!newOperator.equals("=")) {
            // Si no es el botón igual, prepararse para una nueva entrada
            startNewNumber = true;
            L_resultado.setText("0");
            L_operacion.setText(L_operacion.getText() + " " + newOperator + " ");
        } else {
            // Para el botón igual, no reiniciar la entrada ni mostrar el operador
            L_operacion.setText(""); // Opcional: limpiar la operación después de calcular el resultado
        }
    }

    // Método para calcular basado en el operador
    private void calculate() {
        switch (operator) {
            case "+":
                result += currentNumber;
                break;
            case "-":
                result -= currentNumber;
                break;
            case "*":
                result *= currentNumber;
                break;
            case "/":
                if (currentNumber != 0) {
                    result /= currentNumber;
                } else {
                    // En lugar de resetear, muestra el error pero permite continuar
                    L_resultado.setText("SyntaxError");
                    // No llames a resetCalculator aquí para permitir al usuario corregir su acción
                    return;
                }
                break;
            default:
                result = currentNumber;
                break;
        }
        L_resultado.setText(String.valueOf(result));
        currentNumber = Double.parseDouble(L_resultado.getText());
        operator = "";
        startNewNumber = true;
    }

    // Método para reiniciar la calculadora
    private void resetCalculator() {
        result = 0;
        currentNumber = 0;
        operator = "";
        startNewNumber = true;
        L_resultado.setText("0");
        L_operacion.setText("");
        decimalUsed = false;
    }

    @FXML
    public void guardarMemoria(ActionEvent actionEvent) {
        try {
            memory = L_resultado.getText();
        } catch (NumberFormatException e) {
            L_resultado.setText("Error!");
        }
    }

    @FXML
    public void recuperarMemoria(ActionEvent actionEvent) {
        L_resultado.setText(String.valueOf(memory));
        currentNumber = Double.parseDouble(memory);
        startNewNumber = true;
    }


    @FXML
    public void decimal(ActionEvent actionEvent) {
        if (!decimalUsed) {
            if (startNewNumber) {
                L_resultado.setText("0.");
                startNewNumber = false;
            } else {
                L_resultado.setText(L_resultado.getText() + ".");
            }
            decimalUsed = true;
        }
    }

    @FXML
    public void cambiarSigno(ActionEvent actionEvent) {
        double value = Double.parseDouble(L_resultado.getText()) * -1;
        L_resultado.setText(String.valueOf(value));
        currentNumber = value;
    }

    @FXML
    public void recogerTeclado(Event event){
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            String teclaPresionada = keyEvent.getText();
            if (teclaPresionada.matches("[0-9]")) {
                numberButtonPressed(Integer.parseInt(teclaPresionada));
                L_resultado.setText(L_resultado.getText() + teclaPresionada);
            } else if (teclaPresionada.matches("[+/*,.\\-]")){
                operate(teclaPresionada);
            }
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
    public void abrir_conv(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/victor/practica7_3/vista/conversor.fxml"));

            Parent root = fxmlLoader.load();

            ConversorController conversorController = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            String path = "/images/icon_calculator.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("Conversor");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage myStage = (Stage) this.BTN_0.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

}