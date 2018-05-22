package turbine;

// nave do jogador
public class Nave extends Objeto {
    private Ponto local; // posição no espaço
    private Forma forma; // modelo 3d da nave
    private Ponto direcao;
    private Double velocidade;
    //private Colisor colisor; // container de colisão da nave
    
    public Nave() {
        this.local = new Ponto(0d, 0d, 0d);
        this.direcao = new Ponto(0d, 0d, 0d);
        this.velocidade = 0d;
    }
    
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
    
    public void setDirecao(Ponto d) {
        this.direcao = d.versor(); // garante que direcao está normalizado
    }
    
    public Ponto getDirecao() {
        return this.direcao;
    }
    
    public void setVelocidade(Double v) {
        this.velocidade = v;
    }
    
    public Double getVelocidade() {
        return this.velocidade;
    }
    

    public void transladar(Ponto delta) {
        this.local.somar(delta);
        this.atualizarLocalForma();
    }
    
    public void atualizarLocalForma() {
        this.forma.setLocal(new Ponto(this.local));
    }
    
    public Ponto getLocalCamera() {
        return new Ponto(local.x, local.y + 1d, local.z + 4d);
    }
    
    
    public void manterInercia(Double timeDelta) {
        this.transladar(this.direcao.versor().escalar(this.velocidade * timeDelta));
    }
    
    public void movimentar(Controle c, Double timeDelta){
        if (c.direita)
            this.direcao.x += 1d * timeDelta; // coeficiente de agilidade
        
        if (c.esquerda)
            this.direcao.x -= 1d * timeDelta; // coeficiente de agilidade
        
        if (c.cima)
            this.direcao.y += 1d * timeDelta; // coeficiente de agilidade
        
        if (c.baixo)
            this.direcao.y -= 1d * timeDelta; // coeficiente de agilidade
    }
}
