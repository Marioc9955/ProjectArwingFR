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
public class Chunk {

    private float x, y, z;
    private static float size = 420;
    Rock[] r;
    Arco a;
    float[] xr, zr;
    boolean existeArco;
    private Timer t;
    Nube[] nub;
    Estructura e,s,tr; 

    public Chunk(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        r = new Rock[(int) size / 3];
        xr = new float[(int) size / 3];
        zr = new float[(int) size / 3];
        nub = new Nube[(int) size / 40];
        existeArco = Math.random() > 0.1f;
        //existeArco = false;

        for (int i = 0; i < xr.length; i++) {
            xr[i] = (float) Math.random() * (size) - size / 2;
            zr[i] = (float) Math.random() * (size) - size / 2;
        }
        
    }

    public void InicializarElementos(Timer t) {
        this.t = t;
        for (int i = 0; i < r.length; i++) {
            r[i] = new Rock(xr[i] + this.x, 0.1f + this.y, zr[i] + this.z, size / 25);
        }
        if (existeArco) {
            a = new Arco(this.x, this.y, this.z, (float) (Math.random() * 10 + 50), t);
        }
        for (int i = 0; i < nub.length; i++) {
            nub[i] = new Nube(xr[i] + this.x, 150 + this.y, zr[i] + this.z, 20);
        }
        
        e = new Estructura(x + signoRandom()*70, y + 30, z + signoRandom()*70, 60);
        s = new Estructura(x + signoRandom()*140, y + 30, z + signoRandom()*140, 60);
        tr = new Estructura(x + signoRandom()*200, y + 30, z + signoRandom()*100, 60);
    }

    public void Dibujar(GL gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex3f(-size / 2f, 0, size / 2f);
        gl.glVertex3f(size / 2f, 0, size / 2);
        gl.glVertex3f(size / 2f, 0, -size / 2);
        gl.glVertex3f(-size / 2f, 0, -size / 2);
        gl.glEnd();
        gl.glPopMatrix();

        for (int i = 0; i < r.length; i++) {
            r[i].Dibujar(gl);
        }
        if (existeArco) {
            a.Dibujar(gl, glut);
        }
        for (int i = 0; i < nub.length; i++) {
            nub[i].Dibujar(gl, glut);
        }
        e.Dibujar(gl, glut, 1);
        s.Dibujar(gl, glut, 2);
        tr.Dibujar(gl, glut, 3);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public static float getSize() {
        return size;
    }
    
    public int signoRandom(){
        int s;
        boolean es = Math.random()>0.5f;
        if (es) {
            s = 1;
        }else s = -1;
        return s;
    }

}
