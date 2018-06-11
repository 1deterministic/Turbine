package Turbine;

import com.jogamp.opengl.util.texture.Texture;
import java.awt.Color;

// subforma que desenha um cubo
public class NaveModelo extends Forma {
    private Ponto dimensoes; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    private Texture textura; // textura atribuída ao cubo
    private Color cor; // cor base do cubo
    
    // construtor padrão, cria um cubo no local 0, 0, 0, com tamanho 1 em todas as dimensões, sem rotação
    public NaveModelo(){
        this.dimensoes = new Ponto(1d, 1d, 1d);
        this.local = new Ponto(0.0d, 0.0d, 0.0d);
        this.rotacao = new Ponto(0.0d, 0.0d, 0.0d);
        this.angulo = 0.0d;
        this.cor = new Color(255, 255, 255);
    }
    
    // construtor completo
    public NaveModelo(Ponto local, Ponto dimensoes, Ponto rotacao, Double angulo, Texture textura, Color cor) {
        this.local = local;
        this.dimensoes = dimensoes;
        this.rotacao = rotacao;
        this.angulo = angulo;
        this.textura = textura;
        this.cor = cor;
    }
    
    // desenha o cubo na tela
    public void desenhar(OGL ogl) {
        this.textura.enable(ogl.gl); // habilita a textura
        this.textura.bind(ogl.gl);
        
        ogl.gl.glPushMatrix();
            ogl.gl.glTranslated(this.local.x, this.local.y, this.local.z); // aplica a translação definida
            ogl.gl.glRotated(this.angulo, this.rotacao.x, this.rotacao.y, this.rotacao.z); // aplica a rotação definida
            ogl.gl.glScaled(this.dimensoes.x, this.dimensoes.y, this.dimensoes.z); // aplica a escala definida

            ogl.gl.glEnable(ogl.gl.GL_TEXTURE_2D);// configura a textura 2d
            ogl.gl.glColor3d(this.cor.getRed() / 255d, this.cor.getGreen() / 255d, this.cor.getBlue() / 255d); // define a cor básica do objeto
            ogl.gl.glEnable( ogl.gl.GL_BLEND );
            ogl.gl.glBlendFunc( ogl.gl.GL_SRC_ALPHA, ogl.gl.GL_ONE_MINUS_SRC_ALPHA );
            
            ogl.gl.glBegin(ogl.gl.GL_QUADS); // inicia o desenho do cubo
                    // seta as coordenadas para a textura e desenha a face frontal
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);

                    // seta as coordenadas para a textura e desenha a face do fundo
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-0.1d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-0.1d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 0.1d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 0.1d, -1.0d, -1.0d);

                    // seta as coordenadas para a textura e desenha a face de cima
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-0.1d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 0.1d, 1.0d, -1.0d);

                    // seta as coordenadas para a textura e desenha a face de baixo
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);

                    // seta as coordenadas para a textura e desenha a face da direita
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d( 1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d( 1.0d, -1.0d, 1.0d);

                    // seta as coordenadas para a textura e desenha a face da esquerda
                    ogl.gl.glTexCoord2d(0d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, -1.0d);
                    ogl.gl.glTexCoord2d(1d, 0d); ogl.gl.glVertex3d(-1.0d, -1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(1d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, 1.0d);
                    ogl.gl.glTexCoord2d(0d, 1d); ogl.gl.glVertex3d(-1.0d, 1.0d, -1.0d);
                ogl.gl.glEnd(); // termina o cubo
            ogl.gl.glFlush();
        ogl.gl.glPopMatrix();
        
        this.textura.disable(ogl.gl); // desabilita a textura
    }

    // escala o cubo em um certo valor
    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }
    
    // rotaciona o cubo em um certo ângulo em torno de um eixo
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
        this.angulo = angulo % 360;
        this.rotacao = eixo;
    }
    
    // movimenta o cubo de acordo com os valores de delta
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    // define a textura do cubo
    public void setTextura(Texture textura) {
        this.textura = textura;
        
    }
    
    // define a cor básica do cubo
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    // define o local do cubo "na mão"
    public void setLocal(Ponto p) {
        this.local = p;
    }
    
    // retorna o local do cubo
    public Ponto getLocal() {
        return this.local;
    }
    
    // define as dimensões do cubo
    public void setDimensoes(Ponto p) {
        this.dimensoes = p;
    }
    
    // retorna as dimensões do cubo
    public Ponto getDimensoes() {
        return this.dimensoes;
    }
}
