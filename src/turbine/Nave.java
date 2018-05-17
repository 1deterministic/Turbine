package turbine;

// nave do jogador
public class Nave extends Objeto {
    private Ponto local; // posição no espaço
    private Forma forma; // modelo 3d da nave
    //private Colisor colisor; // container de colisão da nave
    
    public void setLocal(Ponto p) {
        this.local = p;
    }
    public Ponto getLocal() {
        return this.local;
    }
    
    public void setForma(Forma forma) {
        this.forma = forma;
    }
    public Forma getForma() {
        return this.forma;
    }
    
    
    public void transladar(Ponto delta) {
        this.local.somar(delta);
        this.atualizarLocalForma();
    }
    
    public void atualizarLocalForma() {
        this.forma.setLocal(new Ponto(this.local));
    }
    
    public Ponto getLocalCamera() {
        return new Ponto(local.x, local.y + 0.1d, local.z + 1d);
    }
}
