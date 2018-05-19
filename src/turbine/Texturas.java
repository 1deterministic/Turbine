package turbine;

// classe que lidará com carregamento e armazenamento de texturas

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// a ideia é reaproveitar a mesma textura em vários objetos que forem usá-la
// em vez de carregar uma vez para cada objeto
public class Texturas {
    private Map<String, Texture> texturas;
    
    public Texturas(){
        this.texturas = new HashMap<>();
    }
    
    public Texture getTextura(String id) {
        return this.texturas.get(id);
    }
    
    public void carregarTextura(String id, String caminho) {
        try{	
            File fin = new File(caminho);
            this.texturas.put(id, TextureIO.newTexture(fin, true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
