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

public class Elipsoide extends Forma {
    private Ponto centro;
    private Double largura, altura, profundidade;
    
    public Elipsoide(Ponto centro, Double largura, Double altura, Double profundidade){
        this.centro = centro;
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
    }

    public void desenha(OGL ogl) {
        // desenhar uma esfera comum e aplicar escala
        
        ogl.gl.glColor3f(0, 1, 0);
        ogl.gl.glScaled(this.largura, this.altura, this.profundidade);
        ogl.gl.glPushMatrix();
            ogl.glut.glutSolidSphere(0.1f, 30, 30);
            ogl.gl.glEnd();
        ogl.gl.glPopMatrix();
    }

    public void escala(Ponto p) {
        this.largura *= p.x;
        this.altura *= p.y;
        this.profundidade *= p.z;
    }
}
