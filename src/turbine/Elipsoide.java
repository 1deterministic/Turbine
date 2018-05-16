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
    private Ponto excentricidade; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    
    public Elipsoide(Ponto excentricidade){
        this.excentricidade = excentricidade;
        this.local = new Ponto(0.0d, 0.0d, 0.0d);
        this.rotacao = new Ponto(0.0d, 0.0d, 0.0d);
        this.angulo = 0.0d;
    }

    public void desenhar(OGL ogl) {
        // desenha uma esfera comum e apica as operações necessárias
        ogl.gl.glPushMatrix();
            ogl.gl.glColor3f(0, 1, 0);
            ogl.gl.glTranslated(this.local.x, this.local.y, this.local.z);
            ogl.gl.glRotated(this.angulo, this.rotacao.x, this.rotacao.y, this.rotacao.z);
            ogl.gl.glScaled(this.excentricidade.x, this.excentricidade.y, this.excentricidade.z);
            ogl.glut.glutSolidSphere(0.1f, 30, 30);
            ogl.gl.glEnd();
        ogl.gl.glPopMatrix();
    }

    public void escalar(Double v) {
        this.excentricidade.multiplicar(v);
    }
    
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
}
