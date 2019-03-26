/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Hiro
 */
public class HostVariableManager {
    
    public static int WINDOW_HEIGHT = 619;
    public static int WINDOW_WIDTH = 800;

    
    enum UserRole{
    Admin,
    Owner,
    Other
    }
    
    private static int CurrentHost = 0;
    private static int CurrentUserID = 6;
    private static UserRole CurrentUserRole = UserRole.Other;

    
        
    public static int getCurrentUserID() {
        return CurrentUserID;
    }
    public static void setCurrentUserID(int CurrentUserID) {
        HostVariableManager.CurrentUserID = CurrentUserID;
    }
    
    public static UserRole getCurrentRole() {
        return CurrentUserRole;
    }
    public static void setCurrentRole(UserRole CurrentRole) {
        HostVariableManager.CurrentUserRole = CurrentRole;
    }
    
    public static int getCurrentHost() {
        return CurrentHost;
    }
    public static void setCurrentHost(int CurrentHost) {
        HostVariableManager.CurrentHost = CurrentHost;
    }
    
}
