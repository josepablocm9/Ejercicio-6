
package modelo;

import interfaces.Documentable;
import interfaces.Auditable;
import interfaces.Consultable;

public class CertificadorFEL extends IntegracionAPI
        implements Documentable, Auditable, Consultable {

    public CertificadorFEL(String id, String nombreProveedor,
            String estadoConexion, double latenciaPromedio) {

        super(id, nombreProveedor, estadoConexion, latenciaPromedio);
    }

    @Override
    public String generarDocumento(String datos) {

        String documento = "<factura>"
                + "<certificador>" + getNombreProveedor()
                + "</certificador>"
                + "<datos>" + datos + "</datos>"
                + "</factura>";

        registrarAuditoria("Generación de documento FEL");

        return documento;
    }

    @Override
    public void registrarAuditoria(String evento) {
        System.out.println("[AUDITORÍA - " + getNombreProveedor()
                + "] " + evento);
    }

    @Override
    public String consultarEstado() {
        return "Estado del certificador FEL: "
                + getEstadoConexion();
    }
}