
package vista;

import java.util.Scanner;

import controlador.IntegracionController;
import modelo.IntegracionAPI;
import modelo.ProveedorLogistico;
import interfaces.Transaccional;
import interfaces.Documentable;
import interfaces.Cotizable;

public class MenuConsola {

    private IntegracionController controlador;
    private Scanner scanner;

    public MenuConsola(IntegracionController controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }


    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("SISTEMA DE INTEGRACIONES API ");
            System.out.println("1. Listar integraciones");
            System.out.println("2. Buscar integración por ID");
            System.out.println("3. Ordenar por latencia");
            System.out.println("4. Procesar cobro");
            System.out.println("5. Procesar reintegro");
            System.out.println("6. Generar documento");
            System.out.println("7. Cotizar envío");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ingrese una opción numérica.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    listarIntegraciones();
                    break;

                case 2:
                    buscarIntegracion();
                    break;

                case 3:
                    ordenarIntegraciones();
                    break;

                case 4:
                    procesarCobro();
                    break;

                case 5:
                    procesarReintegro();
                    break;

                case 6:
                    generarDocumento();
                    break;

                case 7:
                    cotizarEnvio();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    private void listarIntegraciones() {

        System.out.println("\n===== INTEGRACIONES REGISTRADAS =====");

        for (IntegracionAPI integracion : controlador.getIntegraciones()) {
            System.out.println(integracion);
        }
    }


    private void buscarIntegracion() {

        System.out.print("Ingrese el ID de la integración: ");
        String id = scanner.nextLine();

        IntegracionAPI integracion = controlador.buscarPorId(id);

        if (integracion != null) {

            System.out.println("\nIntegración encontrada:");
            System.out.println(integracion);

            System.out.println("Parámetros de configuración: "
                    + integracion.getParametrosConfiguracion());

        } else {
            System.out.println("No se encontró la integración.");
        }
    }


    private void ordenarIntegraciones() {

        controlador.ordenarPorLatencia();

        System.out.println("\nIntegraciones ordenadas por latencia:");
        listarIntegraciones();
    }


    private void procesarCobro() {

        System.out.print("Ingrese el ID de la pasarela: ");
        String id = scanner.nextLine();

        IntegracionAPI integracion = controlador.buscarPorId(id);

        if (integracion instanceof Transaccional) {

            System.out.print("Ingrese el monto a cobrar: Q");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            controlador.procesarOperacion(
                    (Transaccional) integracion, monto);

        } else {
            System.out.println(
                    "La integración seleccionada no permite cobros.");
        }
    }


    private void procesarReintegro() {

        System.out.print("Ingrese el ID de la integración: ");
        String id = scanner.nextLine();

        IntegracionAPI integracion = controlador.buscarPorId(id);

        if (integracion == null) {
            System.out.println("No se encontró la integración.");
            return;
        }

        System.out.print("Ingrese el monto del reintegro: Q");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        controlador.procesarReintegro(integracion, monto);
    }


    private void generarDocumento() {

        System.out.print("Ingrese el ID de la integración: ");
        String id = scanner.nextLine();

        IntegracionAPI integracion = controlador.buscarPorId(id);

        if (integracion instanceof Documentable) {

            System.out.print("Ingrese los datos del documento: ");
            String datos = scanner.nextLine();

            String documento = controlador.procesarOperacion(
                    (Documentable) integracion, datos);

            System.out.println("\nDocumento generado:");
            System.out.println(documento);

        } else {
            System.out.println(
                    "Esta integración no permite generar documentos.");
        }
    }


    private void cotizarEnvio() {

        System.out.print("Ingrese el ID del proveedor logístico: ");
        String id = scanner.nextLine();

        IntegracionAPI integracion = controlador.buscarPorId(id);

        if (integracion instanceof Cotizable) {

            Cotizable proveedor = (Cotizable) integracion;

            System.out.print("Ingrese el peso del paquete: ");
            double peso = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Ingrese el destino: ");
            String destino = scanner.nextLine();

            double costo = proveedor.cotizar(peso, destino);

            if (costo >= 0) {
                System.out.println("Costo estimado del envío: Q" + costo);
            } else {
                System.out.println("No fue posible realizar la cotización.");
            }

        } else {
            System.out.println(
                    "Esta integración no permite cotizar envíos.");
        }
    }
}
