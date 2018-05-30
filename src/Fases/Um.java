package Fases;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import turbine.*;

public class Um extends Fase {
    private Texturas texturas;
    private Ceu ceu;
    private Camera camera;
    private Nave nave;
    private ArrayList<Obstaculo> obstaculos;
    private Obstaculo chegada;
    private Obstaculo chao;
    private boolean colide;
    
    public Um() {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.nave = new Nave();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.obstaculos = new ArrayList<>();
        this.chao = new Obstaculo();
        this.colide = false;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("madeira", diretorioRaiz + "/src/turbine/Arquivos/madeira.jpg");
        this.texturas.carregarTextura("chegada", diretorioRaiz + "/src/turbine/Arquivos/chegada.jpg");
        this.texturas.carregarTextura("abstrato", diretorioRaiz + "/src/turbine/Arquivos/abstrato.jpg");
        this.texturas.carregarTextura("ceu", diretorioRaiz + "/src/turbine/Arquivos/ceu.jpg");
        
        // define a posição inicial da câmera
        this.camera.local.z = 800d;

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("ceu"));
        
        // carrega a nave
        this.nave.setLocal(new Ponto(0d, 0d, 500d));
        this.nave.atualizarLocalForma();
        this.nave.getForma().setDimensoes(new Ponto(1d, 0.1d, 1d));
        this.nave.getForma().setTextura(this.texturas.getTextura("madeira"));
        this.nave.setDirecao(new Ponto(0d, 0d, -1d));
        this.nave.setVelocidade(250d); //900Km/h
        this.nave.atualizarLocalColisor();
        this.nave.getColisor().setDimensoes(nave.getForma().getDimensoes()); // faz o colisor e a forma terem o mesmo tamanho
        
        // anexa a nave na câmera
        this.camera.anexarObjeto(nave);

        // gera os obstáculos
        for(int i = 0; i < 100; i++) {
            Double x = ThreadLocalRandom.current().nextDouble(-100d, 100d);
            Double y = ThreadLocalRandom.current().nextDouble(0d, 50d);
            Double z = i * 100d;
            
            Obstaculo obstaculo = new Obstaculo();
            
            obstaculo.setLocal(new Ponto(x, y, -z));
            obstaculo.atualizarLocalForma();
            obstaculo.getForma().setDimensoes(new Ponto(10d, 100d, 10d));
            obstaculo.getForma().setTextura(this.texturas.getTextura("madeira"));
            obstaculo.setDirecao(new Ponto());
            obstaculo.setVelocidade(0d);
            obstaculo.atualizarLocalColisor();
            obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
            
            this.obstaculos.add(obstaculo);
        }
        
        // carrega a linha de chegada
        this.chegada.setLocal(new Ponto(0d, 50d, -10000d));
        this.chegada.atualizarLocalForma();
        this.chegada.getForma().setDimensoes(new Ponto(100d, 100d, 10d));
        this.chegada.getForma().setTextura(this.texturas.getTextura("chegada"));
        this.chegada.setDirecao(new Ponto());
        this.chegada.setVelocidade(0d);
        this.chegada.atualizarLocalColisor();
        this.chegada.getColisor().setDimensoes(this.chegada.getForma().getDimensoes());
        
        // carrega o chão
        this.chao.setLocal(new Ponto(0d, -1d, -5000d));
        this.chao.atualizarLocalForma();
        this.chao.getForma().setDimensoes(new Ponto(100d, 0d, 10000d));
        this.chao.getForma().setTextura(this.texturas.getTextura("abstrato"));
        this.chao.setDirecao(new Ponto());
        this.chao.setVelocidade(0d);
        this.chao.atualizarLocalColisor();
        this.chao.getColisor().setDimensoes(this.chao.getForma().getDimensoes());
    }
    
    // roda a física e a lógica
    public void atualizar(Double deltaTempo, Controle controle) {
        // roda a física
        this.nave.movimentar(controle, deltaTempo);
        this.nave.limitarAreaMovimento(new Ponto(-100d, 0d, 0d), new Ponto(100d, 100d, 0d));
        this.nave.manterInercia(deltaTempo);
        this.nave.aplicarGravidade(0.1d, deltaTempo);
        
        // verifica as colisões
        for (Obstaculo o: this.obstaculos) {
            if (!this.colide) {
                if (this.nave.getColisor().colideCom(o.getColisor())) {
                    this.colide = true;
                    this.camera.anexarObjeto(o);
                    System.out.println(o.getLocal());
                }
            }
        }
        
        // verifica se o fim da fase foi alcançado
        if (!this.colide) {
            if (this.nave.getColisor().colideCom(this.chegada.getColisor())) {
                this.colide = true;
                this.camera.anexarObjeto(this.chegada);
                System.out.println("Venceu!");
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
        this.nave.getForma().desenhar(ogl);
        this.chao.getForma().desenhar(ogl);
        this.chegada.getForma().desenhar(ogl);
        for (Obstaculo o: this.obstaculos) {
            o.getForma().desenhar(ogl);
        }
    }
}
