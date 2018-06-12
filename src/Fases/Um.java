package Fases;

import Turbine.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Um extends Fase {
    private Texturas texturas; // mapa de texturas
    private Ceu ceu; // skybox da fase
    private Camera camera; // câmera
    private Nave nave; // nave
    private ArrayList<Obstaculo> obstaculos; // vetor de obstáculos
    private Obstaculo chegada; // linha de chegada
    private ArrayList<Obstaculo> chao; // vetor de pisos
    private boolean colide; // guarda se a nave colidiu com algum obstáculo
    
    public Um() {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.nave = new Nave();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.obstaculos = new ArrayList<>();
        this.chao = new ArrayList<>();
        this.colide = false;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("chegada_um", diretorioRaiz + "/src/turbine/Arquivos/chegada_um.png");
        this.texturas.carregarTextura("malha", diretorioRaiz + "/src/turbine/Arquivos/malha.png");
        this.texturas.carregarTextura("borda", diretorioRaiz + "/src/turbine/Arquivos/borda.png");

        
        // define a posição inicial da câmera
        this.camera.local.z = 0d;

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("borda"));
        this.ceu.setCor(Color.white);
        
        // carrega a nave
        this.nave.setLocal(new Ponto(0d, 0d, 500d)); // a nave começa na posição 0, 0, 500
        this.nave.atualizarForma(); // atualiza a posição da forma para o mesmo local da nave
        this.nave.getForma().setDimensoes(new Ponto(1d, 0.1d, 1d)); // faz a forma da nave ter o tamanho 1 em largura, 0.1 em altura e 1 em profundidade
        this.nave.getForma().setTextura(this.texturas.getTextura("borda")); // define a textura da nave para "borda"
        this.nave.getForma().setCor(Color.white); // define a cor base da forma para branco
        this.nave.setDirecao(new Ponto(0d, 0d, -1d)); // define a direção de movimento da nave para 0, 0, -1 (na direção do fundo da fase)
        this.nave.setVelocidade(250d); // defne a velocidade da nave para 250m/s
        this.nave.atualizarColisor(); // atualiza a posição do colisor para o mesmo local da nave
        this.nave.getColisor().setDimensoes(nave.getForma().getDimensoes()); // faz o colisor e a forma terem o mesmo tamanho
        
        // anexa a nave na câmera
        this.camera.anexarObjeto(nave);

        // gera os obstáculos
        for(int i = 0; i < 100; i++) {
            Obstaculo obstaculo = new Obstaculo();
            
            // a posição do obstáculo é um valor aleatório entre -100 e 100 no eixo x, 50 no eixo y e -i * 50 no eixo z (todos os obstáculos estão 50m atrás um do outro)
            obstaculo.setLocal(new Ponto(
                    ThreadLocalRandom.current().nextDouble(-100d, 100d), 
                    50d, 
                    -i * 100d));
            obstaculo.atualizarForma(); // atualiza o local da forma para o mesmo local do obstáculo
            obstaculo.getForma().setDimensoes(new Ponto(10d, 100d, 10d)); // faz a forma do obstáculo ter o tamanho 10 em largura, 100 em altura e 10 em profundidade
            obstaculo.getForma().setTextura(this.texturas.getTextura("borda")); // define a textura "borda" para a forma
            obstaculo.getForma().setCor(Color.white); // define a cor base da forma para branco
            obstaculo.setDirecao(new Ponto()); // define a direção de movimento do colisor para 0, 0, 0 (parado)
            obstaculo.setVelocidade(0d); // define a velocidade do colisor para 0
            obstaculo.atualizarColisor(); // atualiza a posição do colisor para o memo local do obstáculo
            obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes()); // faz o colisor ser do mesmo tamanho da forma do obstáculo
            
            this.obstaculos.add(obstaculo); // adiciona o obstáculo no vetor de obstáculos
        }

        // gera o chão em pedaços
        for (int i = 0; i <= 10; i++) {
            Obstaculo o = new Obstaculo();
            
            o.setLocal(new Ponto(0d, -3d, -1000d * i)); // a posição desse pedaço de chão é 0 no x (para ficar no centro), -3 no y (para ficar levemente abaixo da nave que está no 0) e -1000 * i no z (para ficar espaçado em 1000m do último pedaço) 
            o.atualizarForma(); // atualiza o local da forma para o mesmo local do pedaço de chão
            o.getForma().setDimensoes(new Ponto(100d, 2d, 500d)); // faz a forma do pedaço de chão ter o tamanho 100 em largura, 2 em altura (não pode ser zero) e 500 em profundidade
            o.getForma().setTextura(this.texturas.getTextura("malha")); // define a textura "malha" para a forma
            o.getForma().setCor(Color.white); // define a cor base do pedaço de chão para branco
            o.setDirecao(new Ponto()); // define a velocidade do pedaço de chão para 0
            o.setVelocidade(0d); // define a velocidade do pedaço de chão para 0
            o.atualizarColisor(); // atualiza a posição do pedaço de chão para o memo local do obstáculo
            o.getColisor().setDimensoes(o.getForma().getDimensoes()); // faz o colisor ser do mesmo tamanho da forma do pedaço de chão
            
            this.chao.add(o); // adiciona o obstáculo no vetor de obstáculos
        }
        
        // carrega a linha de chegada
        this.chegada.setLocal(new Ponto(0d, 50d, -10000d));
        this.chegada.atualizarForma();
        this.chegada.getForma().setDimensoes(new Ponto(100d, 100d, 10d));
        this.chegada.getForma().setTextura(this.texturas.getTextura("chegada_um"));
        this.chegada.getForma().setCor(Color.white);
        this.chegada.setDirecao(new Ponto());
        this.chegada.setVelocidade(0d);
        this.chegada.atualizarColisor();
        this.chegada.getColisor().setDimensoes(this.chegada.getForma().getDimensoes());
    }
    
    // roda a física e a lógica
    public void atualizar(Double deltaTempo, Controle controle) {
        // roda a física
        this.nave.movimentar(controle, deltaTempo);
        this.nave.manterInercia(deltaTempo);
        this.nave.aplicarGravidade(0.1d, deltaTempo);
        this.nave.limitarAreaMovimento(new Ponto(-100d, 0d, 0d), new Ponto(100d, 100d, 0d));
        this.nave.atualizarHud();
        
        int pos = 0;
        for (Obstaculo o: this.obstaculos) {
            if (o.getLocal().z < this.nave.getLocal().z)
                pos++;
        }
        
        ((Texto) this.nave.getHud()).setTexto(((Texto) this.nave.getHud()).getTexto() + "\n#: " + pos);
        
        // verifica as colisões
        for (Obstaculo o: this.obstaculos) {
            if (!this.colide) {
                if (this.nave.getColisor().colideCom(o.getColisor())) {
                    this.colide = true;
                    this.camera.anexarObjeto(o);
                    // some com a nave da tela
                    this.nave.setLocal(new Ponto(0d, 0d, this.chegada.getLocal().z - this.chegada.getForma().getDimensoes().z  - 1d));
                }
            }
        }
        
        // verifica se o fim da fase foi alcançado
        if (!this.colide) {
            if (this.nave.getColisor().colideCom(this.chegada.getColisor())) {
                this.colide = true;
                // some com a nave da tela
                this.nave.setLocal(new Ponto(0d, 0d, this.chegada.getLocal().z - this.chegada.getForma().getDimensoes().z  - 1d));
                
                // muda a câmera para a linha de chegada
                this.camera.anexarObjeto(this.chegada);
            }
        }
        
        // atualiza a câmera
        this.camera.transicaoCamera(deltaTempo);
    }
    
    // desenha todos os elementos na tela
    public void desenhar(OGL ogl) {
        // desenha o skybox
        this.ceu.desenhar(ogl);
        
        // ajusta a observação para o novo local da câmera
        this.camera.ajustaObservacao(ogl);
        
         // desenha todos os objetos
        this.nave.desenhar(ogl);
        this.chegada.desenhar(ogl);
        for (Obstaculo o: this.obstaculos) {
            o.desenhar(ogl);
        }
        for (Obstaculo o: this.chao) {
            o.desenhar(ogl);
        }
    }
}
