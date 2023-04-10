package objetos;

import main.juego;

public class trampas extends objetos_de_juego {
    
    public trampas(int x, int y, int type) {
        super(x, y, type);
        inithitbox(80,15);
        xdrawoffset =0;
        xdrawoffset = (int)(-30*juego.SCALE);
        ydrawoffset = (int)(10*juego.SCALE);
        hitbox.x += xdrawoffset +(int)(juego.SCALE * -7);
        hitbox.y += ydrawoffset + (int)(juego.SCALE * 5);
    }
    
    public void update(){
        
    }
    
    
}
