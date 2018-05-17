package turbine;

import com.jogamp.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;


public abstract class Forma {
    private Texture textura;
    
    public abstract void desenhar(OGL ogl);
    
    public abstract void escalar(Double v);
    
    public abstract void transladar(Ponto delta);
    
    public abstract void rotacionar(Double angulo, Ponto eixo);
    
    // a diferença para transladar é que setLocal especifica o local absoluto
    public abstract void setLocal(Ponto p);
    public abstract Ponto getLocal();
    
    
    
    public void carregarTextura(String caminho){
        // implementar ainda
    }
}
