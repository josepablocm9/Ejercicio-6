
import controlador.IntegracionController;
import vista.MenuConsola;

public class Main {

    public static void main(String[] args) {

        IntegracionController controlador =
                new IntegracionController();

        MenuConsola vista =
                new MenuConsola(controlador);

        vista.mostrarMenu();
    }
}
