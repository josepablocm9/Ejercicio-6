package modelo;

import java.util.HashMap;
import java.util.Map;

public abstract class IntegracionAPI implements Comparable<IntegracionAPI> {

    
    private String id;
    private String nombreProveedor;
    private String estadoConexion;
    private double latenciaPromedio;
    private Map<String, String> parametrosConfiguracion;

   
    public IntegracionAPI(String id, String nombreProveedor,
            String estadoConexion, double latenciaPromedio,
            Map<String, String> parametrosConfiguracion) {

        this.id = id;
        this.nombreProveedor = nombreProveedor;
        this.estadoConexion = estadoConexion;
        this.latenciaPromedio = latenciaPromedio;
        this.parametrosConfiguracion = new HashMap<>(parametrosConfiguracion);
    }

    
    public IntegracionAPI(String id, String nombreProveedor,
            String estadoConexion, double latenciaPromedio) {

        this(id, nombreProveedor, estadoConexion, latenciaPromedio,
                new HashMap<>());
    }

    
    public String getId() {
        return id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public Map<String, String> getParametrosConfiguracion() {
        return new HashMap<>(parametrosConfiguracion);
    }

  
    public void setEstadoConexion(String estadoConexion) {
        this.estadoConexion = estadoConexion;
    }

    public void setLatenciaPromedio(double latenciaPromedio) {
        this.latenciaPromedio = latenciaPromedio;
    }

    public void setParametro(String clave, String valor) {
        parametrosConfiguracion.put(clave, valor);
    }


    @Override
    public int compareTo(IntegracionAPI otra) {
        return Double.compare(
                this.latenciaPromedio,
                otra.latenciaPromedio
        );
    }

  
    @Override
    public String toString() {
        return "ID: " + id
                + " | Proveedor: " + nombreProveedor
                + " | Estado: " + estadoConexion
                + " | Latencia: " + latenciaPromedio + " ms"
                + " | Parámetros: " + parametrosConfiguracion;
    }

 
    @Override
    public boolean equals(Object objeto) {

        if (this == objeto) {
            return true;
        }

        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }

        IntegracionAPI otra = (IntegracionAPI) objeto;

        return id.equals(otra.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}