package ui;

import estadosjuego.estadosdejuego;
import estadosjuego.jugando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.juego;
import static utilz.constantes.ui.urmbotons.URM_SIZE;
import utilz.loadsave;

public class gameover_overlay {
    
    private jugando jugando;
    private BufferedImage img;
    private urmboton  menuB,replayB;
    private int bgx,bgy,bgw,bgh;
    
    public gameover_overlay(jugando jugando) {
        this.jugando = jugando;
        loadbg();
        createurmboton();
    }
 
    
    public void draw(Graphics g){
        g.drawImage(img, bgx, bgy,bgw,bgh, null);
        menuB.draw(g);
        replayB.draw(g);
    }
    
    public void update(){
        menuB.update();
        replayB.update();
    }
    
    public void Keypressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            jugando.resetall();
            estadosdejuego.estado  = estadosdejuego.MENU;
        }
    }

    private void loadbg() {
        img = loadsave.GetSpriteAtlas(loadsave.GAMEOVER);
        bgw = (int)(img.getWidth() * juego.SCALE);
        bgh = (int)(img.getHeight() * juego.SCALE);
        bgx = juego.GAME_WIDTH/2  - bgw /2;
        bgy = (int)(-60*juego.SCALE);
    }

    private void createurmboton() {
        int menubx = (int) (300 * juego.SCALE);
        int replayBx = (int) (500*juego.SCALE);        
        int by = (int) (270 * juego.SCALE);
        
        menuB = new urmboton(menubx, by, URM_SIZE,URM_SIZE, 2);
        replayB = new urmboton(replayBx, by, URM_SIZE,URM_SIZE, 1);
    }
    
   
       private boolean  isIn(urmboton b,MouseEvent e){
        return b.getBounds().contains(e.getX(),e.getY());        
    }
       
         public void mousemoved(MouseEvent e){
        replayB.setMouseover(false);
        menuB.setMouseover(false);
        
        if(isIn(menuB,e)){
            menuB.setMouseover(true);
        }
        else if(isIn(replayB,e)){
            replayB.setMouseover(true);
        }
        
    }
    
    public void mousepresed(MouseEvent e){
        
        
            if(isIn(menuB,e)){
            menuB.setMousepresed(true); 
        }
        else if(isIn(replayB,e)){
            replayB.setMousepresed(true);
        }
        
    }
    
    public void mousereleased(MouseEvent e){
            if(isIn(menuB,e)){
                if(menuB.isMousepresed()){
                    jugando.resetall();
                    estadosdejuego.estado = estadosdejuego.MENU;
                }
            
        }
        else if(isIn(replayB,e)){
            if(replayB.isMousepresed()){
                    jugando.resetall();
            }
        }    
            
            menuB.resetbools();
            replayB.resetbools();
    }
            

       

    
    
    
    
}
