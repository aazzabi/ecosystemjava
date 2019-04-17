/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author arafe
 */
public class SignalisationCommentaire {
    private int id;
    private String publicationLibelle;
    private String commentaireLibelle;
    private String commEcritPar;
    private String commSignaleePar;
    private String cause;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicationLibelle() {
        return publicationLibelle;
    }

    public void setPublicationLibelle(String publicationLibelle) {
        this.publicationLibelle = publicationLibelle;
    }

    public String getCommentaireLibelle() {
        return commentaireLibelle;
    }

    public void setCommentaireLibelle(String commentaireLibelle) {
        this.commentaireLibelle = commentaireLibelle;
    }

    public String getCommEcritPar() {
        return commEcritPar;
    }

    public void setCommEcritPar(String commEcritPar) {
        this.commEcritPar = commEcritPar;
    }

    public String getCommSignaleePar() {
        return commSignaleePar;
    }

    public void setCommSignaleePar(String commSignaleePar) {
        this.commSignaleePar = commSignaleePar;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "SignalisationCommentaire{" + "id=" + id + ", publicationLibelle=" + publicationLibelle + ", commentaireLibelle=" + commentaireLibelle + ", commEcritPar=" + commEcritPar + ", commSignaleePar=" + commSignaleePar + ", cause=" + cause + '}';
    }
    
    
    
    
}
