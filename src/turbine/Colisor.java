package turbine;

// o colisor é (a princípio) um cubo que envolve um objeto
// é usado para fazer as detecções de colisão
public class Colisor {
    private Ponto local;
    private Ponto dimensoes; 
    
    public Colisor() {
        this.local = new Ponto(0d, 0d, 0d);
        this.dimensoes = new Ponto(1d, 1d, 1d);
    }
    
    public Colisor(Ponto dimensoes){
        this.local = new Ponto(0d, 0d, 0d);
        this.dimensoes = dimensoes;
    }
    
    public void escalar(Double v) {
        this.dimensoes.multiplicar(v);
    }
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
    }
    
    public void setLocal(Ponto p) {
        this.local = p;
    }
    
    public Ponto getLocal() {
        return this.local;
    }
    
    public void setDimensoes(Ponto p) {
        this.dimensoes = p;
    }
    
    public Ponto getDimensoes() {
        return this.dimensoes;
    }
    
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
