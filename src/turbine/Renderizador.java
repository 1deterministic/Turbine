package turbine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class Renderizador extends MouseAdapter implements GLEventListener, KeyListener {
    private OGL ogl;
    private Double rot = 0d;
    private Camera cam;
    private Objeto obj;

    public Renderizador() {
        ogl = new OGL();      
    }

    public void init(GLAutoDrawable drawable) {
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        
        
//        // Especifica sistema de coordenadas de projeção
//        this.ogl.gl.glMatrixMode(GL2.GL_PROJECTION);
//        // Inicializa sistema de coordenadas de projeção
//        this.ogl.gl.glLoadIdentity();
//
//        // Especifica a projeção perspectiva(angulo,aspecto,zMin,zMax)
//        this.ogl.glu.gluPerspective(30d, 1d, 0.2, 500);
        
        cam = new Camera();
        
        obj = new Nave();
        obj.setLocal(new Ponto(0d, 0d, 10d));
        obj.setForma(new Cubo(new Ponto(1d, 0.1d, 3d)));
        obj.atualizarLocalForma();
        cam.anexarObjeto(obj);
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();
        
        //cam.local.z -= 0.01d;
        obj.transladar(new Ponto(0d, 0d, -0.01d));
        obj.getForma().rotacionar(Math.toDegrees(Math.sin(rot / 100)), new Ponto(0d, 0d, 1d));
        cam.ajustaObservacao(ogl);
        
        Esfera e = new Esfera(new Ponto(1.0d, 2.0d, 1.0d));
        e.escalar(2.0d);
        e.transladar(new Ponto(0.5d, 0d, -5d));
        e.desenhar(this.ogl);
        
        //Cubo c = new Cubo(new Ponto(1.0d, 2.0d, 1.0d));
        //c.escalar(2.0d);
        //c.desenhar(this.ogl);
        
        Cubo terreno = new Cubo(new Ponto(10.0d, 10.0d, 1d));
        terreno.rotacionar(rot, new Ponto(1d, 1d, 0d));
        rot++;
        terreno.desenhar(this.ogl);
        
        obj.getForma().desenhar(ogl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void dispose(GLAutoDrawable arg0) {
    }
}
