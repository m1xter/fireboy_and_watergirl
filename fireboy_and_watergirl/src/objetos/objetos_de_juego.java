package objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import main.juego;
import static utilz.constantes.anispeed;
import static utilz.constantes.objetos.*;

public class objetos_de_juego {
    
    protected int x,y,objtype;
    protected Rectangle2D.Float  hitbox;
    
    protected boolean doanimation,active = true;
    protected int aniTick,aniIndex;
    protected int xdrawoffset,ydrawoffset;

    public objetos_de_juego(int x, int y, int type) {
        this.x =x;
        this.y=y;
        this.objtype = type;
    }
    
    
    public void reset(){
        aniIndex = 0;
        aniTick= 0;
        active = true;
        
        
        if(objtype == PALANCA ||  objtype == PLACA_PRESION){
            doanimation = false;            
        }else if(objtype == PUERTAAZUL || objtype == PUERTAROJA){
            doanimation = false;
            
        }else if(objtype == PLATAFORMAX || objtype == PLATAFORMAY || objtype == CAJA) {
            doanimation = false;
            hitbox.y = y;
        }else{
            doanimation = true;
        }
        
        
        
    }
    
    
    protected void inithitbox(float w,float h) {
        hitbox = new Rectangle2D.Float(x,y,(int)(w*juego.SCALE),(int)(h*juego.SCALE)); 
    }
     
   public  void drawhitbox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }   
   
      public void updateanimationtick() {
        aniTick++;
        if(aniTick >= anispeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpritesAmount(objtype)){
                aniIndex = 0;//activadores
                if(objtype == PALANCA ||  objtype == PLACA_PRESION){
                    doanimation = false;
                    active = true;//puertas    
                }else if(objtype == PUERTAAZUL || objtype == PUERTAROJA){
                    doanimation = false;
                    active = false;
                }
            }
        }
        }

    public int getObjtype() {
        return objtype;
    }

    public void setObjtype(int objtype) {
        this.objtype = objtype;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D.Float hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setanimation(boolean doanimation){
        this.doanimation = doanimation;
    }
  
    public int getXdrawoffset() {
        return xdrawoffset;
    }

    
    public int getYdrawoffset() {
        return ydrawoffset;
    }
    
    
    
    public int getaniIndex(){
        return aniIndex;
    }

    
    

      
      
      



    
}
