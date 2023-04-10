package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class temporizador implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
    
    
    
    
    
    
            int min=0,seg=0;        
        seg = seg +1;
        if(seg==60){
            seg = 0;
            min = min+1;
        }
   
    }    
}

