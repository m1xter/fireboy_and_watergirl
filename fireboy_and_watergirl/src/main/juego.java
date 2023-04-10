package main;


import estadosjuego.comojugar;
import estadosjuego.creditos;
import estadosjuego.estadosdejuego;
import estadosjuego.jugando;
import estadosjuego.menu;

import estadosjuego.seleccion_personaje;
import java.awt.Container;
//import estadosjuego.seleccion;

import java.awt.Graphics;
import utilz.loadsave;


public class juego implements Runnable {
	
        private ventana gameWindow;
        private panel gamePanel;
        private Thread hilojuego;
        private final int FPS_SET =120;
        private final int UPS_SET = 200;
        
        private jugando jugando;
        
        private menu menu;
        private seleccion_personaje seleccion_personaje;
        private creditos creditos;
        private comojugar comojugar;
        
        //variables para determinar el tamano del juego
        public static final  int TILE_DEFAULT_SIZE = 32;
        public static final float SCALE = 1f;
        public static  final int  TILES_N_WIDTH = 26;
        public static  final int  TILES_N_HEIGHT = 14;
        public static  final int  TILES_SIZE = (int)(TILE_DEFAULT_SIZE * SCALE);
        public static final int GAME_WIDTH = TILES_SIZE  *TILES_N_WIDTH;
        public static final int GAME_HEIGHT = TILES_SIZE  *TILES_N_HEIGHT;
       
        public juego() {
                                        
                initclases();
                gamePanel = new panel(this);                
                gameWindow = new ventana(gamePanel);                
                // necesitamos que le panel tenga el focus para que pueda escuchar los inputs 
                gamePanel.setFocusable(true);
                gamePanel.requestFocus();                
                stargameloop();
                
        }
            
         private void initclases() {
                menu = new menu(this);                
                seleccion_personaje = new seleccion_personaje(this,jugando);
                jugando = new jugando(this,seleccion_personaje);                                
                creditos = new creditos(this);
                comojugar = new comojugar(this);
                
        }

    
     
        
        private void stargameloop(){
            hilojuego = new Thread(this);
            hilojuego.start();
        }

        public void render(Graphics g){            
            
          switch(estadosdejuego.estado){                
                
              case MENU:
                    menu.draw(g);
                    break;
               case SELECCION:
                     seleccion_personaje.draw(g);
                    break;
               case CREDITOS:
                   creditos.draw(g);
                   break;
               case COMOJUGAR:
                   comojugar.draw(g);
                   break;
              case JUGAR:
                      
                      jugando.draw(g);
                      
                    break;                                
                default:              
                    break;
}            
        }
        
        
        
        
        private void update() {
            
         switch(estadosdejuego.estado){                
                
              case MENU:
                    menu.update();
                    break;
               case SELECCION:
                    seleccion_personaje.update();
                    break;
              case JUGAR:
                    jugando.update();  
                    break;                              
              case COMOJUGAR:
                  comojugar.update();
                  break;
              case CREDITOS:
                  creditos.update();
                  break;
              case SALIR:
                  System.exit(0);
                break;     
             default:         
                 break;
}
            
            
            
        }
        
        
       
     // este es el loop del juego 
    @Override
    public void run() {
        
        // esta variable nos dira cuantos nanosegundos debera durar cada frame
        double timeperframe = 1000000000.0 / FPS_SET;
        // esta variable nos dira cuando hay que acualizar el jpanel
        double timeperupdate = 1000000000.0 / UPS_SET;
        long previustime = System.nanoTime();
        
        
        
         int frames = 0 ;
         int updates =0;
        long lastcheck = System.currentTimeMillis();
        double deltau = 0;
        double deltaf =0; 
        
        while(true){
            
            long currentime = System.nanoTime();
            
            
            deltau += (currentime - previustime) / timeperupdate;
            deltaf += (currentime - previustime) / timeperframe;
            previustime = currentime;
            
            if(deltau >= 1){
                update();
                updates++;
                deltau--;
            }
                        
            if(deltaf >=  1){
                gamePanel.repaint();                
                frames++;
                deltaf--;
            }
                    
            if(System.currentTimeMillis() - lastcheck >= 1000){
                  lastcheck = System.currentTimeMillis();
                  System.out.println("FPS "+frames  + "  | " +"UPS" + updates);
                  frames = 0;
                  updates =0;
             }
                
            
        }
        
    }
    
    
    public menu getmenu(){
        return menu;
    }
    
    public seleccion_personaje getseleccion(){
        return seleccion_personaje;
    }
    
    public creditos getcreditos(){
        return creditos;
    }
    public comojugar getcomojugar(){
        return comojugar;
    }
    
    public jugando getjugando(){
        return jugando;
    }

 
    
    
    
   
    
    
       
        

}
