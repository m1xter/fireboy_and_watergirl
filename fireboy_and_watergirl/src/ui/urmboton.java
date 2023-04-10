package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.loadsave;
import static utilz.constantes.ui.urmbotons.*;

public class urmboton  extends pausebutton{
    
    private BufferedImage[]  imgs ;
    private int rowindex,index;
    private boolean mousepresed,mousereleased,mouseover;
    
    public urmboton(int x, int y, int w, int h,int rowindex) {
        super(x, y, w, h);
        this.rowindex = rowindex;
        loadimg();
        
    }
    
    public void update(){
        index =0;
        if(mouseover){
            index =1;
        }
        if(mousepresed){
            index = 2;
        }
    }
    
    public void draw(Graphics g){
        g.drawImage(imgs[index], x, y, URM_SIZE,URM_SIZE, null);
    }
    
    
    private void loadimg() {
        BufferedImage temp = loadsave.GetSpriteAtlas(loadsave.URM_BUTTON);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowindex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
        
    }
    
    public void resetbools(){
        mouseover = false;
        mousepresed = false;
    }

    public boolean isMousepresed() {
        return mousepresed;
    }

    public void setMousepresed(boolean mousepresed) {
        this.mousepresed = mousepresed;
    }

    public boolean isMouseover() {
        return mouseover;
    }

    public void setMouseover(boolean mouseover) {
        this.mouseover = mouseover;
    }
    
    
    
    
    
    
}
