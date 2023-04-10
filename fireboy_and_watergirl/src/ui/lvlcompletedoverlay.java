package ui;

import estadosjuego.estadosdejuego;
import estadosjuego.jugando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.juego;
import static utilz.constantes.ui.urmbotons.URM_SIZE;
import utilz.loadsave;

public class lvlcompletedoverlay {
    private jugando jugando;
    private urmboton menuB, next;
    private BufferedImage img;
    private String cant_ruby,cant_diamante,min,seg;
    private int bgx,bgy,bgw,bgh;
    
    public lvlcompletedoverlay(jugando jugando) {
        this.jugando = jugando;
        
        intimg();
        intbotons();
        
    }

    private void intimg() {
        img = loadsave.GetSpriteAtlas(loadsave.LVL_COMPLETED_BG);
        bgw = (int)(img.getWidth() * juego.SCALE);
        bgh = (int)(img.getHeight() * juego.SCALE);
        bgx = juego.GAME_WIDTH/2  - bgw /2;
        bgy = (int)(-60*juego.SCALE);
    }

    private void intbotons() {
         int menubx = (int) (255 * juego.SCALE);        
        int nextx = (int) (455 * juego.SCALE);
        int by = (int) (325 * juego.SCALE);        
        menuB = new urmboton(menubx, by, URM_SIZE,URM_SIZE, 2);        
        next = new urmboton(nextx, by, URM_SIZE,URM_SIZE, 0);
    }
    
    public void update(){
        next.update();
        menuB.update();
    }
    
    public void draw(Graphics g){
        g.drawImage(img, bgx, bgy,bgw,bgh, null);
        g.setColor(Color.white);       
        g.drawString(cant_ruby = Integer.toString(jugando.getCant_Rubys()), 300, 185);
        g.drawString(cant_diamante = Integer.toString(jugando.getCant_Diamante()), 300, 225);
        g.drawString(min = Integer.toString(jugando.getMin()), 300, 135);
        g.drawString(":", 310, 135);
        g.drawString(seg = Integer.toString(jugando.getSeg()), 320, 135);
        
        
        next.draw(g);
        menuB.draw(g);
    }
    
    private boolean isIn(urmboton b,MouseEvent e){
        return b.getBounds().contains(e.getX(),e.getY());
    }
    
    
    public void mousemoved(MouseEvent e){
        next.setMouseover(false);
        menuB.setMouseover(false);
        
        if(isIn(menuB,e)){
            menuB.setMouseover(true);
        }
        else if(isIn(next,e)){
            next.setMouseover(true);
        }
        
    }
    
    public void mousepresed(MouseEvent e){
        
        
            if(isIn(menuB,e)){
            menuB.setMousepresed(true); 
        }
        else if(isIn(next,e)){
            next.setMousepresed(true);
        }
        
    }
    
    public void mousereleased(MouseEvent e){
            if(isIn(menuB,e)){
                if(menuB.isMousepresed()){
                    jugando.resetall();
                    estadosdejuego.estado = estadosdejuego.MENU;
                }
            
        }
        else if(isIn(next,e)){
            if(next.isMousepresed()){
                jugando.loadnextlevel();
            }
        }    
            
            menuB.resetbools();
            next.resetbools();
    }
            

}
