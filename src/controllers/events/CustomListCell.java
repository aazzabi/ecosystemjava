/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.events;

import entities.Evenement;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Mohamed Bousselmi
 */
 public class CustomListCell extends ListCell<Evenement> {
        private HBox content;
        private Text createdBy;
        private Text titre;
        private Text lieu;
        private Text date;
        private Text categorie;
        Button btn = new Button();
       
       
        private ImageView img;
        

        public CustomListCell() {
            super();
            createdBy = new Text();
            titre = new Text();
            lieu = new Text();
            date= new Text();
            categorie= new Text();
            img = new ImageView();
            img.setFitHeight(100);
            img.setFitWidth(100);
            btn.setText("Voir");
            titre.setFont(Font.font(16));
            VBox vBox = new VBox(titre,createdBy,date,lieu,categorie,btn);
            vBox.setSpacing(10);
            
            content = new HBox(img, vBox);
            content.setSpacing(50);
        }

        @Override
        protected void updateItem(Evenement item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                createdBy.setText("Créé par : "+item.getCreatedBy().toString());
                titre.setText(item.getTitre()+"                                                                             "+item.getCategorie().toString()); 
                lieu.setText("à : "+item.getLieu());
                date.setText("Le : "+item.getDate().toString());
            
                
                javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/ecosystemweb/web/uploads/evt/cover/"+item.getCover());   
                img.setImage(im);
                
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }
    

