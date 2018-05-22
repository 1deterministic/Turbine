package turbine;


import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL2.GL_S;
import static com.jogamp.opengl.GL2.GL_SPHERE_MAP;
import static com.jogamp.opengl.GL2.GL_T;
import static com.jogamp.opengl.GL2.GL_TEXTURE_GEN_MODE;
import static com.jogamp.opengl.GL2.GL_TEXTURE_GEN_S;
import static com.jogamp.opengl.GL2.GL_TEXTURE_GEN_T;
import com.jogamp.opengl.GLProfile;
import static com.jogamp.opengl.GLProfile.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Cubo extends Forma {
    private Ponto dimensoes; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    private Texture textura;
    
    public Cubo(){
        this.dimensoes = new Ponto(1d, 1d, 1d);
        this.local = new Ponto(0.0d, 0.0d, 0.0d);
        this.rotacao = new Ponto(0.0d, 0.0d, 0.0d);
        this.angulo = 0.0d;
    }
    
    public Cubo(Ponto dimensoes){
        this.dimensoes = dimensoes;
        this.local = new Ponto(0.0d, 0.0d, 0.0d);
        this.rotacao = new Ponto(0.0d, 0.0d, 0.0d);
        this.angulo = 0.0d;
    }

    public void desenhar(OGL ogl) {
        
        this.textura.enable(ogl.gl);
        this.textura.bind(ogl.gl);
        
        ogl.gl.glPushMatrix();
            ogl.gl.glTranslated(this.local.x, this.local.y, this.local.z);
            ogl.gl.glRotated(this.angulo, this.rotacao.x, this.rotacao.y, this.rotacao.z);
            ogl.gl.glScaled(this.dimensoes.x, this.dimensoes.y, this.dimensoes.z);            

            ogl.gl.glEnable(ogl.gl.GL_TEXTURE_2D);

            ogl.gl.glColor3f(1, 1, 1);
            
            //ogl.gl.glBindTexture(ogl.gl.GL_TEXTURE_2D, textura.getTextureObject(ogl.gl));
            ogl.gl.glBegin(ogl.gl.GL_QUADS);
                    // Front Face
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);

                    // Back Face
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, -1.0d);

                    // Top Face
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, -1.0d);

                    // Bottom Face
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);

                    // Right face
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);

                    // Left Face
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                ogl.gl.glEnd();
            ogl.gl.glFlush();
        ogl.gl.glPopMatrix();
        
        this.textura.disable(ogl.gl);
    }

    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }
    
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
        this.angulo = angulo;
        this.rotacao = eixo;
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    public void setTextura(Texture textura) {
        this.textura = textura;
    }
    
    public void setLocal(Ponto p) {
        this.local = p;
    }
    
    public Ponto getLocal() {
        return this.local;
    }
    
    public void setDimensoes(Ponto p) {
        this.dimensoes = p;
    }
    
    public Ponto getDimensoes() {
        return this.dimensoes;
    }
}
