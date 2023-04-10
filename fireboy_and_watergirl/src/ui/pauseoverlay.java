package ui;

import estadosjuego.estadosdejuego;
import estadosjuego.jugando;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.juego;
import static utilz.constantes.ui.botonesdepausa.SOUND_SIZE;
import static utilz.constantes.ui.urmbotons.*;
import utilz.loadsave;


// esta es la clase del menu de pausa donde se juntan los botones de sonido y de opciones
public class pauseoverlay {
    
    private jugando jugando;
    private BufferedImage bgim ;
    private int bgx,bgy,bgw,bgh;
    private botonesdesonido musicbton,sfxbton;
    private urmboton  menuB,replayB, unpusedB;
    
    public pauseoverlay(jugando jugando) {        
        this.jugando = jugando;
        loadbg();
        createsoundbton();
        createurmboton();
    }
    
    //dibujamos por pantalla todo
    public void draw(Graphics g){
        //bg
        g.drawImage(bgim, bgx, bgy, null);
        //aqui se dibujan los botones de pausa
        musicbton.draw(g);
        sfxbton.draw(g);
        
        menuB.draw(g);
        replayB.draw(g);
        unpusedB.draw(g);
    }
    

    public void update(){
        //boton de musica
        musicbton.update();
        //boton de efectos de sonidos
        sfxbton.update();
        //botones de opciones  volver al menu, reiniciar el nivel , pausar el juego
        menuB.update();
        replayB.update();
        unpusedB.update();
    }
    
    
    private void createurmboton() {
        int menubx = (int) (313 * juego.SCALE);
        int replayBx = (int) (386*juego.SCALE);
        int unpausedx = (int) (462 * juego.SCALE);
        int by = (int) (325 * juego.SCALE);
        
        menuB = new urmboton(menubx, by, URM_SIZE,URM_SIZE, 2);
        replayB = new urmboton(replayBx, by, URM_SIZE,URM_SIZE, 1);
        unpusedB = new urmboton(unpausedx, by, URM_SIZE,URM_SIZE, 0);
        
    }
    
    
    
    
    
    
    //aqui se crean los botones de sonido y sfx
    private void createsoundbton() {
        int sx = (int)(450*juego.SCALE);
        int my =(int)(140 * juego.SCALE);
        int sfxy = (int)(184 * juego.SCALE);
        musicbton = new  botonesdesonido(sx, my,SOUND_SIZE, SOUND_SIZE);
        sfxbton = new  botonesdesonido(sx, sfxy,SOUND_SIZE, SOUND_SIZE);
    }

    
    //cargamos el  bg del menu de apusa
    private void loadbg() {
        bgim = loadsave.GetSpriteAtlas(loadsave.PAUSE_BG);
        bgw = (int) (bgim.getWidth() * juego.SCALE);
        bgh = (int) (bgim.getHeight() * juego.SCALE);
        bgx  = juego.GAME_WIDTH / 2  - bgw / 2;
        bgy = (int) (25 * juego.SCALE);
    }

    
    
     
    public void mouseclick(MouseEvent e) {
        
    }

    //detectamos si presionaron los botones 
    public void mousepressed(MouseEvent e) {
        if(isIn(e,musicbton)){
            musicbton.setMousepresed(true);
        }else if (isIn(e,sfxbton)){
            sfxbton.setMousepresed(true);
        }
        else if(isIn(e,menuB)){
            menuB.setMousepresed(true);
        }
        else if(isIn(e,replayB)){
            replayB.setMousepresed(true);
        }        
        else if(isIn(e,unpusedB)){
            unpusedB.setMousepresed(true);
        }
        
        
        
    }

    
    //detectamos si los soltaron
    public void mousereleased(MouseEvent e) {
        if(isIn(e,musicbton)){            
            if(musicbton.isMousepresed()){
                musicbton.setMuted(!musicbton.isMuted());
            }
        }
        
        
        else if (isIn(e,sfxbton)){
            if(sfxbton.isMousepresed()){
                sfxbton.setMuted(!sfxbton.isMuted());
            }
        }
        
            else if (isIn(e,menuB)){
            if(menuB.isMousepresed()){
                estadosdejuego.estado = estadosdejuego.MENU;
                jugando.unpausedgame();
            }
        }
        
            else if (isIn(e,replayB)){
            if(replayB.isMousepresed()){
                jugando.resetall();
                jugando.unpausedgame();
            }
        }
            
        else if (isIn(e,unpusedB)){
            if(unpusedB.isMousepresed()){
                jugando.unpausedgame();
            }
        }
    
        
        
        musicbton.resetbools();
        sfxbton.resetbools();
        menuB.resetbools();
        replayB.resetbools();
        unpusedB.resetbools();
        
    }
    
    
    

    //detectamos si el raton paso por encima del boton
    public void mousemoved(MouseEvent e) {
        musicbton.setMouseover(false);
        sfxbton.setMouseover(false);
        menuB.setMouseover(false);
        replayB.setMouseover(false);
        unpusedB.setMouseover(false);
        
    
        if(isIn(e,musicbton)){
            musicbton.setMouseover(true);
        }else if (isIn(e,sfxbton)){
            sfxbton.setMouseover(true);
        }
        
        else if (isIn(e,menuB)){
            menuB.setMouseover(true);
        }
        else if (isIn(e,replayB)){
            replayB.setMouseover(true);
        }
        else if (isIn(e,unpusedB)){
            unpusedB.setMouseover(true);
        }
        
    }

    //metodo para saber si el raton esta encima del boton
    private boolean  isIn(MouseEvent e,pausebutton b){
        return b.getBounds().contains(e.getX(),e.getY());        
    }

  
 
  

    
}
