package estadosjuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import ui.botonmenu;
import utilz.loadsave;

/**
 * en esta clase se arma el menu  hereda de estado y implementa interfasestado
 * @author Admin
 */
public class menu extends estado implements interfasestado {

    private botonmenu[ ] boton = new botonmenu[4];
    private BufferedImage bgimg;
    private int menux,menuy,menuw,menuh;
    
    public menu(main.juego juego) {
        super(juego);
        
        loadbotons();
        loadbg();
    }
    private void loadbotons() {
        
        boton[0] = new botonmenu(juego.GAME_WIDTH/2,(int)(225 * juego.SCALE),3,estadosdejuego.SELECCION);
        boton[1] = new botonmenu(juego.GAME_WIDTH/2,(int)(260 * juego.SCALE),0,estadosdejuego.COMOJUGAR);
        boton[2] = new botonmenu(juego.GAME_WIDTH/2,(int)(340 * juego.SCALE),2,estadosdejuego.SALIR);
        boton[3] = new botonmenu(juego.GAME_WIDTH/2,(int)(300 * juego.SCALE),1,estadosdejuego.CREDITOS);                
    }
    
    
    private void loadbg() {
    
        bgimg = loadsave.GetSpriteAtlas(loadsave.MENU_BACKGROUND);
        menuw = (int)(bgimg.getWidth() * juego.SCALE);
        menuh = (int)(bgimg.getHeight() * juego.SCALE);
        menux = juego.GAME_WIDTH/2  - menuw /2;
        menuy = (int)(-60*juego.SCALE);
    }

    
    @Override
    public void update() {
        for(botonmenu mb : boton){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bgimg, menux, menuy,menuw,menuh, null);
        for(botonmenu mb : boton){
            mb.draw(g);
        }
        
    }

    @Override
    public void mouseclick(MouseEvent e) {
       
    }

    @Override
    public void mousepressed(MouseEvent e) {
        for(botonmenu mb : boton){
            if(isIn(e,mb)){
                mb.setMousepreesed(true);
                break;
            }
        }
        
    }

    @Override
    public void mousereleased(MouseEvent e) {
            for(botonmenu mb : boton){
            if(isIn(e,mb)){
                if(mb.isMousepreesed()){
                    mb.aplicarestadodejuego();
                    break;
                }
            }
        }
        resetbotones();
    }

    @Override
    public void mousemoved(MouseEvent e) {
        for(botonmenu mb : boton){
            mb.setMouseover(false);
        }
        
        for(botonmenu mb : boton){
            if(isIn(e,mb)){
                mb.setMouseover(true);
            }
        }
        
        
    }

    @Override
    public void keypreseed(KeyEvent e) {
  
    }

    @Override
    public void keyreleased(KeyEvent e) {
        
    }

    private void resetbotones() {
          for(botonmenu mb : boton){
            mb.resetbools();
        }         
    }

}
