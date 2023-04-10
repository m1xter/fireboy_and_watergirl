package entitty;
//imports
import estadosjuego.jugando;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.juego;
import static utilz.constantes.playerconstants.*;
import static utilz.constantes.anispeed;
import static utilz.constantes.gravity;
import static utilz.helpmethods.*;
import utilz.loadsave;

/**
 * esta es la clase del chico fuego que hereda de entidad
 * @author Admin
 */
public class fireboy extends entity {
    
    private jugando jugando;
    private BufferedImage[ ][ ] animations;    
    private int playeraction = IDLE;
    private int[][] lvldata ;
    private boolean  left,right,jump;
    float xspeed =0;
    private boolean moving = false;
    private float jumpspeed = -2.25f*juego.SCALE;
    private float fallspeedaftercolition = 0.5f*juego.SCALE;
    private boolean inair = false;    
    private float xdrawoffset =10*juego.SCALE;
    private float ydrawoffset =14*juego.SCALE;
    private int flipx = 0;
    private int flipw = 1;
    
/**
 * el constructro de fireboy recive las variables heredadas de entidad e llama a los metodos loadanimations y inithitbox
 * @param x
 * @param y
 * @param w
 * @param h
 * @param jugando 
 */    
    public fireboy(float x, float y,int w ,int h,jugando jugando) {
        super(x, y,w,h);
        this.jugando = jugando;
        loadanimations();
        inithitbox(20,27);
        
    }
    
    public void update(){
        updatepositon();        
        if(moving){
            checktoco();
            checktocopalanca();            
            checktococaja();
            checktocoplacapresion();
            tocopuertaRoja();
            tocoagua();
            tocotoxico();
        }
         updateanimationtick();                
         setanimation();
         
    }

    /**
     * el metodo render permite pintar sobre el panel al personaje y sus animaciones
     * @param g 
     */
    public void render(Graphics g){                
        g.drawImage(animations[playeraction][aniIndex], (int)(hitbox.x - xdrawoffset) + flipx , (int) (hitbox.y - ydrawoffset),w*flipw,h, null);       

    }
    /**
     * el metodo loadanimations usa un atlas de sprites y lo recorre como un array en donde cada espacio es un frame de una animacion
     * guardandolas en un array 2d
     */
    private void loadanimations() {        
                 BufferedImage img = loadsave.GetSpriteAtlas(loadsave.FIREBOY_ATLAS);                 
                 animations = new BufferedImage[5][6];
                        for(int j = 0 ; j <animations.length; j++)
                                for(int i = 0 ; i < animations[j].length; i++)
                                        animations[j][i]  = img.getSubimage(i*24, j*24, 24, 24);        
    }

    /**
     * este metodo recive los datos del nivel y comprueba si la entidad esta en el piso o en el aire
     * @param lvldata 
     */
    public void loadlvldata(int[ ][ ] lvldata){
        this.lvldata = lvldata;
        if(!isEntityOnFloor(hitbox,lvldata)){
                inair = true;
            }
    }
    
    /**
     * este metodo acualiza las animaciones pasando de frame en frame para conseguir el efecto
     * usa la cantidad de sprites que contiene cada animacions para saber hasta cuando ejecutar la 
     * animacion
     */
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
      
      /**
       * este meteodo cambia la accion del jugador  a correr idle o saltar
       */
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
         
         
    /**
     * este metodo es que el que mueve y actualiza la posicion del jugador 
     */
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
    
    
    /**
     * este metodo toma la velocidad del jugador y comprueba de que la hitbox cuando choca con algo 
     * el personaje se quede al lado de la pared
     * @param xspeed 
     */
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
    
    /**
     * este metodo resetea todas las variables del jugador
     */
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
    
    /**
     * este metodo confirma si el hitbox toco un ruby 
     */
    private void checktoco() {
        jugando.checktocoRuby(hitbox);
    }

    /**
     *  este metodo confirma si se toco una palanca
     */
    private void checktocopalanca() {
        jugando.checktocopalanca(hitbox);
    }
    
/**
 * este metodo confirma si se toco una caja
 */
    private void checktococaja() {
        jugando.checktococaja(hitbox);
    }

/**
 * este metodo confirma si se toco una placa de presion
 */
    private void checktocoplacapresion() {
        jugando.checktocoplacapresion(hitbox);
    }
/**
 * confirma si toca la puerta roja
 */
    private void tocopuertaRoja() {
        jugando.tocopuertaRoja(hitbox);
    }

    /**
     * confrima si se toco agua
     */
    private void tocoagua(){
        jugando.tocoagua(hitbox);
    }
    /**
     * confirma si se toco el poso toxico
     */
    private void tocotoxico() {
        jugando.tocotoxico(hitbox);
    }    
   
    
}//final de la clase
