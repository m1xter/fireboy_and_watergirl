package objetos;

import entitty.fireboy;
import estadosjuego.jugando;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import levels.level;

import static utilz.constantes.objetos.*;
import utilz.loadsave;


public class objmanager {

     private jugando jugando;
     private fireboy fireboy;
     private BufferedImage[ ] gemasimg,contenedorimgs,trampasimg;
     private BufferedImage[ ][ ] activadoresimg,puertasimg ;
     private ArrayList<gemas> Gemas;
     private ArrayList<contenedor> Contenedor;
     private ArrayList<activadores> Activadores;
     private ArrayList<puertas> Puertas;
     private ArrayList<trampas> Trampas;
     
     
    public objmanager(jugando jugando) {
        this.jugando = jugando;
        Gemas = new ArrayList<>();
        Contenedor = new ArrayList<>();
        Activadores = new ArrayList<>();
        Puertas = new ArrayList<>();
        Trampas = new ArrayList<>();
        
        loadimgs();  
        
    }
    
    
     public void loadobjs(level newlevel) {
         
         Gemas = newlevel.getgemas();
         Contenedor = newlevel.getContenedor();
         Activadores = newlevel.getActivador();
         Puertas = newlevel.getPuertas();
         Trampas = newlevel.gettrampas();
         
    }
    
     
     public void checktocoRuby(Rectangle2D.Float  hitbox){
         for(gemas p : Gemas ){
             if(p.isActive()){                
                     if(hitbox.intersects(p.getHitbox())){
                         if(p.getObjtype() == RUBY){
                             p.setActive(false);
                             SumarCantGemas(p);                     
                         }
             }
         }
     }         
 }

   public void checktocoDiamante(Rectangle2D.Float  hitbox){
         for(gemas p : Gemas ){
             if(p.isActive()){                
                     if(hitbox.intersects(p.getHitbox())){
                         if(p.getObjtype() == DIAMANTE){
                             p.setActive(false);
                             SumarCantGemas(p);                     
                         }
             }
         }
     }         
 }



     
    public void SumarCantGemas(gemas p) {
        if(p.getObjtype() == RUBY){
            jugando.sumarRuby();
        }if(p.getObjtype() == DIAMANTE){
            jugando.sumarDiamante();
        }
    }
    
    
    //tocamos la palanca
    public void checktocopalanca(Rectangle2D.Float  hitbox){
         for(activadores e : Activadores ){
             if(e.isActive()){                
                     if(hitbox.intersects(e.getHitbox())){
                         if(e.getObjtype() == PALANCA){                             
                             e.setanimation(true);
                             moverplataformax();
                         }
             }
         }
     }      
    }
    
    
    //tocamos la placa de presion
     public void checktocoplacapresion(Rectangle2D.Float  hitbox){
         for(activadores e : Activadores ){
             if(e.isActive()){                
                     if(hitbox.intersects(e.getHitbox())){
                         if(e.getObjtype() == PLACA_PRESION){                             
                             e.setanimation(true);
                                   moverplataformay();
                                   moverplataformah();
                                   
                         }
                     }       
              
         }
     }      
    }
    
     // tocamos la caja
      public void checktococaja(Rectangle2D.Float  hitbox){
          for(contenedor c : Contenedor ){
             if(c.isActive()){                                     
                 if(hitbox.intersects(c.getHitbox())){
                     if(c.getObjtype() == CAJA){
                         
                         
                         
                     }
             }
         }
     }  
    }
   
    //tocamos la plataforma x
    public void checktocoplataformax(Rectangle2D.Float  hitbox){
          for(contenedor c : Contenedor ){
             if(c.isActive()){                                     
                 if(hitbox.intersects(c.getHitbox())){
                     if(c.getObjtype() == PLATAFORMAX){
                         
                         
                         
                     }
             }
         }
     }  
    }
    
    public void moverplataformax(){
         for(contenedor c : Contenedor ){
             if(c.isActive()){                                     
                         if(c.getObjtype() == PLATAFORMAX){
                            c.setanimation(true);
             }
         }
     }  
    }
    
        public void moverplataformah(){
         for(contenedor c : Contenedor ){
             if(c.isActive()){                                     
                         if(c.getObjtype() == PLATAFORMAH){
                            c.setanimation(true);
             }
         }
     }  
    }
    
        
    
    private void moverplataformay() {
         for(contenedor c : Contenedor ){
             if(c.isActive()){                                     
                         if(c.getObjtype() == PLATAFORMAY){
                            c.setanimation(true);                            
             }
         }
     }  
    }

    public void tocopuertaRoja(Rectangle2D.Float  hitbox){
           for(puertas p : Puertas){
            if(p.isActive()){
                if(hitbox.intersects(p.getHitbox())){
                     if(p.getObjtype() == PUERTAROJA){
                         
                         jugando.setlvlcompletado(true);
                         
                     }
            }
        }
        
    }
 }     
    
        public void tocopuertaAzul(Rectangle2D.Float  hitbox){
           for(puertas p : Puertas){
            if(p.isActive()){
                if(hitbox.intersects(p.getHitbox())){
                     if(p.getObjtype() == PUERTAAZUL){
                         
                         jugando.setlvlcompletado(true);
                         
                     }
            }
        }
        
    }
 }     
  
    
    
    
    
    
    
    public void tocolava(Rectangle2D.Float  hitbox){
        for(trampas r: Trampas){
            if(r.isActive()){
                if(hitbox.intersects(r.getHitbox())){
                     if(r.getObjtype() == LAVA){                         
                         jugando.setgameover(true);                         
                     }
            }
            }
        }
    }
    
    public void tocotoxico(Rectangle2D.Float  hitbox){
        for(trampas r: Trampas){
            if(r.isActive()){
                if(hitbox.intersects(r.getHitbox())){
                     if(r.getObjtype() == GREEN){                         
                         jugando.setgameover(true);                         
                     }
            }
            }
        }
    }
    
    
    
    
    
    public void tocoagua(Rectangle2D.Float  hitbox){
        for(trampas r : Trampas){
            if(r.isActive()){
                  if(hitbox.intersects(r.getHitbox())){
                     if(r.getObjtype() == WATER){
                         
                         jugando.setgameover(true);
                         
                     }
            }
        }
    }
    }
     
     
    

    private void loadimgs() {
        BufferedImage gemasprite = loadsave.GetSpriteAtlas(loadsave.GEMAS);        
        gemasimg = new BufferedImage[2];        
        for(int j = 0; j < gemasimg.length; j++){            
                gemasimg[j] = gemasprite.getSubimage(j*32, 0, 32, 32);            
        }        
        
        BufferedImage plataformasprite = loadsave.GetSpriteAtlas(loadsave.PLATAFORMAS);        
         contenedorimgs = new BufferedImage[3];                 
        for(int j = 0; j < contenedorimgs.length; j++){            
             contenedorimgs[j] = plataformasprite.getSubimage(j*57, 0, 57, 61);            
        }        
        
        BufferedImage activadoressprite = loadsave.GetSpriteAtlas(loadsave.ACTIVADORES);        
         activadoresimg = new BufferedImage[2][2];                 
        for(int j = 0; j < activadoresimg.length; j++){            
            for(int i = 0; i< activadoresimg[j].length;i++){
                activadoresimg[j][i] = activadoressprite.getSubimage(j*52, i*44, 52, 44);
            }             
        }        
        
         BufferedImage puertassprite = loadsave.GetSpriteAtlas(loadsave.PUERTAS);        
           puertasimg= new BufferedImage[2][2];                 
        for(int j = 0; j < puertasimg.length; j++){            
            for(int i = 0; i< puertasimg[j].length;i++){
                puertasimg[j][i] = puertassprite.getSubimage(j*67, i*69, 67, 69);
            }             
        }
        
         BufferedImage trampassprite = loadsave.GetSpriteAtlas(loadsave.TRAMPAS);        
           trampasimg= new BufferedImage[3];                 
        for(int j = 0; j < trampasimg.length; j++){                        
                trampasimg[j] = trampassprite.getSubimage(j*26, 0, 26,23 );            
        }  
        
        
        
      
    }
    
    
    
    
    

    public void update(){        
        for(gemas a : Gemas){
            if(a.isActive()){
               a.update();
            }
        }
        
        for(contenedor c : Contenedor){
            if(c.isActive()){
               c.update();
            }
        }
        
         for(activadores e : Activadores){
            if(e.isActive()){
               e.update();
            }
        }
        
         for(puertas p : Puertas){
            if(p.isActive()){
               p.update();
            }
        }
            for(trampas r : Trampas){
            if(r.isActive()){
               r.update();
            }
        }
        
         
    }
    
    public void draw(Graphics g,int xlvloffset){
        
        
        drawgemas(g,xlvloffset);
        drawplataformas(g,xlvloffset);
        drawactivadores(g,xlvloffset);
        drawpuertas(g,xlvloffset);
        drawtrampas(g,xlvloffset);
    }

    private void drawgemas(Graphics g, int xlvloffset) {                
        for(gemas a : Gemas){
            if(a.isActive()){
                
                if(a.getObjtype() == RUBY){
                    //a.drawhitbox(g);
                    g.drawImage(gemasimg[0], (int)(a.getHitbox().x - a.getXdrawoffset() - xlvloffset  ), (int)(a.getHitbox().y - a.getYdrawoffset()),GEMA_WIDTH,GEMA_HEIGHT, null);
                }
                
                if(a.getObjtype() == DIAMANTE){
                   // a.drawhitbox(g);
                    g.drawImage(gemasimg[1], (int)(a.getHitbox().x - a.getXdrawoffset() -  xlvloffset), (int)(a.getHitbox().y - a.getYdrawoffset()),GEMA_WIDTH,GEMA_HEIGHT, null);
                }                              
            }
        }
        
    }
    
    private void drawplataformas(Graphics g, int xlvloffset) {    
        for(contenedor c : Contenedor){
            if(c.isActive()){
                
                if(c.getObjtype() == CAJA){
                   // c.drawhitbox(g);
                    g.drawImage(contenedorimgs[2], (int)(c.getHitbox().x - c.getXdrawoffset() - xlvloffset  ), (int)(c.getHitbox().y - c.getYdrawoffset()),CAJA_WIDHT,CAJA_HEIGHT, null);
                }                
                if(c.getObjtype() == PLATAFORMAY ){
                    //c.drawhitbox(g);
                    g.drawImage(contenedorimgs[1], (int)(c.getHitbox().x - c.getXdrawoffset() -  xlvloffset), (int)(c.getHitbox().y - c.getYdrawoffset()),PLATAFORMAY_WIDTH,PLATAFORMAY_HEIGHT, null);
                }                              
                if(c.getObjtype() == PLATAFORMAX ){
                    //c.drawhitbox(g);
                    g.drawImage(contenedorimgs[0], (int)(c.getHitbox().x - c.getXdrawoffset() -  xlvloffset), (int)(c.getHitbox().y - c.getYdrawoffset()),PLATAFORMAX_WIDTH,PLATAFORMAX_HEIGHT, null);
                }                              
                if(c.getObjtype() == PLATAFORMAH ){
                    //c.drawhitbox(g);
                    g.drawImage(contenedorimgs[0], (int)(c.getHitbox().x - c.getXdrawoffset() -  xlvloffset), (int)(c.getHitbox().y - c.getYdrawoffset()),PLATAFORMAX_WIDTH,PLATAFORMAX_HEIGHT, null);
                }
            }
        }        
    }
    
    private void drawactivadores(Graphics g, int xlvloffset) {
          for(activadores e : Activadores){
            if(e.isActive()){
                
                if(e.getObjtype() == PALANCA){
                    //e.drawhitbox(g);
                    g.drawImage(activadoresimg[e.getaniIndex()][0], (int)(e.getHitbox().x - e.getXdrawoffset() - xlvloffset  ), (int)(e.getHitbox().y - e.getYdrawoffset()),PALANCA_WIDTH,PALANCA_HEIGHT, null);
                }
                
                if(e.getObjtype() == PLACA_PRESION){
                    //e.drawhitbox(g);
                    g.drawImage(activadoresimg[e.getaniIndex()][1], (int)(e.getHitbox().x - e.getXdrawoffset() -  xlvloffset), (int)(e.getHitbox().y - e.getYdrawoffset()),PALANCA_WIDTH,PALANCA_HEIGHT, null);
                }                              
            }
        }
    }

    private void drawpuertas(Graphics g, int xlvloffset) {        
               for(puertas p : Puertas){
                   if(p.isActive()){
                    if(p.getObjtype() == PUERTAAZUL){
                   // p.drawhitbox(g);
                    g.drawImage(puertasimg[p.getaniIndex()][1], (int)(p.getHitbox().x - p.getXdrawoffset() - xlvloffset  ), (int)(p.getHitbox().y - p.getYdrawoffset()),PUERTAS_WIDTH,PUERTAS_HEIGHT, null);
                    }
                    if(p.getObjtype() == PUERTAROJA){
                   // p.drawhitbox(g);
                    g.drawImage(puertasimg[p.getaniIndex()][0], (int)(p.getHitbox().x - p.getXdrawoffset() - xlvloffset  ), (int)(p.getHitbox().y - p.getYdrawoffset()),PUERTAS_WIDTH,PUERTAS_HEIGHT, null);
                    }                       
                   }            
            }         
    }
  

    private void drawtrampas(Graphics g, int xlvloffset) {
        for(trampas r : Trampas){
        if(r.isActive()){
            if(r.getObjtype() == WATER){
                //r.drawhitbox(g);
                    g.drawImage(trampasimg[0], (int)(r.getHitbox().x - r.getXdrawoffset() - xlvloffset  ), (int)(r.getHitbox().y - r.getYdrawoffset()),TRAMPAS_WIDTH,TRAMPAS_HEIGTH, null);
            }
            if(r.getObjtype() == LAVA){
                 //r.drawhitbox(g);
                    g.drawImage(trampasimg[1], (int)(r.getHitbox().x - r.getXdrawoffset() - xlvloffset  ), (int)(r.getHitbox().y - r.getYdrawoffset()),TRAMPAS_WIDTH,TRAMPAS_HEIGTH, null);
            }
            if(r.getObjtype() == GREEN){
                   //  r.drawhitbox(g);
                    g.drawImage(trampasimg[2], (int)(r.getHitbox().x - r.getXdrawoffset() - xlvloffset  ), (int)(r.getHitbox().y - r.getYdrawoffset()),TRAMPAS_WIDTH,TRAMPAS_HEIGTH, null);
            }
        }
    }
    }

  
    
    
    
    
    

    public void resetallobj() {
        for(gemas a : Gemas){
            a.reset();
        }
        
        for(activadores e : Activadores){
            e.reset();
        }
        
        for(contenedor c : Contenedor){
            c.reset();
        }
        
        for(puertas p : Puertas){
            p.reset();
        }
        
        
    }

    
    
    
    
    
   
    
}
