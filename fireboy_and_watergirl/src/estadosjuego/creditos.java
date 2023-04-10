package estadosjuego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import ui.urmboton;
import static utilz.constantes.ui.urmbotons.URM_SIZE;
import utilz.loadsave;

/**
 * la clase creditos es igual a la clase comojugar solo cambia el fondo con la informacion que muestra
 * @author Admin
 */
public class creditos extends estado implements interfasestado{
    
    private BufferedImage bgimg;
    private int menux,menuy,menuw,menuh;
    private urmboton menub;
    
    public creditos(main.juego juego) {
        super(juego);
        loadimg();
        loadbton();
    }
    
    
    private void loadimg() {
         bgimg = loadsave.GetSpriteAtlas(loadsave.CREDITOS);
        menuw = (int)(bgimg.getWidth() * juego.SCALE);
        menuh = (int)(bgimg.getHeight() * juego.SCALE);
        menux = juego.GAME_WIDTH/2  - menuw /2;
        menuy = (int)(-60*juego.SCALE);
    }

    private void loadbton() {
        int menubx = (int) (400 * juego.SCALE);        
        int y = (int) (350 * juego.SCALE);                
        menub = new urmboton(menubx,y,URM_SIZE,URM_SIZE,2);
    }
    
    
    
    
    
    @Override
    public void update() {
        menub.update();
    }

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
    
    public boolean isIn(MouseEvent e,urmboton mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }
    


}
