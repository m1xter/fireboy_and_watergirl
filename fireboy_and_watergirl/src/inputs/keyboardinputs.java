package inputs;

import estadosjuego.estadosdejuego;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.panel;
import static utilz.constantes.direcciones.*;

//implementamos la interfas de Keylistener para manejar los controles del juego
public class keyboardinputs implements KeyListener{

    private panel panel ;
    
    public keyboardinputs(panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(estadosdejuego.estado){
            case MENU:
               //  panel.getjuego().getmenu().keypreseed(e);
                break;
            case SELECCION:
                  //panel.getjuego().getseleccion().keypreseed(e);
                break;
            case JUGAR:
               panel.getjuego().getjugando().keypreseed(e);
                break;
            default:                
                 break;
        }
    

    }

    
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(estadosdejuego.estado){
            case MENU:
                  //panel.getjuego().getmenu().keyreleased(e);
                break;
            case SELECCION:
                  //panel.getjuego().getseleccion().keyreleased(e);
                break;
            case JUGAR:
               panel.getjuego().getjugando().keyreleased(e);
                break;
            default:                
                 break;
        }
    }




    
}
