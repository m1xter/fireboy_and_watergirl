package entitty;

import estadosjuego.jugando;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import main.juego;
import static utilz.constantes.playerconstants.*;
import static utilz.constantes.anispeed;
import static utilz.constantes.gravity;
import static utilz.helpmethods.*;

import utilz.loadsave;


public class watergirl extends entity {
    private BufferedImage[ ][ ] animations;
    private jugando jugando;
    private int playeraction = IDLE;
    private boolean  left,right,jump;
    float xspeed =0;
    private boolean moving = false;    
    private int[][] lvldata ;    
    private float jumpspeed = -2.25f*juego.SCALE;
    private float fallspeedaftercolition = 0.5f*juego.SCALE;
    private boolean inair = false;    
    private float xdrawoffset =10*juego.SCALE;
    private float ydrawoffset =14*juego.SCALE;    
    private int flipx = 0;
    private int flipw = 1;
    
    
    
    public watergirl(float x, float y,int w ,int h,jugando jugando) {
        super(x, y,w,h);
        this.jugando = jugando;
        loadanimations();
        inithitbox(20,27);
        
    }
    
    
    public void update(){
        updatepositon();        
        if(moving){
            
            checktocopalanca();            
            checktococaja();
            checktocoplacapresion();
            tocodiamante();
            checktocolava();
            checktocopuertaAzul();
            tocotoxico();
        }
         updateanimationtick();                
         setanimation();
         
    }

    
    
    
    
    public void render(Graphics g){
                
        g.drawImage(animations[playeraction][aniIndex], (int)(hitbox.x - xdrawoffset) + flipx , (int) (hitbox.y - ydrawoffset),w*flipw,h, null);       
        //drawhitbox(g);
    }
    
    private void loadanimations() {        
                 BufferedImage img = loadsave.GetSpriteAtlas(loadsave.WATERGIRL_ATLAS);                 
                 animations = new BufferedImage[5][6];
                        for(int j = 0 ; j <animations.length; j++)
                                for(int i = 0 ; i < animations[j].length; i++)
                                        animations[j][i]  = img.getSubimage(i*24, j*24, 24, 24);        
    }
    
    public void loadlvldata(int[ ][ ] lvldata){
        this.lvldata = lvldata;
        if(!isEntityOnFloor(hitbox,lvldata)){
                inair = true;
            }
    }
    
      private void updateanimationtick() {
        aniTick++;
        if(aniTick >= anispeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playeraction)){
                aniIndex = 0;
            }
        }
        }
      
         
         private void setanimation() {
             
             int starani = playeraction;
             
        if(moving){
            playeraction = RUNNING;
        }else{
            playeraction = IDLE;
        }
        
        if(inair){
           if(airspeed < 0 ){
            playeraction = JUMPING;   
           }
           else{
              
           }
        }
        
        
        if(starani != playeraction){
            resetAnitick();
        }
        
    }
         
         
         

    private void updatepositon() {
        
        moving = false;
        if(jump){
            jump();
        }
        
        if(!left && !right && !inair)
                return;
        
         xspeed =0;
        
        if(left){
            xspeed -=  playerspeed;
            flipx = w;
            flipw = -1;
        }
        if(right){            
            xspeed +=  playerspeed;            
            flipx = 0;
            flipw =1;
        }        
        
        if(!inair){
            if(!isEntityOnFloor(hitbox,lvldata)){
                inair = true;
            }
        }
        
        
   
        if(inair){
            if(canmovehere(hitbox.x,hitbox.y+airspeed,hitbox.width,hitbox.height,lvldata)){                
                hitbox.y +=  airspeed;
                airspeed +=gravity;
                updatexpos(xspeed);                
            }else{
                hitbox.y = GetEntityYPosUnderRoofAboveFloor(hitbox,airspeed);
                if(airspeed > 0){
                    resetinair();
                }else
                    airspeed = fallspeedaftercolition;        
                updatexpos(xspeed);
            }
        }else{
            updatexpos(xspeed);
        }
        
        moving = true;
        
    }
    
    
    private void updatexpos(float xspeed) {
        if(canmovehere(hitbox.x+xspeed,hitbox.y,hitbox.width,hitbox.height,lvldata)){
            hitbox.x +=  xspeed;                        
        }else{
            hitbox.x = GetEntityXpostnetwall(hitbox,xspeed);
        }
        
    }
    
    
    private void resetinair() {
        inair = false;
        airspeed = 0;
    }

    private void jump() {
        if(inair)
            return;        
        inair = true;
       airspeed = jumpspeed;
       
    }

    public void resetDirbooleans() {   
        left = false;
        right = false;
    }

    private void resetAnitick() {
        aniTick = 0;
        aniIndex =0;
    }

    
    public void resetall(){
        resetDirbooleans();
        inair = false;
        moving = false;
        playeraction = IDLE;
        
        hitbox.x = x;
        hitbox.y = y;
        
         if(!isEntityOnFloor(hitbox,lvldata)){
                inair = true;
            }
    }

    
    
    //getters y setters de la clase
    public void setjump(boolean jump){
        this.jump = jump; 
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    
    
    public void setMoving(boolean moving){
             this.moving = moving;
    }

    
    private void checktocopalanca() {
        jugando.checktocopalanca(hitbox);
    }
    

    private void checktococaja() {
        jugando.checktococaja(hitbox);
    }

    private void checktocoplacapresion() {
        jugando.checktocoplacapresion(hitbox);
    }

    

    private void tocotoxico() {
        jugando.tocotoxico(hitbox);
    }    

    /**
     * este metodo confirma si se toco lava
     */
    private void checktocolava() {
        jugando.tocolava(hitbox);
    }

    /**
     * confirma si se toco la puerta azul
     */
    private void checktocopuertaAzul() {
        jugando.tocopuertaAzul(hitbox);
    }

    /**
     * confirma si se toco diamante
     */
    private void tocodiamante() {
        jugando.checktocoDiamante(hitbox);
    }
   
    
    
    
    
    

    
}// fin de la clase
