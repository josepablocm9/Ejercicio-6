package interfaces;

public interface Transaccional {

    boolean procesarCobro(double monto);

    boolean procesarReintegro(double monto);

}