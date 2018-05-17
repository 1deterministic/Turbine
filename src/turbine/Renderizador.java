package turbine;

import java.util.Date;
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
    private Esfera e ;
    private Cubo terreno;
    private Cubo c;
    long tempo;

    public Renderizador() {
        ogl = new OGL();
        System.out.println(System.getProperty("user.dir"));
    }

    public void init(GLAutoDrawable drawable) {
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        this.ogl.gl.glEnable(GL.GL_TEXTURE_2D);
        
        cam = new Camera();
        
        obj = new Nave();
        obj.setLocal(new Ponto(0d, 0d, 10d));
        obj.setForma(new Cubo(new Ponto(1d, 0.1d, 3d)));
        obj.atualizarLocalForma();
        obj.getForma().carregarTextura("src/turbine/Arquivos/textura.jpg");
        cam.anexarObjeto(obj);
        
        c = new Cubo(new Ponto(10.0d, 10.0d, 1d));
        c.transladar(new Ponto(-1d, 0.5d, 0d));
        c.carregarTextura("src/turbine/Arquivos/textura.jpg");
        
        
        e = new Esfera(new Ponto(1.0d, 2.0d, 1.0d));
        e.escalar(2.0d);
        e.transladar(new Ponto(0.5d, 0d, -5d));
        e.carregarTextura("Arquivos/textura.jpg");
        
        terreno = new Cubo(new Ponto(1000d, 1000d, 0.01d));
        terreno.rotacionar(90d, new Ponto(1d, 0d, 0d));
        //terreno.carregarTextura("Arquivos/textura.jpg");
        terreno.transladar(new Ponto(0d, -1d, 0d));
        terreno.carregarTextura("src/turbine/Arquivos/textura.jpg");
        
        tempo = System.currentTimeMillis();
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();
        
        // teste de troca de câmera
        if (System.currentTimeMillis() > tempo + 5 * 1000) {
            Nave outro = new Nave();
            outro.setLocal(new Ponto(0d, 0d, -1d));
            cam.anexarObjeto(outro);
        }
        
        if (System.currentTimeMillis() > tempo + 10 * 1000) {
            Nave outro = new Nave();
            outro.setLocal(new Ponto(0d, 0d, -4d));
            cam.anexarObjeto(outro);
        }
        
        if (System.currentTimeMillis() > tempo + 13 * 1000) {
            cam.anexarObjeto(obj);
        }
        
        //cam.local.z -= 0.01d;
        obj.transladar(new Ponto(0d, 0d, -0.02d));
        obj.getForma().rotacionar(Math.toDegrees(Math.sin(rot / 100)), new Ponto(0d, 0d, 1d));
        cam.ajustaObservacao(ogl);
        
        
       
        e.desenhar(this.ogl);
        
        c.rotacionar(rot, new Ponto(1d, 1d, 0d));
        rot++;
        c.desenhar(this.ogl);
        
        
        terreno.desenhar(ogl);
        
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
