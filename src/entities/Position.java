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
public class Position {
     private int id;
    private int longitude;
    private int latitude;
    private String adresse ; 
    private String ville ;
    private int codeP  ; 
    private  String pays; 

    public Position(int id, String adresse, String ville, int codeP, String pays) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.codeP = codeP;
        this.pays = pays;
    }

    public Position(String adresse, String ville, String pays) {
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
    }

    public Position(String adresse, String ville, int codeP, String pays) {
        this.adresse = adresse;
        this.ville = ville;
        this.codeP = codeP;
        this.pays = pays;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodeP(int codeP) {
        this.codeP = codeP;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public int getCodeP() {
        return codeP;
    }

    public String getPays() {
        return pays;
    }

    public Position() {
    }
    
    public Position(int longitude,int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position(int id, int longitude, int latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int logitude) {
        this.longitude = logitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
    
    
    
    
}
