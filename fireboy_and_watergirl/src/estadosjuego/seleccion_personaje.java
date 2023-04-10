package estadosjuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import ui.boton_seleccion;
import ui.botonmenu;
import utilz.constantes.ui.botones_seleccion.*;
import utilz.loadsave;



public class seleccion_personaje extends estado implements interfasestado {

  private JTextField textField1;
    private BufferedImage bgimg;
    private boton_seleccion fireboyB,watergirlB;
    private int menux,menuy,menuw,menuh;
    private jugando jugando;
    private boolean isfireboy= true,iswatergirl = false;
    
    public seleccion_personaje(main.juego juego,jugando jugando) {
        super(juego);
        this.jugando = jugando;
        loadbotons();
        loadbg();
        setnombre();
         
    }
    
    private void loadbotons() {  
         int fireboyBx = (int) (330 * juego.SCALE);
        int watergirlBx = (int) (550*juego.SCALE);        
        int y = (int) (325 * juego.SCALE);
        
        fireboyB = new boton_seleccion(fireboyBx, y, 1);
        watergirlB = new boton_seleccion(watergirlBx,y,0);
    }

    
    private void setnombre(){
         textField1 = new JTextField( 10 );
         textField1.setBounds(200, 200, 100, 30);
         
    }
    
    
    private void loadbg() {
    
        bgimg = loadsave.GetSpriteAtlas(loadsave.SELECCION_BG);
        menuw = (int)(bgimg.getWidth() * juego.SCALE);
        menuh = (int)(bgimg.getHeight() * juego.SCALE);
        menux = juego.GAME_WIDTH/2  - menuw /2;
        menuy = (int)(-60*juego.SCALE);
    }

    
    
    
    
    @Override
    public void update() {
        fireboyB.update();
        
        watergirlB.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bgimg, menux, menuy,menuw,menuh, null);
        fireboyB.draw(g);
        watergirlB.draw(g);
    }
    
    
    @Override
    public void mouseclick(MouseEvent e) {
       
    }

    @Override
    public void mousepressed(MouseEvent e) {
       
        if(isIn(e,fireboyB)){
            
            fireboyB.setMousepreesed(true);
    
        }
        else if(isIn(e,watergirlB)){
            watergirlB.setMousepreesed(true);
        }
        
        
    }

    @Override
    public void mousereleased(MouseEvent e) {
       
        if(isIn(e,fireboyB)){
            if(fireboyB.isMousepreesed()){
                
                
                estadosdejuego.estado = estadosdejuego.JUGAR;
                
            }
        }
        
        if(isIn(e,watergirlB)){
            if(watergirlB.isMousepreesed()){
                
                estadosdejuego.estado = estadosdejuego.JUGAR;
            }
        }
        
        fireboyB.resetbools();
        watergirlB.resetbools();
        
    }

    @Override
    public void mousemoved(MouseEvent e) {
      fireboyB.setMouseover(false);
      watergirlB.setMouseover(false);
        
      if(isIn(e,fireboyB)){
          fireboyB.setMouseover(true);
      }
      if(isIn(e,watergirlB)){
          watergirlB.setMouseover(true);
      }
      
      
      
    }

    @Override
    public void keypreseed(KeyEvent e) {
  
    }

    @Override
    public void keyreleased(KeyEvent e) {
        
    }

    private void resetbotones() {
                 
    }

    public void setIsfireboy(boolean isfireboy) {
        this.isfireboy = isfireboy;
    }
    

    public boolean getIsfireboy() {
        return isfireboy;
    }
    
    public boolean getisIswatergirl() {
        return iswatergirl;

    }

 
}
