/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Mario
 */
public class Camara {
    
    private float distancFromPS = 50;
    private float anglAroundPS;
    
    private Vector3f posicion = new Vector3f(0, 0, 0),pntS;
    private float pitch = 20;
    private float yaw = 0;
    private float roll;

    public Camara(Vector3f pntS) {
        this.pntS = pntS;
    }
    
    private void calcZoom(){
       // float zoomLevel = Mouse.getDWheel();
    }
    
    
}
