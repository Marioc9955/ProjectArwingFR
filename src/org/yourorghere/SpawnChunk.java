/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.GLUT;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.media.opengl.GL;

/**
 *
 * @author Mario
 */
public class SpawnChunk {

    private Nave n;
    private Timer t;

    ArrayList<Chunk> chunkMap = new ArrayList<Chunk>();

    public SpawnChunk(Nave n,Timer t) {
        this.n = n;
        this.t = t;
    }

    void MakeChunkAt(int x, int z) {
        x = Math.round(x / Chunk.getSize()) * (int) Chunk.getSize();
        z = Math.round(z / Chunk.getSize()) * (int) Chunk.getSize();
        if (!chunkExiste(chunkMap, x, z)) {
            Chunk chk = new Chunk(x, 0, z);
            chunkMap.add(chk);
            chk.InicializarElementos(t);
        }
    }

    void CargarChunks() {
        int xPos = (int) n.getX();
        int zPos = (int) n.getZ();
        for (int i = xPos - (int) Chunk.getSize(); i < xPos + (int) Chunk.getSize(); i++) {
            for (int j = zPos - (int) Chunk.getSize(); j < zPos + (int) Chunk.getSize(); j++) {
                MakeChunkAt(i, j);
            }
        }
    }

    void Dibujar(GL gl,GLUT glut) {
        for (int i = 0; i < chunkMap.size(); i++) {
            chunkMap.get(i).Dibujar(gl,glut);
        }
    }

    void Update(GL gl,GLUT glut) {
        CargarChunks();
        Dibujar(gl,glut);
        BorrarChunks();
        //System.out.println(chunkMap.size());
    }

    void BorrarChunks() {
        for (int i = 0; i < chunkMap.size(); i++) {
            float dstnc = (float) Math.sqrt(Math.pow(n.getX() - chunkMap.get(i).getX(), 2) + Math.pow(n.getZ() - chunkMap.get(i).getZ(), 2));
            if (dstnc > Chunk.getSize() * 3) {
                chunkMap.remove(i);
            }
        }
    }
    
    boolean chunkExiste(ArrayList<Chunk> chunkMap,float x,float z){
        boolean b = false;
        for (int i = 0; i < chunkMap.size(); i++) {
            if (chunkMap.get(i).getX() == x && chunkMap.get(i).getZ() == z) {
                b = true;
            }
        }
        return b;
    }
}
