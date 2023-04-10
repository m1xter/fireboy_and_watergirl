package entitty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.juego;

/**
 * 
 * @author Vizcaya juan
 * 
 * esta es la clase padre de el chico fuego y chica agua
 */
public abstract class entity {
    

    protected float x,y;    
    protected int w,h;
    protected Rectangle2D.Float  hitbox;
    protected int aniTick,aniIndex;
    protected float airspeed = 0f;
    protected boolean inair = false;
    protected float playerspeed = 1.5f * juego.SCALE;
    
    /**
     * contructor de la clase entidad que tiene las posiciones en x ,y ademas el ancho y alto
     * @param x
     * @param y
     * @param w
     * @param h 
     */
    public entity (float x,float y,int w,int h){
    
        this.x = x;
        
        this.y =y;
        
        this.w =w;
        
        this.h = h;        
    }

/**
 * esta clase nos permite ver la hitbox de los personajes
 * @param g 
 */    
    protected void drawhitbox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    
    /**
     * este metodo crea las hitbox de los personajes con el ancho y alto 
     *  ancho
     * @param w
     * alto 
     * @param h 
     */
    protected void inithitbox(float w,float h) {
        hitbox = new Rectangle2D.Float(x,y,(int)(w*juego.SCALE),(int)(h*juego.SCALE)); 
    }

 
   /**
    * getter para la hitbox
    * @return 
    */
    public Rectangle2D.Float gethitbox(){
        return hitbox;
    }
    
    
   
    
    
    
}

