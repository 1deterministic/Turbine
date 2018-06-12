package Turbine;

// classe abstrata dos modelos 3d

import com.jogamp.opengl.util.texture.Texture;
import java.awt.Color;

// a ideia é que toda forma desenhada seja subclasse dela
public abstract class Forma {
   
    public abstract void desenhar(OGL ogl); // desenha a forma
    
    public abstract void escalar(Double v); // estica a forma na proporção v nos 3 eixos
    
    public abstract void transladar(Ponto delta); // move a forma de acordo com v (soma todas as coordenadas com delta)
    
    public abstract void rotacionar(Double angulo, Ponto eixo); // define a rotação da forma em "angulo" graus em torno de "eixo"
    
    // textura e cor
    public abstract void setTextura(Texture textura); // define a textura da forma
    public abstract void setCor(Color cor); // define a cor base da forma
    
    // a diferença para transladar é que setLocal especifica o local absoluto
    public abstract void setLocal(Ponto p); // define o local da forma
    public abstract Ponto getLocal(); // retorna o local da forma
    
    public abstract void setDimensoes(Ponto p); // define o tamanho da forma
    public abstract Ponto getDimensoes(); // retorna o tamanho da forma
}
