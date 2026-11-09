
package modelo;

import interfaces.Documentable;
import interfaces.Auditable;
import interfaces.Consultable;
import interfaces.Cotizable;

public class ProveedorLogistico extends IntegracionAPI
        implements Documentable, Auditable, Consultable, Cotizable {

    public ProveedorLogistico(String id, String nombreProveedor,
            String estadoConexion, double latenciaPromedio) {

        super(id, nombreProveedor, estadoConexion, latenciaPromedio);
    }

    @Override
    public String generarDocumento(String datos) {

        String documento = "GUÍA DE ENVÍO - "
                + getNombreProveedor()
                + " | Datos: " + datos;

        registrarAuditoria("Generación de guía de envío");

        return documento;
    }

    @Override
    public void registrarAuditoria(String evento) {
        System.out.println("[AUDITORÍA - " + getNombreProveedor()
                + "] " + evento);
    }

    @Override
    public String consultarEstado() {
        return "Estado del proveedor logístico: "
                + getEstadoConexion();
    }

    @Override
    public double cotizar(double peso, String destino) {

        if (peso <= 0 || destino == null || destino.isBlank()) {
            return -1;
        }

        double tarifaBase = 25;
        double costo = tarifaBase + (peso * 5);

        registrarAuditoria("Cotización para " + destino);

        return costo;
    }
}
