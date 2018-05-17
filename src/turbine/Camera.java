package turbine;

import com.jogamp.opengl.GL2;

public class Camera {
    // atributos temporariamente públicos
    public Ponto local; // local da câmera no espaço
    public Ponto rotacao; // rotação na câmera, por ângulo nos 3 eixos. Talvez mude para um vetor de rotação e um ângulo
    private Objeto anexo; // objeto anexado a essa câmera, de modo que ela o siga
    
    
    public Camera() {
        local = new Ponto(0d, 0d, 0d);
        rotacao = new Ponto(0d, 0d, 0d);
    }
    
    // caso seja preciso alterar o local de uma câmera sem que ela esteja anexada a um objeto
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    // ajusta a perspectiva do jogo em relação a essa câmera
    public void ajustaObservacao(OGL ogl) {
        // mudar depois para pegar a posicao do proprio objeto
//        this.local.z = anexo.getForma().getLocal().z + 1d;
//        this.local.y = anexo.getForma().getLocal().y + 0.1d;
        this.local = anexo.getLocalCamera();
        
        // Especifica sistema de coordenadas de projeção
        ogl.gl.glMatrixMode(GL2.GL_PROJECTION);
        // Inicializa sistema de coordenadas de projeção
        ogl.gl.glLoadIdentity();

        // Especifica a projeção perspectiva(angulo,aspecto,zMin,zMax)
        ogl.glu.gluPerspective(30d, 1d, 0.2, 500);
        // Especifica sistema de coordenadas do modelo
        ogl.gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Inicializa sistema de coordenadas do modelo
        ogl.gl.glLoadIdentity();
        // Especifica posição do observador e do alvo
        ogl.gl.glTranslated(-this.local.x, -this.local.y, -this.local.z);
        ogl.gl.glRotated(this.rotacao.x, 1, 0, 0);
        ogl.gl.glRotated(this.rotacao.y, 0, 1, 0);
        ogl.gl.glRotated(this.rotacao.z, 0, 0, 1);
    }
    
    
    // anexa um objeto a essa câmera para que ela o siga
    public void anexarObjeto(Objeto obj) {
        this.anexo = obj;
    }
}
