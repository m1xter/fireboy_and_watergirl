package estadosjuego;

import java.awt.event.MouseEvent;
import main.juego;
import ui.boton_seleccion;
import ui.botonmenu;

/**
 * esta clase padre las clases que son estados de juego 
 * @author Admin
 */
public class estado {

    protected juego juego;
    
    public estado(juego juego) {
        this.juego = juego;
    }
    
    public boolean isIn(MouseEvent e,botonmenu mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }
    
    public boolean isIn(MouseEvent e,boton_seleccion mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }
    
  
    public juego getjuego(){
        return juego;
    }
    
    
    
}
