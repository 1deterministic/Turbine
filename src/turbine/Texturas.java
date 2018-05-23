package turbine;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// classe que lidará com carregamento e armazenamento de texturas
// a ideia é reaproveitar a mesma textura em vários objetos que forem usá-la
// em vez de carregar uma vez para cada objeto
public class Texturas {
    private Map<String, Texture> texturas; // Mapa de texturas carregadas, acessível por uma chave definida no momento da adição
    
    // construtor padrão
    public Texturas(){
        this.texturas = new HashMap<>();
    }
    
    // retorna a textura da chave recebida
    public Texture getTextura(String id) {
        return this.texturas.get(id);
    }
    
    // carrega uma textura em arquivo para uma determinada chave
    public void carregarTextura(String id, String caminho) {
        try{	
            File fin = new File(caminho);
            this.texturas.put(id, TextureIO.newTexture(fin, true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
