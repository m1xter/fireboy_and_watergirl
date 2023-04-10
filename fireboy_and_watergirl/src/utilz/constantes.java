package utilz;


import main.juego;
//en esta clase se guardan todas las constantes que usa el juego
public class constantes {

    public static final  float gravity = 0.04f*juego.SCALE;
    public static final  int anispeed=25;
    
    
public static class objetos{
    public static final int CAJA = 0;
    public static final int PALANCA =1;
    public static final int RUBY =2;
    public static final int DIAMANTE =3;
    public static final int PLATAFORMAX =4;
    public static final int PLATAFORMAH =13;    
    public static final int PLATAFORMAY =5;
    public static final int PLACA_PRESION =6;
    public static final int PUERTAROJA = 7;
    public static final int PUERTAAZUL = 8;
    public static final int LAVA = 9;
    public static final int WATER =10;
    public static final int GREEN = 14;
    // uso estas para la palanca y la placa de presion
    public static final int PALANCA_WIDTH_DEFAULT = 52;
    public static final int PALANCA_HEIGHT_DEFAULT = 44;
    public static final int PALANCA_WIDTH = (int)(PALANCA_WIDTH_DEFAULT * juego.SCALE);
    public static final int PALANCA_HEIGHT = (int)(PALANCA_HEIGHT_DEFAULT*juego.SCALE);
    
    public static final int CAJA_WIDTH_DEFAULT = 80;
    public static final int CAJA_HEIGHT_DEFAULT =80;
    public static final int CAJA_WIDHT = (int)(CAJA_WIDTH_DEFAULT*juego.SCALE);
    public static final int CAJA_HEIGHT = (int)(CAJA_HEIGHT_DEFAULT*juego.SCALE);
    
    public static final int RUBY_SUMA = 1;
    public static final int DIAMANTE_SUMA = 1;    
    public static final int GEMA_WIDTH_DEFAULT = 32;
    public static final int GEMA_HEIGHT_DEFAULT =32;
    public static final int GEMA_WIDTH =(int)(GEMA_WIDTH_DEFAULT*juego.SCALE);
    public static final int GEMA_HEIGHT =(int)(GEMA_HEIGHT_DEFAULT*juego.SCALE);
    
    public static final int PLATAFORMAY_WIDTH_DEFAULT= 70;
    public static final int PLATAFORMAY_HEIGHT_DEFAULT = 120;
    public static final int PLATAFORMAY_WIDTH = (int)(PLATAFORMAY_WIDTH_DEFAULT*juego.SCALE);
    public static final int PLATAFORMAY_HEIGHT = (int)(PLATAFORMAY_HEIGHT_DEFAULT*juego.SCALE);
    
    public static final int PLATAFORMAX_WIDTH_DEFAULT = 100;
    public static final int PLATAFORMAX_HEIGHT_DEFAULT = 80;
    public static final int PLATAFORMAX_WIDTH = (int)(PLATAFORMAX_WIDTH_DEFAULT * juego.SCALE);
    public static final int PLATAFORMAX_HEIGHT = (int)(PLATAFORMAX_HEIGHT_DEFAULT * juego.SCALE);
    
    public static final int PUERTAS_WIDTH_DEFAULT = 84;
    public static final int PUERTAS_HEIGHT_DEFAULT = 80;
    public static final int PUERTAS_WIDTH = (int)(PUERTAS_WIDTH_DEFAULT*juego.SCALE);
    public static final int PUERTAS_HEIGHT =(int)(PUERTAS_HEIGHT_DEFAULT*juego.SCALE);
    
    public static final int TRAMPAS_WIDTH_DEFAULT = 100;
    public static final int TRAMPAS_HEIGTH_DEFAULT = 32;
    public static final int TRAMPAS_WIDTH = (int)(TRAMPAS_WIDTH_DEFAULT * juego.SCALE);
    public static final int TRAMPAS_HEIGTH = (int)(TRAMPAS_HEIGTH_DEFAULT * juego.SCALE);
    
    
    
    
    //indica cuantos frames tiene la animacion de cada objeto 
    public static int GetSpritesAmount(int objtype){
        
        switch(objtype){
            case PALANCA:
            case PUERTAROJA:
            case PUERTAAZUL:
            case PLACA_PRESION:
                return 2;                
        }
        return 1;
    }
    
    
    
    
}
    
    //constantes para la interfas de usuario
    public static class ui{
        //constantes para los botones del menu principal
        public static class botones{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 27;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * juego.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * juego.SCALE);
        }
        //constantes para los botones de sonido del menu de pausa
        public static class botonesdepausa{
            public static final int SOUND_SIZE_DEFAULT =  42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * juego.SCALE);
            
        }
        
        public static class urmbotons{
            public static final int  URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * juego.SCALE) ;
        }
        
        public static class botones_seleccion{
            public static final int SELECCION_DEFAULT_WIDTH = 166;
             public static final int SELECCION_DEFAULT_HEIGHT = 38;
            public static final int SELECCION_WIDTH=(int)(SELECCION_DEFAULT_WIDTH* juego.SCALE) ;
            public static final int SELECCION_HEIGHT=(int)(SELECCION_DEFAULT_HEIGHT* juego.SCALE) ;
            
        }
        
        
    }
    
    //constantes para los controles y estados del jugador
    public static class  playerconstants{
        public static final int IDLE =0;
        public static final int RUNNING =1;
        public static final int JUMPING =2;
        public static final int FALLING =3;
        
        
        public static int GetSpriteAmount(int player_action ){
            switch(player_action){                
                case IDLE:
                    return 4;
                case RUNNING:
                    return 6;                 
                 case JUMPING:                 
                    return 3;                
                 case FALLING:                    
                 default:
                     return 1;
            }
            
            
        }
        
    }
    
    
    public static class direcciones{
        
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;        
        public static final int DOWN = 3;
        
        
        
        
    }
    


    
}
