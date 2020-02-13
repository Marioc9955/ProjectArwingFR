/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import javax.media.opengl.GL;

/**
 *
 * @author Mario
 */
public class Rock extends Chunk{

    private float size,ang;
    
    public Rock(float x, float y, float z, float size) {
        super(x, y, z);
        this.size = size;
        ang = (float) Math.random() * (360);
    }
    
    public void Dibujar(GL gl){
        gl.glPushMatrix();
            gl.glTranslatef(getX(), getY(), getZ());
            gl.glRotatef(ang, 0, 1, 0);
            gl.glColor3f(0.4f, 0.3f, 0.2f);
            gl.glBegin(GL.GL_POLYGON);
            gl.glVertex3f(0, 0, size/4);
            gl.glVertex3f(size/4, 0,0);
            gl.glVertex3f(3*size/4, 0,0);
            gl.glVertex3f(size,0, size/4);
            gl.glVertex3f(size/2 ,0, 3*size/4);
            gl.glVertex3f(0, 0,size/2);
            gl.glEnd();            
        gl.glPopMatrix();
    }
}
