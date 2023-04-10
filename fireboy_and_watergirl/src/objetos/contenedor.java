package objetos;

import main.juego;
import static utilz.constantes.objetos.*;

public class contenedor extends objetos_de_juego{
    
    
    private float hoveroffset;
    private int maxhoveroffset = 2 ,hoverdir =1;
    private float moverx;
    
    
    public contenedor(int x, int y, int type) {
        super(x, y, type);
        createhitbox();
    }

    private void createhitbox() {
        if(objtype == CAJA){
            inithitbox(32,30);
            xdrawoffset = (int)(-10*juego.SCALE);
            ydrawoffset = (int)(20*juego.SCALE);
        }else if(objtype == PLATAFORMAX){
            inithitbox(85,26);
            xdrawoffset = (int)(-24*juego.SCALE);
            ydrawoffset = (int)(25*juego.SCALE);
            hitbox.x += xdrawoffset +(int)(juego.SCALE * -8);
        }else if(objtype == PLATAFORMAY){
            inithitbox(32,95);
            xdrawoffset = (int)(-10*juego.SCALE);
            ydrawoffset = (int)(10*juego.SCALE);
            
            
        }
        
        
        
    }
    
    
public void update(){
    if(doanimation){
        if(objtype == PLATAFORMAX){
            
            MoverPlataformax();                
        }
        
        if(objtype == PLATAFORMAY){
            
            MoverPlataformay();                
        }
        
        if(objtype == PLATAFORMAH){
            
            MoverPlataformah();
                      
        }
        
    }


    
}



private void MoverPlataformax(){
    hoveroffset +=(0.5f * juego.SCALE * hoverdir);
      if(hoveroffset >= maxhoveroffset){
            hoverdir = 1;
      if(hoveroffset == 72){
            hoverdir = 0;
      }
        hitbox.y = y +hoveroffset;    
}

}



 private void MoverPlataformay() {
        hoveroffset +=(0.5f * juego.SCALE * hoverdir);
        if(hoveroffset >= maxhoveroffset){
            hoverdir = -1;
        }else if(hoveroffset < -80  ){
            hoverdir = 0;
        }
        hitbox.y = y +hoveroffset;
    }
 
  private void MoverPlataformah() {
        hoveroffset +=(0.5f * juego.SCALE * hoverdir);
        if(hoveroffset >= maxhoveroffset){
            hoverdir = -1;
        }else if(hoveroffset < -80  ){
            hoverdir = 0;
        }
        hitbox.x = x +hoveroffset;
    }
 
 




    
}


