package ui;

import estadosjuego.estadosdejuego;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.loadsave;
import static utilz.constantes.ui.botones.*;
import static utilz.constantes.ui.botones_seleccion.*;

public class boton_seleccion {
    private int x,y,rowindex,index;
    private estadosdejuego estado;
    private BufferedImage[] imgs;
    private int xoffsetcenter = SELECCION_WIDTH / 2;
    private boolean  mouseover,mousepreesed,isfireboy;
    private Rectangle bounds;
    
    
    public boton_seleccion(int x,int y,int rowindex){
    this.x =x;
    this.y = y;
    this.rowindex = rowindex;
    
    loadImg();
    intbounds();
    }
    
    public Rectangle getBounds(){
        return bounds;
    }

    private void intbounds() {
        bounds = new Rectangle(x - xoffsetcenter,y,SELECCION_WIDTH,SELECCION_HEIGHT);
    }

    
    private void loadImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = loadsave.GetSpriteAtlas(loadsave.SELECCION_BTON);
        for(int i =0; i < imgs.length; i++){
            imgs[i] = temp.getSubimage( i * SELECCION_DEFAULT_WIDTH, rowindex * SELECCION_DEFAULT_HEIGHT,SELECCION_DEFAULT_WIDTH , SELECCION_DEFAULT_HEIGHT);
        }
    }
    
    
    public void draw(Graphics g){
        g.drawImage(imgs[index], x - xoffsetcenter, y, SELECCION_WIDTH,SELECCION_HEIGHT, null);
    }
    
    public void update(){
        index = 0;
        if(mouseover){
            index = 1;
        }
        if(mousepreesed){
            index =2;
        }
    }

    public boolean isMouseover() {
        return mouseover;
    }

    public void setMouseover(boolean mouseover) {
        this.mouseover = mouseover;
    }

    public boolean isMousepreesed() {
        return mousepreesed;
    }

    public void setMousepreesed(boolean mousepreesed) {
        this.mousepreesed = mousepreesed;
    }
    
    public void setisfireboy(boolean isfireboy){
        this.isfireboy = isfireboy;
    }

    public boolean isfireboy(){
        return isfireboy;
    }
    
    public void resetbools(){
        mouseover = false;
        mousepreesed = false;
    }
    



    
}
