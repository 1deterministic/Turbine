package Turbine;

// classe abstrata dos modelos 3d
import com.jogamp.opengl.util.texture.Texture;

// a ideia é que toda forma desenhada seja subclasse dela
public abstract class Forma {
   
    public abstract void desenhar(OGL ogl);
    
    public abstract void escalar(Double v);
    
    public abstract void transladar(Ponto delta);
    
    public abstract void rotacionar(Double angulo, Ponto eixo);
    
    public abstract void setTextura(Texture textura);
    
    // a diferença para transladar é que setLocal especifica o local absoluto
    public abstract void setLocal(Ponto p);
    public abstract Ponto getLocal();
    
    public abstract void setDimensoes(Ponto p);
    public abstract Ponto getDimensoes();
}
