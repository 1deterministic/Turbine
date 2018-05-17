package turbine;

public class Cubo extends Forma {
    private Ponto dimensoes; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    
    public Cubo(Ponto dimensoes){
        this.dimensoes = dimensoes;
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
            ogl.gl.glScaled(this.dimensoes.x, this.dimensoes.y, this.dimensoes.z);
            ogl.glut.glutSolidCube((float) Constantes.TAMANHO_PADRAO);
            ogl.gl.glEnd();
        ogl.gl.glPopMatrix();
    }

    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }
    
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
}
