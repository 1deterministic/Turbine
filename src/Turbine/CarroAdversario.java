package Turbine;

// Nave do jogador

import java.awt.Color;

public class CarroAdversario extends Objeto {
    private Ponto local; // posição no espaço
    private Forma forma; // modelo 3d do carro adversário
    private Texto hud; // painel de informações do carro adversário
    private Colisor colisor; // container de colisão do carro adversário
    
    private Ponto direcao; // direção de movimento do carro adversário
    private Double velocidade; // velocidade do carro adversário
    
    private Double intensidadeTurbo; 
    private Double quantidadeTurbo; // quantidade de powerup de velocidade disponível
    
    // construtor padrão
    public CarroAdversario() {
        this.local = new Ponto(0d, 0d, 0d);
        this.forma = new Cubo();
        this.hud = new Texto(); this.hud.setDimensoes(new Ponto(0.005d, 0.005d, 0.005d)); this.hud.setRotacao(new Ponto(0d, 1d, 0d)); this.hud.setAngulo(-15d);
        this.colisor = new Colisor();
        
        this.direcao = new Ponto(0d, 0d, 0d);
        this.velocidade = 0d;
        
        this.intensidadeTurbo = 0d;
        this.quantidadeTurbo = 5d; // quantidade de tempo disponível
    }
    
    // costrutor completo
    public CarroAdversario(Ponto local, Forma forma, Ponto direcao, Double velocidade, Colisor colisor) {
        this.local = local;
        this.forma = forma;
        this.direcao = direcao;
        this.velocidade = velocidade;
        this.colisor = colisor;
    }
    
    public void desenhar(OGL ogl) {
        this.forma.desenhar(ogl);
        //this.hud.desenhar(ogl);
    }
    
    // define o local do carro adversário (também atualiza o local da forma e colisor atribuídos)
    public void setLocal(Ponto p) {
        this.local = p;
        this.atualizarForma();
        this.atualizarColisor();
    }
    
    // retorna o local do carro adversário
    public Ponto getLocal() {
        return this.local;
    }
    
    // define a forma do carro adversário
    public void setForma(Forma forma) {
        this.forma = forma;
    }
    
    // retorna  a forma do carro adversário
    public Forma getForma() {
        return this.forma;
    }
    
    // define o colisor do carro adversário
    public void setColisor(Colisor c) {
        this.colisor = c;
    }
    
    // retorna o colisor do carro adversário
    public Colisor getColisor() {
        return this.colisor;
    }
    
    // define a direção do carro adversário
    public void setDirecao(Ponto d) {
        this.direcao = d;
    }
    
    // retorna a direção do carro adversário
    public Ponto getDirecao() {
        return this.direcao;
    }
    
    // define a velocidade do carro adversário
    public void setVelocidade(Double v) {
        this.velocidade = v;
    }
    
    // retorna a velocidade do carro adversário
    public Double getVelocidade() {
        return this.velocidade;
    }
    
    // movimenta o carro adversário de acordo com os valores de delta, movimenta também a forma e o colisor anexos
    public void transladar(Ponto delta) {
        this.local.somar(delta);
        this.atualizarForma();
        this.atualizarColisor();
    }
    
    // atualiza o local da forma anexa de acordo com o local do carro adversário
    public void atualizarForma() {
        this.forma.setLocal(new Ponto(this.local));
    }
    
    // atualiza o local do colisor anexo de acordo com o local do carro adversário
    public void atualizarColisor() {
        this.colisor.setLocal(new Ponto(this.local));
    }
    
    // atualiza o hud anexo
    public void atualizarHud() {
        this.hud.setLocal(new Ponto(this.local.x + 3d, this.local.y + 1d, this.local.z));
        this.hud.setTexto(new String(this.velocidade + this.intensidadeTurbo + "").split("\\.")[0] + " m/s\n" + 
                          new String((100d * this.quantidadeTurbo / 5d) + "").split("\\.")[0] + "% turbo", Color.white);
    }
    
    // retorna o local apropriado para a câmera
    public Ponto getLocalCamera() {
        return new Ponto(local.x, local.y + 3d, local.z + 4d);
    }
    
    // movimenta o carro adversário de acordo com a direção e a velocidade atuais
    public void manterInercia(Double timeDelta) {
        this.transladar(this.direcao.escalar((this.velocidade + this.intensidadeTurbo) * timeDelta));
        
        if (this.intensidadeTurbo < 0d) {
            this.intensidadeTurbo += 10 * timeDelta;
        } else if (this.intensidadeTurbo > 0d) {
            this.intensidadeTurbo -= 10 * timeDelta;
        }
        
        if (Math.abs(this.intensidadeTurbo) < timeDelta)
            this.intensidadeTurbo = 0d;
    }
    
    // aplica gravidade ao carro adversário
    public void aplicarGravidade(Double aceleracao, Double timeDelta) {
        this.direcao.y -= aceleracao * timeDelta;
    }
    
    // controle desabilitado
    public void movimentar(Controle c, Double timeDelta){
    }
    
    public void limitarAreaMovimento(Ponto pontoInicial, Ponto pontoFinal) {
        if (this.local.x < pontoInicial.x) {
            this.local.x = pontoInicial.x;
            this.direcao.x = -this.direcao.x;
        } else if (this.local.x > pontoFinal.x) {
            this.local.x = pontoFinal.x;
            this.direcao.x = -this.direcao.x;
        }

        if (this.local.y < pontoInicial.y) {
            this.local.y = pontoInicial.y;
            this.direcao.y = 0d;
        } else if (this.local.y > pontoFinal.y) {
            this.local.y = pontoFinal.y;
            this.direcao.y = 0d;
        }        
    }
    
    // retorna o hud do carro adversário
    public Forma getHud() {
        return this.hud;
    }
    
    // retorna a intensidade do turbo do carro adversário
    public void setIntensidadeTurbo(Double intensidadeTurbo) {
        this.intensidadeTurbo = intensidadeTurbo;
    }
    
    // define a intensidade do turbo do carro adversário
    public Double getIntensidadeTurbo() {
        return this.intensidadeTurbo;
    }
}