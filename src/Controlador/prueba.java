package Controlador;

import Modelo.LaLigaModel;
import Vista.View;
import Vista.VistaCLI;

public class prueba {
    public static void main(String[] args) {
        LaLigaModel model = LaLigaModel.getInstance();
        LaLigaController controller =  new LaLigaController(model);
        controller.getVista().display();
    }


}
