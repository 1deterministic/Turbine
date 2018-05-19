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
    
    private String root;
    
    private Texturas texturas;
    
    private Double rot = 0d;
    private Camera cam;
    private Objeto obj;
    private Esfera e ;
    private Cubo terreno;
    private Cubo c;
    private Relogio relogio;

    public Renderizador() {
        this.ogl = new OGL();
        this.root = System.getProperty("user.dir"); // guarda o caminho da raiz do executável
        this.texturas = new Texturas();
    }

    public void init(GLAutoDrawable drawable) {
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        this.ogl.gl.glEnable(GL.GL_TEXTURE_2D);
        
        this.relogio = new Relogio();
        
        cam = new Camera();
        
        this.texturas.carregarTextura("madeira", this.root + "/src/turbine/Arquivos/madeira.jpg");
        this.texturas.carregarTextura("abstrato", this.root + "/src/turbine/Arquivos/abstrato.jpg");
        
        obj = new Nave();
        obj.setLocal(new Ponto(0d, 0d, 10d));
        obj.setForma(new Cubo(new Ponto(1d, 0.1d, 3d)));
        obj.atualizarLocalForma();
        obj.getForma().setTextura(this.texturas.getTextura("madeira"));
        cam.anexarObjeto(obj);
        
        c = new Cubo(new Ponto(10.0d, 10.0d, 1d));
        c.transladar(new Ponto(-1d, 0.5d, 0d));
        c.setTextura(this.texturas.getTextura("madeira"));
        
        
        e = new Esfera(new Ponto(1.0d, 2.0d, 1.0d));
        e.escalar(2.0d);
        e.transladar(new Ponto(0.5d, 0d, -5d));
        e.setTextura(this.texturas.getTextura("madeira"));
        
        terreno = new Cubo(new Ponto(100d, 1000d, 0.01d));
        terreno.rotacionar(90d, new Ponto(1d, 0d, 0d));
        //terreno.carregarTextura("Arquivos/textura.jpg");
        terreno.transladar(new Ponto(0d, -1d, -10d));
        terreno.setTextura(this.texturas.getTextura("abstrato"));
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();
        
        this.relogio.update();
        System.out.println(this.relogio.getDeltaTempo());
        
        this.cam.ajustaObservacao(this.ogl);

        this.obj.transladar(new Ponto(0d, 0d, -0.02d));
        this.obj.getForma().rotacionar(Math.toDegrees(Math.sin(rot / 100)), new Ponto(0d, 0d, 1d));
        
        this.c.rotacionar(this.rot, new Ponto(1d, 1d, 0d));
        this.rot++;
        
        this.e.desenhar(this.ogl);
        this.c.desenhar(this.ogl);
        this.terreno.desenhar(this.ogl);
        
        this.obj.getForma().desenhar(this.ogl);
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
