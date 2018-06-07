package Turbine;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import java.awt.Color;

// subforma que desenha um texto
public class Texto extends Forma {

    private String texto; // string do texto
    private Ponto dimensoes; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    private Color cor; // textura atribuída ao texto

    // construtor padrão, cria um texto no local 0, 0, 0, com tamanho 1 em todas as dimensões, sem rotação
    public Texto() {
        this.texto = "";
        this.dimensoes = new Ponto(1d, 1d, 1d);
        this.local = new Ponto(0.0d, 0.0d, 0.0d);
        this.rotacao = new Ponto(0.0d, 0.0d, 0.0d);
        this.angulo = 0d;
    }

    // construtor completo
    public Texto(String texto, Ponto local, Ponto dimensoes, Ponto rotacao, Double angulo, Color cor) {
        this.texto = texto;
        this.local = local;
        this.dimensoes = dimensoes;
        this.rotacao = rotacao;
        this.angulo = angulo;
        this.cor = cor;
    }

    public void setTexto(String texto, Color cor) {
        this.texto = texto;
        this.cor = cor;
    }

    // desenha o texto na tela
    public void desenhar(OGL ogl) {
        String textos[] = this.texto.split("\\n+");

        int i = 0;
        for (String s : textos) {
            ogl.gl.glPushMatrix();
            ogl.gl.glTranslated(this.local.x, this.local.y - i, this.local.z); // aplica a translação definida
            ogl.gl.glRotated(this.angulo, this.rotacao.x, this.rotacao.y, this.rotacao.z); // aplica a rotação definida
            ogl.gl.glScaled(this.dimensoes.x, this.dimensoes.y, this.dimensoes.z); // aplica a escala definida

            ogl.gl.glColor3d(this.cor.getRed() / 255d, this.cor.getGreen() / 255d, this.cor.getBlue() / 255d); // define a cor básica do objeto
            ogl.gl.glLineWidth(1.0f);
            ogl.glut.glutStrokeString(GLUT.STROKE_MONO_ROMAN, s);

            //ogl.gl.glRasterPos3i(0, 0, 0);
            //ogl.glut.glutBitmapString(2, s);
            ogl.gl.glFlush();
            ogl.gl.glPopMatrix();

            i++;
        }
    }

    // escala o texto em um certo valor
    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }

    // rotaciona o texto em um certo ângulo em torno de um eixo
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
        this.angulo = angulo % 360;
        this.rotacao = eixo;
    }

    // movimenta o texto de acordo com os valores de delta
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }

    // define a textura do texto
    public void setTextura(Texture textura) {
    }

    // define a cor do texto
    public void setCor(Color cor) {
        this.cor = cor;
    }

    // define o local do texto "na mão"
    public void setLocal(Ponto p) {
        this.local = p;
    }

    // retorna o local do texto
    public Ponto getLocal() {
        return this.local;
    }

    // define as dimensões do texto
    public void setDimensoes(Ponto p) {
        this.dimensoes = p;
    }

    // retorna as dimensões do texto
    public Ponto getDimensoes() {
        return this.dimensoes;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Ponto getRotacao() {
        return rotacao;
    }

    public void setRotacao(Ponto rotacao) {
        this.rotacao = rotacao;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }

}
