package turbine;

// superclasse dos objetos do jogo
// a ideia é que os elementos do jogo sejam subclasses dessa classe
public abstract class Objeto {
    // aplicar isso em algum lugar
    //private boolean aplicar_fisica; // diz se a simulação de física será aplicada nesse objeto
    
    public abstract void setLocal(Ponto p); // define o local do objeto no espaço
    public abstract Ponto getLocal(); // retorna o local do objeto no espaço
    
    public abstract void setForma(Forma f); // define a forma (modelo 3d) do objeto
    public abstract Forma getForma(); // retorna a forma do objeto
    
    public abstract void setDirecao(Ponto d); // define a forma (modelo 3d) do objeto
    public abstract Ponto getDirecao(); // retorna a forma do objeto
    
    public abstract void setVelocidade(Double v); // define a forma (modelo 3d) do objeto
    public abstract Double getVelocidade(); // retorna a forma do objeto
    
    public abstract void setColisor(Colisor c); // define o container colisor desse objeto
    public abstract Colisor getColisor(); // retorna o container colisor desse objeto
    
    public abstract void transladar(Ponto delta); // translada esse objeto no espaço, de acordo com as coordenadas em delta
        public abstract void atualizarLocalForma(); // atualiza o local da forma (modelo 3d) desse objeto (caso o objeto seja movido seu modelo tbm deve mover)
        public abstract void atualizarLocalColisor(); // atualiza o local do container colisor desse objeto (caso o objeto seja movido seu colisor tbm deve mover)
    
    public abstract Ponto getLocalCamera(); // retorna o ponto onde a câmera deve estar para seguir esse objeto
    
    
    // controle
    public abstract void movimentar(Controle c, Double timeDelta);
    public abstract void limitarAreaMovimento(Ponto pontoInicial, Ponto pontoFinal);
    
    // física
    public abstract void manterInercia(Double timeDelta);
}
