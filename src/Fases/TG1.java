package Fases;

import Turbine.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TG1 extends Fase {
    private Texturas texturas;
    private Ceu ceu;
    private Camera camera;
    private Carro carro;
    private ArrayList<CarroAdversario> carrosAdversarios;
    private Obstaculo chegada;
    private ArrayList<Obstaculo> chao;
    private boolean colide;
    
    public TG1() {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.carro = new Carro();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.carrosAdversarios = new ArrayList<>();
        this.chao = new ArrayList<>();
        this.colide = false;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("chegada", diretorioRaiz + "/src/turbine/Arquivos/chegada.jpg");
        this.texturas.carregarTextura("ceu", diretorioRaiz + "/src/turbine/Arquivos/ceu.jpg");
        this.texturas.carregarTextura("pista", diretorioRaiz + "/src/turbine/Arquivos/pista.png");
        this.texturas.carregarTextura("carrovermelho", diretorioRaiz + "/src/turbine/Arquivos/carrovermelho.png");
        this.texturas.carregarTextura("carroverde", diretorioRaiz + "/src/turbine/Arquivos/carroverde.png");
        this.texturas.carregarTextura("carrobranco", diretorioRaiz + "/src/turbine/Arquivos/carrobranco.png");
        this.texturas.carregarTextura("carroroxo", diretorioRaiz + "/src/turbine/Arquivos/carroroxo.png");
        
        String carrosaleatorios[] = {"carrovermelho", "carroverde", "carrobranco", "carroroxo"};
        
        // define a posição inicial da câmera
        this.camera.local.z = 800d;

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("ceu"));
        this.ceu.setCor(Color.white);
        
        // carrega a carro
        this.carro.setLocal(new Ponto(0d, 0d, 200d));
        this.carro.atualizarForma();
        this.carro.getForma().setDimensoes(new Ponto(1.5d, 1d, 0d));
        this.carro.getForma().setTextura(this.texturas.getTextura("carrovermelho"));
        this.carro.getForma().setCor(Color.white);
        this.carro.setDirecao(new Ponto(0d, 0d, -1d));
        this.carro.setVelocidade(55d);
        this.carro.atualizarColisor();
        this.carro.getColisor().setDimensoes(new Ponto(1.5d, 1d, 1d));
        
        // anexa a carro na câmera
        this.camera.anexarObjeto(carro);

        // gera os obstáculos
        for(int i = 0; i < 100; i++) {
            CarroAdversario obstaculo = new CarroAdversario();
            
            obstaculo.setLocal(new Ponto(
                    ThreadLocalRandom.current().nextDouble(-10d, 10d), 
                    0d, 
                    -i * 50d));
            obstaculo.atualizarForma();
            obstaculo.getForma().setDimensoes(new Ponto(1.5d, 1d, 0d));
            obstaculo.getForma().setTextura(this.texturas.getTextura(carrosaleatorios[new Random().nextInt(4)]));
            obstaculo.getForma().setCor(Color.white);
            obstaculo.setDirecao(new Ponto(ThreadLocalRandom.current().nextDouble(-0.2d, 0.2d), 0d, -1d));
            obstaculo.setVelocidade(ThreadLocalRandom.current().nextDouble(35d, 45d));
            obstaculo.atualizarColisor();
            obstaculo.getColisor().setDimensoes(new Ponto(1.5d, 1d, 1d));
            
            this.carrosAdversarios.add(obstaculo);
        }
        
        // carrega a linha de chegada
        this.chegada.setLocal(new Ponto(0d, 0d, -5000d));
        this.chegada.atualizarForma();
        this.chegada.getForma().setDimensoes(new Ponto(10d, 10d, 10d));
        this.chegada.getForma().setTextura(this.texturas.getTextura("chegada"));
        this.chegada.getForma().setCor(Color.white);
        this.chegada.setDirecao(new Ponto());
        this.chegada.setVelocidade(0d);
        this.chegada.atualizarColisor();
        this.chegada.getColisor().setDimensoes(this.chegada.getForma().getDimensoes());
        
        for(int i = 0; i < 125; i++) {
            // carrega o chão
            Obstaculo chao = new Obstaculo();
            chao.setLocal(new Ponto(0d, -1d, -40d * i));
            chao.atualizarForma();
            chao.getForma().setDimensoes(new Ponto(40d, 0d, 20d));
            chao.getForma().setTextura(this.texturas.getTextura("pista"));
            chao.getForma().setCor(Color.white);
            chao.setDirecao(new Ponto());
            chao.setVelocidade(0d);
            chao.atualizarColisor();
            chao.getColisor().setDimensoes(chao.getForma().getDimensoes());
            this.chao.add(chao);
        }
    }
    
    // roda a física e a lógica
    public void atualizar(Double deltaTempo, Controle controle) {
        // roda a física
        this.carro.movimentar(controle, deltaTempo);
        this.carro.manterInercia(deltaTempo);
        this.carro.aplicarGravidade(0.1d, deltaTempo);
        this.carro.limitarAreaMovimento(new Ponto(-10d, 0d, 0d), new Ponto(10d, 10d, 0d));
        this.carro.atualizarHud();
        
        // verifica as colisões
        for (CarroAdversario o: this.carrosAdversarios) {
            o.movimentar(controle, deltaTempo);
            o.manterInercia(deltaTempo);
            o.aplicarGravidade(0.1d, deltaTempo);
            o.limitarAreaMovimento(new Ponto(-10d, 0d, 0d), new Ponto(10d, 10d, 0d));
            o.atualizarHud();
            
            if (!this.colide) {
                if (this.carro.getColisor().colideCom(o.getColisor())) {
                    this.colide = true;
                    this.carro.setIntensidadeTurbo(-30d);
                    o.setIntensidadeTurbo(30d);
                }
            }
        }
        
        if (this.colide) {
            boolean nao_colide_mais = true;
            for (CarroAdversario o: this.carrosAdversarios) {
                if (this.carro.getColisor().colideCom(o.getColisor()))
                    nao_colide_mais = false;
            }   
            
            if (nao_colide_mais)
                this.colide = false;
        }
        
        for (CarroAdversario o: this.carrosAdversarios) {
            for (CarroAdversario p: this.carrosAdversarios) {
                if (o != p) {
                    if (o.getColisor().colideCom(p.getColisor())) {
                        if (o.getLocal().z > p.getLocal().z) {
                            o.setIntensidadeTurbo(-30d);
                            p.setIntensidadeTurbo(30d);
                        } else {
                            p.setIntensidadeTurbo(-30d);
                            o.setIntensidadeTurbo(30d);
                        }               
                    }
                }
            }
        }
        
        // verifica se o fim da fase foi alcançado
        if (!this.colide) {
            if (this.carro.getColisor().colideCom(this.chegada.getColisor())) {
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
        for (Obstaculo o: this.chao) {
            o.desenhar(ogl);
        }
        this.chegada.desenhar(ogl);
        for (CarroAdversario o: this.carrosAdversarios) {
            o.desenhar(ogl);
        }
        this.carro.desenhar(ogl);
    }
}
