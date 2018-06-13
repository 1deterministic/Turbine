package Fases;

import Turbine.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Menu extends Fase {
    private Texturas texturas;
    private Ceu ceu;
    private Camera camera;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Texto> textos;
    private int ultimoSelecionado;
    private int selecionado;
    EscolhaFase escolhaFase;

    
    public Menu(EscolhaFase escolhaFase) {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.ceu = new Ceu();
        this.obstaculos = new ArrayList<>();
        this.textos = new ArrayList<>();
        this.ultimoSelecionado = 0;
        this.selecionado = 0;
        this.escolhaFase = escolhaFase;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("background", diretorioRaiz + "/src/turbine/Arquivos/Menu/background.png");
        this.texturas.carregarTextura("Um", diretorioRaiz + "/src/turbine/Arquivos/Menu/Um.png");
        this.texturas.carregarTextura("Dois", diretorioRaiz + "/src/turbine/Arquivos/Menu/Dois.png");
        this.texturas.carregarTextura("Tres", diretorioRaiz + "/src/turbine/Arquivos/Menu/Tres.png");
        this.texturas.carregarTextura("TG1", diretorioRaiz + "/src/turbine/Arquivos/Menu/TG1.png");
        this.texturas.carregarTextura("DLC", diretorioRaiz + "/src/turbine/Arquivos/Menu/DLC.png");

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("background"));
        this.ceu.setCor(Color.white);
        
        Obstaculo obstaculo;
        Texto t;

        // opção de menu da fase Um
        obstaculo = new Obstaculo();
        obstaculo.setLocal(new Ponto(0d, 0d, 0d));
        obstaculo.atualizarForma();
        obstaculo.getForma().setDimensoes(new Ponto(1d, 0.5d, 0.01d));
        obstaculo.getForma().setTextura(this.texturas.getTextura("Um"));
        obstaculo.getForma().setCor(Color.white);
        obstaculo.setDirecao(new Ponto());
        obstaculo.setVelocidade(0d);
        obstaculo.atualizarColisor();
        obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
        this.obstaculos.add(obstaculo);
        t = new Texto();
        t.setTexto("80's neon", Color.white);
        t.setLocal(new Ponto(0d, 1d, 0d));
        t.setDimensoes(new Ponto(0.001d, 0.001d, 0.001d));
        this.textos.add(t);
        
        
        // opção de menu da fase Dois
        obstaculo = new Obstaculo();
        obstaculo.setLocal(new Ponto(2d, 0d, -0.5d));
        obstaculo.atualizarForma();
        obstaculo.getForma().setDimensoes(new Ponto(1d, 0.5d, 0.01d));
        obstaculo.getForma().setTextura(this.texturas.getTextura("Dois"));
        obstaculo.getForma().setCor(Color.white);
        obstaculo.setDirecao(new Ponto());
        obstaculo.setVelocidade(0d);
        obstaculo.atualizarColisor();
        obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
        this.obstaculos.add(obstaculo);
        t = new Texto();
        t.setTexto("Dois", Color.white);
        t.setLocal(new Ponto(2d, 1d, -0.5d));
        t.setDimensoes(new Ponto(0.001d, 0.001d, 0.001d));
        this.textos.add(t);
        
        
        // opção de menu da fase TG1
        obstaculo = new Obstaculo();
        obstaculo.setLocal(new Ponto(4d, 0d, -1d));
        obstaculo.atualizarForma();
        obstaculo.getForma().setDimensoes(new Ponto(1d, 0.5d, 0.01d));
        obstaculo.getForma().setTextura(this.texturas.getTextura("TG1"));
        obstaculo.getForma().setCor(Color.white);
        obstaculo.setDirecao(new Ponto());
        obstaculo.setVelocidade(0d);
        obstaculo.atualizarColisor();
        obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
        this.obstaculos.add(obstaculo);
        t = new Texto();
        t.setTexto("Beat Ritchie", Color.white);
        t.setLocal(new Ponto(4d, 1d, -1d));
        t.setDimensoes(new Ponto(0.001d, 0.001d, 0.001d));
        this.textos.add(t);
        
        // opção de menu da fase Tres
        obstaculo = new Obstaculo();
        obstaculo.setLocal(new Ponto(6d, 0d, -1.5d));
        obstaculo.atualizarForma();
        obstaculo.getForma().setDimensoes(new Ponto(1d, 0.5d, 0.01d));
        obstaculo.getForma().setTextura(this.texturas.getTextura("Tres"));
        obstaculo.getForma().setCor(Color.white);
        obstaculo.setDirecao(new Ponto());
        obstaculo.setVelocidade(0d);
        obstaculo.atualizarColisor();
        obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
        this.obstaculos.add(obstaculo);
        t = new Texto();
        t.setTexto("Matrix Flight", Color.white);
        t.setLocal(new Ponto(6d, 1d, -1.5d));
        t.setDimensoes(new Ponto(0.001d, 0.001d, 0.001d));
        this.textos.add(t);
        
        // opção de menu da DLC
        obstaculo = new Obstaculo();
        obstaculo.setLocal(new Ponto(8d, 0d, -2d));
        obstaculo.atualizarForma();
        obstaculo.getForma().setDimensoes(new Ponto(1d, 0.5d, 0.01d));
        obstaculo.getForma().setTextura(this.texturas.getTextura("DLC"));
        obstaculo.getForma().setCor(Color.white);
        obstaculo.setDirecao(new Ponto());
        obstaculo.setVelocidade(0d);
        obstaculo.atualizarColisor();
        obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
        this.obstaculos.add(obstaculo);
        t = new Texto();
        t.setTexto("DLC", Color.white);
        t.setLocal(new Ponto(8d, 1d, -2d));
        t.setDimensoes(new Ponto(0.001d, 0.001d, 0.001d));
        this.textos.add(t);
        
        // anexa a nave na câmera
        this.camera.anexarObjeto(this.obstaculos.get(this.selecionado));
    }
    
    // roda a física e a lógica
    public void atualizar(Double deltaTempo, Controle controle) {
        // caso seja recebido o comando para passar para a opção da direita
        if (controle.direita) {
            // só permite selecionar a próxima opção caso a última mudança não tenha sido para a direita
            // isso impede que o menu fique trocando rápido demais
            if (this.ultimoSelecionado != 1)
                // seleciona a próxima opção do menu ou retorna para a primeira
                this.selecionado = Math.floorMod((this.selecionado + 1), this.obstaculos.size());
            // guarda que a última mudança foi para a direita
            this.ultimoSelecionado = 1;
        }
        // caso seja recebido o comando para passar para a opção da esquerda
        else if (controle.esquerda)  {
            // só permite selecionar a opção anterior caso a última mudança não tenha sido para a esquerda
            // isso impede que o menu fique trocando rápido demais
            if (this.ultimoSelecionado != -1)
                // seleciona a opção anterior do menu ou retorna para a última
                this.selecionado = Math.floorMod((this.selecionado - 1), this.obstaculos.size());
            // guarda que a última mudança foi para a esquerda
            this.ultimoSelecionado = -1;
        }
        // caso nenhuma tenha sido recebida
        else
            // reinicia a última opção
            this.ultimoSelecionado = 0;
        
        // caso tenha recebido o comando de confirmação
        if (controle.turbo) {
            // indica que a fase deve mudar
            this.escolhaFase.setMudar(true);
            
            // instancia a nova fase e reinicia o controle
            switch(this.selecionado) {
                case 0: this.escolhaFase.setFase(new Um()); controle.resetarControle(); break;
                case 1: this.escolhaFase.setFase(new Dois()); controle.resetarControle(); break;
                case 2: this.escolhaFase.setFase(new TG1()); controle.resetarControle(); break;
                case 3: this.escolhaFase.setFase(new Tres()); controle.resetarControle(); break;
                case 4: controle.resetarControle(); break;
            }
        }
        
        // passa a câmera para a opção selecionada
        this.camera.anexarObjeto(this.obstaculos.get(this.selecionado));
        // atualiza a câmera
        this.camera.transicaoCamera(deltaTempo);
    }
    
    // desenha todos os elementos na tela
    public void desenhar(OGL ogl) {
        // desenha o skybox
        this.ceu.desenhar(ogl);
        
        // ajusta a observação para o novo local da câmera
        this.camera.ajustaObservacao(ogl);
        
        this.defineIluminacao(ogl);

        for (Obstaculo o: this.obstaculos) {
            o.desenhar(ogl);
        }
        
        for (Texto t: this.textos) {
            t.desenhar(ogl);
        }
    }
    
    public void defineIluminacao(OGL ogl) {}
}
