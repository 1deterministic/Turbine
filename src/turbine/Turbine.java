/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turbine;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Turbine implements GLEventListener {

	public static void main(String[] args) {
		
		/*
		 * Inicialmente carrega um conjunto predefinido de configurações do OpenGL a ser utilizado durante
		 *  a execução da aplicação e cria uma nova tela
		 */
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        //Cria o frame correspondente à tela da aplicação
        Frame frame = new Frame("OpenGL");
        frame.setSize(800, 600);
        frame.add(canvas);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);

        //Adiciona um listener para o fechamento da janela
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        //Adiciona um listener de eventos OpenGL à aplicação criada
        canvas.addGLEventListener(new Turbine());
        canvas.setVisible(true);
    }

	@Override
	/** 
	 * Chamado imediatamente após o contexto OpenGL ter sido inicializado.
	 * Pode ser utilizado para a inicialização de configurações.
	 * */
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	}
	
	@Override
	/**
	 * Chamado para iniciar a renderização OpenGL pelo cliente.
	 * É o método que realmente desenha na tela.
	 */
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
		final GL2 gl2 = drawable.getGL().getGL2();
		
		this.desenhaTriangulo(gl2);
	}

	/*
	 * Método criado para desenhar um triângulo na tela.
	 */
	private void desenhaTriangulo(GL2 gl2) {
	       
        gl2.glBegin(GL.GL_TRIANGLES);
	        gl2.glColor3f(1f,1f,0f);
	        gl2.glVertex2d(0, 0.7);
	        gl2.glColor3f(1f,0f,0f);
	        gl2.glVertex2d(-0.2, 0.4);
	        gl2.glColor3f(0f,0f,1f);
	        gl2.glVertex2d(0.2, 0.4);
        gl2.glEnd();
        
    }

	@Override
	/**
	 * Método utilizado para notificar o listener para realizar a liberação de recursos, como memória
	 */
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	/**
	 * Chamado após o redimensionamento do componente ou da janela de visualização
	 */
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}	
}