package levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objetos.activadores;
import objetos.contenedor;
import objetos.gemas;
import objetos.puertas;
import objetos.trampas;
import utilz.helpmethods;
import static utilz.helpmethods.GetLevelData;
public class level {
    
    private BufferedImage img; 
    private int[][] lvlData ;
    private ArrayList<gemas> Gemas;
    private ArrayList<contenedor> Contenedor;
    private ArrayList<activadores> Activador;
    private ArrayList<puertas>Puertas;
    private ArrayList<trampas>Trampas;
    
    

    public level(BufferedImage img) {
        this.img = img;
        createleveldata();        
        creategemas();
        createcontenedores();
        createactivadores();
        createpuertas();
        createtrampas();
    }
    
    public int getSpriteIndex(int x,int y){        
        return lvlData[y][x];
    }
    
    public int[][] getlvldata(){
        return lvlData;
    }

    private void createleveldata() {
        lvlData = GetLevelData(img);
    }

    private void creategemas() {
        Gemas =  helpmethods.GetgemasData(img);
    }
    
    private void createcontenedores() {
        Contenedor =  helpmethods.GetcontenedoresData(img);
    }

    public void createactivadores() {
        Activador = helpmethods.GetactivadoresData(img);
    }
    
    private void createpuertas() {
        Puertas = helpmethods.GetPuertasData(img);
    }

    
   private void createtrampas() {
        Trampas = helpmethods.GetTrampasData(img);
    }

    
        
    public ArrayList<gemas> getgemas(){
        return Gemas;
    }

    public ArrayList<puertas> getPuertas() {
        return Puertas;
    }    
    
    public ArrayList<contenedor> getContenedor() {
        return Contenedor;
    }

    public ArrayList<activadores> getActivador() {
        return Activador;
    }
    public ArrayList<trampas> gettrampas(){
        return Trampas;
    }

    
    
}
