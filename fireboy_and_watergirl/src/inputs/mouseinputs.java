package inputs;

import estadosjuego.estadosdejuego;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.panel;

public class mouseinputs implements MouseListener,MouseMotionListener {
    private panel panel;
    
    public mouseinputs(panel panel) {
        this.panel = panel;
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
           switch(estadosdejuego.estado){
            case MENU:
                panel.getjuego().getmenu().mousepressed(e);
                break;
            case SELECCION:
                panel.getjuego().getseleccion().mousepressed(e);
                break;
            case CREDITOS:
                panel.getjuego().getcreditos().mousepressed(e);
                break;
            case COMOJUGAR:
                panel.getjuego().getcomojugar().mousepressed(e);
                break;
            case JUGAR:
                panel.getjuego().getjugando().mousepressed(e);
                break;
            default:                
                 break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
           switch(estadosdejuego.estado){
            case MENU:
                panel.getjuego().getmenu().mousereleased(e);
                break;
            case SELECCION:
                panel.getjuego().getseleccion().mousereleased(e);
                break;
            case CREDITOS:
                panel.getjuego().getcreditos().mousereleased(e);
                break;
            case COMOJUGAR:
                panel.getjuego().getcomojugar().mousereleased(e);
                break;
            case JUGAR:
                panel.getjuego().getjugando().mousereleased(e);
                break;
            default:                
                 break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
                 switch(estadosdejuego.estado){
            case MENU:
                panel.getjuego().getmenu().mousemoved(e);
                break;
            case SELECCION:
                panel.getjuego().getseleccion().mousemoved(e);
                break;
            case CREDITOS:
                panel.getjuego().getcreditos().mousemoved(e);
            case COMOJUGAR:
                panel.getjuego().getcomojugar().mousemoved(e);
                break;
            case JUGAR:
                panel.getjuego().getjugando().mousemoved(e);
                break;
            default:                
                 break;
        }
    }
    
}
