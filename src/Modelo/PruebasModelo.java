package Modelo;

public class PruebasModelo {
    public static void main(String[] args) {
        LaLigaModel model = LaLigaModel.getInstance();

        try {
            model.initModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
