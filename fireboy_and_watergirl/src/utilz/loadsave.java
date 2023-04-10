package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import main.juego;

//esta clase carga todas las imagenes de la carpeta res 
public class loadsave {
                  //cada string es el nombre de un archivo .png en la carpeta res
	public static final String FIREBOY_ATLAS = "fireboy_sprites.png";
                  public static final String WATERGIRL_ATLAS = "watergirl_sprites.png" ;
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	
                  //interfas de usuario
                  public static final String TEMPORIZADOR = "cronometro.png";
	public static final String MENU_BACKGROUND = "menu.png";
                  public static final String CREDITOS = "creditos.png";
                  public static final String COMOJUGAR = "comojugar.png";
                  public static final String GAMEOVER = "gameover.png";
                  public static final String BOTONES_MENU = "botones_menu.png";
                  public static final String SELECCION_BG = "bg_seleccion.png";
                  public static final String SELECCION_BTON = "botones_de_seleccion.png";                  
                  public static final String PAUSE_BG = "pause_menu.png";
                  public static final String SOUND_BUTTON = "sound_button.png";
                  public static final String URM_BUTTON = "urm_buttons.png";
                  public static final String LVLBG = "lvlbg.png";
                  public static final String LVL_COMPLETED_BG = "nivel_completado.png";
                  //objetos
                  public static final String PLATAFORMAS = "plataformas.png";
                  public static final String PUERTAS = "puertas.png";
                  public static final String ACTIVADORES = "activadores.png";                  
                  public static final String GEMAS = "gemas.png";
                  public static final String TRAMPAS = "trampas.png";
                  
                  //metodo que busca la img por su nombre
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = loadsave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

        
        
        public static BufferedImage[] GetAllLevels(){
            URL  url = loadsave.class.getResource("/niveles");
            File file = null;
            
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            
            File[ ] files  = file.listFiles();
            File[ ] filessorted = new File[files.length];
            
            
            for(int i=0; i < filessorted.length; i++){
                for(int j  = 0 ; j < files.length; j++){
                    if(files[j].getName().equals((i + 1) + ".png")){
                        filessorted[i] = files[j];
                    }
                }
            }
           
          BufferedImage[ ] imgs =   new BufferedImage[filessorted.length];
            
          for(int i = 0; i <imgs.length; i++){
                try {
                    imgs[i] = ImageIO.read(filessorted[i]);
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
          }
              
           return imgs;           
        }
        
        
        
        
       
        
        
        
        
        
}
