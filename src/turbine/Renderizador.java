package turbine;

import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Renderizador extends MouseAdapter implements GLEventListener, KeyListener {
    private OGL ogl;
    
    private String root;
    
    private Texturas texturas;
    
    private Double rot = 0d;
    private Camera cam;
    private Objeto obj;
    private Esfera e ;
    private Cubo terreno;
    private Relogio relogio;
    private Controle controle;
    private Esfera planeta;
    private Cubo parede;
    private Colisor colisorteste;
    private ArrayList<Obstaculo> obstaculos;
    private boolean colide = false;
    private Obstaculo chegada;
    private Ceu ceu;
    

    public Renderizador() {
        this.ogl = new OGL();
        this.root = System.getProperty("user.dir"); // guarda o caminho da raiz do executável
        this.texturas = new Texturas();
        this.controle = new Controle();
    }

    public void init(GLAutoDrawable drawable) {
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        this.ogl.gl.glEnable(GL.GL_TEXTURE_2D);
        
        cam = new Camera();
        cam.local.z = 800d;
        
        this.texturas.carregarTextura("madeira", this.root + "/src/turbine/Arquivos/madeira.jpg");
        this.texturas.carregarTextura("abstrato", this.root + "/src/turbine/Arquivos/abstrato.jpg");
        this.texturas.carregarTextura("planeta", this.root + "/src/turbine/Arquivos/planeta.jpg");
        this.texturas.carregarTextura("chegada", this.root + "/src/turbine/Arquivos/chegada.jpg");
        this.texturas.carregarTextura("ceu", this.root + "/src/turbine/Arquivos/ceu.jpg");
        
        this.ceu = new Ceu(
          this.texturas.getTextura("ceu")
        );
        
        this.parede = new Cubo(
                new Ponto(0d, 0d, 0d),
                new Ponto(10d, 10d, 10d),
                new Ponto(0d, 0d, 0d),
                0d,
                this.texturas.getTextura("madeira"));
                
        this.parede.setTextura(this.texturas.getTextura("madeira"));
        
        this.colisorteste = new Colisor();
        this.colisorteste.setDimensoes(new Ponto(10d, 10d, 10d));
        
        obj = new Nave();
        obj.setLocal(new Ponto(0d, 0d, 500d));
        obj.setForma(new Cubo(
                new Ponto(0d, 0d, 0d),
                new Ponto(1d, 0.1d, 1d),
                new Ponto(0d, 0d, 0d),
                0d,
                this.texturas.getTextura("madeira")));
        
        
        obj.setDirecao(new Ponto(0d, 0d, -1d));
        obj.setVelocidade(250d); //300Km/h
        Colisor colisor = new Colisor();
        colisor.setDimensoes(obj.getForma().getDimensoes());
        obj.setColisor(colisor); // cria um colisor com as mesmas dimensões da forma
        obj.atualizarLocalForma();
        obj.atualizarLocalColisor();
        obj.getForma().setTextura(this.texturas.getTextura("madeira"));
        cam.anexarObjeto(obj);
        
        
        e = new Esfera(
                new Ponto(3d, 0d, -150d),
                new Ponto(2d, 2d, 2d),
                new Ponto(0d, 0d, 0d),
                0d,
                this.texturas.getTextura("abstrato"));
        
        terreno = new Cubo(
                new Ponto(0d, -10d, -450d),
                new Ponto(100d, 10000d, 1d),
                new Ponto(1d, 0d, 0d),
                90d,
                this.texturas.getTextura("abstrato"));
        terreno.rotacionar(90d, new Ponto(1d, 0d, 0d));

        
        planeta = new Esfera(
                new Ponto(2500d, 0d, -2500d),
                new Ponto(2000d, 2000d, 2000d),
                new Ponto(-1d, 0d, 0d),
                90d,
                this.texturas.getTextura("planeta"));

        
        this.obstaculos = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Double x = ThreadLocalRandom.current().nextDouble(-100d, 100d);
            Double y = ThreadLocalRandom.current().nextDouble(0d, 50d);
            Double z = i * 100d;
            
            this.obstaculos.add(new Obstaculo(
                new Ponto(x, y, -z),
                new Cubo(
                    new Ponto(x, y, -z),
                    new Ponto(10d, 100d, 10d),
                    new Ponto(0d, 0d, 0d),
                    0d,
                    this.texturas.getTextura("madeira")),
                new Ponto(),
                0d,
                new Colisor(
                    new Ponto(x, y, -z),
                    new Ponto(10d, 100d, 10d))
            ));
        }
        
        // trocar para uma classe própria
        this.chegada = new Obstaculo(
                new Ponto(0d, 50d, -10000d),
                new Cubo(
                    new Ponto(0d, 0d, -10000d),
                    new Ponto(100d, 100d, 10d),
                    new Ponto(0d, 0d, 0d),
                    0d,
                    this.texturas.getTextura("chegada")),
                new Ponto(),
                0d,
                new Colisor(
                    new Ponto(0d, 50d, -10000d),
                    new Ponto(100d, 100d, 10d))
            );
        
        // inicia o relógio
        this.relogio = new Relogio();
        this.relogio.update();
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();

        // atualiza o relógio 
        this.relogio.update();
        
        // desenha o skybox
        this.ceu.desenhar(this.ogl);
        
        //System.out.println(this.controle);

        // roda a física
        this.obj.movimentar(this.controle, this.relogio.getDeltaTempo());
        this.obj.limitarAreaMovimento(new Ponto(-100d, 0d, 0d), new Ponto(100d, 100d, 0d));
        this.obj.manterInercia(this.relogio.getDeltaTempo());
        
        // verifica as colisões
        for (Obstaculo o: this.obstaculos) {
            if (!this.colide) {
                if (this.obj.getColisor().colideCom(o.getColisor())) {
                    this.colide = true;
                    this.cam.anexarObjeto(o);
                    System.out.println(o.getLocal());
                }
            }
        }
        
        // verifica se o fim da fase foi alcançado
        if (!this.colide) {
            if (this.obj.getColisor().colideCom(this.chegada.getColisor())) {
                this.colide = true;
                this.cam.anexarObjeto(this.chegada);
                System.out.println("Venceu!");
            }
        }
        
        // atualiza a câmera
        this.cam.transicaoCamera(this.relogio.getDeltaTempo());
        this.cam.ajustaObservacao(this.ogl);


        // desenha todos os objetos
        this.planeta.desenhar(this.ogl);
        this.e.desenhar(this.ogl);
        this.terreno.desenhar(this.ogl);
        this.parede.desenhar(this.ogl);
        this.obj.getForma().desenhar(this.ogl);
        for (Obstaculo o: this.obstaculos) {
            o.getForma().desenhar(this.ogl);
        }
        this.chegada.getForma().desenhar(this.ogl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                this.controle.cima = true; break;
                
            case KeyEvent.VK_DOWN:
                this.controle.baixo = true; break;
                
            case KeyEvent.VK_RIGHT:
                this.controle.direita = true; break;
                
            case KeyEvent.VK_LEFT:
                this.controle.esquerda = true; break;
        }
        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                this.controle.cima = false; break;
                
            case KeyEvent.VK_DOWN:
                this.controle.baixo = false; break;
                
            case KeyEvent.VK_RIGHT:
                this.controle.direita = false; break;
                
            case KeyEvent.VK_LEFT:
                this.controle.esquerda = false; break;
        }
    }

    public void dispose(GLAutoDrawable arg0) {
    }
}
