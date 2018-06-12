package Turbine;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

// guarda informações trocadas entre métodos que usam essas variáveis
// em vez de passar todos os parâmetros, passa-se apenas uma instância dessa classe
public class OGL {
    // atributos públicos para fácil acesso
    public GL2 gl;
    public GLU glu;
    public GLUT glut;
    public GLAutoDrawable glDrawable;
}
