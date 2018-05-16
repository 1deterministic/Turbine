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
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();
        
        Elipsoide e = new Elipsoide(new Ponto(0.0d, 0.0d, 0.0d), 4.0d, 3.0d, 1.0d);
        e.escala(new Ponto(2.0d, 1.0d, 1.0d));
        e.desenha(this.ogl);
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
