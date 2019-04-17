package utils;


import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hiro
 */
public class MapClass  extends MapView{

    public Map getCurrentMap() {
        return CurrentMap;
    }
    public void setCurrentMap(Map CurrentMap) {
        this.CurrentMap = CurrentMap;
    }
    public JFrame getFrame() {
        return Frame;
    }
    public void setFrame(JFrame Frame) {
        this.Frame = Frame;
    }
    
    
    private Map CurrentMap;
    private JFrame Frame;
    
    public MapClass(String Name){

        Frame = new JFrame();
        Frame.setName(Name);
        
        setOnMapReadyHandler((MapStatus ms) -> {
            
            if (ms == MapStatus.MAP_STATUS_OK){
                
                CurrentMap = getMap();
                
                //Set UP options 
                MapOptions Options = new MapOptions();
                MapTypeControlOptions ControlOptions = new MapTypeControlOptions();   
                Options.setMapTypeControlOptions(ControlOptions);
                
                CurrentMap.setOptions(Options);
                CurrentMap.setCenter(new LatLng(36.898821, 10.190233));
                CurrentMap.setZoom(10);


            }
        });
        
        
        Frame.add(this, BorderLayout.CENTER);
        Frame.setSize(700, 500);
        Frame.setVisible(true);
                
                



//                SetMarkerAtMouseLocation();        
//        Frame.addMouseListener(new MouseInputListener() {
//
//            @Override
//            public void mousePressed(MouseEvent me) {
//                SetMarkerAtMouseLocation();
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseExited(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseDragged(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseClicked(MouseEvent me) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
//            
//        });
        
    }
    
    
    public Marker SetMarkerAtScreenCenter(){
        Marker Mark = new Marker(CurrentMap);
        Mark.setPosition(CurrentMap.getCenter());
        return Mark;
    }
    public double getMouseX(){
        return Frame.getMousePosition().getX();
    }
    public double getMouseY(){
        return Frame.getMousePosition().getY();
    } 
    
    
    /**
     * THIS WILL TAKE MORE THAN 2 SECONDS
     * @param Name
     * @return 
     */
    public static MapClass LaunchMap(String Name){
        MapClass TempMap0 = new MapClass(Name);
        
        try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        MapClass TempMap1 = new MapClass(Name);
        
        try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapClass.class.getName()).log(Level.SEVERE, null, ex);
            }        
        
        TempMap0.getFrame().dispose();
        TempMap0.dispose();
        
        
        
        
        return TempMap1;
    }
    public void ShutDownMap(){
        getFrame().dispose();
        dispose();
    }
    
    
    
    public LatLng GetLocationFromMap(){
        Marker Mark;
        Mark = new Marker(getCurrentMap());
        Mark.setPosition(getCurrentMap().getCenter());
        return getCurrentMap().getCenter();
    }
    public void SetLocationInMap(LatLng CurrLatLng){
        SetLocationInMap(CurrLatLng, 17, true);
    }
    public void SetLocationInMap(LatLng CurrentLatLng, double Zoom, boolean MarkLocation){
            getCurrentMap().setCenter(CurrentLatLng);
            getCurrentMap().setZoom(Zoom);
            if (MarkLocation){
                Marker Mark = new Marker(getCurrentMap());
                Mark.setPosition(CurrentLatLng); 
            }
    }
    
    
    
    public static String LatLngToString(LatLng Value){
        System.out.println("Converting LATLNG to String : " + Value.getLat()+ "," + Value.getLng());
        return Value.getLat()+ "," + Value.getLng();
    }
    public static LatLng StringToLatLng(String Value){
        Double Lat;
        Double Lng;
        String [] LatLngStrings;
        LatLngStrings = Value.split(",");
        Lat = Double.valueOf(LatLngStrings[0]); 
        Lng = Double.valueOf(LatLngStrings[1]);
        System.out.println("Converting String to :" +Lat + "  " + Lng);
        return new LatLng(Lat, Lng);
    }
    
    
}
