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
public class Sol {
    private Nave n;
    private float size;
    private Vector3f posi;

    public Sol(Nave n, float size, Vector3f posi) {
        this.n = n;
        this.size = size;
        this.posi = posi;
    }

    public void Dibujar(GL gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(posi.getX() + n.getX(), posi.getY() + n.getY(), posi.getZ() + n.getZ());
        gl.glColor3f(1, 0.5f, 0.3f);
        glut.glutSolidSphere(size, 20, 20);
        gl.glPopMatrix();
    }
    
    
}
