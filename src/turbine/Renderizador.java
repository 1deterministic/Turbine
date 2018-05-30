package turbine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Renderizador extends MouseAdapter implements GLEventListener, KeyListener {
    private String diretorioRaiz;
    
    // atributos comuns
    private OGL ogl;
    private Texturas texturas;
    private Relogio relogio;
    private Controle controle;
    private Ceu ceu;
    private Camera camera;
    private Nave nave;
    private ArrayList<Obstaculo> obstaculos;
    private Obstaculo chegada;
    
    private boolean colide = false;

    public Renderizador() {
        this.diretorioRaiz = System.getProperty("user.dir"); // guarda o caminho da raiz do executável
        
        // todos os atributos comuns são pré alocados mas serão sobrescritos no carregamento da fase escolhida
        this.ogl = new OGL();
        this.relogio = new Relogio();
        this.texturas = new Texturas();
        this.controle = new Controle();
        this.camera = new Camera();
        this.nave = new Nave();
        this.ceu = new Ceu();
        this.chegada = new Obstaculo();
        this.obstaculos = new ArrayList<>();
    }
    
    public void carregaFaseUm() {
        // carrega todas as texturas necessárias
        this.texturas.carregarTextura("madeira", this.diretorioRaiz + "/src/turbine/Arquivos/madeira.jpg");
        this.texturas.carregarTextura("chegada", this.diretorioRaiz + "/src/turbine/Arquivos/chegada.jpg");
        this.texturas.carregarTextura("ceu", this.diretorioRaiz + "/src/turbine/Arquivos/ceu.jpg");
        
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
        this.nave.setVelocidade(250d); //300Km/h
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
    }
 
    public void init(GLAutoDrawable drawable) {
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        this.ogl.gl.glEnable(GL.GL_TEXTURE_2D);
        
        // carrega a fase correta
        this.carregaFaseUm();
        
        // inicia o relógio
        this.relogio.update();
    }

    public void display(GLAutoDrawable drawable) {
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();

        // atualiza o relógio 
        this.relogio.update();
        
        // desenha o skybox
        this.ceu.desenhar(this.ogl);
        
        // debug de controle
        //System.out.println(this.controle);

        // roda a física
        this.nave.movimentar(this.controle, this.relogio.getDeltaTempo());
        this.nave.limitarAreaMovimento(new Ponto(-100d, 0d, 0d), new Ponto(100d, 100d, 0d));
        this.nave.manterInercia(this.relogio.getDeltaTempo());
        
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
        this.camera.transicaoCamera(this.relogio.getDeltaTempo());
        this.camera.ajustaObservacao(this.ogl);

        // desenha todos os objetos
        this.nave.getForma().desenhar(this.ogl);
        this.chegada.getForma().desenhar(this.ogl);
        for (Obstaculo o: this.obstaculos) {
            o.getForma().desenhar(this.ogl);
        }
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
