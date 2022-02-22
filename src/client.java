import Controlador.LaLigaController;
import Modelo.LaLigaModel;
import Vista.LaLigaView;
import Vista.vistaGrafica1;

public class client {
    public static void main(String[] args) {
        LaLigaModel model = LaLigaModel.getInstance();
        LaLigaView view = new vistaGrafica1();
        LaLigaController controller =  new LaLigaController(model, view);
        controller.getVista().display();
    }
}
