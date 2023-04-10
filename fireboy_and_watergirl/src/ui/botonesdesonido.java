package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.loadsave;
import static utilz.constantes.ui.botonesdepausa.*;
public class botonesdesonido extends pausebutton {
    
    private  BufferedImage[][] soundimg;
    private boolean mouseover,mousepresed;
    private int rowindex,colindex;
    private boolean muted;
    public botonesdesonido(int x, int y, int w,int h) {
        super(x, y,w, h);
        loadsoundimg();
    }

    private void loadsoundimg() {
        BufferedImage temp =   loadsave.GetSpriteAtlas(loadsave.SOUND_BUTTON);
        soundimg = new BufferedImage[2][3];
        for(int j = 0 ; j < soundimg.length ; j ++){
            for(int i = 0 ; i < soundimg[j].length ; i++){
                soundimg[j][i] = temp.getSubimage(i*SOUND_SIZE_DEFAULT, j *SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
        }
    }
    
}
    
    
    public void update(){
        if(muted){
            rowindex = 1;
        }else{
            rowindex = 0;
        }
        
        colindex =0;
        if(mouseover){
            colindex = 1;
        }
        if(mousepresed){
            colindex = 2;
        }
        
    }
    
    public void resetbools(){
        mouseover = false;
        mousepresed = false;
    }
    
    
    public void draw(Graphics g){
        g.drawImage(soundimg[rowindex][colindex ], x, y,w,h, null);
    }

    public boolean isMouseover() {
        return mouseover;
    }

    public void setMouseover(boolean mouseover) {
        this.mouseover = mouseover;
    }

    public boolean isMousepresed() {
        return mousepresed;
    }

    public void setMousepresed(boolean mousepresed) {
        this.mousepresed = mousepresed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    
    
    
    
    
}