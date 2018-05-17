package turbine;

public class Esfera extends Forma {
    private Ponto excentricidade; // distorções nos 3 eixos
    private Ponto local; // centro
    private Ponto rotacao; // eixo de rotação
    private Double angulo; // angulo de rotação
    
    public Esfera(Ponto excentricidade){
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
            ogl.glut.glutSolidSphere(Constantes.TAMANHO_PADRAO, 30, 30);
            ogl.gl.glEnd();
        ogl.gl.glPopMatrix();
    }

    public void escalar(Double v) {
        this.excentricidade.multiplicar(v);
    }
    
    public void rotacionar(Double angulo, Ponto eixo) {
        // utilizar quaternions para combinar rotações
        this.angulo = angulo % 360;
        this.rotacao = eixo;
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    public void setLocal(Ponto p) {
        this.local = p;
    }
    
    public Ponto getLocal() {
        return this.local;
    }
}
