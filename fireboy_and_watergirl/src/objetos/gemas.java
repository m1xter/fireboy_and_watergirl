package objetos;

import main.juego;

public class gemas extends objetos_de_juego {
    
    private float hoveroffset;
    private int maxhoveroffset,hoverdir =1;
    
    public gemas(int x, int y, int type) {
        super(x, y, type);
        
        inithitbox(32,32);
        xdrawoffset = (int)(-32*juego.SCALE);
        ydrawoffset = (int)(1*juego.SCALE);
        maxhoveroffset = (int)(7*juego.SCALE);
    }
    
    public void update(){
        
        updatehover();
    }

    private void updatehover() {
        hoveroffset +=(0.076f * juego.SCALE * hoverdir);
        if(hoveroffset >= maxhoveroffset){
            hoverdir = -1;
        }else if(hoveroffset < 0 ){
            hoverdir = 1;
        }
        hitbox.y = y +hoveroffset;
    }
    
    
    
    
    
}

    
    