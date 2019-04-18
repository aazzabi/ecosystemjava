/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.reparateur;

/**
 *
 * @author actar
 */
public class DemandeComptePro {

    int id;
    String statut;
    String urlPhoto;
    int repId;
    String nomRep;
    String dateDemande;

    public DemandeComptePro() {
    }
    
    
    

    public DemandeComptePro(int id, String statut, String urlPhoto, int repId, String nomRep, String dateDemande) {
        this.id = id;
        this.statut = statut;
        this.urlPhoto = urlPhoto;
        this.repId = repId;
        this.nomRep = nomRep;
        this.dateDemande = dateDemande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public String getNomRep() {
        return nomRep;
    }

    public void setNomRep(String nomRep) {
        this.nomRep = nomRep;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

}
