/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

/**
 *
 * @author Mario
 */
public class Timer {
    private long t,ti,tf;
    private float dt;
    
    public Timer(){
        ti = System.currentTimeMillis();
    }
    
    public void initDt(){
        dt = t;
    }
    
    public void Update(){
        tf = System.currentTimeMillis();
        t = tf - ti;
        dt = t - dt;
    }
    
    public float deltaTimeSeg(){
        return dt/1000f;
    }
    
    public float framesPorSeg(){
        return 1000/dt;
    }
}
