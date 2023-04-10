package objetos;

import main.juego;

import static utilz.constantes.objetos.PALANCA;
import static utilz.constantes.objetos.PLACA_PRESION;

public class activadores extends objetos_de_juego {
    
    public activadores(int x, int y, int type) {
        super(x, y, type);
        createhitbox();
    }

    private void createhitbox() {
        if(objtype == PALANCA){
            inithitbox(25,25);            
            xdrawoffset = (int)(-20*juego.SCALE);
            ydrawoffset = (int)(12*juego.SCALE);
        }else if(objtype == PLACA_PRESION){
            inithitbox(32,12);
            xdrawoffset = (int)(-22*juego.SCALE);
            ydrawoffset = (int)(21*juego.SCALE);  
            hitbox.y += ydrawoffset + (int)(juego.SCALE * 2);
    }        
}
    

public void update(){
    if(doanimation){
        
        updateanimationtick();
    }    
}

}
