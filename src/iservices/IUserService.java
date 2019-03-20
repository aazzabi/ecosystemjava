/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import entities.Utilisateur;
import java.util.List;

/**
 *
 * @author anasc
 */
public interface IUserService {

    public int Inscription(Utilisateur u);

    public boolean testMotDePasse(String motDePasseGUI, String motDePasseBD);

    public List<Utilisateur> getTtUtilisateur();

}
