package Turbine;

import com.jogamp.opengl.GL2;

public class Camera {
    // atributos temporariamente públicos
    public Ponto local; // local da câmera no espaço
    public Ponto rotacao; // rotação na câmera, por ângulo nos 3 eixos. Talvez mude para um vetor de rotação e um ângulo
    public Objeto anexo; // objeto anexado a essa câmera, de modo que ela o siga
    
    // construtor padrão, cria a câmera na posição 0, 0, 0
    public Camera() {
        this.local = new Ponto(0d, 0d, 0d);
        this.rotacao = new Ponto(0d, 0d, 0d);
    }
    
    // caso seja preciso alterar o local de uma câmera sem que ela esteja anexada a um objeto
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    // ajusta a perspectiva do jogo em relação a essa câmera
    public void ajustaObservacao(OGL ogl) {
        // Especifica sistema de coordenadas de projeção
        ogl.gl.glMatrixMode(GL2.GL_PROJECTION);
        // Inicializa sistema de coordenadas de projeção
        ogl.gl.glLoadIdentity();

        // Especifica a projeção perspectiva(angulo,aspecto,zMin,zMax)
        // 16:9, 120 graus de ângulo visão e 5000 metros de distância de visão
        ogl.glu.gluPerspective(120d, 1.77d, 0.2, 5000);
        // Especifica sistema de coordenadas do modelo
        ogl.gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Inicializa sistema de coordenadas do modelo
        ogl.gl.glLoadIdentity();
        // Especifica posição do observador e do alvo
        ogl.glu.gluLookAt(this.local.x, this.local.y, this.local.z, this.anexo.getLocal().x, this.anexo.getLocal().y, this.anexo.getLocal().z, 0, 1, 0);
        //ogl.gl.glTranslated(-this.local.x, -this.local.y, -this.local.z);
        //ogl.gl.glRotated(this.rotacao.x, 1, 0, 0);
        //ogl.gl.glRotated(this.rotacao.y, 0, 1, 0);
        //ogl.gl.glRotated(this.rotacao.z, 0, 0, 1);
    }
    
    
    // anexa um objeto a essa câmera para que ela o siga
    public void anexarObjeto(Objeto obj) {
        this.anexo = obj;
    }
    
    // transiciona essa câmera para o local indicado pelo objeto que ela deve seguir
    public void transicaoCamera(Double deltaTime) {
//         a câmera ficará travada no objeto caso a distância seja menor do que a distância percorrida pelo objeto em 2 frames + 50cm (no caso de o objeto estar parado)
        if (this.local.getDistancia(anexo.getLocalCamera()) < 2 * this.anexo.getVelocidade() * deltaTime + 0.5d) {
            this.local = anexo.getLocalCamera();
       // caso a dustância seja maior, a câmera se aproximará do objeto a uma velocidade igual à soma da velocidade do objeto mais a distância entre eles
       // dessa forma ela se aproximará mais rápido se estiver mais distante e mais devagar quando estiver perto
       // em uma velocidade que é sempre maior do que a velocidade do objeto que ela irá seguir
        } else {
            Double velocidade = this.anexo.getVelocidade() + 2d * this.local.getDistancia(this.anexo.getLocal());
            
            // calcula a direção na qual a câmera precisa se movimentar
            Ponto vetorDirecao = new Ponto(this.anexo.getLocalCamera().x - this.local.x, this.anexo.getLocalCamera().y - this.local.y, this.anexo.getLocalCamera().z - this.local.z).versor();
            this.transladar(vetorDirecao.versor().escalar(velocidade * deltaTime));
        }
    }
}
