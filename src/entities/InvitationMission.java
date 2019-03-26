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
public class InvitationMission {
    private int idIviteur ; 
    private int idEvent ;
    private int idIvite ; 
    private int etat ;

    public InvitationMission() {
    }

    public InvitationMission(int idIviteur, int idEvent, int idIvite, int etat) {
        this.idIviteur = idIviteur;
        this.idEvent = idEvent;
        this.idIvite = idIvite;
        this.etat = etat;
    }

    public void setIdIviteur(int idIviteur) {
        this.idIviteur = idIviteur;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdIvite(int idIvite) {
        this.idIvite = idIvite;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdIviteur() {
        return idIviteur;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdIvite() {
        return idIvite;
    }

    public int getEtat() {
        return etat;
    }
    
}
