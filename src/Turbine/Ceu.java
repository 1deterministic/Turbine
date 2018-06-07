package Turbine;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import java.awt.Color;

// subforma que desenha a um skybox
public class Ceu extends Forma {
    private Texture textura; // textura do céu
    private Color cor; // cor base do skybox
    
    // construtor padrão
    public Ceu(){}
    
    // construtor completo
    public Ceu(Texture textura, Color cor) {
        this.textura = textura;
        this.cor = cor;
    }
    
    // desenha o skybox na tela
    public void desenhar(OGL ogl) {
        this.textura.enable(ogl.gl); // habilita a textura
        this.textura.bind(ogl.gl);
        
        ogl.gl.glPushMatrix();
            ogl.gl.glDisable(GL.GL_DEPTH_TEST);
            ogl.gl.glDisable(GL2.GL_LIGHTING);
            ogl.gl.glDisable(GL.GL_BLEND);

            ogl.gl.glEnable(ogl.gl.GL_TEXTURE_2D);// configura a textura 2d
            ogl.gl.glColor3d(this.cor.getRed() / 255d, this.cor.getGreen() / 255d, this.cor.getBlue() / 255d); // define a cor básica do objeto
            
            ogl.gl.glBegin(ogl.gl.GL_QUADS); // inicia o desenho do skybox
                // seta as coordenadas para a textura e desenha a face frontal
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(1.0d, -1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(1.0d, 1.0d, 1.0d);
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);

                // seta as coordenadas para a textura e desenha a face do fundo
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(1.0d, 1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(1.0d, -1.0d, -1.0d);

                // seta as coordenadas para a textura e desenha a face de cima
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(1.0d, 1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(1.0d, 1.0d, -1.0d);

                // seta as coordenadas para a textura e desenha a face de baixo
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(1.0d, -1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(1.0d, -1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);

                // seta as coordenadas para a textura e desenha a face da direita
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(1.0d, -1.0d, -1.0d);
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(1.0d, 1.0d, -1.0d);
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(1.0d, 1.0d, 1.0d);
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(1.0d, -1.0d, 1.0d);

                // seta as coordenadas para a textura e desenha a face da esquerda
                ogl.gl.glTexCoord2d(0d, 0d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                ogl.gl.glTexCoord2d(1d, 0d);
                ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                ogl.gl.glTexCoord2d(1d, 1d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                ogl.gl.glTexCoord2d(0d, 1d);
                ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                    
            ogl.gl.glEnd(); // termina o skybox
            ogl.gl.glEnable(GL.GL_DEPTH_TEST);
            ogl.gl.glFlush();
        ogl.gl.glPopMatrix();
        
        this.textura.disable(ogl.gl); // desabilita a textura
    }

    // desabilitado
    public void escalar(Double v) {}
    
    // desabilitado
    public void rotacionar(Double angulo, Ponto eixo) {}
    
    // desabilitado
    public void transladar(Ponto delta) {}
    
    // define a textura do skybox
    public void setTextura(Texture textura) {
        this.textura = textura;
    }
    
    // define a cor base do skybox
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    // desabilitado
    public void setLocal(Ponto p) {}
    
    // desabilitado
    public Ponto getLocal() {
        return new Ponto();
    }
    
    // desabilitado
    public void setDimensoes(Ponto p) {}
    
    // desabilitado
    public Ponto getDimensoes() {
        return new Ponto(1d, 1d, 1d);
    }
}
