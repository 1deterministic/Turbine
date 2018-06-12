package Turbine;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Turbine {
    private Renderizador renderizador; // guarda a classe que lidará com o OpenGL

    // construtor executado no main
    public Turbine() {
        // cria a janela do jogo
        JFrame janela = new JFrame("NOME DO JOGO"); // cria a janela com o título
        janela.setSize(800, 600); // define o tamanho da janela
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // permite que a janela seja fechada no botão fechar
        BorderLayout layout = new BorderLayout();
        Container caixa = janela.getContentPane();
        caixa.setLayout(layout);

        // cria um objeto GLCapabilities para especificar o número de bits
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        caps.setRedBits(8);
        caps.setBlueBits(8);
        caps.setGreenBits(8);
        caps.setAlphaBits(8);

        // cria um canvas, adiciona na janela, e especifica o objeto "ouvinte"
        GLCanvas canvas = new GLCanvas(caps);
        janela.add(canvas, BorderLayout.CENTER);
        
        // cria o objeto que irá gerenciar os eventos
        renderizador = new Renderizador(canvas);
        janela.setVisible(true);
        canvas.requestFocus();
    }

    public static void main(String[] args) {
        // inicia o jogo
        Turbine t = new Turbine();
    }
}