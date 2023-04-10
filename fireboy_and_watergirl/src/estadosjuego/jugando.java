
package estadosjuego;

import entitty.fireboy;
import entitty.watergirl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

import javax.swing.JOptionPane;
import levels.levelmanager;
import static main.juego.SCALE;
import objetos.objmanager;
import ui.boton_seleccion;
import ui.gameover_overlay;
import ui.lvlcompletedoverlay;
import ui.pauseoverlay;
import ui.temporizador;
import static utilz.constantes.objetos.DIAMANTE_SUMA;
import static utilz.constantes.objetos.RUBY_SUMA;
import utilz.loadsave;



/**
 * esta es la clase jugando en donde esta toda la logica para correr el juego y jugar , moverse ,completar niveles,cargar niveles,etc
 * esta clase hereda de estado y implementa la interfasestado
 * @author Admin
 */
public class jugando extends estado  implements interfasestado{
    
    private fireboy  fireboy;   
    private watergirl watergirl;
    private levelmanager levelmanager;
    private boolean  pause = false;
    private pauseoverlay pauseoverlay;
    private gameover_overlay gameover_overlay;
    private lvlcompletedoverlay lvlcompletedoverlay;
    private objmanager objmanager;
    private int Cant_Rubys = 0,Cant_Diamante= 0;
    private BufferedImage lvlbg,tempo;
    private boolean isfireboy =true,iswatergirl=false;
    private seleccion_personaje seleccion;
    private boolean  gameover;
    private boolean lvlcompletado = false;
    Timer Temporizador;
    int min=0,seg=0;
    
    /**
     * en el contructor cargamos todo lo necesario para que el juego funcione
     * @param juego
     * @param seleccion 
     */
    public jugando(main.juego juego,seleccion_personaje seleccion) {
        super(juego);
        this.seleccion = seleccion;        
        initclases();
        
        lvlbg = loadsave.GetSpriteAtlas(loadsave.LVLBG);        
        tempo = loadsave.GetSpriteAtlas(loadsave.TEMPORIZADOR);
        loadstarlvl();
    }
    
    
    /**
     * este metodo carga el siguente nivel
     */
    public void loadnextlevel(){
        resetall();
        levelmanager.loadnextlevel();
    }
    
    /**
     * este metodo se encarga de cargar la posicion de todos los objetos del nivel
     */
    private void loadstarlvl() {
        objmanager.loadobjs(levelmanager.getcurrentlvl());
    }
 
    
    /**
     * este metodo inicializa todas las clases que usa el estado de juego jugando
     */
    private void initclases() {
        
        levelmanager = new levelmanager(juego);        
        
        objmanager = new objmanager(this);
        /**
         * temporizador cada 1seg ejecutara lo que esta dentro de la clase interna tempo
         */
        Temporizador = new javax.swing.Timer(1000,new Tempo());
         
        if(isfireboy){
            fireboy  = new fireboy(100,380,(int)(48*SCALE),(int)(48*SCALE),this);
            fireboy.loadlvldata(levelmanager.getcurrentlvl().getlvldata());                    
        }            
            if(iswatergirl){
                watergirl = new watergirl(100,380,(int)(48*SCALE),(int)(48*SCALE),this);
            watergirl.loadlvldata(levelmanager.getcurrentlvl().getlvldata());
        
            }
            
      
        pauseoverlay = new pauseoverlay(this);
        gameover_overlay = new gameover_overlay(this);
        lvlcompletedoverlay = new lvlcompletedoverlay(this);
    }

    
    public void WindowsFocusLos(){
        
        
    }
    
   /**
    * este metodo se encarga de guardar la cantidad de rubys objtenidos
    */
    public void sumarRuby(){
        Cant_Rubys += RUBY_SUMA;
    }
    /**
     * este metodo se encarga de guardar la cantidad de diamantes objtenidos
     */
    public void sumarDiamante(){
        Cant_Diamante += DIAMANTE_SUMA;
    }
    
        
    
     public fireboy fireboy(){
        return fireboy;
    }
     public watergirl watergirl(){
         return watergirl;
     }
     
/**
 * metodo donde acutalizamos el comportamiento del juego dependiendo si estamos pausando completamos un nivel o morimos
 */
    @Override
    public void update() {
        
        if(pause){
            pauseoverlay.update();
            Temporizador.stop();
        }else if(lvlcompletado){
            lvlcompletedoverlay.update();
            Temporizador.stop();
        }else if(!gameover){
                levelmanager.update();                    
                if(isfireboy){
                    fireboy.update(); 
                }else if(iswatergirl){
                    watergirl.update();
                }
                objmanager.update(); 
        }else if(gameover){
            gameover_overlay.update();
            Temporizador.stop();
        }
        
        Temporizador.start();
    }

    /**
     * en este metodo se dibuja toda la parte grafica del juego
     * el metodo draw esta persente en la mayoria de clases que necesitan pintar algo por la pantalla
     * en este metodo se llama a se metodo en concreto
     * @param g 
     */
    @Override
    public void draw(Graphics g) {
        
        g.drawImage(lvlbg, 0, 0,juego.GAME_WIDTH,juego.GAME_HEIGHT, null);
        
        levelmanager.draw(g);        
        objmanager.draw(g, 32);
       
           
           if(isfireboy){
           fireboy.render(g);    
           }
           if(iswatergirl){
           watergirl.render(g);    
           }
           
        g.drawImage(tempo, juego.GAME_WIDTH / 2 -20, 30,60,30, null);
        g.setColor(Color.white);
        g.drawString(Integer.toString(min)+":"+Integer.toString(seg) , juego.GAME_WIDTH / 2, 50);
        
        
        
        if(pause){
                pauseoverlay.draw(g);
        }else if(gameover){
            gameover_overlay.draw(g);
        }else if(lvlcompletado){
            lvlcompletedoverlay.draw(g);
        }
           
    }

    
    
    @Override
    public void mouseclick(MouseEvent e) {
        
    }

    @Override
    public void mousepressed(MouseEvent e) {
      if(!gameover)  {
            if(pause)
                pauseoverlay.mousepressed(e);            
            else if(lvlcompletado)
                lvlcompletedoverlay.mousepresed(e);
            }else{
          gameover_overlay.mousepresed(e);
      }
    }
    
    
    @Override
    public void mousereleased(MouseEvent e) {
     if(!gameover){
            if(pause)
                pauseoverlay.mousereleased(e);
            else if(lvlcompletado)
                lvlcompletedoverlay.mousereleased(e);
     }else{
         gameover_overlay.mousereleased(e);
     }    
         
            
    }

    @Override
    public void mousemoved(MouseEvent e) {
    if(!gameover){
            if(pause){
                pauseoverlay.mousemoved(e);
            }                
            else if(lvlcompletado){
                lvlcompletedoverlay.mousemoved(e);
            }
    }else{
        gameover_overlay.mousemoved(e);
    }     
        
      
    }
    
    public void  unpausedgame(){
        pause = false;
    }
    
    
    
    
/**
 * este metodo incluye los eventos de teclado al juego permiendo controlar al personaje
 * @param e 
 */
    @Override
    public void keypreseed(KeyEvent e) {
        if(gameover){
            gameover_overlay.Keypressed(e);
        }else
                switch(e.getKeyCode()){
                    case KeyEvent.VK_A:
                        if(isfireboy){
                            fireboy.setLeft(true);
                        }else if(iswatergirl){
                            watergirl.setLeft(true);
                        }                            
                        break;            
                    case KeyEvent.VK_D:
                        if(isfireboy){
                        fireboy.setRight(true);        
                        }else if(iswatergirl){
                            watergirl.setRight(true);
                        }                        
                        break;
                    case KeyEvent.VK_W: 
                        if(isfireboy){
                        fireboy.setjump(true);        
                        }else if(iswatergirl){
                            watergirl.setjump(true);
                        }                        
                        break;
                    case KeyEvent.VK_ESCAPE: 
                        pause = !pause;
                        break;
                    case KeyEvent.VK_1: 
                        setlvlcompletado(true);
                        break;
                   case KeyEvent.VK_2: 
                        
                        break;   
                }
    }

    /**
     * este metodo incluye los eventos cuando sueltas una tecla 
     * @param e 
     */
    @Override
    public void keyreleased(KeyEvent e) {
        if(!gameover)
                    switch(e.getKeyCode()){
                 case KeyEvent.VK_A:                               
                     if(isfireboy){
                         fireboy().setLeft(false);
                     }else if(iswatergirl){
                         watergirl().setLeft(false);
                     }                         
                     
                 case KeyEvent.VK_D:
                     if(isfireboy){
                      fireboy().setRight(false);    
                     }else if(iswatergirl){
                         watergirl().setRight(false);
                     }                    
                 case KeyEvent.VK_W:
                     if(isfireboy){
                          fireboy().setjump(false);
                     }else if(iswatergirl){
                         watergirl().setjump(false);
                     }

                     
                     break; 

             }
    }
   
    /**
     * este metodo se encarga de resetear todos los parametros importantes del juego 
     */
    public void resetall(){
        //aqui se resetea todo 
        gameover = false;
        pause = false;
        lvlcompletado = false;
        Cant_Rubys = 0;
        Cant_Diamante =0;
        seg = 0;
        min =0;
        objmanager.resetallobj();
        if(isfireboy){
            fireboy.resetall();
        }
        if(iswatergirl){
        watergirl.resetall();    
        }
        
        
    }

    public boolean isIsfireboy() {
        return isfireboy;
    }
    public boolean iswatergirl(){
        return iswatergirl;
    }

    public void setIsfireboy(boolean isfireboy) {
        this.isfireboy = isfireboy;
    }
    
    public int getCant_Rubys(){
        return Cant_Rubys;
    }

    public int getCant_Diamante() {
        return Cant_Diamante;
    }
    
    
    
    public void setgameover(boolean gameover){
        this.gameover = gameover;
    }
    
    public void setlvlcompletado(boolean lvlcompletado){
        this.lvlcompletado = lvlcompletado;
    }
    
    
    public  objmanager  getobjmanager(){
        return objmanager;
    }

    public void checktocoRuby(Rectangle2D.Float hitbox) {
        objmanager.checktocoRuby(hitbox);
    }
    
   public void checktocoDiamante(Rectangle2D.Float hitbox) {
        objmanager.checktocoDiamante(hitbox);
    }

    public int getMin() {
        return min;
    }

    public int getSeg() {
        return seg;
    }
   
   
    

    public void checktocopalanca(Rectangle2D.Float hitbox) {
        objmanager.checktocopalanca(hitbox);
    }

    
     public void checktococaja(Rectangle2D.Float hitbox) {
        objmanager.checktococaja(hitbox);
    }
     
     public void checktocoplacapresion(Rectangle2D.Float hitbox) {
        objmanager.checktocoplacapresion(hitbox);
    }

    public void tocopuertaRoja(Rectangle2D.Float hitbox) {
        objmanager.tocopuertaRoja(hitbox);
    }
     
   public void tocoagua(Rectangle2D.Float hitbox){
        objmanager.tocoagua(hitbox);
    }
   
   public void tocolava(Rectangle2D.Float hitbox){
        objmanager.tocolava(hitbox);
    }

   public void tocopuertaAzul(Rectangle2D.Float hitbox) {
        objmanager.tocopuertaAzul(hitbox);
    }
     
    public void tocotoxico(Rectangle2D.Float hitbox) {
        objmanager.tocotoxico(hitbox);
    }
    
    
    
    
    
    
    /**
     * tempo es una clase interna que contiene la logica para el temporizador del juego
     */
    class Tempo implements ActionListener{
                public void actionPerformed(ActionEvent e) {
                        
                    seg = seg +1;
                    if(seg == 60){
                        seg =0;
                        min = min +1;
                    }                    
                }
}


    
    
    
    
    
    
    
    
    
    
    
 
}


