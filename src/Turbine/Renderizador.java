package Turbine;

import Fases.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.MouseListener;

public class Renderizador implements GLEventListener, KeyListener, MouseListener {
    // canvas e animator
    private GLCanvas canvas;
    private FPSAnimator animator;
    
    // caminho para o diretório base do programa
    private String diretorioRaiz;
    
    // atributos comuns
    private OGL ogl;
    private Relogio relogio;
    private Controle controle;
    
    // contém o controle de mudança de fases e a fase ativa
    private EscolhaFase escolhaFase;
    
    // fase ativa
    private Fase fase;

    public Renderizador(GLCanvas canvas) {
        // guarda o canvas e adiciona os escutadores de eventos, mouse e teclado
        this.canvas = canvas;
        this.canvas.addGLEventListener(this);
        this.canvas.addMouseListener(this);
        this.canvas.addKeyListener(this);
        
        // inicia o animator a 60fps
        this.animator = new FPSAnimator(this.canvas, 60);
        this.animator.start();

        // guarda o caminho da raiz do projeto
        this.diretorioRaiz = System.getProperty("user.dir");
        
        // atributos comuns inicializados
        this.ogl = new OGL();
        this.relogio = new Relogio();
        this.controle = new Controle();
        
        // seta a escolha da fase para o valor padrão (fase menu)
        this.escolhaFase = new EscolhaFase();
        
        // carrega a fase ativa
        this.fase = this.escolhaFase.getFase();
    }
 
    public void init(GLAutoDrawable drawable) {
        // armazena no ogl os objetos de uso comum
        this.ogl.glDrawable = drawable;
        this.ogl.gl = drawable.getGL().getGL2();
        this.ogl.glu = new GLU();
        this.ogl.glut = new GLUT();
        
        // limpa o quadro e habilita o uso de texturas
        this.ogl.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.ogl.gl.glEnable(GL.GL_DEPTH_TEST);
        this.ogl.gl.glEnable(GL.GL_TEXTURE_2D);
        
        // carrega a fase
        this.fase.carregar(this.diretorioRaiz);
        
        // inicia o relógio
        this.relogio.update();
    }

    public void display(GLAutoDrawable drawable) {
        // verifica se foi escolhida uma fase diferente
        if (this.escolhaFase.isMudar()) {
            // caso tenha sido, carrega a nova fase e reinicia a variável de controle
            this.fase = this.escolhaFase.getFase();
            this.fase.carregar(this.diretorioRaiz);
            this.escolhaFase.setMudar(false);
            
            // reinicia o controle de tempo
            this.relogio = new Relogio();
            this.relogio.update();
        }
        
        this.ogl.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        this.ogl.gl.glLoadIdentity();

        // atualiza o relógio 
        this.relogio.update();
        
        // debug do controle
        //System.out.println(this.controle);
        
        // atualiza a física e a lógica da fase
        this.fase.atualizar(this.relogio.getDeltaTempo(), this.controle);
        
        // realiza todas as operações de desenho da fase
        this.fase.desenhar(this.ogl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }
    
    public void dispose(GLAutoDrawable arg0) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    // repassa os eventos de pressionamento de tecla para a classe controle
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
                
            case KeyEvent.VK_SPACE:
                this.controle.turbo = true; break;
        }
        
    }

    // repassa os eventos de liberação de tecla para a classe controle
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
                
            case KeyEvent.VK_SPACE:
                this.controle.turbo = false; break;
                
            case KeyEvent.VK_BACK_SPACE:
                this.escolhaFase.setFase(new Menu(this.escolhaFase)); this.escolhaFase.setMudar(true); break;
                
            case KeyEvent.VK_ESCAPE:
                this.animator.stop(); System.exit(0);
        }
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    // repassa os eventos de perssionamento de botão do mouse para a classe controle
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: this.controle.esquerda = true; break;
            case MouseEvent.BUTTON3: this.controle.direita = true; break;
            case MouseEvent.BUTTON2: this.controle.turbo = true; break;
        }
    }

    // repassa os eventos de liberação de botão do mouse para a classe controle
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: this.controle.esquerda = false; break;
            case MouseEvent.BUTTON3: this.controle.direita = false; break;
            case MouseEvent.BUTTON2: this.controle.turbo = false; break;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
