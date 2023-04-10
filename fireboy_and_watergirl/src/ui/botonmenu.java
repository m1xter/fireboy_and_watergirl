package ui;

import estadosjuego.estadosdejuego;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.loadsave;
import static utilz.constantes.ui.botones.*;

public class botonmenu {
    private int x,y,rowindex,index;
    private estadosdejuego estado;
    private BufferedImage[] imgs;
    private int xoffsetcenter = B_WIDTH / 2;
    private boolean  mouseover,mousepreesed;
    private Rectangle bounds;
    
    
    public botonmenu(int x,int y,int rowindex,estadosdejuego estado){
    this.x =x;
    this.y = y;
    this.rowindex = rowindex;
    this.estado =estado;
    loadImg();
    intbounds();
    }
    
    public Rectangle getBounds(){
        return bounds;
    }

    private void intbounds() {
        bounds = new Rectangle(x - xoffsetcenter,y,B_WIDTH,B_HEIGHT);
    }

    
    private void loadImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = loadsave.GetSpriteAtlas(loadsave.BOTONES_MENU);
        for(int i =0; i < imgs.length; i++){
            imgs[i] = temp.getSubimage( i * B_WIDTH_DEFAULT, rowindex * B_HEIGHT_DEFAULT,B_WIDTH_DEFAULT , B_HEIGHT_DEFAULT);
        }
    }
    
    
    public void draw(Graphics g){
        g.drawImage(imgs[index], x - xoffsetcenter, y, B_WIDTH,B_HEIGHT, null);
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

    public void aplicarestadodejuego(){
        estadosdejuego.estado = estado;
    }
    
    public void resetbools(){
        mouseover = false;
        mousepreesed = false;
    }
    



    
}
