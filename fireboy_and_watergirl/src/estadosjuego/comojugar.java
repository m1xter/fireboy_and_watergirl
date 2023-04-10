package estadosjuego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import ui.urmboton;
import static utilz.constantes.ui.urmbotons.URM_SIZE;
import utilz.loadsave;

/**
 * esta clase muestra las instrucciones de como jugar el juego
 * heredando de la clase estado y implementa la interfas interfasestado
 * @author Admin
 */
public class comojugar extends estado implements interfasestado{
    
    private BufferedImage bgimg;
    private int menux,menuy,menuw,menuh;
    private urmboton menub;
    
    public comojugar(main.juego juego) {
        super(juego);
        loadimg();
        loadbton();
    }
    
    /**
     * este metodo carga la imagen de fondo de la clase
     */
    private void loadimg() {
         bgimg = loadsave.GetSpriteAtlas(loadsave.COMOJUGAR);
        menuw = (int)(bgimg.getWidth() * juego.SCALE);
        menuh = (int)(bgimg.getHeight() * juego.SCALE);
        menux = juego.GAME_WIDTH/2  - menuw /2;
        menuy = (int)(-60*juego.SCALE);
    }

    /**
     * este metodo inicializa el boton de volver al menu
     */
    private void loadbton() {
        int menubx = (int) (440 * juego.SCALE);        
        int y = (int) (320 * juego.SCALE);                
        menub = new urmboton(menubx,y,URM_SIZE,URM_SIZE,2);
    }
    
    
    
    
    /**
     * el metodo update  actualiza el estado del boton
     */
    @Override
    public void update() {
        menub.update();
    }
/**
 * el metodo draw dibuja el fondo y el boton de la clase
 * @param g 
 */
    @Override
    public void draw(Graphics g) {
        g.drawImage(bgimg, 0, 0, juego.GAME_WIDTH, juego.GAME_HEIGHT, null);
        menub.draw(g);
    }

    @Override
    public void mouseclick(MouseEvent e) {
        
    }

    @Override
    public void mousepressed(MouseEvent e) {
        if(isIn(e,menub)){
            menub.setMousepresed(true);
        }
    }

    @Override
    public void mousereleased(MouseEvent e) {
        if(isIn(e,menub)){
            if(menub.isMousepresed()){
                estadosdejuego.estado = estadosdejuego.MENU;
            }
        }
    }

    @Override
    public void mousemoved(MouseEvent e) {
        menub.setMouseover(false);
          if(isIn(e,menub)){
              menub.setMouseover(true);
          }
    }

    @Override
    public void keypreseed(KeyEvent e) {
        
    }

    @Override
    public void keyreleased(KeyEvent e) {
        
    }
    
    /**
     * este metodo revisa si el mouse esta dentro del boton
     * @param e
     * @param mb
     * @return 
     */
    public boolean isIn(MouseEvent e,urmboton mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }
    


}
