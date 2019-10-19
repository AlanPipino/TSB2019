package interfaz;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import negocio.Pais;

import java.io.File;

public class PrincipalController {
    public Button btnCargar;
    public TextArea txtResultados;

    public void cargar(ActionEvent actionEvent) {
        DirectoryChooser fc = new DirectoryChooser();
        File file = fc.showDialog(null);
        if(file != null){
            Pais pais = new Pais(file.getPath());
            txtResultados.setText(pais.toString());
        }
    }
}
