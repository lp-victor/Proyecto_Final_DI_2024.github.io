package victor.practica7_3.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloConversion {

    private static ModeloConversion modeloConversion = null;
    private List<String> unidadesLongitud = List.of(new String[] {"mm", "cm", "dm", "m", "dam", "hm", "km"});
    private List<String> unidadesMasa = List.of(new String[] {"mg", "cg", "dg", "g", "dag", "hg", "kg", "T"});
    private List<String> unidadesTiempo = List.of(new String[] {"ms", "S", "M", "h", "d", "s", "m", "a"});
    private String[] monedas = new String[] {"EUR", "GBP", "USD", "RUB", "YEN"};

    private Map<String, Double> factoresLongitud;
    private Map<String, Double> factoresTiempo;
    private Map<String, Double> factoresMasa;
    private Map<String, Double> factoresMoneda;

    private ModeloConversion(){
        inicializarFactoresConversion();
    }

    public static ModeloConversion getModeloConversion(){
        if (modeloConversion == null){
            modeloConversion = new ModeloConversion();
        }
        return modeloConversion;
    }

    public List<String> getUnidadesLongitud() {
        return unidadesLongitud;
    }

    public List<String> getUnidadesMasa() {
        return unidadesMasa;
    }

    public List<String> getUnidadesTiempo() {
        return unidadesTiempo;
    }

    public String[] getMonedas() {
        return monedas;
    }

    private void inicializarFactoresConversion() {

        factoresLongitud = new HashMap<>();
        factoresLongitud.put("mm", 0.001);
        factoresLongitud.put("cm", 0.01);
        factoresLongitud.put("dm", 0.1);
        factoresLongitud.put("m", 1.0);
        factoresLongitud.put("dam", 10.0);
        factoresLongitud.put("hm", 100.0);
        factoresLongitud.put("km", 1000.0);


        factoresTiempo = new HashMap<>();
        factoresTiempo.put("ms", 0.001);
        factoresTiempo.put("S", 1.0);
        factoresTiempo.put("M", 60.0);
        factoresTiempo.put("h", 3600.0);
        factoresTiempo.put("d", 86400.0);
        factoresTiempo.put("s", 604800.0);
        factoresTiempo.put("m", 18144000.0);
        factoresTiempo.put("a", 6622560000.0);

        factoresMasa = new HashMap<>();
        factoresMasa.put("mg", 0.001);
        factoresMasa.put("cg", 0.01);
        factoresMasa.put("dg", 0.1);
        factoresMasa.put("g", 1.0);
        factoresMasa.put("dag", 10.0);
        factoresMasa.put("hg", 100.0);
        factoresMasa.put("kg", 1000.0);
        factoresMasa.put("T", 1000000.0);

        factoresMoneda = new HashMap<>();
        factoresMoneda.put("EUR", 1.0);
        factoresMoneda.put("GBP", 2.0);
        factoresMoneda.put("USD", 3.0);
        factoresMoneda.put("RUB", 4.0);
        factoresMoneda.put("YEN", 5.0);
    }

    public double convertir(String tipo, String unidadOrigen, String unidadDestino, double valor) {
        // Verificar el tipo de conversión y usar el mapa correspondiente
        Map<String, Double> factores = null;
        switch (tipo) {
            case "MONEDA":
                factores = factoresMoneda;
                break;
            case "Longitud":
                factores = factoresLongitud;
                break;
            case "Tiempo":
                factores = factoresTiempo;
                break;
            case "Masa":
                factores = factoresMasa;
                break;
            default:
                throw new IllegalArgumentException("Tipo de conversión no soportado");
        }

        Double factorOrigen = factores.get(unidadOrigen);
        if (factorOrigen == null) {
            throw new IllegalArgumentException("Unidad de origen no soportada o desconocida: " + unidadOrigen);
        }

        Double factorDestino = factores.get(unidadDestino);
        if (factorDestino == null) {
            throw new IllegalArgumentException("Unidad de destino no soportada o desconocida: " + unidadDestino);
        }

        double valorEnBase = valor * factorOrigen;


        return valorEnBase / factorDestino;
    }

    public void setFactorConversionMoneda(HashMap<String, Double> factoresMonedas) {
        factoresMoneda = factoresMonedas;
    }

    public HashMap<String, Double> getFactoresMoneda (){
        return new HashMap<>(factoresMoneda);
    }

}

