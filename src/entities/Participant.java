 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author weepey
 */
public class Participant {
    public enum StatutP{organisateur,invité,participant};
    public enum Avis{commentaire,signal};
    private int id_event;
    private int id_user ;
    private String statut_p;
    private String avis ; 
    private String commentaire ;
    private int id_ticket ;

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }



    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_event() {
        return id_event;
    }

    public int getId_user() {
        return id_user;
    }


    public String getCommentaire() {
        return commentaire;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public Participant() {
    }

    public Participant(int id_event, int id_user, String statut_p, String avis, String commentaire, int id_ticket) {
        this.id_event = id_event;
        this.id_user = id_user;
        this.statut_p = statut_p;
        this.avis = avis;
        this.commentaire = commentaire;
        this.id_ticket = id_ticket;
    }

    public void setStatut_p(String statut_p) {
        this.statut_p = statut_p;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getStatut_p() {
        return statut_p;
    }

    public String getAvis() {
        return avis;
    }

 
    
    
}
