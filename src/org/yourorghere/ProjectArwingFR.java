package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * ProjectArwingFR.java <BR>
 * author: Mario Cabrera
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class ProjectArwingFR implements GLEventListener, KeyListener, MouseMotionListener {

    //Chunk c;
    public static float zfar = 420;
    static float eyeXYaw = 0, eyeZYaw = 0, eyeXPitch = 0, eyeYPitch = 0, cos = 0, sen = 0, ang = 0;
    Nave n;
    SpawnChunk sc;
    static int nCam = 1;
    Arco a;
    Timer t;
    Sol s1, s2;
    static float radioCam = 10;

    public static void main(String[] args) {
        Frame frame = new Frame("ArwingFR");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new ProjectArwingFR());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.8f, 0.0f, 0.4f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

        t = new Timer();
        n = new Nave(0, 30, 0, 16, t);
        sc = new SpawnChunk(n, t);
        s1 = new Sol(n, 30, new Vector3f(0.75f * zfar * (float) Math.cos(Math.toRadians(45)), 0.75f * zfar * (float) Math.sin(Math.toRadians(45)), 0.75f * zfar * (float) Math.sin(Math.toRadians(45))));
        s2 = new Sol(n, 20, new Vector3f(0.75f * zfar * (float) Math.cos(Math.toRadians(45)), 0.75f * zfar * (float) Math.sin(Math.toRadians(45)), 0.75f * zfar * (float) Math.sin(Math.toRadians(30))));
        drawable.addKeyListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(n);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, zfar);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        t.initDt();
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUT glut = new GLUT();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glTranslatef(0, 0, -50);
        t.Update();
        switch (nCam) {
            case 1:
                glu.gluLookAt(n.getX() + eyeXYaw + eyeXPitch, n.getY() + 2 + eyeYPitch, n.getZ() + eyeZYaw, n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                break;
            case 2:
                glu.gluLookAt(n.getX() + cos, n.getY() + 2, n.getZ() + sen, n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                cos = (float) Math.cos(ang) * 3f;
                sen = (float) Math.sin(ang) * 9f;
                ang += 2.5f * t.deltaTimeSeg();
                if (ang >= Math.PI * 2) {
                    ang = 0;
                }
                break;
            case 3:
                glu.gluLookAt(n.getX() + cos, n.getY() + 2, n.getZ() + sen, n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                cos = (float) Math.cos(ang) * 3f;
                sen = (float) Math.sin(ang) * 3f;
                ang += 2.5f * t.deltaTimeSeg();
                if (ang >= Math.PI * 2) {
                    ang = 0;
                }
                break;
            case 4:
                glu.gluLookAt(n.getX() + cos, n.getY() + 2, n.getZ() + sen, n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                cos = (float) Math.cos(ang) * radioCam;
                sen = (float) Math.sin(ang) * radioCam;
                ang += 1.5f * t.deltaTimeSeg();
                if (ang >= Math.PI * 2) {
                    ang = 0;
                }
                break;
            case 5:
                glu.gluLookAt(n.getX() + 20, n.getY() + 10, n.getZ(), n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                break;
            default:
                glu.gluLookAt(n.getX() + eyeXYaw + eyeXPitch, n.getY() + 2 + eyeYPitch, n.getZ() + eyeZYaw, n.getX(), n.getY(), n.getZ(), 0, 1, 0);
                break;
        }

        n.Dibujar(gl, glut);
        n.setX(n.getPuntoX().getX() + n.getX());
        n.setY(n.getPuntoX().getY() + n.getY());
        n.setZ(n.getPuntoX().getZ() + n.getZ());

        sc.Update(gl, glut);
        s1.Dibujar(gl, glut);
        s2.Dibujar(gl, glut);

        // Flush all drawing operations to the graphics card
        gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_W) {
//            n.setX(n.getX() + 1f);
//        }
//        if (e.getKeyCode() == KeyEvent.VK_S) {
//            n.setX(n.getX() - 1f);
//        }
//        if (e.getKeyCode() == KeyEvent.VK_D) {
//            n.setZ(n.getZ() + 1f);
//        }
//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            n.setZ(n.getZ() - 1f);
//        }
//        if (e.getKeyCode() == KeyEvent.VK_E) {
//            n.setY(n.getY() + 1f);
//        }
//        if (e.getKeyCode() == KeyEvent.VK_Q) {
//            n.setY(n.getY() - 1f);
//        }

        if (e.getKeyCode() == KeyEvent.VK_1) {
            nCam = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            nCam = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            nCam = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            nCam = 4;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            nCam = 5;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            radioCam++;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            radioCam--;
        }
        
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        float angYaw = e.getX() * 0.75f;
        float angPitch = e.getY() * 0.75f;
//        if (angPitch < 30) {
//            angPitch = 30;
//        } else if (angPitch > 270) {
//            angPitch = 270;
//        }
//        if (angYaw < 100) {
//            angYaw = 100;
//        } else if (angYaw > 420) {
//            angYaw = 420;
//        }
        eyeXYaw = (float) Math.sin(Math.toRadians(angYaw)) * 5;
        eyeZYaw = (float) Math.cos(Math.toRadians(angYaw)) * 5;

        eyeXPitch = (float) Math.cos(Math.toRadians(angPitch)) * 5;
        eyeYPitch = (float) Math.sin(Math.toRadians(angPitch)) * 5;
        
    }
}
