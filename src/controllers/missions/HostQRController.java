/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missions;

import com.jfoenix.controls.JFXButton;
import static gui.missions.HostVariableManager.WINDOW_HEIGHT;
import static gui.missions.HostVariableManager.WINDOW_WIDTH;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import loaders.HostListLoader;

/**
 * FXML Controller class
 *
 * @author Weepey
 */
public class HostQRController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Button buttonQR;
    @FXML
    private JFXButton ReturnButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    /*   String imagePath = "src/res/QRCodeMailer.png";
       
    Image image = new Image(imagePath);

    ImageView imageView = new ImageView(image);

    Button saveBtn = new Button("Save Image");
    saveBtn.setOnAction(e -> saveToFile(image));
/*
    VBox root = new VBox(10, imageView, saveBtn);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("");
    stage.show();*/
    buttonQR.setOnAction(e -> saveToFile());

       ReturnButton.onMouseReleasedProperty().set((event) -> {
            ((Stage) ReturnButton.getScene().getWindow()).close();
        });
    }    

    @FXML
    private void AddHostAction(ActionEvent event) {
    }


    @FXML
    private void saveToFile() {
        {
    String imagePath = "/res/QRCodeMailer.png";
    Image image = new Image(imagePath);

    File outputFile = new File("C:/ecosystemjava/src/res/CanvasImage.png");
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
      ImageIO.write(bImage, "png",  outputFile );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
        
    }
    
}
