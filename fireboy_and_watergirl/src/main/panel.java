package main;

import inputs.keyboardinputs;
import inputs.mouseinputs;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import static main.juego.GAME_HEIGHT;
import static main.juego.GAME_WIDTH;


public class panel extends JPanel {
        //variables
         private mouseinputs mouseinputs;
         private juego juego;
         
         public panel(juego juego) {                         
             this.juego = juego;
              addKeyListener(new keyboardinputs(this));
              mouseinputs = new mouseinputs(this);              
              addMouseListener(mouseinputs);
              addMouseMotionListener(mouseinputs);
              setpanelsize();
              
           }
         
         
        private void setpanelsize() {
            Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
            setPreferredSize(size);
            System.out.println("SIZE : " + GAME_WIDTH  + " : " + GAME_HEIGHT);
        }

    
         
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                juego.render(g);
                              
            }

        public void  updategame(){
            
        }
        
        
        public juego getjuego(){
            return juego;
        }


        
        
        

}
