/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GL;

/**
 *
 * @author Mario
 */
public class Nave implements KeyListener {

    private float x, y, z, size, ang = 0, angZ = 0, angY = 0;
    private Vector3f puntoX, dir;
    private Timer t;
    
    private final float velc,velcRot;

    public Nave(float x, float y, float z, float size, Timer t) {
        this.velc = 150f;
        velcRot = 200;
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;        
        puntoX = new Vector3f(1, 0, 0);
        dir = puntoX;
        this.t = t;
    }

    public void Dibujar(GL gl, GLUT glut) {
        gl.glPushMatrix();
        
        gl.glLineWidth(1);
        
        gl.glTranslatef(x, y, z);

        gl.glRotatef(angY, 0, 1, 0);
        gl.glRotatef(angZ, 0, 0, 1);

        gl.glPushMatrix();

        gl.glRotatef(90, 0, 1, 0);
        gl.glColor3f(0.71f, 0.71f, 0.71f);
        glut.glutSolidCone(2 * size / 6, 7 * size / 10, 3, 3);
        gl.glColor3f(0.5f, 0.5f, 0.5f);

        gl.glRotatef(-30, 0, 0, 1);
        glut.glutWireCone(2 * size / 6, 7 * size / 10, 3, 2);
        gl.glColor3f(0.71f, 0.71f, 0.71f);
        gl.glRotatef(30, 0, 0, 1);
        glut.glutSolidCone(2 * size / 6, -3 * size / 10, 3, 3);
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glRotatef(-30, 0, 0, 1);
        glut.glutWireCone(2 * size / 6, -3 * size / 10, 3, 3);
        gl.glRotatef(30, 0, 0, 1);

        gl.glPushMatrix();
        gl.glColor3f(0.95f, 0.41f, 0.39f);
        gl.glTranslatef(-3 * size / 20, -size / 10, -3 * size / 10);
        glut.glutSolidCylinder(size / 11, size, 6, 6);
        gl.glTranslatef(6 * size / 20, 0, 0);
        glut.glutSolidCylinder(size / 11, size, 6, 6);
        gl.glPopMatrix();

        gl.glRotatef(-20, 1, 0, 0);
        gl.glTranslatef(size / 4, 0, 0);
        gl.glColor3f(0.2f, 0.1f, 0.8f);
        glut.glutSolidCone(size / 5, 8 * size / 20, 3, 3);
        glut.glutSolidCone(size / 5, -5 * size / 20, 3, 3);
        gl.glTranslatef(-size / 2, 0, 0);
        glut.glutSolidCone(size / 5, 8 * size / 20, 3, 3);
        glut.glutSolidCone(size / 5, -5 * size / 20, 3, 3);
        gl.glRotatef(20, 1, 0, 0);

        gl.glTranslatef(size / 4, size / 5, size / 6);
        gl.glScalef(size / 6, size / 11, size / 5);
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        gl.glRotatef(-35, 1, 0, 0);
        gl.glRotatef(230, 0, 0, 1);
        glut.glutSolidTetrahedron();
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(0.71f, 0.71f, 0.71f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(3 * size / 20, 0, 6 * size / 20);
        gl.glVertex3f(-2 * size / 20, 0, 6 * size / 20);
        gl.glVertex3f(-4 * size / 20, -size / 10, 10 * size / 20);
        gl.glVertex3f(0, -size / 10, 10 * size / 20);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(-4 * size / 20, -size / 10, 10 * size / 20);
        gl.glVertex3f(0, -size / 10, 10 * size / 20);
        gl.glVertex3f(-3 * size / 20, -size / 10, 17 * size / 20);
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(3 * size / 20, 0, -6 * size / 20);
        gl.glVertex3f(-2 * size / 20, 0, -6 * size / 20);
        gl.glVertex3f(-4 * size / 20, -size / 10, -10 * size / 20);
        gl.glVertex3f(0, -size / 10, -10 * size / 20);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(-4 * size / 20, -size / 10, -10 * size / 20);
        gl.glVertex3f(0, -size / 10, -10 * size / 20);
        gl.glVertex3f(-3 * size / 20, -size / 10, -17 * size / 20);
        gl.glEnd();

        gl.glColor3f(0.26f, 0.19f, 0.95f);
        gl.glTranslatef(7 * size / 10 - size / 12, -size / 10, 0);
        gl.glRotatef(ang, 1, 1, 1);
        glut.glutSolidSphere(size / 15, 7, 7);
        gl.glColor3f(0.55f, 0.16f, 0.95f);
        glut.glutWireSphere(size / 12, 7, 7);
        ang++;
        if (ang >= 360) {
            ang = 0;
        }

        gl.glPopMatrix();
        gl.glPopMatrix();
        
        puntoX = Vector3f.MultEscl(velc*t.deltaTimeSeg(), dir);
    }

    private void rotarPuntoZ(float angZ) {

        double[][] mRZ = {{Math.cos(Math.toRadians(angZ)), -Math.sin(Math.toRadians(angZ)), 0},
        {Math.sin(Math.toRadians(angZ)), Math.cos(Math.toRadians(angZ)), 0},
        {0, 0, 1},};
        Vector3f px = new Vector3f(0, 0, 0);
        for (int i = 0; i < 3; i++) {
            float s = 0;
            for (int j = 0; j < 3; j++) {
                //s += Az[i][j] * puntoX.get(j);
                s += mRZ[i][j] * puntoX.get(j);
                px.set(i, s);
            }
        }
        dir = px.normalizado();
    }

    private void rotarPuntoY(float angY) {
        double[][] mRY = {{Math.cos(Math.toRadians(angY)), 0, Math.sin(Math.toRadians(angY))},
        {0, 1, 0},
        {-Math.sin(Math.toRadians(angY)), 0, Math.cos(Math.toRadians(angY))}};

        Vector3f px = new Vector3f(0, 0, 0);
        for (int i = 0; i < 3; i++) {
            float s = 0;
            for (int j = 0; j < 3; j++) {
                //s += Az[i][j] * puntoX.get(j);
                s += mRY[i][j] * puntoX.get(j);
                px.set(i, s);
            }
        }
        dir = px.normalizado();

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

    public float getSize() {
        return size;
    }


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            angZ += velcRot*t.deltaTimeSeg();
            rotarPuntoZ(velcRot*t.deltaTimeSeg());
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            angZ -= velcRot*t.deltaTimeSeg();
            rotarPuntoZ(-velcRot*t.deltaTimeSeg());
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            angY -= velcRot*t.deltaTimeSeg();
            rotarPuntoY(-velcRot*t.deltaTimeSeg());
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            angY += velcRot*t.deltaTimeSeg();
            rotarPuntoY(velcRot*t.deltaTimeSeg());
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public Vector3f getPuntoX() {
        return puntoX;
    }

}
