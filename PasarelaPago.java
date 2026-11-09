
package modelo;

import interfaces.Transaccional;
import interfaces.Consultable;
import interfaces.Auditable;

public class PasarelaPago extends IntegracionAPI
        implements Transaccional, Consultable, Auditable {

    public PasarelaPago(String id, String nombreProveedor,
            String estadoConexion, double latenciaPromedio) {

        super(id, nombreProveedor, estadoConexion, latenciaPromedio);
    }

    @Override
    public boolean procesarCobro(double monto) {

        if (monto <= 0) {
            return false;
        }

        System.out.println("Cobro procesado por " + getNombreProveedor()
                + ". Monto: Q" + monto);

        registrarAuditoria("Cobro de Q" + monto);

        return true;
    }

    @Override
    public boolean procesarReintegro(double monto) {

        if (monto <= 0) {
            return false;
        }

        System.out.println("Reintegro procesado por " + getNombreProveedor()
                + ". Monto: Q" + monto);

        registrarAuditoria("Reintegro de Q" + monto);

        return true;
    }

    @Override
    public String consultarEstado() {
        return "Estado de la pasarela: " + getEstadoConexion();
    }

    @Override
    public void registrarAuditoria(String evento) {
        System.out.println("[AUDITORÍA - " + getNombreProveedor()
                + "] " + evento);
    }
}
