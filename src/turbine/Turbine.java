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
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("Turbine");
        frame.setSize(800, 600);
        frame.add(canvas);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new Turbine());
        canvas.setVisible(true);
    }

    public void init(GLAutoDrawable drawable) {

    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl2 = drawable.getGL().getGL2();
        this.desenhaTriangulo(gl2);
    }

    private void desenhaTriangulo(GL2 gl2) {
        gl2.glBegin(GL.GL_TRIANGLES);
        gl2.glColor3f(1f, 1f, 0f);
        gl2.glVertex2d(0, 0.7);
        gl2.glColor3f(1f, 0f, 0f);
        gl2.glVertex2d(-0.2, 0.4);
        gl2.glColor3f(0f, 0f, 1f);
        gl2.glVertex2d(0.2, 0.4);
        gl2.glEnd();
    }

    public void dispose(GLAutoDrawable arg0) {

    }

    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

    }
}
