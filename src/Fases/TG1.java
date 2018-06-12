package Fases;

import Turbine.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TG1 extends Fase {
    private Texturas texturas; // mapa de texturas
    private Ceu ceu; // skybox
    private Camera camera; // câmera
    private Carro carro; // carro do jogador
    private ArrayList<CarroAdversario> carrosAdversarios; // vetor de carros adversários
    private ArrayList<Obstaculo> predios; // vetor de prédios
    private Obstaculo chegada; // linha de chegada
    private ArrayList<Obstaculo> chao; // vetor de pedaços de chão
    private boolean colide; // guarda se o carro colidiu com algum adversário
    
    public TG1() {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.carro = new Carro();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.carrosAdversarios = new ArrayList<>();
        this.chao = new ArrayList<>();
        this.predios = new ArrayList<>();
        this.colide = false;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("chegada", diretorioRaiz + "/src/turbine/Arquivos/chegada.png");
        this.texturas.carregarTextura("predio", diretorioRaiz + "/src/turbine/Arquivos/predio.jpg");
        this.texturas.carregarTextura("ceu", diretorioRaiz + "/src/turbine/Arquivos/ceu.jpg");
        this.texturas.carregarTextura("pista", diretorioRaiz + "/src/turbine/Arquivos/pista.png");
        this.texturas.carregarTextura("carrovermelho", diretorioRaiz + "/src/turbine/Arquivos/carrovermelho.png");
        this.texturas.carregarTextura("carroverde", diretorioRaiz + "/src/turbine/Arquivos/carroverde.png");
        this.texturas.carregarTextura("carrobranco", diretorioRaiz + "/src/turbine/Arquivos/carrobranco.png");
        this.texturas.carregarTextura("carroroxo", diretorioRaiz + "/src/turbine/Arquivos/carroroxo.png");
        
        // vetor de possíveis texturas que um carro adversário pode ter
        String carrosaleatorios[] = {"carrovermelho", "carroverde", "carrobranco", "carroroxo"};
        
        // define a posição inicial da câmera
        this.camera.local.z = 0d;

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("ceu"));
        this.ceu.setCor(Color.white);
        
        // carrega a carro
        this.carro.setLocal(new Ponto(0d, 0d, 0d));
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

        // gera os adversários
        for(int i = 0; i < 50; i++) {
            CarroAdversario obstaculo = new CarroAdversario();
            
            obstaculo.setLocal(new Ponto(
                    ThreadLocalRandom.current().nextDouble(-10d, 10d), 
                    0d, 
                    -i * 5d)); // local aleatório mas relativamente próximo
            obstaculo.atualizarForma();
            obstaculo.getForma().setDimensoes(new Ponto(1.5d, 1d, 0d));
            obstaculo.getForma().setTextura(this.texturas.getTextura(carrosaleatorios[new Random().nextInt(4)])); // atribui uma textura de carro aleatória para este adversário
            obstaculo.getForma().setCor(Color.white);
            obstaculo.setDirecao(new Ponto(ThreadLocalRandom.current().nextDouble(-0.2d, 0.2d), 0d, -1d)); // define uma direção aleatória entre -0.1 e 0.2 no eixo x (para que ele fique andando lateralmente)
            obstaculo.setVelocidade(ThreadLocalRandom.current().nextDouble(35d, 45d)); // define uma velocidade aleatória entre 35m/s e 45m/s
            obstaculo.setIntensidadeTurbo(100d); // dá um impulso inicial (para que os adversários saiam na frente)
            obstaculo.atualizarColisor();
            obstaculo.getColisor().setDimensoes(new Ponto(1.5d, 1d, 1d));
            
            this.carrosAdversarios.add(obstaculo);
        }
        
        // carrega o chão
        for(int i = 0; i < 100; i++) {
            Obstaculo obstaculo = new Obstaculo();
            
            obstaculo.setLocal(new Ponto(
                    30d, 
                    5d, 
                    -i * 50d));
            obstaculo.atualizarForma();
            obstaculo.getForma().setDimensoes(new Ponto(3d, 10d, 5d));
            obstaculo.getForma().setTextura(this.texturas.getTextura("predio"));
            obstaculo.getForma().setCor(Color.white);
            obstaculo.setDirecao(new Ponto());
            obstaculo.setVelocidade(0d);
            obstaculo.atualizarColisor();
            obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
            
            this.predios.add(obstaculo);
            
            obstaculo = new Obstaculo();
            
            obstaculo.setLocal(new Ponto(
                    -30d, 
                    5d, 
                    -i * 50d));
            obstaculo.atualizarForma();
            obstaculo.getForma().setDimensoes(new Ponto(3d, 10d, 5d));
            obstaculo.getForma().setTextura(this.texturas.getTextura("predio"));
            obstaculo.getForma().setCor(Color.white);
            obstaculo.setDirecao(new Ponto());
            obstaculo.setVelocidade(0d);
            obstaculo.atualizarColisor();
            obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
            
            this.predios.add(obstaculo);
        }
        
        // carrega a linha de chegada
        this.chegada.setLocal(new Ponto(0d, 0d, -5000d));
        this.chegada.atualizarForma();
        this.chegada.getForma().setDimensoes(new Ponto(20d, 20d, 10d));
        this.chegada.getForma().setTextura(this.texturas.getTextura("chegada"));
        this.chegada.getForma().setCor(Color.white);
        this.chegada.setDirecao(new Ponto());
        this.chegada.setVelocidade(0d);
        this.chegada.atualizarColisor();
        this.chegada.getColisor().setDimensoes(this.chegada.getForma().getDimensoes());
        
        for(int i = 0; i < 200; i++) {
            // carrega o chão
            Obstaculo chao = new Obstaculo();
            chao.setLocal(new Ponto(0d, -3d, -40d * i));
            chao.atualizarForma();
            chao.getForma().setDimensoes(new Ponto(40d, 2d, 20d));
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
        
        // calcula a posição de corrida do carro
        int pos = 1;
        for (CarroAdversario o: this.carrosAdversarios) {
            if (o.getLocal().z < this.carro.getLocal().z)
                pos++;
        }
        // manda a posição calculada para io hud do carro
        ((Texto) this.carro.getHud()).setTexto(((Texto) this.carro.getHud()).getTexto() + "\nP: " + pos);
        
        // movimenta os adversários e verifica as colisões
        for (CarroAdversario o: this.carrosAdversarios) {
            o.movimentar(controle, deltaTempo);
            o.manterInercia(deltaTempo);
            o.aplicarGravidade(0.1d, deltaTempo);
            o.limitarAreaMovimento(new Ponto(-10d, 0d, 0d), new Ponto(10d, 10d, 0d));
            o.atualizarHud();
            
            // verifica se o carro colide com este adversário
            if (!this.colide) {
                if (this.carro.getColisor().colideCom(o.getColisor())) {
                    this.colide = true;
                    
                    // caso colida, adiciona impulso positivo ao carro da frente e negativo ao carro de trás (o carro que estiver à frente ganha 30 de impulso e o de trás perde 30 de impulso)
                    if (this.carro.getLocal().z > o.getLocal().z) {
                            this.carro.setIntensidadeTurbo(-30d);
                            o.setIntensidadeTurbo(30d);
                        } else {
                            o.setIntensidadeTurbo(-30d);
                            this.carro.setIntensidadeTurbo(30d);
                        }   
                }
            }
        }
        
        // verifica se o carro não colide mais com ninguém
        if (this.colide) {
            // supõe que o carro não colide mais com ninguém
            boolean nao_colide_mais = true;
            // procura em todos os adversários
            for (CarroAdversario o: this.carrosAdversarios) {
                // se o carro colide com esse adversário
                if (this.carro.getColisor().colideCom(o.getColisor())) {
                    // nega a suposição e quebra o laço
                    nao_colide_mais = false;
                    break;
                }
            }   
            
            // caso ele realmente não colida mais
            if (nao_colide_mais)
                // repassa essa informação para o controle
                this.colide = false;
        }
        
        // realiza as colisões entre os carros adversários
        int i = 0;
        for (CarroAdversario o: this.carrosAdversarios) {
            // compara um certo carro adversário com todos os outros, mas compara apenas de i para frente (para evitar comparar os mesmos carros duas vezes)
            for (CarroAdversario p: this.carrosAdversarios.subList(i, this.carrosAdversarios.size())) {
                // caso os carros sejam diferentes (para não comparar o carro com ele mesmo)
                if (o != p) {
                    // testa a colisão e, caso colida, adiciona impulso positivo ao carro da frente e negativo ao carro de trás (o carro que estiver à frente ganha 30 de impulso e o de trás perde 30 de impulso)
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
            
            i++;
        }
        
        // verifica se o fim da fase foi alcançado
        if (!this.colide) {
            if (this.carro.getColisor().colideCom(this.chegada.getColisor())) {
                this.colide = true; // guarda essa informação
                this.carro.setLocal(new Ponto(0d, 0d, this.chegada.getLocal().z - this.chegada.getForma().getDimensoes().z  - 1d)); // some com a nave da tela
                this.camera.anexarObjeto(this.chegada); // passa a câmera para a linha de chegada
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
        
        for (Obstaculo o: this.predios) {
            o.desenhar(ogl);
        }
        
        this.carro.desenhar(ogl);
    }
}
