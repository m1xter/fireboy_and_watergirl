package levels;

import estadosjuego.estadosdejuego;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.juego;
import static main.juego.TILES_SIZE;

import utilz.loadsave;

public class levelmanager {
    
    private juego juego ;
    private BufferedImage[]  Levelsprite;
    private ArrayList<level> levels;
    private int lvlindex =0;
    
    public levelmanager(juego juego) {
        this.juego = juego;        
        ImportOutSideSprites();
        levels =  new ArrayList<>();
        BuildAllLevels();
    }
    
    public void loadnextlevel(){
        lvlindex++;
        if(lvlindex >= levels.size()){
            lvlindex =0;
            System.out.println("no hay mas pa ");
            estadosdejuego.estado = estadosdejuego.MENU;
        }
        level  newlevel = levels.get(lvlindex);
        if(juego.getjugando().isIsfireboy()){
            juego.getjugando().fireboy().loadlvldata(newlevel.getlvldata());
            juego.getjugando().getobjmanager().loadobjs(newlevel);
        
        }else if(juego.getjugando().iswatergirl()){
            juego.getjugando().watergirl().loadlvldata(newlevel.getlvldata());
            juego.getjugando().getobjmanager().loadobjs(newlevel);
        }
        
        
    }
    
    
    public void draw(Graphics g){
        
        for(int j = 0; j<juego.TILES_N_HEIGHT;j++){
            for(int i =0; i< levels.get(lvlindex).getlvldata()[0].length;i++){
                int index = levels.get(lvlindex).getSpriteIndex(i, j);  
                g.drawImage(Levelsprite[index], TILES_SIZE*i, TILES_SIZE*j,TILES_SIZE,TILES_SIZE, null);
        }        
    }
        
    }
    
    public void update(){
        
    }

    private void ImportOutSideSprites() {
        BufferedImage img = loadsave.GetSpriteAtlas(loadsave.LEVEL_ATLAS);
        Levelsprite = new BufferedImage[48];
        for(int j = 0 ; j  < 4 ; j++){
            for(int i =0;i< 12;i++){
                int index = j*12 + i;
                        Levelsprite [index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }
    
    
    public level getcurrentlvl(){
        return levels.get(lvlindex);
    }

    private void BuildAllLevels() {
        BufferedImage[ ] allLevels = loadsave.GetAllLevels();
        for(BufferedImage img : allLevels){
            levels.add(new level(img));
        }
    }
    
    public int GetAmountOfLevels(){
        return levels.size();
    }
    
    
    
}
