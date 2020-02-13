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
public class Estructura extends Chunk{
    
    private float size;
    private int rotr;
    
    public Estructura(float x, float y, float z, float size) {
        super(x, y, z);
        this.size = size;
    }
    
    public void Dibujar(GL gl, GLUT glut, int t){
        gl.glPushMatrix();
        gl.glTranslatef(getX(), getY(), getZ());
        switch (t) {
            case 1:             
                gl.glScalef(size, size, size);
                gl.glRotatef(45, 0, 1, 0);
                gl.glRotatef(45, 1, 0, 0);
                gl.glColor3f(0.5f, 0.6f, 1);
                glut.glutSolidTetrahedron();
                gl.glColor3f(0.1f, 0.2f, 0.1f);
                gl.glLineWidth(size/7);
                glut.glutWireTetrahedron();
                break;
            case 2:
                gl.glRotatef(rotr, 0, 1, 0);
                gl.glScalef(size/10, size, size/10);
                gl.glRotatef(45, 0, 1, 0);
                gl.glRotatef(45, 1, 0, 0);
                gl.glColor3f(0.8f, 0.3f, 1);
                glut.glutSolidOctahedron();
                gl.glColor3f(0.1f, 0.2f, 0.1f);
                gl.glLineWidth(size/14);
                glut.glutWireOctahedron();                
                break;
             case 3:
                gl.glRotatef(rotr, 0, 0, 1);
                gl.glScalef(size/2, size/2, size/2);
                gl.glRotatef(45, 0, 1, 0);
                gl.glRotatef(45, 1, 0, 0);
                gl.glColor3f(0.7f, 1, 0.5f);
                glut.glutSolidRhombicDodecahedron();                
                gl.glColor3f(0.1f, 0.2f, 0.1f);
                gl.glLineWidth(size/14);
                glut.glutWireRhombicDodecahedron();
                break;
        }
        rotr++;
        if (rotr>=360) {
            rotr = 0;
        }
        gl.glPopMatrix();
    }
    
}
