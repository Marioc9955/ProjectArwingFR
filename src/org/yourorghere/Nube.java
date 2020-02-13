/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;

/**
 *
 * @author Mario
 */
public class Nube extends Chunk{
    
    private float size;
    private float ang;
    
    public Nube(float x, float y, float z,float size) {
        super(x, y, z);
        this.size = size;
        ang = (float) Math.random() * (360);
    }
    
    public void Dibujar(GL gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(1, 1, 1);
        gl.glTranslatef(getX(), getY(), getZ());
        gl.glRotatef(ang, 0, 1, 0);
        gl.glScalef(size, size/2, size/3);
        glut.glutSolidSphere(1, 10, 10);        
        gl.glPopMatrix();            
    }
    
}
