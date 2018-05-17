package turbine;

// superclasse que guarda as informações de um objeto do jogo
// a ideia é que os elementos do jogo sejam subclasses dessa classe
public abstract class Objeto {
    //private Forma forma; // modelo 3d a ser desenhado
    //private Colisor colisor; // envelope usado na detecção de colisão
    //private boolean aplicar_fisica; // diz se a simulação de física será aplicada nesse objeto
    
    public abstract void setLocal(Ponto p);
    public abstract Ponto getLocal();
    
    public abstract void setForma(Forma f);
    public abstract Forma getForma();
    
    public abstract void transladar(Ponto delta);
    public abstract void atualizarLocalForma();
    
    public abstract Ponto getLocalCamera();
    
    //public abstract void setColisor(Colisor c);
}
