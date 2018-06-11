package Fases;

import Turbine.Texturas;
import Turbine.Nave2;
import Turbine.OGL;
import Turbine.Ponto;
import Turbine.Ceu;
import Turbine.Obstaculo;
import Turbine.Camera;
import Turbine.Controle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Dois extends Fase {
    private Texturas texturas;
    private Ceu ceu;
    private Camera camera;
    private Nave2 nave;
    private ArrayList<Obstaculo> obstaculos;
    private Obstaculo chegada;
    private Obstaculo chao;
    private boolean colide;
    
    public Dois() {
        this.texturas = new Texturas();
        this.camera = new Camera();
        this.nave = new Nave2();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.obstaculos = new ArrayList<>();
        this.chao = new Obstaculo();
        this.colide = false;
    }
    
    // carrega todos os elementos da fase
    public void carregar(String diretorioRaiz) {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("maquina", diretorioRaiz + "/src/turbine/Arquivos/maquina.jpg");
        this.texturas.carregarTextura("chegada", diretorioRaiz + "/src/turbine/Arquivos/chegada.png");
        this.texturas.carregarTextura("abstrato", diretorioRaiz + "/src/turbine/Arquivos/abstrato.jpg");
        this.texturas.carregarTextura("azulgelo", diretorioRaiz + "/src/turbine/Arquivos/azulgelo.png");
        this.texturas.carregarTextura("ceu", diretorioRaiz + "/src/turbine/Arquivos/ceu.jpg");
        
        // define a posição inicial da câmera
        this.camera.local.z = 800d;

        // carrega o skybox
        this.ceu.setTextura(this.texturas.getTextura("maquina"));
        this.ceu.setCor(Color.white);
        
        // carrega a nave
        this.nave.setLocal(new Ponto(0d, 0d, 500d));
        this.nave.atualizarForma();
        this.nave.getForma().setDimensoes(new Ponto(1d, 0.1d, 1d));
        this.nave.getForma().setTextura(this.texturas.getTextura("abstrato"));
        this.nave.getForma().setCor(Color.white);
        this.nave.setDirecao(new Ponto(0d, 0d, -1d));
        this.nave.setVelocidade(300d); //1080Km/h
        this.nave.atualizarColisor();
        this.nave.getColisor().setDimensoes(nave.getForma().getDimensoes()); // faz o colisor e a forma terem o mesmo tamanho
        
        // anexa a nave na câmera
        this.camera.anexarObjeto(nave);

        // gera os obstáculos
        for(int i = 0; i < 150; i++) {
            Obstaculo obstaculo = new Obstaculo();
            
            obstaculo.setLocal(new Ponto(
                    ThreadLocalRandom.current().nextDouble(-100d, 100d), 
                    50d, 
                    -i * 100d));
            obstaculo.atualizarForma();
            obstaculo.getForma().setDimensoes(new Ponto(10d, 100d, 10d));
            obstaculo.getForma().setTextura(this.texturas.getTextura("azulgelo"));
            obstaculo.getForma().setCor(Color.white);
            obstaculo.setDirecao(new Ponto());
            obstaculo.setVelocidade(0d);
            obstaculo.atualizarColisor();
            obstaculo.getColisor().setDimensoes(obstaculo.getForma().getDimensoes());
            
            this.obstaculos.add(obstaculo);
        }
        
        // carrega a linha de chegada
        this.chegada.setLocal(new Ponto(0d, 50d, -15000d));
        this.chegada.atualizarForma();
        this.chegada.getForma().setDimensoes(new Ponto(100d, 100d, 10d));
        this.chegada.getForma().setTextura(this.texturas.getTextura("chegada"));
        this.chegada.getForma().setCor(Color.white);
        this.chegada.setDirecao(new Ponto());
        this.chegada.setVelocidade(0d);
        this.chegada.atualizarColisor();
        this.chegada.getColisor().setDimensoes(this.chegada.getForma().getDimensoes());
        
        // carrega o chão
        this.chao.setLocal(new Ponto(0d, -3d, -7500d));
        this.chao.atualizarForma();
        this.chao.getForma().setDimensoes(new Ponto(100d, 2d, 15000d));
        this.chao.getForma().setTextura(this.texturas.getTextura("ceu"));
        this.chao.getForma().setCor(Color.white);
        this.chao.setDirecao(new Ponto());
        this.chao.setVelocidade(0d);
        this.chao.atualizarColisor();
        this.chao.getColisor().setDimensoes(this.chao.getForma().getDimensoes());
    }
    
    // roda a física e a lógica
    public void atualizar(Double deltaTempo, Controle controle) {
        // roda a física
        this.nave.movimentar(controle, deltaTempo);
        this.nave.manterInercia(deltaTempo);
        this.nave.aplicarGravidade(0.1d, deltaTempo);
        this.nave.limitarAreaMovimento(new Ponto(-100d, 0d, 0d), new Ponto(100d, 100d, 0d));
        this.nave.atualizarHud();
        
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
        
        this.defineIluminacao(ogl);
        
         // desenha todos os objetos
        this.nave.desenhar(ogl);
        this.chao.desenhar(ogl);
        this.chegada.desenhar(ogl);
        for (Obstaculo o: this.obstaculos) {
            o.desenhar(ogl);
        }
    }
    
    public void defineIluminacao(OGL ogl) {
//        // Habilita o modelo de coloriza��o de Gouraud
//        //gl.glShadeModel(GL2.GL_SMOOTH);
//        ogl.gl.glShadeModel(ogl.gl.GL_SMOOTH);
//
//        //Luz ambiente
//        //Define os par�metros atrav�s de vetores RGBA
//        float luzAmbiente[] = {1.0f, 1.0f, 1.0f, 0.5f};
//        ogl.gl.glLightModelfv(ogl.gl.GL_LIGHT_MODEL_TWO_SIDE, luzAmbiente, 0);
//
//        //Define o tipo de	 reflex�o dos objetos
//        //gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_SPECULAR);
//        ogl.gl.glColorMaterial(ogl.gl.GL_FRONT, ogl.gl.GL_DIFFUSE);
//
//        //Define os par�metros da luz de n�mero 0
//        float posicaoLuz[] = {0, 0, 0, 1}; // �ltimo par�metro: 0-direcional, 1-pontual/posicional 
//        float luzDifusa[] = {1.0f, 1.0f, 1.0f, 1.0f}; //define cor  
//        float luzEspecular[] = {1.0f, 1.0f, 1.0f, 1.0f}; // define brilho
//        //ogl.gl.glLightfv(ogl.gl.GL_LIGHT0, ogl.gl.GL_AMBIENT, luzAmbiente, 0);
//        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0 );
//        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
//        ogl.gl.glLightfv(ogl.gl.GL_LIGHT0, ogl.gl.GL_POSITION, posicaoLuz, 0);
//
//        //Define os par�metros da luz de n�mero 1
//        float posicaoLuz1[] = {-10.0f, 10.0f, -100.0f, 0.0f}; // �ltimo par�metro: 0-direcional, 1-pontual/posicional 
//        float luzEspecular1[] = {1.0f, 1.0f, 1.0f, 1.0f}; //define brilho
//        float luzDifusa1[] = {1.0f, 1.0f, 1.0f, 1.0f}; //define cor
//
//        //ogl.gl.glLightfv(ogl.gl.GL_LIGHT1, ogl.gl.GL_AMBIENT, luzAmbiente, 0);
//        //gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, luzDifusa1, 0 );
//        //gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, luzEspecular1, 0);
//
//        // Brilho do material
//        float especularidade[] = {1.0f, 0.5f, 1.0f, 1.0f};
//        int especMaterial = 10;
//
//        // Define a reflect�ncia do material 
//        ogl.gl.glMaterialfv(ogl.gl.GL_FRONT, ogl.gl.GL_SPECULAR, especularidade, 0);
//        // Define a concentra��o do brilho
//        ogl.gl.glMateriali(ogl.gl.GL_FRONT, ogl.gl.GL_SHININESS, especMaterial);
//
//        ogl.gl.glEnable(ogl.gl.GL_LIGHT0);
//        ogl.gl.glEnable(ogl.gl.GL_LIGHT1);
//        ogl.gl.glEnable(ogl.gl.GL_LIGHTING);
//        ogl.gl.glEnable(ogl.gl.GL_COLOR_MATERIAL);

        ogl.gl.glEnable(ogl.gl.GL_LIGHTING); 
        ogl.gl.glEnable(ogl.gl.GL_LIGHT0);
        ogl.gl.glEnable(ogl.gl.GL_NORMALIZE);

        //float[] ambientLight = {0.1f, 0.1f, 0.1f, 0f};  // weak RED ambient 
        //ogl.gl.glLightfv(ogl.gl.GL_LIGHT0, ogl.gl.GL_AMBIENT, ambientLight, 0);

        float[] diffuseLight = {0.3f, 0.3f, 0.3f, 0f};  // multicolor diffuse 
        ogl.gl.glLightfv(ogl.gl.GL_LIGHT0, ogl.gl.GL_DIFFUSE, diffuseLight, 0);
    }
}
