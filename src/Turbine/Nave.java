package Turbine;

// Nave do jogador

import java.awt.Color;

public class Nave extends Objeto {
    private Ponto local; // posição no espaço
    private Forma forma; // modelo 3d da nave
    private Texto hud; // painel de informações da nave
    private Colisor colisor; // container de colisão da nave
    
    private Ponto direcao; // direção de movimento da nave
    private Double velocidade; // velocidade da nave
    
    private Double intensidadeTurbo; // intensidade do turbo aplicado
    private Double quantidadeTurbo; // quantidade de powerup de velocidade disponível
    
    // construtor padrão
    public Nave() {
        this.local = new Ponto(0d, 0d, 0d);
        this.forma = new NaveModelo();
        this.hud = new Texto(); this.hud.setDimensoes(new Ponto(0.005d, 0.005d, 0.005d)); this.hud.setRotacao(new Ponto(0d, 1d, 0d)); this.hud.setAngulo(-15d);
        this.colisor = new Colisor();
        
        this.direcao = new Ponto(0d, 0d, 0d);
        this.velocidade = 0d;
        
        this.intensidadeTurbo = 0d;
        this.quantidadeTurbo = 5d;
    }
    
    // costrutor completo
    public Nave(Ponto local, Forma forma, Ponto direcao, Double velocidade, Colisor colisor) {
        this.local = local;
        this.forma = forma;
        this.direcao = direcao;
        this.velocidade = velocidade;
        this.colisor = colisor;
    }
    
    // desenha essa nave (repassa para a forma desenhar)
    public void desenhar(OGL ogl) {
        this.forma.desenhar(ogl);
        this.hud.desenhar(ogl);
    }
    
    // define o local da nave (também atualiza o local da forma e colisor atribuídos)
    public void setLocal(Ponto p) {
        this.local = p;
        this.atualizarForma();
        this.atualizarColisor();
    }
    
    // retorna o local da nave
    public Ponto getLocal() {
        return this.local;
    }
    
    // define a forma da nave
    public void setForma(Forma forma) {
        this.forma = forma;
    }
    
    // retorna  a forma da nave
    public Forma getForma() {
        return this.forma;
    }
    
    // define o colisor da nave
    public void setColisor(Colisor c) {
        this.colisor = c;
    }
    
    // retorna o colisor da nave
    public Colisor getColisor() {
        return this.colisor;
    }
    
    // define a direção da nave
    public void setDirecao(Ponto d) {
        this.direcao = d;
    }
    
    // retorna a direção da nave
    public Ponto getDirecao() {
        return this.direcao;
    }
    
    // define a velocidade da nave
    public void setVelocidade(Double v) {
        this.velocidade = v;
    }
    
    // retorna a velocidade da nave
    public Double getVelocidade() {
        return this.velocidade;
    }
    
    // movimenta a nave de acordo com os valores de delta, movimenta também a forma e o colisor anexos
    public void transladar(Ponto delta) {
        this.local.somar(delta);
        this.atualizarForma();
        this.atualizarColisor();
    }
    
    // atualiza o local da forma anexa de acordo com o local da nave
    public void atualizarForma() {
        this.forma.setLocal(new Ponto(this.local));
    }
    
    // atualiza o local do colisor anexo de acordo com o local da nave
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
        return new Ponto(local.x, local.y + 1d, local.z + 4d);
    }
    
    // movimenta a nave de acordo com a direção e a velocidade atuais
    public void manterInercia(Double timeDelta) {
        this.transladar(this.direcao.escalar((this.velocidade + this.intensidadeTurbo) * timeDelta));
        
        // diminui gradativamente a intensidade do turbo
        this.intensidadeTurbo -= 10 * timeDelta;
        if (this.intensidadeTurbo < 0d) {
            this.intensidadeTurbo = 0d;
        }
    }
    
    // aplica gravidade à nave
    public void aplicarGravidade(Double aceleracao, Double timeDelta) {
        this.direcao.y -= aceleracao * timeDelta;
    }
    
    // aplica as entradas do controle para a nave
    public void movimentar(Controle c, Double timeDelta){
        if (c.direita)
            this.direcao.x += 1d * timeDelta;
        
        if (c.esquerda)
            this.direcao.x -= 1d * timeDelta;
        
        if (c.cima)
            this.direcao.y += 1d * timeDelta;
        
        if (c.baixo)
            this.direcao.y -= 1d * timeDelta;
        
        if (c.turbo) {
            if (this.quantidadeTurbo > 0d) {
                this.intensidadeTurbo += 50d * timeDelta; 
                
                this.quantidadeTurbo -= timeDelta;
                if (this.quantidadeTurbo < 0d)
                    this.quantidadeTurbo = 0d;
            }
        }
        
        // limitação de movimento
        if (this.direcao.x > 10d * timeDelta)
            this.direcao.x = 10d * timeDelta;
        else if (this.direcao.x < -10d * timeDelta)
            this.direcao.x = -10d * timeDelta;
        
        if (this.direcao.y > 10d * timeDelta)
            this.direcao.y = 10d * timeDelta;
        else if (this.direcao.y < -10d * timeDelta)
            this.direcao.y = -10d * timeDelta;

        
        if (this.intensidadeTurbo > 100d)
            this.intensidadeTurbo = 100d;
        else if (this.intensidadeTurbo < 0d) {
            this.intensidadeTurbo = 0d;
        }

        // inclina a nave pro lado onde está virando
        this.forma.rotacionar(this.direcao.x * 80d, new Ponto(0d, 0d, -1d));
    }
    
    // restringe a movimentação a um cubo entre pontoInicial e pontoFinal
    public void limitarAreaMovimento(Ponto pontoInicial, Ponto pontoFinal) {
        if (this.local.x < pontoInicial.x) {
            this.local.x = pontoInicial.x;
            this.direcao.x = 0d;
        } else if (this.local.x > pontoFinal.x) {
            this.local.x = pontoFinal.x;
            this.direcao.x = 0d;
        }
        
        if (this.local.y < pontoInicial.y) {
            this.local.y = pontoInicial.y;
            this.direcao.y = 0d;
        } else if (this.local.y > pontoFinal.y) {
            this.local.y = pontoFinal.y;
            this.direcao.y = 0d;
        }
        
        // ignorar o z por enquanto
    }
    
    // retorna o hud da nave
    public Forma getHud() {
        return this.hud;
    }
}