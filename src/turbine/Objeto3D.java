package turbine;

// classe que guarda as informações de um objeto 3d mostrado na tela
public class Objeto3D {
    private Forma forma; // modelo 3d a ser desenhado
    private Colisor colisor; // envelope de colisão usado na detecção de colisão
    
    private boolean aplicar_fisica; // diz se a simulação de física será aplicada nesse objeto
    
    public Objeto3D(){}
}
