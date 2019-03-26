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
public class FeedBack {
     private String type_avis; 
     private  String commentaire ; 

    public FeedBack(String type_avis, String commentaire) {
        this.type_avis = type_avis;
        this.commentaire = commentaire;
    }

    public void setType(String Value) {
        this.type_avis = Value;
    }

    public void setCommentaire(String Value) {
        this.commentaire = Value;
    }


    public String getType() {
        return type_avis;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public FeedBack() {
    } 
    
}
