/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Rania
 */
public class Categorie_Evts extends RecursiveTreeObject<Categorie_Evts> {
   
    private int id;
    private StringProperty libelle;
    private StringProperty but;

    public Categorie_Evts(String libelle, String but) {
       
        this.libelle = new SimpleStringProperty(libelle);
        this.but =new SimpleStringProperty(but);
    }
    
   public Categorie_Evts(int id, String libelle, String but) {
   this.id = id;
   this.libelle = new SimpleStringProperty(libelle);
   this.but = new SimpleStringProperty(but);
    }

    public Categorie_Evts(int id, String libelle) {
        this.id = id;
        this.libelle = new SimpleStringProperty(libelle);
    }

    public Categorie_Evts(String libelle) {
        this.libelle = new SimpleStringProperty(libelle);
    }
   
   

    public Categorie_Evts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public final String getLibelle() {
        return this.libelle.get();  
    }


    public void setLibelle(StringProperty libelle) {
        this.libelle = libelle;
    }

    public String getBut() {
        return this.but.get();
    }
    
    public final StringProperty libelleProperty() {
        return this.libelle;
    }
    
    public final StringProperty butProperty() {
        return this.but; 
    }

    public void setBut(StringProperty but) {
        this.but = but;
    }

    @Override
    public String toString() {
        return ""+getLibelle()+"";
    }
    
    

    
}
