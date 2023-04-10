package estadosjuego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
/**
 * esta interfas contiene todos los metodos que los estados de juego necesitan tener
 * @author Admin
 */
public interface interfasestado {
    
    
    public void update();
    public void draw(Graphics g);
    public void mouseclick(MouseEvent e);
    public void mousepressed(MouseEvent e);
    public void mousereleased(MouseEvent e);
    public void mousemoved(MouseEvent e);
    public void keypreseed(KeyEvent e);
    public void keyreleased(KeyEvent e);
    
    
    
}
