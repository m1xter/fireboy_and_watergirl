package objetos;

import main.juego;
import static utilz.constantes.objetos.PUERTAAZUL;
import static utilz.constantes.objetos.PUERTAROJA;

public class puertas extends objetos_de_juego {

    public puertas(int x, int y, int type) {
        super(x, y, type);
        
        inithitbox(60,70);
        xdrawoffset = (int)(-16*juego.SCALE);
        ydrawoffset = (int)(4*juego.SCALE);
        hitbox.y += ydrawoffset + (int)(juego.SCALE * -40);
    }
    
    
     public void update(){        
        if(doanimation){
            updateanimationtick();
    }
     }

    
}
