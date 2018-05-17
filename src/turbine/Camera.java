package turbine;

import com.jogamp.opengl.GL2;

public class Camera {
    public Ponto local;
    public Ponto rotacao;
    
    public Camera() {
        local = new Ponto(0d, 0d, 0d);
        rotacao = new Ponto(0d, 0d, 0d);
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    public void ajustaObservacao(OGL ogl) {
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
}
