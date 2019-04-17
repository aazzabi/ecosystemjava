/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author imene
 */
public class TypeCategorie {
    int id ; 
    String nom ; 
    int type ; 
  public TypeCategorie() {
        
    }
    public TypeCategorie(int id, String nom, int type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }
    public TypeCategorie(String nom, int type) {
        
        this.nom = nom;
        this.type = type;
    }
    public TypeCategorie(String nome) {
        
        this.nom = nom;
       
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
