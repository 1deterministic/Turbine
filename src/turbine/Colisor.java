package turbine;

// o colisor é (a princípio) um cubo que envolve um objeto
// é usado para fazer as detecções de colisão
public class Colisor {
    private Ponto local; // local do colisor no espaço
    private Ponto dimensoes; // dimensões do colisor
    
    // construtor padrão, cria um cubo 1x1x1 no ponto 0, 0, 0
    public Colisor() {
        this.local = new Ponto(0d, 0d, 0d);
        this.dimensoes = new Ponto(1d, 1d, 1d);
    }
    
    // construtor útil para definir automaticamente do tamanho da forma associada ao objeto
    public Colisor(Ponto local, Ponto dimensoes) {
        this.local = local;
        this.dimensoes = dimensoes;
    }
    
    // escala o colisor por um valor em todas as 
    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }
    
    // move o colisor da posição onde está em um valor nas 3 coordenadas
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    // define o local "na mão"
    public void setLocal(Ponto p) {
        this.local = p;
    }
    
    // retorna o local do colisor
    public Ponto getLocal() {
        return this.local;
    }
    
    // define as dimensões do colisor
    public void setDimensoes(Ponto p) {
        this.dimensoes = p;
    }
    
    
    // retorna as dimensões do colisor
    public Ponto getDimensoes() {
        return this.dimensoes;
    }
    
    
    // testa se esse colisor colide com outro
    public boolean colideCom(Colisor outro) {
            // x final ou x inicial dentro dos limites do outro objeto
        if (((((this.local.x + this.dimensoes.x) > (outro.local.x - outro.dimensoes.x)) &&
              ((this.local.x + this.dimensoes.x) < (outro.local.x + outro.dimensoes.x))) ||
             (((this.local.x - this.dimensoes.x) > (outro.local.x - outro.dimensoes.x)) &&
              ((this.local.x - this.dimensoes.x) < (outro.local.x + outro.dimensoes.x))))
            &&
            // y final ou y inicial dentro dos limites do outro objeto
            ((((this.local.y + this.dimensoes.y) > (outro.local.y - outro.dimensoes.y)) &&
              ((this.local.y + this.dimensoes.y) < (outro.local.y + outro.dimensoes.y))) ||
             (((this.local.y - this.dimensoes.y) > (outro.local.y - outro.dimensoes.y)) &&
              ((this.local.y - this.dimensoes.y) < (outro.local.y + outro.dimensoes.y))))
            &&
            // z final ou z inicial dentro dos limites do outro objeto
            ((((this.local.z + this.dimensoes.z) > (outro.local.z - outro.dimensoes.z)) &&
              ((this.local.z + this.dimensoes.z) < (outro.local.z + outro.dimensoes.z))) ||
             (((this.local.z - this.dimensoes.z) > (outro.local.z - outro.dimensoes.z)) &&
              ((this.local.z - this.dimensoes.z) < (outro.local.z + outro.dimensoes.z))))) {
            
            return true;
        }
        
        return false;
    }
    
}
