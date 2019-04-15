/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.Instant;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Hiro
 */
public class Host {
    
    //Variables
    private int ID;
    private String Owner;
    private Date DateStart;
    private Date DateEnd;
    private int TotalPlaces;
    private int AvailablePlaces;
    private String Localisation;
    private String Participants;
    private int OwnerID;


    //Constructors
    public Host() {
        this.ID = 0;
        this.Owner = "";
        this.DateStart = Date.valueOf(LocalDate.now());
        this.DateEnd = Date.valueOf(LocalDate.now());
        this.TotalPlaces = 0;
        this.AvailablePlaces = 0;
        this.Localisation = "";
        this.Participants = "";
    }
    public Host(String Owner, Date DateStart, Date DateEnd, int TotalPlaces, int AvailablePlaces, String Localisation, String People) {

        this.Owner = Owner;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
        this.TotalPlaces = TotalPlaces;
        this.AvailablePlaces = AvailablePlaces;
        this.Localisation = Localisation;
        this.Participants = People;
    }
   

    
    //Setters
    public int getOwnerID() {
        return OwnerID;
    }
    public void setAvailablePlaces(int AvailablePlaces) {
        this.AvailablePlaces = AvailablePlaces;
    }
    public void setDateEnd(Date DateEnd) {
        this.DateEnd = DateEnd;
    }
    public void setDateStart(Date DateStart) {
        this.DateStart = DateStart;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }
    public void setParticipants(String Participants) {
        this.Participants = Participants;
    }
    public void setTotalPlaces(int TotalPlaces) {
        this.TotalPlaces = TotalPlaces;
    }
    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
        
    
    // Getters
    public int getAvailablePlaces() {
        return AvailablePlaces;
    }
    public Date getDateEnd() {
        return DateEnd;
    }
    public Date getDateStart() {
        return DateStart;
    }
    public int getID() {
        return ID;
    }
    public String getLocalisation() {
        return Localisation;
    }
    public String getParticipants() {
        return Participants;
    }
    public int getTotalPlaces() {
        return TotalPlaces;
    }
    public String getOwner() {
        return Owner;
    }
    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }
    
}
