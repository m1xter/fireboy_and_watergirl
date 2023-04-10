package utilz;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.juego;
import objetos.activadores;
import objetos.contenedor;
import objetos.gemas;
import objetos.puertas;
import objetos.trampas;
import static utilz.constantes.objetos.*;
import static utilz.loadsave.GetSpriteAtlas;

public class helpmethods {

 
public static boolean canmovehere(float x, float y, float w,float h,int[][] lvlData){
    
    //revisa las cuatro esquinas de la hitbox y comprueba si es un solido si no es un solido nos podemos mover ahi
    if(!issolid(x,y,lvlData))
        if(!issolid(x+w,y+h,lvlData))
               if(!issolid(x+w,y,lvlData)) 
                    if(!issolid(x,y+h,lvlData)) 
                            return true;
 //si alguna de las esquinas se encuentra con un solido no nos podremos mover ahi   
return false;
}






private static boolean issolid(float x,float y,int[][] lvlData){
    //si es menor que cero o mayor que el ancho del juego es un solido
    if(x < 0 || x >= juego.GAME_WIDTH){
        return true;
    }
    //si es menor que cero o mayor que la altura del juego es un solido
    if(y <0  ||  y >=  juego.GAME_HEIGHT){
        return true;
    }
    
    
    
    float xindex = x / juego.TILES_SIZE;
    float yindex = y / juego.TILES_SIZE;
    
    int value = lvlData[(int)yindex][(int)xindex];
    
    // si se cumple alguna de estas condiciones el jugador podra pasar atravez de estas ya que no son solidos
    if(value >=48 || value < 0 || value != 11){       
        return true;
    }else{
        return false;
    }
    
    
}

//calcula la posicion de la entidad cerca de una pared 
public static float GetEntityXpostnetwall(Rectangle2D.Float hitbox, float xspeed){
    // la hitbox siempre es mas pequena que un cuadro si xspeed es mayor que cero es derecha caso contrario izquierda
    //pero nunca puede ser 0 eso seria no colicion
    //con esto sacamos en que coordenada esta 
    int currenttile = (int) (hitbox.x / juego.TILES_SIZE);
    
    if(xspeed > 0 ){
        //conlicion a la derecha
        int  tilexpos = currenttile * juego.TILES_SIZE;
        int xoffset = (int)(juego.TILES_SIZE - hitbox.width);
        return tilexpos + xoffset - 1;
    }else{
        //colicion con la izquierda
        return currenttile * juego.TILES_SIZE;
    }
    
    
    
}




public static float GetEntityYPosUnderRoofAboveFloor(Rectangle2D.Float hitbox, float airspeed){
    
    int currenttile = (int) (hitbox.y / juego.TILES_SIZE);
        
    if(airspeed > 0 ){                                                                                  
        //estamos callendo - tocar suelo
        int  tileypos = currenttile * juego.TILES_SIZE;
        int yoffset = (int)(juego.TILES_SIZE - hitbox.height);
        return tileypos  + yoffset -1;
    }else
        //saltando
        
        return  currenttile * juego.TILES_SIZE;
        
    
    
    
}


public static boolean isEntityOnFloor(Rectangle2D.Float hitbox,int[][] lvlData ){
    
    
    if(!issolid(hitbox.x,hitbox.y+hitbox.height+1,lvlData))
            if(!issolid(hitbox.x +hitbox.width,hitbox.y+hitbox.height+1,lvlData))                 
                            return false;
    
    
    return true;
}


 //metodo optiene la data que se usa para construir los niveles
        public static int[][] GetLevelData( BufferedImage img) {
                        
            int[][] lvlData = new int[juego.TILES_N_HEIGHT][juego.TILES_N_WIDTH];	

	for (int j = 0; j < img.getHeight(); j++)
                        for (int i = 0; i < img.getWidth(); i++) {
                                Color color = new Color(img.getRGB(i, j));
                                        int value = color.getRed();
			if (value >= 48)
                                                                 value = 0;
                                                               lvlData[j][i] = value;
			}
		return lvlData;

	}
        

        
      public static ArrayList<gemas> GetgemasData(BufferedImage img){
          ArrayList<gemas> list  = new ArrayList<>();
          for(int j = 0 ;j < img.getHeight(); j ++){
              for(int i =0; i < img.getWidth(); i ++){
                  Color color = new Color(img.getRGB(i, j));
                  int value = color.getBlue();  
                  if(value == RUBY || value == DIAMANTE){
                      list.add(new gemas(i* juego.TILES_SIZE,j  * juego.TILES_SIZE,value));                  
              }
          }          
      }        
        return list;   
}
      
      
   public static ArrayList<contenedor> GetcontenedoresData(BufferedImage img){
          ArrayList<contenedor> list  = new ArrayList<>();
          for(int j = 0 ;j < img.getHeight(); j ++){
              for(int i =0; i < img.getWidth(); i ++){
                  Color color = new Color(img.getRGB(i, j));
                  int value = color.getBlue();  
                  if(value == PLATAFORMAY || value == PLATAFORMAX || value == CAJA){                      
                      list.add(new contenedor(i*juego.TILES_SIZE,j*juego.TILES_SIZE,value));
              }
          }          
      }        
        return list;   
}
   
     public static ArrayList<activadores> GetactivadoresData(BufferedImage img){
          ArrayList<activadores> list  = new ArrayList<>();
          for(int j = 0 ;j < img.getHeight(); j ++){
              for(int i =0; i < img.getWidth(); i ++){
                  Color color = new Color(img.getRGB(i, j));
                  int value = color.getBlue();  
                  if(value == PALANCA || value == PLACA_PRESION ){                      
                      list.add(new activadores(i*juego.TILES_SIZE,j*juego.TILES_SIZE,value));
              }
          }          
      }        
        return list;   
}
     
     public static ArrayList<puertas> GetPuertasData(BufferedImage img){
          ArrayList<puertas> list  = new ArrayList<>();
          for(int j = 0 ;j < img.getHeight(); j ++){
              for(int i =0; i < img.getWidth(); i ++){
                  Color color = new Color(img.getRGB(i, j));
                  int value = color.getBlue();  
                  if(value == PUERTAAZUL || value == PUERTAROJA ){                      
                      list.add(new puertas(i*juego.TILES_SIZE,j*juego.TILES_SIZE,value));
              }
          }          
      }        
        return list;   
}

    public static ArrayList<trampas> GetTrampasData(BufferedImage img) {
        ArrayList<trampas> list  = new ArrayList<>();
          for(int j = 0 ;j < img.getHeight(); j ++){
              for(int i =0; i < img.getWidth(); i ++){
                  Color color = new Color(img.getRGB(i, j));
                  int value = color.getBlue();  
                  if(value == LAVA || value == WATER  || value == GREEN){                      
                      list.add(new trampas(i*juego.TILES_SIZE,j*juego.TILES_SIZE,value));
              }
          }          
      }        
        return list;   
    }
 
    
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

}