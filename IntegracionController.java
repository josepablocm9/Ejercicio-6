
package controlador;

import java.util.ArrayList;
import java.util.Collections;
import modelo.IntegracionAPI;
import modelo.PasarelaPago;
import modelo.ProveedorLogistico;
import modelo.CertificadorFEL;
import interfaces.Transaccional;
import interfaces.Documentable;

public class IntegracionController {


    private ArrayList<IntegracionAPI> integraciones;

    public IntegracionController() {
        integraciones = new ArrayList<>();
        cargarIntegracionesIniciales();
    }


    private void cargarIntegracionesIniciales() {

        integraciones.add(new PasarelaPago(
                "P001", "PayPal", "Activa", 120));

        integraciones.add(new PasarelaPago(
                "P002", "Stripe", "Activa", 95));

        integraciones.add(new PasarelaPago(
                "P003", "PayU", "Inactiva", 180));

        integraciones.add(new PasarelaPago(
                "P004", "Kushki", "Activa", 110));

        integraciones.add(new ProveedorLogistico(
                "L001", "DHL", "Activa", 150));

        integraciones.add(new ProveedorLogistico(
                "L002", "FedEx", "Activa", 130));

        integraciones.add(new ProveedorLogistico(
                "L003", "UPS", "Inactiva", 210));

        integraciones.add(new ProveedorLogistico(
                "L004", "Cargo Expreso", "Activa", 170));

        integraciones.add(new CertificadorFEL(
                "F001", "Certificador FEL A", "Activa", 90));

        integraciones.add(new CertificadorFEL(
                "F002", "Certificador FEL B", "Activa", 105));
    }


    public ArrayList<IntegracionAPI> getIntegraciones() {
        return integraciones;
    }


    public IntegracionAPI buscarPorId(String id) {

        for (IntegracionAPI integracion : integraciones) {

            if (integracion.getId().equalsIgnoreCase(id)) {
                return integracion;
            }
        }

        return null;
    }

 
    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

  
    public boolean procesarOperacion(Transaccional integracion,
            double monto) {

        return integracion.procesarCobro(monto);
    }


    public String procesarOperacion(Documentable integracion,
            String datos) {

        return integracion.generarDocumento(datos);
    }


    public boolean procesarReintegro(IntegracionAPI integracion,
            double monto) {

        if (integracion instanceof Transaccional) {

            Transaccional transaccion = (Transaccional) integracion;

            return transaccion.procesarReintegro(monto);
        }

        System.out.println("Esta integración no permite reintegros.");
        return false;
    }
}
