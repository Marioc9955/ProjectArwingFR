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
public class Arco extends Chunk {

    private float size, ang;
    private float trslY, sclY;
    private boolean cerrada;
    private Timer t;

    public Arco(float x, float y, float z, float size, Timer t) {
        super(x, y, z);
        this.size = size;
        ang = 90;
        trslY = 0;
        sclY = 1;
        cerrada = true;
        this.t = t;
    }

    public void Dibujar(GL gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glTranslatef(getX(), getY() + size, getZ());
        gl.glRotatef(ang, 0, 1, 0);
        gl.glRotatef(90, 1, 0, 0);
        gl.glTranslatef(-size * 0.7f, 0, 0);
        gl.glColor3f(0.6f, 0.7f, 0.8f);
        glut.glutSolidCylinder(0.3 * size, size, 5, 5);
        gl.glTranslatef(size * 1.4f, 0, 0);
        glut.glutSolidCylinder(0.3 * size, size, 5, 5);
        gl.glRotatef(-90, 0, 1, 0);
        gl.glColor3f(0.6f, 0.7f, 0.4f);
        glut.glutSolidCylinder(0.3 * size, 1.4f * size, 5, 5);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        
        gl.glTranslatef(getX(), getY() + size / 2 + trslY, getZ());
        gl.glScalef(1, 1 / 0.2f, 1.7f / 0.2f);
        gl.glScalef(1, sclY, 1);
        
        glut.glutSolidCube(size * 0.2f);
        
        gl.glPopMatrix();
        if (cerrada) {
            sclY -= 0.4f * t.deltaTimeSeg();
            trslY += 5f * t.deltaTimeSeg();
            if (sclY <= 0.1f) {
                cerrada = false;
            }
        } else {
            sclY += 0.4f * t.deltaTimeSeg();
            trslY -= 5f * t.deltaTimeSeg();
            if (sclY >= 1f) {
                cerrada = true;
            }
        }
    }

}
